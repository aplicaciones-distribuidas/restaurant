package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

import dto.EmpleadoView;
import dto.MesaOcupacionView;
import dto.MesaView;
import dto.PedidoReposicionView;
import dto.PedidoView;
import dto.ReporteView;
import excepciones.AreaNoExisteException;
import excepciones.BaseDeDatosException;
import excepciones.NoHayMesasDisponiblesException;
import excepciones.InsumoNoExisteException;
import excepciones.RubroNoExisteException;
import excepciones.SucursalNoExisteException;
import excepciones.TareaNoExisteException;

public interface NegocioTDA extends Remote {

	// NO SE QUE SE SUPONEN QUE TIENEN QUE HACER ESTOS 3 METODOS
	public int stockPlatoBebida() throws RemoteException;
	public void centralizacionCompras() throws RemoteException;
	public void centralizacionDistribucion() throws RemoteException;

	public void asignarHorasTrabajo(int horas, Long trabajoId) throws RemoteException, TareaNoExisteException;
	public void reservar(String sucursal, int cantPersonas, Date fecha) throws RemoteException;
	public List<MesaView> mesasDisponibles(String sucursal, int cantPersonas) throws RemoteException, SucursalNoExisteException, BaseDeDatosException;
	public List<MesaOcupacionView> mesasOcupadas(String sucursal) throws RemoteException, SucursalNoExisteException, BaseDeDatosException;
	public void asignarEmpleadoSectorSucursal(Long idEmpleado, String sucursal, int numero) throws RemoteException;
	public EmpleadoView buscarEmpleado(String nombre, String apellido, String rol) throws RemoteException;
	public MesaOcupacionView abrirMesa(String sucursal, int cantPersonas, int idEmpleado) throws RemoteException, NoHayMesasDisponiblesException;
	public void lanzarPedido(String sucursal, int idMesaOcupacion) throws RemoteException;
	public void realizarReclamo(String sucursal, int idMesaOcupacion, String reclamo) throws RemoteException;
	public void marcarComandaRealizada(int nroPedido) throws RemoteException;
	public void facturarMesa(String sucursal, int idMesaOcupacion) throws RemoteException;
	public PedidoReposicionView buscarPedidoReposicion() throws RemoteException;
	public PedidoView buscarPedido() throws RemoteException;
	public ReporteView generarReporteReposicionInsumos() throws RemoteException;
	public void verificarStock() throws RemoteException;
	public void generarOrdenCompra() throws RemoteException;
	public void actualizarStock(Long idProducto, int cantidad) throws RemoteException;
	public void cerrarMesa(String sucursal, int idMesaOcupacion) throws RemoteException;
	public void liberarMesa(String sucursal, int idMesaOcupacion) throws RemoteException;
	public void registrarCobro(String sucursal, int idMesaOcupacion) throws RemoteException;

	// PARA PRESENTACION TPO SEGUN DOC DE GODIO
	public void crearPlatoDirecto(String rubro, int caducidad, float comisionMozo, Date fecha, float precio, String nombreArea, int idInsumo, float cantInsumo) throws RemoteException, AreaNoExisteException, InsumoNoExisteException, RubroNoExisteException, BaseDeDatosException;
	/*public void crearPlatoSemielaborado(String rubro, int caducidad, float comisionMozo, Date fecha, float precio, String nombreArea, List<InsumoProductoView> insumos) throws RemoteException, AreaNoExisteException, ProveedorNoExisteException;
	public MesaOcupacionView abrirMesa(String sucursal, int cantPersonas, int idEmpleado) throws RemoteException, NoHayMesasDisponiblesException;
	public void agregarPlatoAMesa(String sucursal, int nroMesa, int nroPlato) throws RemoteException;
	public void cerrarMesa(String sucursal, int nroMesa) throws RemoteException;
	public void cobrarMesa(String sucursal, int nroMesa) throws RemoteException;
	public ComisionesMozosView getComisionesMozos(String sucursal) throws RemoteException;*/

}
