package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import entities.FacturaEntity;
import entities.MesaEntity;
import entities.MesaOcupacionEntity;
import entities.SectorSalonEntity;
import excepciones.BaseDeDatosException;
import hibernate.HibernateUtil;
import negocio.Factura;
import negocio.Mesa;
import negocio.MesaOcupacion;
import negocio.SectorSalon;

public class MesasOcupacionDAO {
	private static MesasOcupacionDAO instancia;

	private MesasOcupacionDAO() {
	}

	public static MesasOcupacionDAO getInstancia() {
		if (instancia == null)
			instancia = new MesasOcupacionDAO();
		return instancia;
	}

	public MesaOcupacion toBusiness(MesaOcupacionEntity entity) {
		return toBusiness(entity, true);
	}

	public MesaOcupacion toBusiness(MesaOcupacionEntity entity, boolean includeSectorSalon) {
		List<Mesa> mesas = MesasDAO.getInstancia().toBusiness(entity.getMesaItems());
		Factura factura = null; // TODO: translate entity.getFactura() to business
		return new MesaOcupacion(entity.getId(), entity.getFechaIngreso(), entity.getFechaEgreso(), entity
				.isProximaLiberarse(), entity.getCantidadPersonas(), mesas, factura);
	}

	public List<MesaOcupacion> toBusiness(List<MesaOcupacionEntity> entities) {
		return this.toBusiness(entities, true);
	}

	public List<MesaOcupacion> toBusiness(List<MesaOcupacionEntity> entities, boolean includeSectorSalon) {
		List<MesaOcupacion> business = new ArrayList<MesaOcupacion>();
		for (MesaOcupacionEntity entity : entities) {
			business.add(this.toBusiness(entity, includeSectorSalon));
		}
		return business;
	}

	public MesaOcupacionEntity toEntity(MesaOcupacion business) {
		List<MesaEntity> mesas = MesasDAO.getInstancia().toEntity(business.getMesaItems());
		FacturaEntity factura = null; // TODO: translate business.getFactura() to entity
		return new MesaOcupacionEntity(business.getId(), new java.sql.Date(business.getFechaIngreso().getTime()),
				new java.sql.Date(business.getFechaEgreso().getTime()), business.getProximaLiberarse(), business
						.getCantidadPersonas(), mesas, factura);
	}

	public List<MesaOcupacionEntity> toEntity(List<MesaOcupacion> businesses) {
		List<MesaOcupacionEntity> entities = new ArrayList<MesaOcupacionEntity>();
		for (MesaOcupacion business : businesses) {
			entities.add(this.toEntity(business));
		}
		return entities;
	}

	@SuppressWarnings("unchecked")
	public List<MesaOcupacion> getAll() throws BaseDeDatosException {
		List<MesaOcupacionEntity> all = new ArrayList<MesaOcupacionEntity>();
		try {
			Session session = HibernateUtil.getInstancia().getSession();
			all = session.createQuery("from MesaOcupacionEntity").list();
			session.close();
		} catch (HibernateException e) {
			throw new BaseDeDatosException(e);
		}
		return this.toBusiness(all);
	}

	@SuppressWarnings("unchecked")
	public List<MesaOcupacion> getOcupadasBySectorSalon(SectorSalon sectorSalon) throws BaseDeDatosException {
		SectorSalonEntity sectorSalonEntity = new SectorSalonEntity(sectorSalon.getId());

		List<MesaOcupacionEntity> all = new ArrayList<MesaOcupacionEntity>();
		try {
			Session session = HibernateUtil.getInstancia().getSession();
			all = session.createQuery(
					"select mo from MesaOcupacionEntity mo join mo.mesaItems m where mo.fechaEgreso is null and m.sectorSalon = :sectorSalon")
					.setParameter("sectorSalon", sectorSalonEntity).list();
			session.close();
		} catch (HibernateException e) {
			throw new BaseDeDatosException(e);
		}
		return this.toBusiness(all);
	}

	public Long save(MesaOcupacion mesa) throws BaseDeDatosException {
		MesaOcupacionEntity entity = this.toEntity(mesa);
		try {
			Session session = HibernateUtil.getInstancia().getSession();
			session.beginTransaction();
			session.save(entity);
			session.getTransaction().commit();
			session.close();
		} catch (HibernateException e) {
			throw new BaseDeDatosException(e);
		}
		return entity.getId();
	}

}
