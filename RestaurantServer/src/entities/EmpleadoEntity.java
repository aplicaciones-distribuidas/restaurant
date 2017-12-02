package entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "empleados")
public class EmpleadoEntity implements Serializable {
	private static final long serialVersionUID = -7273500272703721840L;

	@Id
	@GeneratedValue
	private Long id;

	private String nombre;
	private String apellido;
	private int porcentajeComision;

	@OneToOne
	private RolEntity rol;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "idEmpleado")
	private List<ComisionEntity> comisiones;

	@OneToOne
	private SectorSalonEntity sectorSalon;

	public EmpleadoEntity(String nombre, String apellido, int porcentajeComision, RolEntity rol,
			List<ComisionEntity> comisiones, SectorSalonEntity sectorSalon) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.porcentajeComision = porcentajeComision;
		this.rol = rol;
		this.comisiones = comisiones;
		this.sectorSalon = sectorSalon;
	}

	public EmpleadoEntity() {
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public int getPorcentajeComision() {
		return porcentajeComision;
	}

	public RolEntity getRol() {
		return rol;
	}

	public List<ComisionEntity> getComisiones() {
		return comisiones;
	}

	public SectorSalonEntity getSectorSalon() {
		return this.sectorSalon;
	}

}
