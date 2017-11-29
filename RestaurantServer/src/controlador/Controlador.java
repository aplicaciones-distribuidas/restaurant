package controlador;

import java.util.ArrayList;
import java.util.List;

import excepciones.BaseDeDatosException;
import negocio.Mesa;
import negocio.MesaCompuesta;
import negocio.MesaSimple;
import negocio.SectorSalon;

public class Controlador {
	private static Controlador instancia;

	private Controlador() {
	}

	public static Controlador getInstancia() {
		if (instancia == null)
			instancia = new Controlador();
		return instancia;
	}

	/*public List<SectorSalon> getSectoresSalon() throws BaseDeDatosException {
		return SectoresSalonDAO.getInstancia().getAll();
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
		SectorSalon s1 = new SectorSalon("A");
		s1.save();

		SectorSalon s2 = new SectorSalon("B");
		s2.save();

		Mesa m1 = new MesaSimple(1);
		m1.setSectorSalon(s1);
		m1.save();

		Mesa m2 = new MesaSimple(2);
		m2.setSectorSalon(s1);
		m2.save();

		Mesa m3 = new MesaSimple(3);
		m3.setSectorSalon(s1);
		m3.save();

		List<Mesa> simples;

		MesaCompuesta m4 = new MesaCompuesta(4);
		m4.setSectorSalon(s2);
		simples = new ArrayList<Mesa>();
		simples.add(m1);
		simples.add(m2);
		m4.setMesas(simples);
		m4.save();

		MesaCompuesta m5 = new MesaCompuesta(5);
		m5.setSectorSalon(s2);
		simples = new ArrayList<Mesa>();
		simples.add(m3);
		m5.setMesas(simples);
		m5.save();
	}*/
}
