package excepciones;

public class NoHayMesasDisponiblesException extends Exception {
	private static final long serialVersionUID = 7578195966873156954L;

	public String getMessage() {
		return "No hay mesas disponibles";
	}
}
