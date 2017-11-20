package negocio;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class Producto {
	
	private String rubro;
	private int caducidad;
	private float comisionMozo;
	private Date fecha;
	private float precio;
	private List<InsumoProducto> insumosProducto;
	private Area area;
	
	
	public Producto(String rubro, int caducidad, float comisionMozo, Date fecha, float precio, List<InsumoProducto> insumosProducto, Area area) {
		this.rubro = rubro;
		this.caducidad = caducidad;
		this.comisionMozo = comisionMozo;
		this.fecha = fecha;
		this.precio = precio;
		this.insumosProducto = insumosProducto;
		this.area = area;
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


	public List<InsumoProducto> getInsumosProducto() {
		return insumosProducto;
	}


	public Area getArea() {
		return area;
	}
	
	public List<InsumoProducto> getInsumosFaltantes() {
		List<InsumoProducto> resultado = new ArrayList<>();
		
		for (InsumoProducto ip : this.getInsumosProducto()) if (ip.getCantidad() == 0) resultado.add(ip);
		
		return resultado;
	}
	
	public void actualizarStockInsumo(String nombre, float cantidad) {
		for (InsumoProducto ip : this.getInsumosProducto()) {
			if (ip.getInsumo().getNombre().equals(nombre)) {
				ip.setCantidad(cantidad);
				break;
			}
		}
	}
	
}
