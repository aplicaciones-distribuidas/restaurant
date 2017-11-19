package negocio;

import java.util.Date;

public abstract class Producto {
	
	private String rubro;
	private int caducidad;
	private float comisionMozo;
	private Date fecha;
	private float precio;
	private InsumoProducto insumoProducto;


	public Producto(String rubro, int caducidad, float comisionMozo, Date fecha, float precio,
			InsumoProducto insumoProducto) {
		this.rubro = rubro;
		this.caducidad = caducidad;
		this.comisionMozo = comisionMozo;
		this.fecha = fecha;
		this.precio = precio;
		this.insumoProducto = insumoProducto;
	}

	public String getRubro() {
		return rubro;
	}

	public int getCaducidad() {
		return caducidad;
	}

	public float getComisionMozo() {
		return comisionMozo;
	}

	public Date getFecha() {
		return fecha;
	}

	public float getPrecio() {
		return precio;
	}

	public InsumoProducto getInsumoProducto() {
		return insumoProducto;
	}

}
