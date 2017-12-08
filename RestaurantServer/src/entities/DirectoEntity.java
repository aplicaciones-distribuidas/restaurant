package entities;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
@DiscriminatorValue("directo")
public class DirectoEntity extends ProductoEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1208157806455955532L;
	
	@OneToOne
	private InsumoProductoEntity insumoProducto;

	public DirectoEntity(String rubro, int caducidad, float comisionMozo, Date fecha, float precio, AreaEntity area, InsumoProductoEntity insumoProducto) {
		super(rubro, caducidad, comisionMozo, fecha, precio, area);
		this.insumoProducto = insumoProducto;
	}
	
	public DirectoEntity() {}

	public InsumoProductoEntity getInsumoProducto() {
		return insumoProducto;
	}

}
