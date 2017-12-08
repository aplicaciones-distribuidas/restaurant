package dto;

import java.util.Date;

public class InsumoProductoView {
	private float cantidad;
	private String clasificacion;
	private String nombre;
	private int cantidadMinima;
	private Date fechaVencimiento;
	private Date fechaCompra;
	
	public InsumoProductoView(float cantidad, String clasificacion, String nombre, int cantidadMinima,
			Date fechaVencimiento, Date fechaCompra) {
		this.cantidad = cantidad;
		this.clasificacion = clasificacion;
		this.nombre = nombre;
		this.cantidadMinima = cantidadMinima;
		this.fechaVencimiento = fechaVencimiento;
		this.fechaCompra = fechaCompra;
	}

	public float getCantidad() {
		return cantidad;
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
	
}
