package entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "mesas")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo", discriminatorType = DiscriminatorType.STRING)
public abstract class MesaEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3970956696784630019L;
	
	@Id
	@GeneratedValue
	private Long id;
	
	private int numero;
	private boolean ocupada;
	@OneToOne
	private SectorSalonEntity sectorSalon;
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "idMesa")
	private List<FacturaEntity> facturas;

	public MesaEntity(int numero) {
		this.numero = numero;
	}

	public MesaEntity(int numero, boolean ocupada, SectorSalonEntity sectorSalon, List<FacturaEntity> facturas) {
		this.numero = numero;
		this.ocupada = ocupada;
		this.sectorSalon = sectorSalon;
		this.facturas = facturas;
	}
	
	public MesaEntity() {}

	public boolean getOcupada() {
		return this.ocupada;
	}

	public int getNumero() {
		return numero;
	}

	public SectorSalonEntity getSectorSalon() {
		return sectorSalon;
	}

	public List<FacturaEntity> getFacturas() {
		return facturas;
	}
}
