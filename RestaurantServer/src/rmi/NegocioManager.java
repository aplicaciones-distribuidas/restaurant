package rmi;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;
import java.util.List;

import controlador.Controlador;
import dto.EmpleadoView;
import dto.MesaView;
import dto.PedidoReposicionView;
import dto.PedidoView;
import dto.ReporteView;
import excepciones.BaseDeDatosException;
import excepciones.SucursalNoExisteException;
import interfaces.NegocioTDA;

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
	public void asignarHorasTrabajo(int horas, Long trabajoId) throws RemoteException {
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
	public void asignarEmpleadoSectorSucursal(Long idEmpleado, String sucursal, int numero) throws RemoteException {
		// TODO Auto-generated method stub

	}

	@Override
	public EmpleadoView buscarEmpleado(String nombre, String apellido, String rol) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void agregarMesa(String sucursal, int nroMesa, int cantPersonas, EmpleadoView empleado)
			throws RemoteException {
		// TODO Auto-generated method stub

	}

	@Override
	public void lanzarPedido(String sucursal, int nroMesa) throws RemoteException {
		// TODO Auto-generated method stub

	}

	@Override
	public void realizarReclamo(String sucursal, int nroMesa, String reclamo) throws RemoteException {
		// TODO Auto-generated method stub

	}

	@Override
	public void marcarComandaRealizada(int nroPedido) throws RemoteException {
		// TODO Auto-generated method stub

	}

	@Override
	public void facturarMesa(String sucursal, int nroMesa) throws RemoteException {
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
	public void cerrarMesa(String sucursal, int nroMesa) throws RemoteException {
		// TODO Auto-generated method stub

	}

	@Override
	public void liberarMesa(String sucursal, int nroMesa) throws RemoteException {
		// TODO Auto-generated method stub

	}

	@Override
	public void registrarCobro(String sucursal, int nroMesa) throws RemoteException {
		// TODO Auto-generated method stub

	}
}
