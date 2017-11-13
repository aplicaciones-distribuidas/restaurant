package negocio;

public abstract class Deposito {
	
	private String nombre;
	
	public Deposito(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

}
