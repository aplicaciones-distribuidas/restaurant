package entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mesas")
public class MesaEntity implements Serializable {
	private static final long serialVersionUID = -6292230881349650925L;

	@Id
	@Column(name = "numero")
	private int numero;

	@Column(name = "ocupada")
	private boolean ocupada;

	public MesaEntity() {
	}

	public MesaEntity(int numero, boolean ocupada) {
		this.numero = numero;
		this.ocupada = ocupada;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public boolean isOcupada() {
		return ocupada;
	}

	public void setOcupada(boolean ocupada) {
		this.ocupada = ocupada;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + numero;
		result = prime * result + (ocupada ? 1231 : 1237);
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
		if (numero != other.numero)
			return false;
		if (ocupada != other.ocupada)
			return false;
		return true;
	}
}
