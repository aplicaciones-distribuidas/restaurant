package negocio;

import java.util.ArrayList;
import java.util.List;

import dao.MesasDAO;
import dao.SectoresSalonDAO;
import dto.SectorSalonView;
import excepciones.BaseDeDatosException;

public class SectorSalon {
	private Long id;
	private String nombre;
	private Sucursal sucursal;
	private List<Mesa> mesas = new ArrayList<Mesa>();
	private List<Empleado> empleados = new ArrayList<Empleado>();

	public SectorSalon(String nombre) {
		this.nombre = nombre;
	}

	public SectorSalon(Long id, String nombre, Sucursal sucursal, List<Mesa> mesas, List<Empleado> empleados) {
		this.id = id;
		this.nombre = nombre;
		this.sucursal = sucursal;
		this.mesas = mesas;
		this.empleados = empleados;
	}

	public Long getId() {
		return id;
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

	public SectorSalonView toView() {
		return new SectorSalonView(this.id, this.nombre);
	}

	@Override
	public String toString() {
		return String.format(
				"SectorSalon [id => %d, nombre => %s, cantidad de mesas => %d, cantidad de empleados => %d]", this
						.getId(), this.getNombre(), this.getMesas().size(), this.getEmpleados().size());
	}

	public void save() throws BaseDeDatosException {
		this.id = SectoresSalonDAO.getInstancia().save(this);
	}

	public List<Mesa> getMesasDisponibles(int cantPersonas) throws BaseDeDatosException {
		List<Mesa> mesas = new ArrayList<Mesa>();
		for (Mesa mesa : MesasDAO.getInstancia().getDisponiblesBySectorSalon(this)) {
			int capacidadMaxima = mesa.getCapacidad();
			switch (capacidadMaxima) {
			case 8:
				capacidadMaxima += 2;
				break;
			case 6:
				capacidadMaxima += 1;
				break;
			default:
				break;
			}
			if (capacidadMaxima >= cantPersonas) {
				mesas.add(mesa);
			}
		}
		return mesas;
	}

}
