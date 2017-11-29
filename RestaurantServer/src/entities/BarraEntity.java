package entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("barra")
public class BarraEntity extends AreaEntity {
	private static final long serialVersionUID = 6868187410802515197L;

	public BarraEntity(String nombre) {
		super(nombre);
	}

	public BarraEntity() {
	}

}
