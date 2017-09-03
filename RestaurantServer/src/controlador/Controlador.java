package controlador;

import java.util.ArrayList;
import java.util.List;

import excepciones.MesaNoExisteException;
import excepciones.MesaYaExisteException;
import negocio.Mesa;

public class Controlador {
	private static Controlador instancia;
	private List<Mesa> mesas;

	private Controlador() {
		this.mesas = new ArrayList<Mesa>();
	}

	public static Controlador getInstancia() {
		if (instancia == null)
			instancia = new Controlador();
		return instancia;
	}

	public List<Mesa> getMesas() {
		return this.mesas;
	}

	private Mesa buscarMesa(int numero) throws MesaNoExisteException {
		for (Mesa m : this.mesas)
			if (m.esLaMesa(numero))
				return m;
		throw new MesaNoExisteException(numero);
	}

	public void agregarMesa(int numero) throws MesaYaExisteException {
		try {
			this.buscarMesa(numero);
			throw new MesaYaExisteException(numero);
		} catch (MesaNoExisteException e) {
			this.mesas.add(new Mesa(numero));
		}
	}

	public void cargarDatos() throws Exception {
		cargarMesas();
	}

	private void cargarMesas() throws Exception {
		this.mesas.add(new Mesa(1));
		this.mesas.add(new Mesa(2));
		this.mesas.add(new Mesa(3));
	}
}
