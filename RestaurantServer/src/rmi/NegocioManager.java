package rmi;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;
import java.util.List;

import controlador.Controlador;
import dao.AreaDAO;
import dao.InsumoDAO;
import dao.ProductoDAO;
import dto.*;
import excepciones.*;
import interfaces.NegocioTDA;
import negocio.Area;
import negocio.Directo;
import negocio.Insumo;
import negocio.InsumoProducto;

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
	public MesaOcupacionView abrirMesa(String sucursal, int cantPersonas, Long idEmpleado) throws RemoteException,
			NoHayMesasDisponiblesException {
		// TODO Auto-generated method stub
		return null;
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
	public void crearPlatoSemielaborado(String rubro, int caducidad, float comisionMozo, Date fecha, float precio, String nombreArea, List<InsumoProductoView> insumos) throws RemoteException, AreaNoExisteException, ProveedorNoExisteException {
		// TODO Auto-generated method stub

	}
}
