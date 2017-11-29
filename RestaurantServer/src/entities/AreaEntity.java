package entities;

import java.io.Serializable;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name = "areas")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo", discriminatorType = DiscriminatorType.STRING)
public abstract class AreaEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2404141548058946672L;
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String nombre;
	
	public AreaEntity(String nombre) {
		this.nombre = nombre;
	}
	
	public AreaEntity() {}
	
	public String getNombre() {
		return nombre;
	}

}
