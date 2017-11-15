package entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "mesas")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo", discriminatorType = DiscriminatorType.STRING)
public abstract class MesaEntity implements Serializable {
	private static final long serialVersionUID = -6292230881349650925L;

	public MesaEntity() {
	}

	public MesaEntity(int numero, boolean ocupada, SectorSalonEntity sectorSalon) {
		this.numero = numero;
		this.ocupada = ocupada;
		this.sectorSalon = sectorSalon;
	}

	@Id
	@Column(name = "numero")
	private int numero;

	@Column(name = "lugares")
	private int lugares;

	@Column(name = "activa")
	private boolean activa;

	@Column(name = "original")
	private boolean original;

	@Column(name = "ocupada")
	private boolean ocupada;

	@ManyToOne
	@JoinColumn(name = "sector_salon")
	private SectorSalonEntity sectorSalon;

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public int getLugares() {
		return lugares;
	}

	public void setLugares(int lugares) {
		this.lugares = lugares;
	}

	public boolean isActiva() {
		return activa;
	}

	public void setActiva(boolean activa) {
		this.activa = activa;
	}

	public boolean isOriginal() {
		return original;
	}

	public void setOriginal(boolean original) {
		this.original = original;
	}

	public boolean isOcupada() {
		return ocupada;
	}

	public void setOcupada(boolean ocupada) {
		this.ocupada = ocupada;
	}

	public SectorSalonEntity getSectorSalon() {
		return sectorSalon;
	}

	public void setSectorSalon(SectorSalonEntity sectorSalon) {
		this.sectorSalon = sectorSalon;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (activa ? 1231 : 1237);
		result = prime * result + lugares;
		result = prime * result + numero;
		result = prime * result + (ocupada ? 1231 : 1237);
		result = prime * result + (original ? 1231 : 1237);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MesaEntity other = (MesaEntity) obj;
		if (activa != other.activa)
			return false;
		if (lugares != other.lugares)
			return false;
		if (numero != other.numero)
			return false;
		if (ocupada != other.ocupada)
			return false;
		if (original != other.original)
			return false;
		return true;
	}
}
