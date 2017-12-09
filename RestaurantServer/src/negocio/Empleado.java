package negocio;

import java.util.ArrayList;
import java.util.List;

public class Empleado {

	private String nombre;
	private String apellido;
	private int porcentajeComision;
	private Rol rol;
	private List<Comision> comisiones = new ArrayList<>();
	
	
	public Empleado(String nombre, String apellido, int porcentajeComision, Rol rol, List<Comision> comisiones) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.porcentajeComision = porcentajeComision;
		this.rol = rol;
		this.comisiones = comisiones;
	}


	public String getNombre() {
		return nombre;
	}


	public String getApellido() {
		return apellido;
	}


	public int getPorcentajeComision() {
		return porcentajeComision;
	}


	public Rol getRol() {
		return rol;
	}


	public List<Comision> getComisiones() {
		return comisiones;
	}
	
}
