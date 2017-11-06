package controlador;

import java.util.List;

import dao.MesasDAO;
import excepciones.BaseDeDatosException;
import excepciones.MesaNoExisteException;
import negocio.Mesa;

public class Controlador {
	private static Controlador instancia;

	private Controlador() {
	}

	public static Controlador getInstancia() {
		if (instancia == null)
			instancia = new Controlador();
		return instancia;
	}

	public List<Mesa> getMesas() throws BaseDeDatosException {
		return MesasDAO.getInstancia().getAll();
	}

	private Mesa buscarMesa(int numero) throws BaseDeDatosException, MesaNoExisteException {
		return MesasDAO.getInstancia().getByNumero(numero);
	}

	public void agregarMesa(int numero) throws BaseDeDatosException {
		Mesa m = new Mesa(numero);
		m.save();
	}

	public void cargarDatos() throws BaseDeDatosException {
		cargarMesas();
	}

	private void cargarMesas() throws BaseDeDatosException {
		this.agregarMesa(10);
		this.agregarMesa(20);
		this.agregarMesa(30);
	}
}
