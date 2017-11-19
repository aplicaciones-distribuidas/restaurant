package negocio;

import java.util.Date;

public class SemiElaborado extends Producto {

	public SemiElaborado(String rubro, int caducidad, float comisionMozo, Date fecha, float precio,
			InsumoProducto insumoProducto) {
		super(rubro, caducidad, comisionMozo, fecha, precio, insumoProducto);
	}
	
}
