package dto;

import java.io.Serializable;

public class MesaView implements Serializable {
	private static final long serialVersionUID = -7716280088967048996L;
	private int numero;
	private boolean ocupada;

	public MesaView() {
		this.setNumero(0);
		this.setOcupada(false);
	}

	public MesaView(int numero, boolean ocupada) {
		this.setNumero(numero);
		this.setOcupada(ocupada);
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

	public String toString() {
		return this.numero + " - " + this.ocupada;
	}
}
