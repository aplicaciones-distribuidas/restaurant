package negocio;

import java.util.Date;

public class Directo extends Producto {
	
	private InsumoProducto insumoProducto;

	public Directo(String rubro, int caducidad, float comisionMozo, Date fecha, float precio, Area area, InsumoProducto insumoProducto) {
		super(rubro, caducidad, comisionMozo, fecha, precio, area);
		this.insumoProducto = insumoProducto;
	}

	public InsumoProducto getInsumoProducto() {
		return insumoProducto;
	}

}
