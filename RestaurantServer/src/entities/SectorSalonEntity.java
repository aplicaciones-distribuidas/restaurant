package entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "sectores_salon")
public class SectorSalonEntity implements Serializable {
	private static final long serialVersionUID = 3194137122676407785L;

	public SectorSalonEntity() {
	}

	public SectorSalonEntity(String nombre, List<MesaEntity> mesas) { // TODO: add `List<Empleado> empleados`
		this.nombre = nombre;
		this.mesas = mesas;
	}

	@Id
	@Column(name = "nombre")
	private String nombre;

	@OneToMany(mappedBy = "sectorSalon", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<MesaEntity> mesas;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<MesaEntity> getMesas() {
		return mesas;
	}

	public void setMesas(List<MesaEntity> mesas) {
		this.mesas = mesas;
	}
}
