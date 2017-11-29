package entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "comisiones")
public class ComisionEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5705275033395515062L;

	@Id
	@GeneratedValue
	private Long id;
	
	private float monto;
	private Date fecha;
	
	
	public ComisionEntity(float monto, Date fecha) {
		this.monto = monto;
		this.fecha = fecha;
	}
	
	public ComisionEntity() {}


	public float getMonto() {
		return monto;
	}


	public Date getFecha() {
		return fecha;
	}
	

}
