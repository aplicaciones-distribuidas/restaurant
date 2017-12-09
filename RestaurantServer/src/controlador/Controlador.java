package controlador;

import java.util.ArrayList;
import java.util.List;

import dao.MesasDAO;
import dao.SectoresSalonDAO;
import dao.SucursalDAO;
import dto.MesaOcupacionView;
import dto.MesaView;
import excepciones.BaseDeDatosException;
import excepciones.MesaNoExisteException;
import excepciones.SucursalNoExisteException;
import negocio.Mesa;
import negocio.MesaOcupacion;
import negocio.SectorSalon;
import negocio.Sucursal;

public class Controlador {
	private static Controlador instancia;

	private Controlador() {
	}

	public static Controlador getInstancia() {
		if (instancia == null)
			instancia = new Controlador();
		return instancia;
	}

	public List<MesaView> getMesasDisponibles(String nombreSucursal, int cantPersonas) throws SucursalNoExisteException,
			BaseDeDatosException {
		List<MesaView> mesas = new ArrayList<MesaView>();
		Sucursal sucursal = SucursalDAO.getInstancia().getByNombre(nombreSucursal);
		for (Mesa mesa : sucursal.getMesasDisponibles(cantPersonas)) {
			mesas.add(mesa.toView());
		}
		return mesas;
	}

	public List<MesaOcupacionView> getMesasOcupadas(String nombreSucursal) throws SucursalNoExisteException,
			BaseDeDatosException {
		List<MesaOcupacionView> mesasOcupacion = new ArrayList<MesaOcupacionView>();
		Sucursal sucursal = SucursalDAO.getInstancia().getByNombre(nombreSucursal);
		for (MesaOcupacion mesa : sucursal.getMesasOcupadas()) {
			mesasOcupacion.add(mesa.toView());
		}
		return mesasOcupacion;
	}

	public List<Sucursal> getSucursales() throws BaseDeDatosException {
		return SucursalDAO.getInstancia().getAll();
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

	public void cargarDatos() throws BaseDeDatosException {
		Sucursal sucursal = new Sucursal("Belgrano", "Av. Juramento 1234", 100);
		sucursal.save();

		SectorSalon sA = new SectorSalon("A");
		sA.setSucursal(sucursal);
		sA.save();

		SectorSalon sB = new SectorSalon("B");
		sB.setSucursal(sucursal);
		sB.save();

		Mesa m1 = new Mesa(null, 1, false, 8, sA);
		m1.save();

		Mesa m2 = new Mesa(null, 2, false, 6, sA);
		m2.save();

		Mesa m3 = new Mesa(null, 3, false, 4, sB);
		m3.save();
	}
}
