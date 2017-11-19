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
	
}
