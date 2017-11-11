package negocio;

import dao.MesasDAO;
import excepciones.BaseDeDatosException;

public abstract class Mesa {
	private int numero;
	private boolean ocupada;

	public Mesa(int numero) {
		this.numero = numero;
	}

	public Mesa(int numero, boolean ocupada) {
		this.numero = numero;
		this.ocupada = ocupada;
	}

	public boolean esLaMesa(int numero) {
		return this.getNumero() == numero;
	}

	public void setOcupada(boolean ocupada) {
		this.ocupada = ocupada;
	}

	public boolean isOcupada() {
		return this.ocupada;
	}

	public int getNumero() {
		return numero;
	}

	@Override
	public String toString() {
		return String.format("Mesa [numero => %d]", this.getNumero());
	}

	public void save() throws BaseDeDatosException {
		MesasDAO.getInstancia().save(this);
	}
}
