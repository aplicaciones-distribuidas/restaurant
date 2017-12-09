package dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import entities.DirectoEntity;
import entities.InsumoProductoEntity;
import excepciones.BaseDeDatosException;
import hibernate.HibernateUtil;
import negocio.Directo;
import negocio.InsumoProducto;

public class ProductoDAO {
	private static ProductoDAO instancia;

	private ProductoDAO() {
	}

	public static ProductoDAO getInstancia() {
		if (instancia == null)
			instancia = new ProductoDAO();
		return instancia;
	}

	public Directo toBusiness(DirectoEntity entity) {
		return new Directo(
				entity.getRubro(), 
				entity.getCaducidad(), 
				entity.getComisionMozo(), 
				entity.getFecha(), 
				entity.getPrecio(), 
				AreaDAO.getInstancia().toBusiness(entity.getArea()),
				new InsumoProducto(entity.getInsumoProducto().getCantidad(), InsumoDAO.getInstancia().toBusiness(entity.getInsumoProducto().getInsumo()))
				);
	}

	public DirectoEntity toEntity(Directo business) {
		return new DirectoEntity(
				business.getRubro(), 
				business.getCaducidad(), 
				business.getComisionMozo(), 
				business.getFecha(), 
				business.getPrecio(), 
				AreaDAO.getInstancia().toEntity(business.getArea()),
				new InsumoProductoEntity(business.getInsumoProducto().getCantidad(), InsumoDAO.getInstancia().toEntity(business.getInsumoProducto().getInsumo()))
				);
	}


	public void save(Directo directo) throws BaseDeDatosException {
		DirectoEntity entity = this.toEntity(directo);
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
