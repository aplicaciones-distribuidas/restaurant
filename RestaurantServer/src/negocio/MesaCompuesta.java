package negocio;

import java.util.List;

public class MesaCompuesta extends Mesa {

	public MesaCompuesta(int numero) {
		super(numero);
	}

	public MesaCompuesta(int numero, boolean ocupada, SectorSalon sectorSalon, List<Mesa> mesaItems, List<Factura> facturas) {
		super(numero, ocupada, sectorSalon, facturas);
		this.mesaItems = mesaItems;
	}

	private List<Mesa> mesaItems;

	public List<Mesa> getMesaItems() {
		return mesaItems;
	}

	public void setMesaItems(List<Mesa> mesaItems) {
		this.mesaItems = mesaItems;
	}

	@Override
	public String toString() {
		return String.format("MesaCompuesta [numero => %d, sectorSalon => %s]", this.getNumero(), this.getSectorSalon()
				.getNombre());
	}

}
