package negocio;

import java.util.Date;

public class Comision {
	
	private float monto;
	private Date fecha;
	
	
	public Comision(float monto, Date fecha) {
		super();
		this.monto = monto;
		this.fecha = fecha;
	}


	public float getMonto() {
		return monto;
	}


	public Date getFecha() {
		return fecha;
	}
	

}
