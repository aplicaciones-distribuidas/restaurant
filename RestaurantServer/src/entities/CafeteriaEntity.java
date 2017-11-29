package entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("cafeteria")
public class CafeteriaEntity extends AreaEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1714078812709536589L;
	
	private int horasPreparacion;

	public CafeteriaEntity(String nombre, int horasPreparacion) {
		super(nombre);
		this.horasPreparacion = horasPreparacion;
	}
	
	public CafeteriaEntity() {}

	public int getHorasPreparacion() {
		return horasPreparacion;
	}

}
