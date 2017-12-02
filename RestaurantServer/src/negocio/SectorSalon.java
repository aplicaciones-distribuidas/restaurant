package negocio;

import java.util.ArrayList;
import java.util.List;

import dao.MesasDAO;
import dao.SectoresSalonDAO;
import excepciones.BaseDeDatosException;

public class SectorSalon {

	private String nombre;
	private Sucursal sucursal;
	private List<Mesa> mesas = new ArrayList<Mesa>();
	private List<Empleado> empleados = new ArrayList<Empleado>();

	public SectorSalon(String nombre) {
		super();
		this.nombre = nombre;
	}

	public SectorSalon(String nombre, Sucursal sucursal, List<Mesa> mesas, List<Empleado> empleados) {
		super();
		this.nombre = nombre;
		this.sucursal = sucursal;
		this.mesas = mesas;
		this.empleados = empleados;
	}

	public String getNombre() {
		return nombre;
	}

	public Sucursal getSucursal() {
		return sucursal;
	}

	public void setSucursal(Sucursal sucursal) {
		this.sucursal = sucursal;
	}

	public List<Mesa> getMesas() {
		return mesas;
	}

	public List<Empleado> getEmpleados() {
		return empleados;
	}

	@Override
	public String toString() {
		return String.format("SectorSalon [nombre => %s, cantidad de mesas => %d, cantidad de empleados => %d]", this
				.getNombre(), this.getMesas().size(), this.getEmpleados().size());
	}

	public void save() throws BaseDeDatosException {
		SectoresSalonDAO.getInstancia().save(this);
	}

	public List<Mesa> getMesasDisponibles() throws BaseDeDatosException {
		return MesasDAO.getInstancia().getDisponiblesBySectorSalon(this.nombre);
	}

}
