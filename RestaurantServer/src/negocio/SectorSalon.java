package negocio;

import java.util.List;

public class SectorSalon {
	
	private String nombre;
	private List<Mesa> mesas;
	private List<Empleado> empleados;
	
	
	public SectorSalon(String nombre, List<Mesa> mesas, List<Empleado> empleados) {
		super();
		this.nombre = nombre;
		this.mesas = mesas;
		this.empleados = empleados;
	}


	public String getNombre() {
		return nombre;
	}


	public List<Mesa> getMesas() {
		return mesas;
	}


	public List<Empleado> getEmpleados() {
		return empleados;
	}

}
