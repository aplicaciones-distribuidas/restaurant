package negocio;

import java.util.Date;
import java.util.List;

public class SemiElaborado extends Producto {

	public SemiElaborado(String rubro, int caducidad, float comisionMozo, Date fecha, float precio,
			List<InsumoProducto> insumoProducto, Area area) {
		super(rubro, caducidad, comisionMozo, fecha, precio, insumoProducto, area);
	}
	
}
