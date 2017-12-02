package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

import dto.MesaView;
import excepciones.BaseDeDatosException;

public interface NegocioTDA extends Remote {
	
	// NO SE QUE SE SUPONEN QUE TIENEN QUE HACER ESTOS 3 METODOS
	public int stockPlatoBebida() throws RemoteException;
	public void centralizacionCompras() throws RemoteException;
	public void centralizacionDistribucion() throws RemoteException;
	
	public void asignarHorasTrabajo(int horas, Long trabajoId) throws RemoteException;
	public void reservar(String sucursal, int cantPersonas, Date fecha) throws RemoteException;
	public List<MesaView> mesasDisponibles(String sucursal, int cantPersonas) throws RemoteException;
	public void asignarEmpleadoSectorSucursal(Long idEmpleado, String sucursal, int numero) throws RemoteException;
	public EmpleadoView buscarEmpleado(String nombre, String apellido, String rol) throws RemoteException;
	public void agregarMesa(String sucursal, int nroMesa, int cantPersonas, EmpleadoView empleado) throws RemoteException;
	public void lanzarPedido(String sucursal, int nroMesa) throws RemoteException;
	public void realizarReclamo(String sucursal, int nroMesa, String reclamo) throws RemoteException;
	public void marcarComandaRealizada(int nroPedido) throws RemoteException;
	public void facturarMesa(String sucursal, int nroMesa) throws RemoteException;
	public PedidoReposicionView buscarPedidoReposicion() throws RemoteException;
	public PedidoView buscarPedido() throws RemoteException;
	public ReporteView generarReporteReposicionInsumos() throws RemoteException;
	public void verificarStock() throws RemoteException;
	public void generarOrdenCompra() throws RemoteException;
	public void actualizarStock(Long idProducto, int cantidad) throws RemoteException;
	public void cerrarMesa(String sucursal, int nroMesa) throws RemoteException;
	public void liberarMesa(String sucursal, int nroMesa) throws RemoteException;
	public void registrarCobro(String sucursal, int nroMesa) throws RemoteException;
	
	public void agregarMesa(MesaView mesa) throws BaseDeDatosException, RemoteException;
}
