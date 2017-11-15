package negocio;

public class MesaSimple extends Mesa {

	public MesaSimple(int numero) {
		super(numero);
	}

	public MesaSimple(int numero, boolean ocupada, SectorSalon sectorSalon) {
		super(numero, ocupada, sectorSalon);
	}

	@Override
	public String toString() {
		return String.format("MesaSimple [numero => %d], sectorSalon => %s]", this.getNumero(), this.getSectorSalon()
				.getNombre());
	}

}
