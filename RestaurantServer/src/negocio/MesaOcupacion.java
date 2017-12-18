package negocio;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dao.MesasOcupacionDAO;
import dto.FacturaView;
import dto.MesaOcupacionView;
import dto.MesaView;
import excepciones.BaseDeDatosException;

public class MesaOcupacion {

	public MesaOcupacion(Long id, Date fechaIngreso, Date fechaEgreso, boolean proximaLiberarse, int cantidadPersonas,
						 List<Mesa> mesaItems, Factura factura, Empleado empleado) {
		this.id = id;
		this.fechaIngreso = fechaIngreso;
		this.fechaEgreso = fechaEgreso;
		this.proximaLiberarse = proximaLiberarse;
		this.cantidadPersonas = cantidadPersonas;
		this.mesaItems = mesaItems;
		this.factura = factura;
		this.empleado = empleado;
	}

	public MesaOcupacion(Date fechaIngreso, Date fechaEgreso, Boolean proximaLiberarse, Integer cantidadPersonas, List<Mesa> mesaItems, Factura factura, Empleado empleado) {
		this.fechaIngreso = fechaIngreso;
		this.fechaEgreso = fechaEgreso;
		this.proximaLiberarse = proximaLiberarse;
		this.cantidadPersonas = cantidadPersonas;
		this.mesaItems = mesaItems;
		this.factura = factura;
		this.empleado = empleado;
	}

	private Long id;
	private Date fechaIngreso;
	private Date fechaEgreso;
	private Boolean proximaLiberarse;
	private Integer cantidadPersonas;
	private List<Mesa> mesaItems;
	private Factura factura;
	private Empleado empleado;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public Date getFechaEgreso() {
		return fechaEgreso;
	}

	public void setFechaEgreso(Date fechaEgreso) {
		this.fechaEgreso = fechaEgreso;
	}

	public Boolean getProximaLiberarse() {
		return proximaLiberarse;
	}

	public void setProximaLiberarse(Boolean proximaLiberarse) {
		this.proximaLiberarse = proximaLiberarse;
	}

	public Integer getCantidadPersonas() {
		return cantidadPersonas;
	}

	public void setCantidadPersonas(Integer cantidadPersonas) {
		this.cantidadPersonas = cantidadPersonas;
	}

	public List<Mesa> getMesaItems() {
		return mesaItems;
	}

	public void setMesaItems(List<Mesa> mesaItems) {
		this.mesaItems = mesaItems;
	}

	public Factura getFactura() {
		return factura;
	}

	public void setFactura(Factura factura) {
		this.factura = factura;
	}

	public Empleado getEmpleado() {
		return this.empleado;
	}

	public void agregarProducto(Producto producto, int cantidadProducto) throws BaseDeDatosException {
		if (this.getFactura() == null) { //es el primer plato que se agrega y no tiene factura creada
			List<ItemFactura> itemsFactura = new ArrayList<>();
			itemsFactura.add(new ItemFactura(producto, cantidadProducto, producto.getPrecio()));
			Factura factura = new Factura(null, new Date(), producto.getComisionMozo()*cantidadProducto, false, producto.getPrecio()*cantidadProducto, itemsFactura, null);
			this.setFactura(factura);
		} else { // ya tiene platos y por lo tanto tiene factura con al menos 1 item creada, se agrega el nuevo item y se actualizan los valores
			Factura factura = this.getFactura();
			factura.setComisionMozo(factura.getComisionMozo() + (producto.getComisionMozo()*cantidadProducto));
			factura.getItemsFactura().add(new ItemFactura(producto, cantidadProducto, producto.getPrecio()));
			factura.setMonto(factura.getMonto() + (producto.getPrecio() * cantidadProducto));
			this.setFactura(factura);
		}
		this.update();
	}

	public void cerrar(FormaPago formaDePago) throws BaseDeDatosException {
		Factura factura = this.getFactura();
		factura.setCobrado(true);
		factura.setFormaPago(formaDePago);
		factura.setFecha(new Date());
		factura.update();

		this.setFechaEgreso(new Date());
		this.setProximaLiberarse(true);
		for (Mesa mesa : this.getMesaItems()) mesa.setOcupada(false);

		Comision comision = new Comision(this.getEmpleado(), this.getFactura().getComisionMozo());
		comision.save();

		this.update();
	}

	public MesaOcupacionView toView() {
		List<MesaView> mesas = new ArrayList<MesaView>();
		for (Mesa mesa : this.mesaItems) {
			mesas.add(mesa.toView());
		}
		return new MesaOcupacionView(this.id, mesas, this.factura != null ? new FacturaView(this.factura.getMonto()) : null);
	}

	@Override
	public String toString() {
		return String.format("MesaOcupacion [id => %d, cantidadPersonas => %s]", this.getId(), this
				.getCantidadPersonas());
	}

	public void save() throws BaseDeDatosException {
		this.id = MesasOcupacionDAO.getInstancia().save(this);
	}

	public void update() throws BaseDeDatosException {
		MesasOcupacionDAO.getInstancia().update(this);
	}

	public void saveWithoutSectorMesa() throws BaseDeDatosException {
		this.id = MesasOcupacionDAO.getInstancia().saveWithoutSectorMesa(this);
	}

}
