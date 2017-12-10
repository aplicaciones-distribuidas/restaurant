package dto;

public class InsumoProductoView {
	private float cantidad;
	private int insumoId;
	
	
	public InsumoProductoView(float cantidad, int insumoId) {
		super();
		this.cantidad = cantidad;
		this.insumoId = insumoId;
	}


	public float getCantidad() {
		return cantidad;
	}


	public int getInsumoId() {
		return insumoId;
	}

	
}
