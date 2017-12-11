package controlador;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dao.AreaDAO;
import dao.EmpleadoDAO;
import dao.FormaPagoDAO;
import dao.InsumoDAO;
import dao.MesasDAO;
import dao.MesasOcupacionDAO;
import dao.ProductoDAO;
import dao.SectoresSalonDAO;
import dao.SucursalDAO;
import dto.*;
import excepciones.AreaNoExisteException;
import excepciones.BaseDeDatosException;
import excepciones.EmpleadoNoExisteException;
import excepciones.FormaDePagoNoExisteException;
import excepciones.InsumoNoExisteException;
import excepciones.MesaNoExisteException;
import excepciones.MesaOcupacionNoExisteException;
import excepciones.NoHayMesasDisponiblesException;
import excepciones.ProductoNoExisteException;
import excepciones.ProductoSinStockException;
import excepciones.RubroNoExisteException;
import excepciones.SucursalNoExisteException;
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
import negocio.Proveedor;
import negocio.Rol;
import negocio.SectorSalon;
import negocio.SemiElaborado;
import negocio.Sucursal;

public class Controlador {
	private static Controlador instancia;

	private Controlador() {
	}

	public static Controlador getInstancia() {
		if (instancia == null)
			instancia = new Controlador();
		return instancia;
	}

	public List<MesaView> getMesasDisponibles(String nombreSucursal, int cantPersonas) throws SucursalNoExisteException,
			BaseDeDatosException {
		List<MesaView> mesas = new ArrayList<MesaView>();
		Sucursal sucursal = SucursalDAO.getInstancia().getByNombre(nombreSucursal);
		for (Mesa mesa : sucursal.getMesasDisponibles(cantPersonas)) {
			mesas.add(mesa.toView());
		}
		return mesas;
	}

	public List<MesaOcupacionView> getMesasOcupadas(String nombreSucursal) throws SucursalNoExisteException,
			BaseDeDatosException {
		List<MesaOcupacionView> mesasOcupacion = new ArrayList<MesaOcupacionView>();
		Sucursal sucursal = SucursalDAO.getInstancia().getByNombre(nombreSucursal);
		for (MesaOcupacion mesa : sucursal.getMesasOcupadas()) {
			mesasOcupacion.add(mesa.toView());
		}
		return mesasOcupacion;
	}

	public List<SucursalView> getSucursales() throws BaseDeDatosException {
		List<Sucursal> sucursales = SucursalDAO.getInstancia().getAll();
		List<SucursalView> sucursalesView = new ArrayList<>();

		for (Sucursal sucursal : sucursales) {
			sucursalesView.add(new SucursalView(sucursal.getId(), sucursal.getNombre(), sucursal.getUbicacion(), sucursal.getCapacidad()));
		}

		return sucursalesView;
	}

	public List<SectorSalon> getSectoresSalon() throws BaseDeDatosException {
		return SectoresSalonDAO.getInstancia().getAll();
	}

	public List<EmpleadoView> getEmpleadosBySucursal(String nombreSucursal) throws BaseDeDatosException, SucursalNoExisteException {
		Sucursal sucursal = SucursalDAO.getInstancia().getByNombre(nombreSucursal);
		List<EmpleadoView> empleados = new ArrayList<>();
		for (Empleado empleado : sucursal.getEmpleados()) {
			empleados.add(empleado.toView());
		}
		return empleados;
	}

	public List<FormaPagoView> getFormasDePago() throws BaseDeDatosException {
		List<FormaPago> formasDePago = FormaPagoDAO.getInstancia().getAll();
		List<FormaPagoView> formasDePagoView = new ArrayList<>();
		for (FormaPago formaPago : formasDePago) {
			formasDePagoView.add(formaPago.toView());
		}
		return formasDePagoView;
	}

	public List<MesaView> getMesas() throws BaseDeDatosException {
		List<MesaView> mesas = new ArrayList<>();
		for (Mesa m : MesasDAO.getInstancia().getAll()) mesas.add(m.toView());
		return mesas;
	}

	public Mesa buscarMesa(int numero) throws BaseDeDatosException, MesaNoExisteException {
		return MesasDAO.getInstancia().getByNumero(numero);
	}

	public MesaOcupacionView abrirMesa(String nombreSucursal, int cantPersonas, Long idEmpleado) throws NoHayMesasDisponiblesException, BaseDeDatosException, SucursalNoExisteException, EmpleadoNoExisteException {
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
			for (Empleado e : ss.getEmpleados()) {
				if (e.getNombre().equals(empleado.getNombre()) && e.getApellido().equals(empleado.getApellido()))
					sectoresSalonEmpleado.add(ss);
			}
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
		MesasOcupacionDAO.getInstancia().saveWithoutSectorMesa(mesaOcupacion);

		//update de mesas
		for (Mesa mesa : mesasDisponibles) MesasDAO.getInstancia().update(mesa);

		return mesaOcupacion.toView();

	}

	public void agregarProductoAMesa(Long idMesaOcupacion, Long idProducto, int cantidadProducto) throws BaseDeDatosException, ProductoNoExisteException, InsumoNoExisteException, MesaOcupacionNoExisteException, ProductoSinStockException {
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

	public void cerrarMesa(Long idMesaOcupacion, Long idFormaDePago) throws BaseDeDatosException, MesaOcupacionNoExisteException, FormaDePagoNoExisteException {
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
		//mesaOcupacion.setFactura(new Factura(new Date(), 0, true, 0, null, formaDePago));
		mesaOcupacion.getFactura().setCobrado(true);
		mesaOcupacion.getFactura().setFormaPago(formaDePago);
		mesaOcupacion.getFactura().setFecha(new Date());
		// liberar mesas del sector salon
		for (Mesa mesa : mesaOcupacion.getMesaItems()) mesa.setOcupada(false);

		//actualizar el empleado con la comision
		mesaOcupacion.getEmpleado().getComisiones().add(new Comision(mesaOcupacion.getFactura().getComisionMozo(), new Date()));

		MesasOcupacionDAO.getInstancia().update(mesaOcupacion);
	}

	public List<ComisionView> getComisionesMozos(String nombreSucursal) throws BaseDeDatosException, SucursalNoExisteException {
		Sucursal sucursal = SucursalDAO.getInstancia().getByNombre(nombreSucursal);
		List<ComisionView> comisionView = new ArrayList<>();

		//filtro los sectores salon de la sucursal elegida
		List<SectorSalon> sectoresSalon = SectoresSalonDAO.getInstancia().getAll();
		List<SectorSalon> sectoresSalonDeLaSucursal = new ArrayList<>();
		for (SectorSalon ss : sectoresSalon) {
			if (ss.getSucursal().getNombre().equals(sucursal.getNombre())) sectoresSalonDeLaSucursal.add(ss);
		}
		List<Empleado> empleados = new ArrayList<>();
		for (SectorSalon ss : sectoresSalonDeLaSucursal) {
			empleados.addAll(ss.getEmpleados());
		}

		for (Empleado e : empleados) {
			float comision = 0;
			for (Comision c : e.getComisiones()) {
				comision += c.getMonto();
			}
			comisionView.add(new ComisionView(e.getNombre(), e.getApellido(), comision * e.getPorcentajeComision()));
		}

		return comisionView;
	}

	public void crearPlatoDirecto(String rubro, int caducidad, float comisionMozo, Date fecha, float precio, String nombreArea, Long idInsumo, float cantInsumo) throws AreaNoExisteException, InsumoNoExisteException, RubroNoExisteException, BaseDeDatosException {
		Area area = AreaDAO.getInstancia().getByNombre(nombreArea);
		Insumo insumo = InsumoDAO.getInstancia().getById(idInsumo);

		Directo productoDirecto = new Directo(null, rubro, caducidad, comisionMozo, fecha, precio, area, new InsumoProducto(cantInsumo, insumo));
		productoDirecto.save();
	}

	public void crearPlatoSemielaborado(String rubro, int caducidad, float comisionMozo, Date fecha, float precio, String nombreArea, List<InsumoProductoView> insumosView) throws AreaNoExisteException, BaseDeDatosException, InsumoNoExisteException {
		Area area = AreaDAO.getInstancia().getByNombre(nombreArea);
		List<InsumoProducto> insumos = new ArrayList<>();

		for (InsumoProductoView insumo : insumosView) {
			insumos.add(
					new InsumoProducto(insumo.getCantidad(), InsumoDAO.getInstancia().getById(insumo.getInsumoId()))
			);
		}

		SemiElaborado semiElaborado = new SemiElaborado(null, rubro, caducidad, comisionMozo, fecha, precio, insumos, area);
		semiElaborado.save();
	}

	public void cargarDatos() throws BaseDeDatosException, SucursalNoExisteException, NoHayMesasDisponiblesException, EmpleadoNoExisteException {
		Sucursal sucursal = new Sucursal("Belgrano", "Av. Juramento 1234", 100);
		sucursal.save();

		Sucursal sucursal2 = new Sucursal("Caballito", "Av. Pedro Goyena 432", 85);
		sucursal2.save();

		SectorSalon sA = new SectorSalon("A");
		sA.setSucursal(sucursal);
		sA.save();

		SectorSalon sB = new SectorSalon("B");
		sB.setSucursal(sucursal);
		sB.save();

		SectorSalon sZ = new SectorSalon("Z");
		sZ.setSucursal(sucursal2);
		sZ.save();

		Rol mozo = new Rol(null, "mozo");
		mozo.save();

		List<Comision> comisiones = new ArrayList<>();

		Empleado empleado = new Empleado(null, "José", "Pérez", 10, mozo, comisiones, sA);
		empleado.save();

		Empleado empleado2 = new Empleado(null, "Pepe", "González", 12, mozo, comisiones, sZ);
		empleado2.save();

		Mesa m1 = new Mesa(null, 1, false, 8, sA);
		m1.save();

		Mesa m2 = new Mesa(null, 2, false, 6, sA);
		m2.save();

		Mesa m3 = new Mesa(null, 3, false, 4, sB);
		m3.save();

		Area areaCocina = new Area(null, "cocina");
		areaCocina.save();
		Area areaBarra = new Area(null, "barra");
		areaBarra.save();
		Area areaCafeteria = new Area(null, "cafeteria");
		areaCafeteria.save();

		Proveedor proveedor1 = new Proveedor("proveedor 1", "43028730", "calle falsa 123");

		Insumo insumo1 = new Insumo("insumo 1", "arroz", 10, new Date(), new Date(), proveedor1, 15);
		Insumo insumo2 = new Insumo("insumo 2", "pure", 10, new Date(), new Date(), proveedor1, 15);
		Insumo insumo3 = new Insumo("insumo 3", "papas", 10, new Date(), new Date(), proveedor1, 15);

		InsumoProducto insumoProducto1 = new InsumoProducto(10, insumo1);
		InsumoProducto insumoProducto2 = new InsumoProducto(10, insumo2);
		InsumoProducto insumoProducto3 = new InsumoProducto(10, insumo3);

		Directo directo1 = new Directo(null, "plato", 10, 10, new Date(), 100, areaCocina, insumoProducto1);
		directo1.save();
		Directo directo2 = new Directo(null, "plato", 10, 10, new Date(), 100, areaBarra, insumoProducto2);
		directo2.save();
		Directo directo3 = new Directo(null, "plato", 10, 10, new Date(), 100, areaCafeteria, insumoProducto3);
		directo3.save();

		FormaPago fp = new FormaPago(null, "debito", 1, "santander", 10);
		fp.save();

		this.abrirMesa(sucursal.getNombre(), 3, empleado.getId());
	}
}
