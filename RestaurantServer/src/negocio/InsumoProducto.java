package negocio;

public class InsumoProducto {
	
	private float cantidad;
	private Insumo insumo;

	public InsumoProducto(float cantidad, Insumo insumo) {
		this.cantidad = cantidad;
		this.insumo = insumo;
	}

	public float getCantidad() {
		return cantidad;
	}

	public Insumo getInsumo() {
		return insumo;
	}
	
	public void setCantidad(float cantidad) {
		this.cantidad = cantidad;
	}

}
