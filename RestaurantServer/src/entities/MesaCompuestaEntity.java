package entities;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("compuesta")
public class MesaCompuestaEntity extends MesaEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5058963836374858052L;
	
	private List<MesaEntity> mesaItems;

	public MesaCompuestaEntity(int numero) {
		super(numero);
	}

	public MesaCompuestaEntity(int numero, boolean ocupada, SectorSalonEntity sectorSalon, List<MesaEntity> mesaItems, List<FacturaEntity> facturas) {
		super(numero, ocupada, sectorSalon, facturas);
		this.mesaItems = mesaItems;
	}
	
	public MesaCompuestaEntity() {}

	public List<MesaEntity> getMesaItems() {
		return mesaItems;
	}

}
