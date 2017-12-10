package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import entities.ComisionEntity;
import entities.EmpleadoEntity;
import excepciones.BaseDeDatosException;
import excepciones.EmpleadoNoExisteException;
import hibernate.HibernateUtil;
import negocio.Comision;
import negocio.Empleado;

public class EmpleadoDAO {
	private static EmpleadoDAO instancia;

	private EmpleadoDAO() {
	}

	public static EmpleadoDAO getInstancia() {
		if (instancia == null)
			instancia = new EmpleadoDAO();
		return instancia;
	}

	public Empleado toBusiness(EmpleadoEntity entity) {
		List<Comision> comisiones = new ArrayList<>();

		for (ComisionEntity comision : entity.getComisiones()) {
			comisiones.add(ComisionDAO.getInstancia().toBusiness(comision));
		}

		return new Empleado(entity.getNombre(), entity.getApellido(), entity.getPorcentajeComision(), RolDAO.getInstancia().toBusiness(entity.getRol()), comisiones);
	}

	public EmpleadoEntity toEntity(Empleado business) {
		List<ComisionEntity> comisiones = new ArrayList<>();

		for (Comision comision : business.getComisiones()) {
			comisiones.add(ComisionDAO.getInstancia().toEntity(comision));
		}

		return new EmpleadoEntity(business.getNombre(), business.getApellido(), business.getPorcentajeComision(), RolDAO.getInstancia().toEntity(business.getRol()), comisiones);
	}


	public Empleado getById(Long id) throws BaseDeDatosException, EmpleadoNoExisteException {
		EmpleadoEntity entity;
		try {
			Session session = HibernateUtil.getInstancia().getSession();
			entity = (EmpleadoEntity) session.createQuery("from EmpleadoEntity s where s.id = :id")
					.setParameter("id", id).uniqueResult();
			session.close();
		} catch (HibernateException e) {
			throw new BaseDeDatosException(e);
		}

		if (entity == null) {
			throw new EmpleadoNoExisteException();
		}

		return this.toBusiness(entity);
	}

	public void save(Empleado empleado) throws BaseDeDatosException {
		EmpleadoEntity entity = this.toEntity(empleado);
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
