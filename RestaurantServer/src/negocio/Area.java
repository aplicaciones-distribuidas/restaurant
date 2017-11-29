package negocio;

public abstract class Area {

	private String nombre;

	public Area(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

}
