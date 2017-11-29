package negocio;

import java.util.Date;
import java.util.List;

public class MesaOcupacion {

	public MesaOcupacion(List<Mesa> mesaItems, Factura factura) {
		this.mesaItems = mesaItems;
	}

	private Long id;
	private Date fechaIngreso;
	private Date fechaEgreso;
	private Boolean proximaLiberarse;
	private Integer cantidadPersonas;
	private List<Mesa> mesaItems;
	private Factura factura;

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

	@Override
	public String toString() {
		return String.format("MesaOcupacion [id => %d, cantidadPersonas => %s]", this.getId(), this
				.getCantidadPersonas());
	}

}
