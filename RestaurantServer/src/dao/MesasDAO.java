package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;

import entities.MesaCompuestaEntity;
import entities.MesaEntity;
import entities.MesaSimpleEntity;
import excepciones.MesaNoExisteException;
import excepciones.BaseDeDatosException;
import hibernate.HibernateUtil;
import negocio.Mesa;
import negocio.MesaCompuesta;
import negocio.MesaSimple;

public class MesasDAO {
	private static MesasDAO instancia;

	private MesasDAO() {
	}

	public static MesasDAO getInstancia() {
		if (instancia == null)
			instancia = new MesasDAO();
		return instancia;
	}

	private Mesa toBusiness(MesaEntity entity) {
		if (MesaSimpleEntity.class.isInstance(entity)) {
			return this.toBusinessSimple((MesaSimpleEntity) entity);
		} else if (MesaCompuestaEntity.class.isInstance(entity)) {
			return this.toBusinessCompuesta((MesaCompuestaEntity) entity);
		} else {
			return null; // TODO: throw custom exception?
		}
	}

	private Mesa toBusinessSimple(MesaSimpleEntity entity) {
		return new MesaSimple(entity.getNumero(), entity.isOcupada());
	}

	private Mesa toBusinessCompuesta(MesaCompuestaEntity entity) {
		return new MesaCompuesta(entity.getNumero(), entity.isOcupada(), this.toBusiness(entity.getMesas()));
	}

	private List<Mesa> toBusiness(List<MesaEntity> entities) {
		List<Mesa> business = new ArrayList<Mesa>();
		for (MesaEntity entity : entities) {
			business.add(this.toBusiness(entity));
		}
		return business;
	}

	private MesaEntity toEntity(Mesa business) {
		if (MesaSimple.class.isInstance(business)) {
			return this.toEntitySimple((MesaSimple) business);
		} else if (MesaCompuesta.class.isInstance(business)) {
			return this.toEntityCompuesta((MesaCompuesta) business);
		} else {
			return null; // TODO: throw custom exception?
		}
	}

	private MesaEntity toEntitySimple(MesaSimple business) {
		return new MesaSimpleEntity(business.getNumero(), business.isOcupada());
	}

	private MesaEntity toEntityCompuesta(MesaCompuesta business) {
		return new MesaCompuestaEntity(business.getNumero(), business.isOcupada(), this.toEntity(business.getMesas()));
	}

	private List<MesaEntity> toEntity(List<Mesa> businesses) {
		List<MesaEntity> entities = new ArrayList<MesaEntity>();
		for (Mesa business : businesses) {
			entities.add(this.toEntity(business));
		}
		return entities;
	}

	@SuppressWarnings("unchecked")
	public List<Mesa> getAll() throws BaseDeDatosException {
		List<MesaEntity> all = new ArrayList<MesaEntity>();
		try {
			Session session = HibernateUtil.getInstancia().getSession();
			all = session.createQuery("from MesaEntity").list();
			session.close();
		} catch (HibernateException e) {
			throw new BaseDeDatosException(e);
		}
		return this.toBusiness(all);
	}

	public Mesa getByNumero(int numero) throws BaseDeDatosException, MesaNoExisteException {
		MesaEntity entity;
		try {
			Session session = HibernateUtil.getInstancia().getSession();
			entity = (MesaEntity) session.createQuery("from MesaEntity m where m.numero = :numero").setParameter(
					"numero", numero).uniqueResult();
			session.close();
		} catch (ObjectNotFoundException e) {
			throw new MesaNoExisteException(numero);
		} catch (HibernateException e) {
			throw new BaseDeDatosException(e);
		}

		return this.toBusiness(entity);
	}

	public void save(Mesa mesa) throws BaseDeDatosException {
		MesaEntity entity = this.toEntity(mesa);
		try {
			Session session = HibernateUtil.getInstancia().getSession();
			session.beginTransaction();
			session.save(entity);
			session.getTransaction().commit();
			session.close();
		} catch (HibernateException e) {
			throw new BaseDeDatosException(e);
		}
	}
}
