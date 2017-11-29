package entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("cocina")
public class CocinaEntity extends AreaEntity {
	private static final long serialVersionUID = 1452440288449074824L;

	private int horasPreparacion;

	public CocinaEntity(String nombre, int horasPreparacion) {
		super(nombre);
		this.horasPreparacion = horasPreparacion;
	}

	public CocinaEntity() {
	}

	public int getHorasPreparacion() {
		return horasPreparacion;
	}

}
