package negocio;

import java.util.Date;

public class Directo extends Producto {

	public Directo(String rubro, int caducidad, float comisionMozo, Date fecha, float precio,
			InsumoProducto insumoProducto) {
		super(rubro, caducidad, comisionMozo, fecha, precio, insumoProducto);
	}

}
