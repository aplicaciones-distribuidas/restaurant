package entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("simple")
public class MesaSimpleEntity extends MesaEntity {
	private static final long serialVersionUID = -1129696116580168277L;

	public MesaSimpleEntity() {
		super();
	}

	public MesaSimpleEntity(int numero, boolean ocupada, SectorSalonEntity sectorSalon) {
		super(numero, ocupada, sectorSalon);
	}
}
