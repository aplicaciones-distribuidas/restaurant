package controlador;

import java.util.List;

import dao.MesasDAO;
import dao.SectoresSalonDAO;
import excepciones.BaseDeDatosException;
import excepciones.MesaNoExisteException;
import negocio.Mesa;
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

	public List<SectorSalon> getSectoresSalon() throws BaseDeDatosException {
		return SectoresSalonDAO.getInstancia().getAll();
	}

	public List<Mesa> getMesas() throws BaseDeDatosException {
		return MesasDAO.getInstancia().getAll();
	}

	public Mesa buscarMesa(int numero) throws BaseDeDatosException, MesaNoExisteException {
		return MesasDAO.getInstancia().getByNumero(numero);
	}

	public void agregarMesa(int numero) throws BaseDeDatosException {
		Mesa m = new Mesa(numero);
		m.save();
	}

	public void cargarDatos() throws BaseDeDatosException {
		SectorSalon s1 = new SectorSalon("A");
		s1.save();

		SectorSalon s2 = new SectorSalon("B");
		s2.save();

		Mesa m1 = new Mesa(1);
		m1.setSectorSalon(s1);
		m1.save();

		Mesa m2 = new Mesa(2);
		m2.setSectorSalon(s1);
		m2.save();

		Mesa m3 = new Mesa(3);
		m3.setSectorSalon(s2);
		m3.save();
	}
}
