package entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "facturas")
public class FacturaEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -798692605392125434L;

	@Id
	@GeneratedValue
	private Long id;

	private Date fecha;
	private float comisionMozo;
	private boolean cobrado;
	private float monto;
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "idFactura")
	private List<ItemFacturaEntity> itemsFactura;
	@OneToOne
	private FormaPagoEntity formaPago;

	public FacturaEntity(Date fecha, float comisionMozo, boolean cobrado, float monto,
			List<ItemFacturaEntity> itemsFactura, FormaPagoEntity formaPago) {
		this.fecha = fecha;
		this.comisionMozo = comisionMozo;
		this.cobrado = cobrado;
		this.monto = monto;
		this.itemsFactura = itemsFactura;
		this.formaPago = formaPago;
	}

	public FacturaEntity() {
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

	public List<ItemFacturaEntity> getItemsFactura() {
		return itemsFactura;
	}

	public FormaPagoEntity getFormaPago() {
		return formaPago;
	}

}