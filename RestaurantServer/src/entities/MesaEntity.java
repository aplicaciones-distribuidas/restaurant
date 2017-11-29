package entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "mesas")
public class MesaEntity implements Serializable {
	private static final long serialVersionUID = 3970956696784630019L;

	@Id
	private int numero;
	private boolean ocupada;

	@OneToOne
	private SectorSalonEntity sectorSalon;

	public MesaEntity() {
	}

	public MesaEntity(int numero) {
		this.numero = numero;
	}

	public MesaEntity(int numero, boolean ocupada, SectorSalonEntity sectorSalon) {
		this.numero = numero;
		this.ocupada = ocupada;
		this.sectorSalon = sectorSalon;
	}

	public boolean isOcupada() {
		return this.ocupada;
	}

	public int getNumero() {
		return numero;
	}

	public SectorSalonEntity getSectorSalon() {
		return sectorSalon;
	}

}
