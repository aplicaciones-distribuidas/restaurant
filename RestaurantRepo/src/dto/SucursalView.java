package dto;

public class SucursalView {

	private Long id;
	private String nombre;
	private String ubicacion;
	private int capacidad;

	public SucursalView(String nombre, String ubicacion, int capacidad) {
		this.nombre = nombre;
		this.ubicacion = ubicacion;
		this.capacidad = capacidad;
	}

	public Long getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public int getCapacidad() {
		return capacidad;
	}

	@Override
	public String toString() {
		return String.format("SucursalView [id => %d, nombre => %s, ubicacion => %s, capacidad => %d]", this.getId(), this.getNombre(), this.getUbicacion(), this.getCapacidad());
	}
}
