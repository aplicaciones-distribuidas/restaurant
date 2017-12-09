package hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import excepciones.BaseDeDatosException;

public class HibernateUtil {
	private static HibernateUtil instancia;
	private SessionFactory sessionFactory;

	private HibernateUtil() throws BaseDeDatosException {
		this.sessionFactory = buildSessionFactory();
	}

	public static HibernateUtil getInstancia() throws BaseDeDatosException {
		if (instancia == null)
			instancia = new HibernateUtil();
		return instancia;
	}

	private SessionFactory buildSessionFactory() throws BaseDeDatosException {
		try {
			return new Configuration().configure("/hibernate.cfg.xml").buildSessionFactory();
		} catch (Exception ex) {
			throw new BaseDeDatosException(ex);
		}
	}

	public Session getSession() {
		return this.sessionFactory.openSession();
	}
}
