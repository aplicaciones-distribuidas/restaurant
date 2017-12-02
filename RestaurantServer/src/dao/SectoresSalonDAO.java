package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import entities.EmpleadoEntity;
import entities.MesaEntity;
import entities.SectorSalonEntity;
import excepciones.BaseDeDatosException;
import hibernate.HibernateUtil;
import negocio.Empleado;
import negocio.Mesa;
import negocio.SectorSalon;

public class SectoresSalonDAO {
	private static SectoresSalonDAO instancia;

	private SectoresSalonDAO() {
	}

	public static SectoresSalonDAO getInstancia() {
		if (instancia == null)
			instancia = new SectoresSalonDAO();
		return instancia;
	}

	public SectorSalon toBusiness(SectorSalonEntity entity) {
		boolean includeSectorSalon = false;
		List<Mesa> mesas = MesasDAO.getInstancia().toBusiness(entity.getMesas(), includeSectorSalon);
		List<Empleado> empleados = new ArrayList<Empleado>(); // TODO: translate entity.getEmpleados() to business
		return new SectorSalon(entity.getNombre(), mesas, empleados);
	}

	public List<SectorSalon> toBusiness(List<SectorSalonEntity> entities) {
		List<SectorSalon> business = new ArrayList<SectorSalon>();
		for (SectorSalonEntity entity : entities) {
			business.add(this.toBusiness(entity));
		}
		return business;
	}

	public SectorSalonEntity toEntity(SectorSalon business) {
		List<MesaEntity> mesas = MesasDAO.getInstancia().toEntity(business.getMesas());
		List<EmpleadoEntity> empleados = new ArrayList<EmpleadoEntity>(); // TODO: translate business.getEmpleados() to
																			// entities
		return new SectorSalonEntity(business.getNombre(), mesas, empleados);
	}

	public void save(SectorSalon sectorSalon) throws BaseDeDatosException {
		SectorSalonEntity entity = this.toEntity(sectorSalon);
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

	@SuppressWarnings("unchecked")
	public List<SectorSalon> getAll() throws BaseDeDatosException {
		List<SectorSalonEntity> all = new ArrayList<SectorSalonEntity>();
		try {
			Session session = HibernateUtil.getInstancia().getSession();
			all = session.createQuery("from SectorSalonEntity").list();
			session.close();
		} catch (HibernateException e) {
			throw new BaseDeDatosException(e);
		}
		return this.toBusiness(all);
	}
}
