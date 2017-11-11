package negocio;

public class MesaSimple extends Mesa {

	public MesaSimple(int numero) {
		super(numero);
	}

	public MesaSimple(int numero, boolean ocupada) {
		super(numero, ocupada);
	}

	@Override
	public String toString() {
		return String.format("MesaSimple [numero => %d]", this.getNumero());
	}

}
