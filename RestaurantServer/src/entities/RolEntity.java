package entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
public class RolEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4187896705998037734L;
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String nombre;

	public RolEntity(String nombre) {
		this.nombre = nombre;
	}
	
	public RolEntity() {}

	public String getNombre() {
		return nombre;
	}
	

}
