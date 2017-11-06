package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;

import entities.MesaEntity;
import excepciones.MesaNoExisteException;
import excepciones.BaseDeDatosException;
import hibernate.HibernateUtil;
import negocio.Mesa;

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
		return new Mesa(entity.getNumero(), entity.isOcupada());
	}

	private List<Mesa> toBusiness(List<MesaEntity> entities) {
		List<Mesa> business = new ArrayList<Mesa>();
		for (MesaEntity entity : entities) {
			business.add(this.toBusiness(entity));
		}
		return business;
	}

	private MesaEntity toEntity(Mesa business) {
		return new MesaEntity(business.getNumero(), business.isOcupada());
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
		MesaEntity entity = new MesaEntity();
		try {
			Session session = HibernateUtil.getInstancia().getSession();
			session.load(entity, numero);
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
