package negocio;

public class Cocina extends Area {
	
	private int horasPreparacion;

	public Cocina(String nombre, int horasPreparacion) {
		super(nombre);
		this.horasPreparacion = horasPreparacion;
	}

	public int getHorasPreparacion() {
		return horasPreparacion;
	}

}
