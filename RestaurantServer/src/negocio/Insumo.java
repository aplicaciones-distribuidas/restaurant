package negocio;

import java.util.Date;

public class Insumo {
	
	private String clasificacion;
	private String nombre;
	private int cantidadMinima;
	private Date fechaVencimiento;
	private Date fechaCompra;
	private Proveedor proveedor;
	

	public Insumo(String clasificacion, String nombre, int cantidadMinima, Date fechaVencimiento, Date fechaCompra,
			Proveedor proveedor) {
		this.clasificacion = clasificacion;
		this.nombre = nombre;
		this.cantidadMinima = cantidadMinima;
		this.fechaVencimiento = fechaVencimiento;
		this.fechaCompra = fechaCompra;
		this.proveedor = proveedor;
	}

	public String getClasificacion() {
		return clasificacion;
	}

	public String getNombre() {
		return nombre;
	}

	public int getCantidadMinima() {
		return cantidadMinima;
	}

	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}

	public Date getFechaCompra() {
		return fechaCompra;
	}

	public Proveedor getProveedor() {
		return proveedor;
	}

}
