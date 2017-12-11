package excepciones;

public class FormaDePagoNoExisteException extends Exception {
	private static final long serialVersionUID = -7966528003211566284L;
	private static final String MESSAGE = "La forma de pago seleccionada no existe";

	public FormaDePagoNoExisteException() {
		super();
	}

	public String getMessage() {
		return MESSAGE;
	}
}
