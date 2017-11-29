package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;

import entities.MesaEntity;
import entities.MesaEntity;
import entities.MesaEntity;
import entities.SectorSalonEntity;
import excepciones.MesaNoExisteException;
import excepciones.BaseDeDatosException;
import hibernate.HibernateUtil;
import negocio.Mesa;
import negocio.MesaCompuesta;
import negocio.MesaSimple;
import negocio.SectorSalon;

public class MesasDAO {
	/*private static MesasDAO instancia;

	private MesasDAO() {
	}

	public static MesasDAO getInstancia() {
		if (instancia == null)
			instancia = new MesasDAO();
		return instancia;
	}

	public Mesa toBusiness(MesaEntity entity) {
		return toBusiness(entity, true);
	}

	public Mesa toBusiness(MesaEntity entity, boolean includeSectorSalon) {
		if (MesaSimpleEntity.class.isInstance(entity)) {
			return this.toBusinessSimple((MesaSimpleEntity) entity, includeSectorSalon);
		} else if (MesaCompuestaEntity.class.isInstance(entity)) {
			return this.toBusinessCompuesta((MesaCompuestaEntity) entity, includeSectorSalon);
		} else {
			return null; // TODO: throw custom exception?
		}
	}

	private Mesa toBusinessSimple(MesaSimpleEntity entity, boolean includeSectorSalon) {
		SectorSalon sectorSalon = null;
		if (includeSectorSalon)
			sectorSalon = SectoresSalonDAO.getInstancia().toBusiness(entity.getSectorSalon());
		return new MesaSimple(entity.getNumero(), entity.isOcupada(), sectorSalon);
	}

	private Mesa toBusinessCompuesta(MesaCompuestaEntity entity, boolean includeSectorSalon) {
		SectorSalon sectorSalon = null;
		if (includeSectorSalon)
			sectorSalon = SectoresSalonDAO.getInstancia().toBusiness(entity.getSectorSalon());
		List<Mesa> mesas = this.toBusiness(entity.getMesas(), includeSectorSalon);
		return new MesaCompuesta(entity.getNumero(), entity.isOcupada(), sectorSalon, mesas);
	}

	public List<Mesa> toBusiness(List<MesaEntity> entities) {
		return this.toBusiness(entities, true);
	}

	public List<Mesa> toBusiness(List<MesaEntity> entities, boolean includeSectorSalon) {
		List<Mesa> business = new ArrayList<Mesa>();
		for (MesaEntity entity : entities) {
			business.add(this.toBusiness(entity, includeSectorSalon));
		}
		return business;
	}

	public MesaEntity toEntity(Mesa business) {
		if (MesaSimple.class.isInstance(business)) {
			return this.toEntitySimple((MesaSimple) business);
		} else if (MesaCompuesta.class.isInstance(business)) {
			return this.toEntityCompuesta((MesaCompuesta) business);
		} else {
			return null; // TODO: throw custom exception?
		}
	}

	private MesaEntity toEntitySimple(MesaSimple business) {
		SectorSalonEntity sectorSalon = SectoresSalonDAO.getInstancia().toEntity(business.getSectorSalon());
		return new MesaSimpleEntity(business.getNumero(), business.isOcupada(), sectorSalon);
	}

	private MesaEntity toEntityCompuesta(MesaCompuesta business) {
		SectorSalonEntity sectorSalon = SectoresSalonDAO.getInstancia().toEntity(business.getSectorSalon());
		List<MesaEntity> mesas = this.toEntity(business.getMesas());
		return new MesaCompuestaEntity(business.getNumero(), business.isOcupada(), sectorSalon, mesas);
	}

	public List<MesaEntity> toEntity(List<Mesa> businesses) {
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
	}*/
}
