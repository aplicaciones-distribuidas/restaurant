package negocio;

import java.util.Date;
import java.util.List;

public class Factura {
	
	private Date fecha;
	private float comisionMozo;
	private boolean cobrado;
	private float monto;
	private List<ItemFactura> itemsFactura;
	private FormaPago formaPago;
	
	public Factura(Date fecha, float comisionMozo, boolean cobrado, float monto, List<ItemFactura> itemsFactura, FormaPago formaPago) {
		this.fecha = fecha;
		this.comisionMozo = comisionMozo;
		this.cobrado = cobrado;
		this.monto = monto;
		this.itemsFactura = itemsFactura;
		this.formaPago = formaPago;
	}

	public Date getFecha() {
		return fecha;
	}

	public float getComisionMozo() {
		return comisionMozo;
	}

	public boolean isCobrado() {
		return cobrado;
	}

	public float getMonto() {
		return monto;
	}

	public List<ItemFactura> getItemsFactura() {
		return itemsFactura;
	}

	public FormaPago getFormaPago() {
		return formaPago;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public void setComisionMozo(float comisionMozo) {
		this.comisionMozo = comisionMozo;
	}

	public void setCobrado(boolean cobrado) {
		this.cobrado = cobrado;
	}

	public void setMonto(float monto) {
		this.monto = monto;
	}

	public void setItemsFactura(List<ItemFactura> itemsFactura) {
		this.itemsFactura = itemsFactura;
	}

	public void setFormaPago(FormaPago formaPago) {
		this.formaPago = formaPago;
	}
	
}
