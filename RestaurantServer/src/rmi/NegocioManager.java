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
import dao.FormaPagoDAO;
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
import excepciones.FormaDePagoNoExisteException;
import excepciones.InsumoNoExisteException;
import excepciones.MesaOcupacionNoExisteException;
import excepciones.NoHayMesasDisponiblesException;
import excepciones.ProductoNoExisteException;
import excepciones.ProductoSinStockException;
import excepciones.RubroNoExisteException;
import excepciones.SucursalNoExisteException;
import excepciones.TareaNoExisteException;
import interfaces.NegocioTDA;
import negocio.Area;
import negocio.Comision;
import negocio.Directo;
import negocio.Empleado;
import negocio.Factura;
import negocio.FormaPago;
import negocio.Insumo;
import negocio.InsumoProducto;
import negocio.ItemFactura;
import negocio.Mesa;
import negocio.MesaOcupacion;
import negocio.Producto;
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
		for (SectorSalon ss : sectoresSalon) {
			if (ss.getSucursal().getNombre().equals(sucursal.getNombre())) sectoresSalonDeLaSucursal.add(ss);
		}
		//filtro los sectores salon que tengan al empleado elegido
		List<SectorSalon> sectoresSalonEmpleado = new ArrayList<>();
		for (SectorSalon ss : sectoresSalonDeLaSucursal) {
			if (ss.getEmpleados().contains(empleado)) sectoresSalonEmpleado.add(ss);
		}

		//mesas disponibles
		List<Mesa> mesasDisponibles = new ArrayList<>();

		for (SectorSalon ss : sectoresSalonEmpleado) {
			if (!ss.getMesasDisponibles(cantPersonas).isEmpty()) {
				mesasDisponibles.addAll(ss.getMesasDisponibles(cantPersonas));
				break;
			}
		}

		if (mesasDisponibles.isEmpty()) throw new NoHayMesasDisponiblesException();

		//hay mesas dispobibles, entonces se marcan las mesas como ocupadas y se crea un objeto MesaOcupacion
		for (Mesa mesa : mesasDisponibles) mesa.setOcupada(true);

		MesaOcupacion mesaOcupacion = new MesaOcupacion(new Date(), null, false, cantPersonas, mesasDisponibles, null, empleado);

		//guardar el objeto mesa ocupacion
		MesasOcupacionDAO.getInstancia().save(mesaOcupacion);

		//update de mesas
		for (Mesa mesa : mesasDisponibles) MesasDAO.getInstancia().save(mesa);

		return mesaOcupacion.toView();

	}

	@Override
	public void agregarProductoAMesa(Long idMesaOcupacion, Long idProducto, int cantidadProducto) throws RemoteException, BaseDeDatosException, ProductoNoExisteException, InsumoNoExisteException, MesaOcupacionNoExisteException, ProductoSinStockException {
		//buscar el producto y sino existe tirar una ProductoNoExisteException
		Producto producto = ProductoDAO.getInstancia().getById(idProducto);
		Directo productoDirecto = null;
		SemiElaborado productoSemiElaborado = null;

		try {
			productoDirecto = (Directo) producto;
		} catch (Exception e) {
			productoSemiElaborado = (SemiElaborado) producto;
		}


		//buscar la mesaocupacion y sino existe tirar una mesaocupacionNoExisteException
		MesaOcupacion mesaOcupacion = null;
		for (MesaOcupacion mo : MesasOcupacionDAO.getInstancia().getAll()) {
			if (mo.getId().equals(idMesaOcupacion)) {
				mesaOcupacion = mo;
				break;
			}
		}

		if (mesaOcupacion == null) throw new MesaOcupacionNoExisteException();

		//validar que el producto tenga el stock que se solicita sino ProductoSinStockException, si tiene, descontar el stock y actualizar los insumos
		if (productoDirecto != null) { // es directo

			if (productoDirecto.getInsumoProducto().getInsumo().getCantidad() >= productoDirecto.getInsumoProducto().getCantidad()) { // hay stock
				// descuento stock y actualizo insumo
				productoDirecto.getInsumoProducto().getInsumo().setCantidad(productoDirecto.getInsumoProducto().getInsumo().getCantidad() - productoDirecto.getInsumoProducto().getCantidad());
				InsumoDAO.getInstancia().update(productoDirecto.getInsumoProducto().getInsumo());
			} else { // no hay stock
				throw new ProductoSinStockException();
			}

		} else { // es semielaborado
			boolean tieneStock = true;

			for (InsumoProducto ip : productoSemiElaborado.getInsumosProducto()) {
				if (ip.getInsumo().getCantidad() < ip.getCantidad()) {
					tieneStock = false;
					break;
				}
			}

			if (!tieneStock) throw new ProductoSinStockException();

			// si llega aca es porque todos los insumos del semielaborado tienen stock suficiente, entonces descuento stock y actualizo insumos
			for (InsumoProducto ip : productoSemiElaborado.getInsumosProducto()) {
				ip.getInsumo().setCantidad(ip.getInsumo().getCantidad() - ip.getCantidad());
				InsumoDAO.getInstancia().update(ip.getInsumo());
			}
		}

		//agregar a mesa ocupacion, factura, el producto y la cantidad que se agregaron (y actualizar el objeto mesaocupacion)
		if (mesaOcupacion.getFactura() == null) { //es el primer plato que se agrega y no tiene factura creada
			List<ItemFactura> itemsFactura = new ArrayList<>();
			itemsFactura.add(new ItemFactura(producto, cantidadProducto, producto.getPrecio()));
			Factura factura = new Factura(new Date(), producto.getComisionMozo(), false, producto.getPrecio(), itemsFactura, null);
			mesaOcupacion.setFactura(factura);
		} else { // ya tiene platos y por lo tanto tiene factura con al menos 1 item creada, se agrega el nuevo item y se actualizan los valores
			Factura factura = mesaOcupacion.getFactura();
			factura.setComisionMozo(factura.getComisionMozo() + producto.getComisionMozo());
			factura.getItemsFactura().add(new ItemFactura(producto, cantidadProducto, producto.getPrecio()));
			factura.setMonto(factura.getMonto() + producto.getPrecio());
			mesaOcupacion.setFactura(factura);
		}

		MesasOcupacionDAO.getInstancia().update(mesaOcupacion);
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
	public void cerrarMesa(Long idMesaOcupacion, Long idFormaDePago) throws RemoteException, BaseDeDatosException, MesaOcupacionNoExisteException, FormaDePagoNoExisteException {
		//buscar la forma de pago y sino tirar un FormaDePagoNoExisteException
		FormaPago formaDePago = FormaPagoDAO.getInstancia().getById(idFormaDePago);

		// buscar la mesa ocupacion y sino tirar un mesaOcupacionNoExiste exception
		MesaOcupacion mesaOcupacion = null;
		for (MesaOcupacion mo : MesasOcupacionDAO.getInstancia().getAll()) {
			if (mo.getId().equals(idMesaOcupacion)) {
				mesaOcupacion = mo;
				break;
			}
		}

		if (mesaOcupacion == null) throw new MesaOcupacionNoExisteException();

		//actualizar valores de MesaOcupacion
		mesaOcupacion.setFechaEgreso(new Date());
		mesaOcupacion.setProximaLiberarse(true);
		mesaOcupacion.getFactura().setCobrado(true);
		mesaOcupacion.getFactura().setFormaPago(formaDePago);
		mesaOcupacion.getFactura().setFecha(new Date());
		// liberar mesas del sector salon
		for (Mesa mesa : mesaOcupacion.getMesaItems()) mesa.setOcupada(false);

		//actualizar el empleado con la comision
		mesaOcupacion.getEmpleado().getComisiones().add(new Comision(mesaOcupacion.getFactura().getComisionMozo(), new Date()));

		MesasOcupacionDAO.getInstancia().update(mesaOcupacion);

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
