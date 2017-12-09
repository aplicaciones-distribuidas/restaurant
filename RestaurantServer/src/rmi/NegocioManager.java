package rmi;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import controlador.Controlador;
import dao.AreaDAO;
import dao.EmpleadoDAO;
import dao.InsumoDAO;
import dao.MesasDAO;
import dao.MesasOcupacionDAO;
import dao.ProductoDAO;
import dao.SectoresSalonDAO;
import dao.SucursalDAO;
import dto.ComisionesMozosView;
import dto.EmpleadoView;
import dto.InsumoProductoView;
import dto.MesaOcupacionView;
import dto.MesaView;
import dto.PedidoReposicionView;
import dto.PedidoView;
import dto.ReporteView;
import excepciones.AreaNoExisteException;
import excepciones.BaseDeDatosException;
import excepciones.EmpleadoNoExisteException;
import excepciones.InsumoNoExisteException;
import excepciones.NoHayMesasDisponiblesException;
import excepciones.RubroNoExisteException;
import excepciones.SucursalNoExisteException;
import excepciones.TareaNoExisteException;
import interfaces.NegocioTDA;
import negocio.Area;
import negocio.Directo;
import negocio.Empleado;
import negocio.Insumo;
import negocio.InsumoProducto;
import negocio.Mesa;
import negocio.MesaOcupacion;
import negocio.SectorSalon;
import negocio.SemiElaborado;
import negocio.Sucursal;

public class NegocioManager extends UnicastRemoteObject implements NegocioTDA, Serializable {
	public NegocioManager() throws RemoteException {
		super();
	}

	private static final long serialVersionUID = 3548218673814294625L;

	@Override
	public int stockPlatoBebida() throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void centralizacionCompras() throws RemoteException {
		// TODO Auto-generated method stub

	}

	@Override
	public void centralizacionDistribucion() throws RemoteException {
		// TODO Auto-generated method stub

	}

	@Override
	public void asignarHorasTrabajo(int horas, Long trabajoId) throws RemoteException, TareaNoExisteException {
		// TODO Auto-generated method stub

	}

	@Override
	public void reservar(String sucursal, int cantPersonas, Date fecha) throws RemoteException {
		// TODO Auto-generated method stub

	}

	@Override
	public List<MesaView> mesasDisponibles(String sucursal, int cantPersonas) throws RemoteException,
			SucursalNoExisteException, BaseDeDatosException {
		return Controlador.getInstancia().getMesasDisponibles(sucursal, cantPersonas);
	}

	@Override
	public List<MesaOcupacionView> mesasOcupadas(String sucursal) throws RemoteException, SucursalNoExisteException,
			BaseDeDatosException {
		return Controlador.getInstancia().getMesasOcupadas(sucursal);
	}

	@Override
	public void asignarEmpleadoSectorSucursal(Long idEmpleado, String sucursal, int numero) throws RemoteException {
		// TODO Auto-generated method stub

	}

	@Override
	public EmpleadoView buscarEmpleado(String nombre, String apellido, String rol) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MesaOcupacionView abrirMesa(String nombreSucursal, int cantPersonas, Long idEmpleado) throws RemoteException,
			NoHayMesasDisponiblesException, BaseDeDatosException, SucursalNoExisteException, EmpleadoNoExisteException {
		
		Sucursal sucursal = SucursalDAO.getInstancia().getByNombre(nombreSucursal);
		Empleado empleado = EmpleadoDAO.getInstancia().getById(idEmpleado);
		
		//filtro los sectores salon de la sucursal elegida
		List<SectorSalon> sectoresSalon = SectoresSalonDAO.getInstancia().getAll();
		List<SectorSalon> sectoresSalonDeLaSucursal = new ArrayList<>(); 
		for(SectorSalon ss : sectoresSalon) {
			if (ss.getSucursal().getNombre().equals(sucursal.getNombre())) sectoresSalonDeLaSucursal.add(ss);
		}
		//filtro los sectores salon que tengan al empleado elegido
		List<SectorSalon> sectoresSalonEmpleado = new ArrayList<>();
		for(SectorSalon ss : sectoresSalonDeLaSucursal) {
			if (ss.getEmpleados().contains(empleado)) sectoresSalonEmpleado.add(ss);
		}

		//mesas disponibles
		List<Mesa> mesasDisponibles = new ArrayList<>();
		
		for(SectorSalon ss : sectoresSalonEmpleado) {
			if (!ss.getMesasDisponibles(cantPersonas).isEmpty()) {
				mesasDisponibles.addAll(ss.getMesasDisponibles(cantPersonas));
				break;
			}
		}
		
		if (mesasDisponibles.isEmpty()) throw new NoHayMesasDisponiblesException();
		
		//hay mesas dispobibles, entonces se marcan las mesas como ocupadas y se crea un objeto MesaOcupacion
		for(Mesa mesa : mesasDisponibles) mesa.setOcupada(true);
		
		MesaOcupacion mesaOcupacion = new MesaOcupacion(new Date(), null, false, cantPersonas, mesasDisponibles, null);
		
		//guardar el objeto mesa ocupacion
		MesasOcupacionDAO.getInstancia().save(mesaOcupacion);
		
		//update de mesas
		for(Mesa mesa : mesasDisponibles) MesasDAO.getInstancia().save(mesa);
		
		return mesaOcupacion.toView();
		
	}

	@Override
	public void agregarProductoAMesa(Long idMesaOcupacion, Long idProducto, int cantidadProducto) throws RemoteException {
		// TODO Auto-generated method stub

	}

	@Override
	public void lanzarPedido(Long idMesaOcupacion) throws RemoteException {
		// TODO Auto-generated method stub

	}

	@Override
	public void realizarReclamo(Long idMesaOcupacion, String reclamo) throws RemoteException {
		// TODO Auto-generated method stub

	}

	@Override
	public void marcarComandaRealizada(int nroPedido) throws RemoteException {
		// TODO Auto-generated method stub

	}

	@Override
	public void facturarMesa(Long idMesaOcupacion) throws RemoteException {
		// TODO Auto-generated method stub

	}

	@Override
	public PedidoReposicionView buscarPedidoReposicion() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PedidoView buscarPedido() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ReporteView generarReporteReposicionInsumos() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void verificarStock() throws RemoteException {
		// TODO Auto-generated method stub

	}

	@Override
	public void generarOrdenCompra() throws RemoteException {
		// TODO Auto-generated method stub

	}

	@Override
	public void actualizarStock(Long idProducto, int cantidad) throws RemoteException {
		// TODO Auto-generated method stub

	}

	@Override
	public void cerrarMesa(Long idMesaOcupacion) throws RemoteException {
		// TODO Auto-generated method stub

	}

	@Override
	public void cobrarMesa(Long idMesaOcupacion) throws RemoteException {
		// TODO Auto-generated method stub

	}

	@Override
	public void liberarMesa(Long idMesaOcupacion) throws RemoteException {
		// TODO Auto-generated method stub

	}

	@Override
	public ComisionesMozosView getComisionesMozos(String sucursal) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void crearPlatoDirecto(String rubro, int caducidad, float comisionMozo, Date fecha, float precio,
								  String nombreArea, int idInsumo, float cantInsumo)
			throws RemoteException, AreaNoExisteException, InsumoNoExisteException, RubroNoExisteException, BaseDeDatosException {

		Area area = AreaDAO.getInstancia().getByNombre(nombreArea);
		Insumo insumo = InsumoDAO.getInstancia().getById(idInsumo);

		Directo productoDirecto = new Directo(rubro, caducidad, comisionMozo, fecha, precio, area, new InsumoProducto(cantInsumo, insumo));

		ProductoDAO.getInstancia().save(productoDirecto);
	}

	@Override
	public void crearPlatoSemielaborado(String rubro, int caducidad, float comisionMozo, Date fecha, float precio, String nombreArea, List<InsumoProductoView> insumosView) throws RemoteException, AreaNoExisteException, BaseDeDatosException, InsumoNoExisteException {
		Area area = AreaDAO.getInstancia().getByNombre(nombreArea);
		List<InsumoProducto> insumos = new ArrayList<>();
		
		for (InsumoProductoView insumo : insumosView) {
			insumos.add(
					new InsumoProducto(insumo.getCantidad(), InsumoDAO.getInstancia().getById(insumo.getInsumoId()))
					);
		}
		
		SemiElaborado semiElaborado = new SemiElaborado(rubro, caducidad, comisionMozo, fecha, precio, insumos, area);
		
		ProductoDAO.getInstancia().save(semiElaborado);

	}
}
