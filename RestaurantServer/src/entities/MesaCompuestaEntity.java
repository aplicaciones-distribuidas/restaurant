package entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorValue("compuesta")
public class MesaCompuestaEntity extends MesaEntity {
	private static final long serialVersionUID = -7940432510715392606L;

	public MesaCompuestaEntity() {
		super();
	}

	public MesaCompuestaEntity(int numero, boolean ocupada, SectorSalonEntity sectorSalon, List<MesaEntity> mesas) {
		super(numero, ocupada, sectorSalon);
		this.mesas = mesas;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(
		name = "mesas_compuestas_simples",
		joinColumns = {
			@JoinColumn(table = "mesas", name = "mesa_compuesta_numero", referencedColumnName = "numero")
		},
		inverseJoinColumns = {
			@JoinColumn(table = "mesas", name = "mesa_simple_numero", referencedColumnName = "numero")
		}
	)
	private List<MesaEntity> mesas;

	public List<MesaEntity> getMesas() {
		return mesas;
	}

	public void setMesas(List<MesaEntity> mesas) {
		this.mesas = mesas;
	}
}
