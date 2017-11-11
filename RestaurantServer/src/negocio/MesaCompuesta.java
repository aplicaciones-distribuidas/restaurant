package negocio;

import java.util.List;

public class MesaCompuesta extends Mesa {

	public MesaCompuesta(int numero) {
		super(numero);
	}

	public MesaCompuesta(int numero, boolean ocupada, List<Mesa> mesas) {
		super(numero, ocupada);
		this.mesas = mesas;
	}

	private List<Mesa> mesas;

	public List<Mesa> getMesas() {
		return mesas;
	}

	public void setMesas(List<Mesa> mesas) {
		this.mesas = mesas;
	}

	@Override
	public String toString() {
		return String.format("MesaCompuesta [numero => %d]", this.getNumero());
	}

}
