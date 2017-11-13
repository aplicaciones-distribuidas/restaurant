package negocio;

public class InsumoProducto {
	
	private float cantidad;
	private Insumo insumo;

	public InsumoProducto(float cantidad, Insumo insumo) {
		super();
		this.cantidad = cantidad;
		this.insumo = insumo;
	}

	public float getCantidad() {
		return cantidad;
	}

	public Insumo getInsumo() {
		return insumo;
	}

}
