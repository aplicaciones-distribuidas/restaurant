package entities;

import java.util.Date;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("semiElaborado")
public class SemiElaboradoEntity extends ProductoEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8502227926996442892L;

	public SemiElaboradoEntity(String rubro, int caducidad, float comisionMozo, Date fecha, float precio, List<InsumoProductoEntity> insumoProducto, AreaEntity area) {
		super(rubro, caducidad, comisionMozo, fecha, precio, insumoProducto, area);
	}
	
	public SemiElaboradoEntity() {}
	
}
