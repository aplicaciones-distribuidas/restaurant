package negocio;

public class Mesa {
	private int numero;
	private boolean ocupada;

	public Mesa(int numero) {
		this.numero = numero;
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

	public String toString() {
		return String.format("Mesa número: %d", this.numero);
	}
}
