package negocio;

import java.util.List;

public class Sucursal {
	
	private String nombre;
	private String ubicacion;
	private int capacidad;
	private List<Carta> cartas;
	private Caja caja;
	private List<Area> areas;
	private List<Pedido> pedidos;
	private List<Reserva> reservas;
	private List<Tarea> tareas;
	private List<SectorSalon> sectores;
	private List<Mesa> mesas;
	private List<PedidoReposicion> pedidosReposicion;
	private Deposito deposito;
	
	
	public Sucursal(String nombre, String ubicacion, int capacidad, List<Carta> cartas, Caja caja, List<Area> areas,
			List<Pedido> pedidos, List<Reserva> reservas, List<Tarea> tareas, List<SectorSalon> sectores,
			List<Mesa> mesas, List<PedidoReposicion> pedidosReposicion, Deposito deposito) {
		super();
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
		this.mesas = mesas;
		this.pedidosReposicion = pedidosReposicion;
		this.deposito = deposito;
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


	public List<Mesa> getMesas() {
		return mesas;
	}


	public List<PedidoReposicion> getPedidosReposicion() {
		return pedidosReposicion;
	}


	public Deposito getDeposito() {
		return deposito;
	}
	

}
