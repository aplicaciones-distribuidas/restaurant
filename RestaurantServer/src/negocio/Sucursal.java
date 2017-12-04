package negocio;

import java.util.ArrayList;
import java.util.List;

import dao.SucursalDAO;
import excepciones.BaseDeDatosException;

public class Sucursal {

	private Long id;
	private String nombre;
	private String ubicacion;
	private int capacidad;
	private List<Carta> cartas = new ArrayList<Carta>();
	private Caja caja;
	private List<Area> areas = new ArrayList<Area>();
	private List<Pedido> pedidos = new ArrayList<Pedido>();
	private List<Reserva> reservas = new ArrayList<Reserva>();
	private List<Tarea> tareas = new ArrayList<Tarea>();
	private List<SectorSalon> sectores = new ArrayList<SectorSalon>();
	private List<PedidoReposicion> pedidosReposicion = new ArrayList<PedidoReposicion>();
	private Deposito deposito;

	public Sucursal(String nombre, String ubicacion, int capacidad) {
		super();
		this.nombre = nombre;
		this.ubicacion = ubicacion;
		this.capacidad = capacidad;
	}

	public Sucursal(Long id, String nombre, String ubicacion, int capacidad, List<Carta> cartas, Caja caja,
			List<Area> areas, List<Pedido> pedidos, List<Reserva> reservas, List<Tarea> tareas,
			List<SectorSalon> sectores, List<PedidoReposicion> pedidosReposicion, Deposito deposito) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.ubicacion = ubicacion;
		this.capacidad = capacidad;
		this.cartas = cartas;
		this.caja = caja;
		this.areas = areas;
		this.pedidos = pedidos;
		this.reservas = reservas;
		this.tareas = tareas;
		this.sectores = sectores;
		this.pedidosReposicion = pedidosReposicion;
		this.deposito = deposito;
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

	public List<Carta> getCartas() {
		return cartas;
	}

	public Caja getCaja() {
		return caja;
	}

	public List<Area> getAreas() {
		return areas;
	}

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public List<Reserva> getReservas() {
		return reservas;
	}

	public List<Tarea> getTareas() {
		return tareas;
	}

	public List<SectorSalon> getSectores() {
		return sectores;
	}

	public List<PedidoReposicion> getPedidosReposicion() {
		return pedidosReposicion;
	}

	public Deposito getDeposito() {
		return deposito;
	}

	@Override
	public String toString() {
		return String.format("Sucursal [nombre => %s, ubicación => %s, capacidad => %d, cantidad de sectores => %d]",
				this.getNombre(), this.getUbicacion(), this.getCapacidad(), this.getSectores().size());
	}

	public void save() throws BaseDeDatosException {
		this.id = SucursalDAO.getInstancia().save(this);
	}

	public List<Mesa> getMesasDisponibles(int cantPersonas) throws BaseDeDatosException {
		List<Mesa> mesas = new ArrayList<Mesa>();
		for (SectorSalon sector : this.sectores) {
			mesas.addAll(sector.getMesasDisponibles(cantPersonas));
		}
		return mesas;
	}

}
