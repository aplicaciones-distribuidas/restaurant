package negocio;

public class FormaPago {
	
	private String tipo;
	private int numeroCupon;
	private String banco;
	private float monto;
	
	
	public FormaPago(String tipo, int numeroCupon, String banco, float monto) {
		this.tipo = tipo;
		this.numeroCupon = numeroCupon;
		this.banco = banco;
		this.monto = monto;
	}


	public String getTipo() {
		return tipo;
	}


	public int getNumeroCupon() {
		return numeroCupon;
	}


	public String getBanco() {
		return banco;
	}


	public float getMonto() {
		return monto;
	}

}
