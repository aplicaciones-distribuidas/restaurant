package dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import entities.FormaPagoEntity;
import excepciones.BaseDeDatosException;
import excepciones.FormaDePagoNoExisteException;
import hibernate.HibernateUtil;
import negocio.FormaPago;

public class FormaPagoDAO {
	private static FormaPagoDAO instancia;

	private FormaPagoDAO() {
	}

	public static FormaPagoDAO getInstancia() {
		if (instancia == null)
			instancia = new FormaPagoDAO();
		return instancia;
	}

	public FormaPago toBusiness(FormaPagoEntity entity) {
		return new FormaPago(entity.getTipo(), entity.getNumeroCupon(), entity.getBanco(), entity.getMonto());
	}

	public FormaPagoEntity toEntity(FormaPago business) {
		return new FormaPagoEntity(business.getTipo(), business.getNumeroCupon(), business.getBanco(), business.getMonto());
	}


	public FormaPago getById(Long id) throws BaseDeDatosException, FormaDePagoNoExisteException {
		FormaPagoEntity entity;
		try {
			Session session = HibernateUtil.getInstancia().getSession();
			entity = (FormaPagoEntity) session.createQuery("from FormaPagoEntity s where s.id = :id")
					.setParameter("id", id).uniqueResult();
			session.close();
		} catch (HibernateException e) {
			throw new BaseDeDatosException(e);
		}

		if (entity == null) {
			throw new FormaDePagoNoExisteException();
		}

		return this.toBusiness(entity);
	}

	public void save(FormaPago formaDePago) throws BaseDeDatosException {
		FormaPagoEntity entity = this.toEntity(formaDePago);
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
