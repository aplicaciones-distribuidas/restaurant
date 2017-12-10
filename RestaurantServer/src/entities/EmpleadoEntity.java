package entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

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

	@ManyToOne
	private SectorSalonEntity sectorSalon;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "idEmpleado")
	private List<ComisionEntity> comisiones;

	public EmpleadoEntity(String nombre, String apellido, int porcentajeComision, RolEntity rol,
						  List<ComisionEntity> comisiones) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.porcentajeComision = porcentajeComision;
		this.rol = rol;
		this.comisiones = comisiones;
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
		return sectorSalon;
	}

	public void setSectorSalon(SectorSalonEntity sectorSalon) {
		this.sectorSalon = sectorSalon;
	}
}
