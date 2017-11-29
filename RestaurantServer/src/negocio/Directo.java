package negocio;

import java.util.Date;
import java.util.List;

public class Directo extends Producto {

	public Directo(String rubro, int caducidad, float comisionMozo, Date fecha, float precio,
			List<InsumoProducto> insumoProducto, Area area) {
		super(rubro, caducidad, comisionMozo, fecha, precio, insumoProducto, area);
	}

}
