package negocio;

import dao.MesasDAO;
import excepciones.BaseDeDatosException;

public abstract class Mesa {
	private int numero;
	private boolean ocupada;
	private SectorSalon sectorSalon;

	public Mesa(int numero) {
		this.numero = numero;
	}

	public Mesa(int numero, boolean ocupada, SectorSalon sectorSalon) {
		this.numero = numero;
		this.ocupada = ocupada;
		this.sectorSalon = sectorSalon;
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

	@Override
	public String toString() {
		return String.format("Mesa [numero => %d, sectorSalon => %s]", this.getNumero(), this.getSectorSalon()
				.getNombre());
	}

	public void save() throws BaseDeDatosException {
		MesasDAO.getInstancia().save(this);
	}
}
