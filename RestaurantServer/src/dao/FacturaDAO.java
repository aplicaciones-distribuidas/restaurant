package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import entities.FacturaEntity;
import entities.ItemFacturaEntity;
import excepciones.BaseDeDatosException;
import hibernate.HibernateUtil;
import negocio.Factura;
import negocio.ItemFactura;

public class FacturaDAO {
	private static FacturaDAO instancia;

	private FacturaDAO() {
	}

	public static FacturaDAO getInstancia() {
		if (instancia == null)
			instancia = new FacturaDAO();
		return instancia;
	}

	public Factura toBusiness(FacturaEntity entity) {
		List<ItemFactura> itemsFactura = new ArrayList<>();
		for (ItemFacturaEntity ife : entity.getItemsFactura()) {
			//itemsFactura.add(new Item);
		}
		return new Factura(entity.getFecha(),entity.getComisionMozo(), entity.getCobrado(), entity.getMonto(), itemsFactura, FormaPagoDAO.getInstancia().toBusiness(entity.getFormaPago()));
	}

	public FacturaEntity toEntity(Factura business) {
		List<ItemFacturaEntity> itemsFactura = new ArrayList<>();
		for (ItemFactura ifac : business.getItemsFactura()) {
			//itemsFactura.add(new Item);
		}
		return new FacturaEntity(business.getFecha(),business.getComisionMozo(), business.isCobrado(), business.getMonto(), itemsFactura, FormaPagoDAO.getInstancia().toEntity(business.getFormaPago()));
	}

	public void save(Factura factura) throws BaseDeDatosException {
		FacturaEntity entity = this.toEntity(factura);
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
