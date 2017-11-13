package negocio;

public class Cafeteria extends Area {
	
	private int horasPreparacion;

	public Cafeteria(String nombre, int horasPreparacion) {
		super(nombre);
		this.horasPreparacion = horasPreparacion;
	}

	public int getHorasPreparacion() {
		return horasPreparacion;
	}

}
