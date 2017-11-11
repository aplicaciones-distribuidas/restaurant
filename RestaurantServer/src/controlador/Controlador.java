package controlador;

import java.util.ArrayList;
import java.util.List;

import dao.MesasDAO;
import excepciones.BaseDeDatosException;
import excepciones.MesaNoExisteException;
import negocio.Mesa;
import negocio.MesaCompuesta;
import negocio.MesaSimple;

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

	public Mesa buscarMesa(int numero) throws BaseDeDatosException, MesaNoExisteException {
		return MesasDAO.getInstancia().getByNumero(numero);
	}

	public void agregarMesa(int numero) throws BaseDeDatosException {
		Mesa m = new MesaSimple(numero);
		m.save();
	}

	public void cargarDatos() throws BaseDeDatosException {
		cargarMesas();
	}

	private void cargarMesas() throws BaseDeDatosException {
		Mesa m1 = new MesaSimple(1);
		Mesa m2 = new MesaSimple(2);
		Mesa m3 = new MesaSimple(3);

		m1.save();
		m2.save();
		m3.save();

		List<Mesa> simples = new ArrayList<Mesa>();
		simples.add(m1);
		simples.add(m2);

		MesaCompuesta m4 = new MesaCompuesta(4);
		m4.setMesas(simples);
		m4.save();

		simples = new ArrayList<Mesa>();
		simples.add(m3);

		MesaCompuesta m5 = new MesaCompuesta(5);
		m5.setMesas(simples);
		m5.save();
	}
}
