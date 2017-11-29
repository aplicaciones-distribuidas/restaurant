package entities;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("simple")
public class MesaSimpleEntity extends MesaEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9109842758683801205L;

	public MesaSimpleEntity(int numero) {
		super(numero);
	}

	public MesaSimpleEntity(int numero, boolean ocupada, SectorSalonEntity sectorSalon, List<FacturaEntity> facturas) {
		super(numero, ocupada, sectorSalon, facturas);
	}
	
	public MesaSimpleEntity() {}

}
