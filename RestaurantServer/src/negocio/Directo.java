package negocio;

import dao.ProductoDAO;
import excepciones.BaseDeDatosException;

import java.util.Date;

public class Directo extends Producto {

	private InsumoProducto insumoProducto;

	public Directo(Long id, String rubro, int caducidad, float comisionMozo, Date fecha, float precio, Area area, InsumoProducto insumoProducto) {
		super(id, rubro, caducidad, comisionMozo, fecha, precio, area);
		this.insumoProducto = insumoProducto;
	}

	public InsumoProducto getInsumoProducto() {
		return insumoProducto;
	}

	@Override
	public void save() throws BaseDeDatosException {
		this.setId(ProductoDAO.getInstancia().save(this));
	}
}
