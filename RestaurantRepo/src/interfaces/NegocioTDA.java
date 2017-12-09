package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

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
import excepciones.FormaDePagoNoExisteException;
import excepciones.InsumoNoExisteException;
import excepciones.MesaOcupacionNoExisteException;
import excepciones.NoHayMesasDisponiblesException;
import excepciones.ProductoNoExisteException;
import excepciones.ProductoSinStockException;
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
	public void lanzarPedido(Long idMesaOcupacion) throws RemoteException;
	public void realizarReclamo(Long idMesaOcupacion, String reclamo) throws RemoteException;
	public void marcarComandaRealizada(int nroPedido) throws RemoteException;
	public PedidoReposicionView buscarPedidoReposicion() throws RemoteException;
	public PedidoView buscarPedido() throws RemoteException;
	public ReporteView generarReporteReposicionInsumos() throws RemoteException;
	public void verificarStock() throws RemoteException;
	public void generarOrdenCompra() throws RemoteException;
	public void actualizarStock(Long idProducto, int cantidad) throws RemoteException;


	// PARA PRESENTACION TPO SEGUN DOC DE GODIO
	public void crearPlatoDirecto(String rubro, int caducidad, float comisionMozo, Date fecha, float precio, String nombreArea, int idInsumo, float cantInsumo) throws RemoteException, AreaNoExisteException, InsumoNoExisteException, RubroNoExisteException, BaseDeDatosException;
	public void crearPlatoSemielaborado(String rubro, int caducidad, float comisionMozo, Date fecha, float precio, String nombreArea, List<InsumoProductoView> insumosView) throws RemoteException, AreaNoExisteException, BaseDeDatosException, InsumoNoExisteException;
	public MesaOcupacionView abrirMesa(String sucursal, int cantPersonas, Long idEmpleado) throws RemoteException, NoHayMesasDisponiblesException, BaseDeDatosException, SucursalNoExisteException, EmpleadoNoExisteException;
	public void agregarProductoAMesa(Long idMesaOcupacion, Long idProducto, int cantidadProducto) throws RemoteException, BaseDeDatosException, ProductoNoExisteException, InsumoNoExisteException, MesaOcupacionNoExisteException, ProductoSinStockException;
	public void cerrarMesa(Long idMesaOcupacion, Long idFormaDePago) throws RemoteException, BaseDeDatosException, MesaOcupacionNoExisteException, FormaDePagoNoExisteException; // cerrar mesa implica facturarla, cobrarla y liberarla, se unifican todos en uno
	//public void cobrarMesa(Long idMesaOcupacion) throws RemoteException; 
	//public void facturarMesa(Long idMesaOcupacion) throws RemoteException;
	//public void liberarMesa(Long idMesaOcupacion) throws RemoteException;
	public ComisionesMozosView getComisionesMozos(String sucursal) throws RemoteException;

}
