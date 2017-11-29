package negocio;

import java.util.List;

public class MesaSimple extends Mesa {

	public MesaSimple(int numero) {
		super(numero);
	}

	public MesaSimple(int numero, boolean ocupada, SectorSalon sectorSalon, List<Factura> facturas) {
		super(numero, ocupada, sectorSalon, facturas);
	}

	@Override
	public String toString() {
		return String.format("MesaSimple [numero => %d], sectorSalon => %s]", this.getNumero(), this.getSectorSalon()
				.getNombre());
	}

}
