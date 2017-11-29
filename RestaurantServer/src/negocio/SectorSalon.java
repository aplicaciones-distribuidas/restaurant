package negocio;

import java.util.ArrayList;
import java.util.List;

import dao.SectoresSalonDAO;
import excepciones.BaseDeDatosException;

public class SectorSalon {

	private String nombre;
	private List<Mesa> mesas = new ArrayList<Mesa>();
	private List<Empleado> empleados = new ArrayList<Empleado>();

	public SectorSalon(String nombre) {
		super();
		this.nombre = nombre;
	}

	public SectorSalon(String nombre, List<Mesa> mesas) {
		super();
		this.nombre = nombre;
		this.mesas = mesas;
	}

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

	@Override
	public String toString() {
		return String.format("SectorSalon [nombre => %s, cantidad de mesas => %d]", this.getNombre(), this.getMesas()
				.size());
	}

	/*public void save() throws BaseDeDatosException {
		SectoresSalonDAO.getInstancia().save(this);
	}*/

}
