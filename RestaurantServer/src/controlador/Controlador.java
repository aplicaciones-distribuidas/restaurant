package controlador;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dao.*;
import dto.ComisionView;
import dto.InsumoProductoView;
import dto.MesaOcupacionView;
import dto.MesaView;
import excepciones.*;
import negocio.*;

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

	public List<Sucursal> getSucursales() throws BaseDeDatosException {
		return SucursalDAO.getInstancia().getAll();
	}

	public List<SectorSalon> getSectoresSalon() throws BaseDeDatosException {
		return SectoresSalonDAO.getInstancia().getAll();
	}

	public List<Mesa> getMesas() throws BaseDeDatosException {
		return MesasDAO.getInstancia().getAll();
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

	public void crearPlatoDirecto(String rubro, int caducidad, float comisionMozo, Date fecha, float precio, String nombreArea, int idInsumo, float cantInsumo) throws AreaNoExisteException, InsumoNoExisteException, RubroNoExisteException, BaseDeDatosException {
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

	public void cargarDatos() throws BaseDeDatosException {
		Sucursal sucursal = new Sucursal("Belgrano", "Av. Juramento 1234", 100);
		sucursal.save();

		SectorSalon sA = new SectorSalon("A");
		sA.setSucursal(sucursal);
		sA.save();

		SectorSalon sB = new SectorSalon("B");
		sB.setSucursal(sucursal);
		sB.save();

		Rol mozo = new Rol(null, "mozo");
		mozo.save();

		List<Comision> comisiones = new ArrayList<>();

		Empleado empleado = new Empleado(null, "José", "Pérez", 10, mozo, comisiones, sA);
		empleado.save();

		Mesa m1 = new Mesa(null, 1, false, 8, sA);
		m1.save();

		Mesa m2 = new Mesa(null, 2, false, 6, sA);
		m2.save();

		Mesa m3 = new Mesa(null, 3, false, 4, sB);
		m3.save();
	}
}
