package negocio;

import dao.MesasDAO;
import excepciones.BaseDeDatosException;

public class Mesa {
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

	public int getNumero() {
		return this.numero;
	}

	public void setOcupada(boolean ocupada) {
		this.ocupada = ocupada;
	}

	public boolean isOcupada() {
		return this.ocupada;
	}

	public SectorSalon getSectorSalon() {
		return this.sectorSalon;
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
