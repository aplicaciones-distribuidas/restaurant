package dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import entities.ComisionEntity;
import excepciones.BaseDeDatosException;
import hibernate.HibernateUtil;
import negocio.Comision;

public class ComisionDAO {
	private static ComisionDAO instancia;

	private ComisionDAO() {
	}

	public static ComisionDAO getInstancia() {
		if (instancia == null)
			instancia = new ComisionDAO();
		return instancia;
	}

	public Comision toBusiness(ComisionEntity entity) {
		return new Comision(entity.getMonto(), entity.getFecha());
	}

	public ComisionEntity toEntity(Comision business) {
		return new ComisionEntity(business.getMonto(), business.getFecha());
	}

	public void save(Comision comision) throws BaseDeDatosException {
		ComisionEntity entity = this.toEntity(comision);
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
