package negocio;

import java.util.List;

import dao.MesasDAO;
import excepciones.BaseDeDatosException;

public abstract class Mesa {
	private int numero;
	private boolean ocupada;
	private SectorSalon sectorSalon;
	private List<Factura> facturas;

	public Mesa(int numero) {
		this.numero = numero;
	}

	public Mesa(int numero, boolean ocupada, SectorSalon sectorSalon, List<Factura> facturas) {
		this.numero = numero;
		this.ocupada = ocupada;
		this.sectorSalon = sectorSalon;
		this.facturas = facturas;
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

	public SectorSalon getSectorSalon() {
		return sectorSalon;
	}

	public void setSectorSalon(SectorSalon sectorSalon) {
		this.sectorSalon = sectorSalon;
	}

	public List<Factura> getFacturas() {
		return facturas;
	}

	@Override
	public String toString() {
		return String.format("Mesa [numero => %d, sectorSalon => %s]", this.getNumero(), this.getSectorSalon()
				.getNombre());
	}

	public void save() throws BaseDeDatosException {
		MesasDAO.getInstancia().save(this);
	}
}
