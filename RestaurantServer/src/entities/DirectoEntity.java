package entities;

import java.util.Date;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("directo")
public class DirectoEntity extends ProductoEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1208157806455955532L;

	public DirectoEntity(String rubro, int caducidad, float comisionMozo, Date fecha, float precio,
			List<InsumoProductoEntity> insumoProducto, AreaEntity area) {
		super(rubro, caducidad, comisionMozo, fecha, precio, insumoProducto, area);
	}
	
	public DirectoEntity() {}

}
