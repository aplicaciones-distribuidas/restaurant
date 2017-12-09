package business_delegate;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

import dto.MesaOcupacionView;
import dto.MesaView;
import excepciones.BaseDeDatosException;
import excepciones.ConexionException;
import excepciones.NoHayMesasDisponiblesException;
import excepciones.SucursalNoExisteException;
import interfaces.NegocioTDA;

public class BusinessDelegate {
	private static BusinessDelegate instancia;

	NegocioTDA remoteObject;

	private BusinessDelegate() throws ConexionException {
		try {
			remoteObject = (NegocioTDA) Naming.lookup("//localhost/restaurant");
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			e.printStackTrace();
			throw new ConexionException();
		}
	}

	public static BusinessDelegate getInstancia() throws ConexionException {
		if (instancia == null)
			instancia = new BusinessDelegate();
		return instancia;
	}

	public List<MesaView> mesasDisponibles(String sucursal, int cantPersonas) throws ConexionException,
			SucursalNoExisteException, BaseDeDatosException {
		try {
			return remoteObject.mesasDisponibles(sucursal, cantPersonas);
		} catch (RemoteException e) {
			e.printStackTrace();
			throw new ConexionException();
		}
	}

	public List<MesaOcupacionView> mesasOcupadas(String sucursal) throws ConexionException, SucursalNoExisteException,
			BaseDeDatosException {
		try {
			return remoteObject.mesasOcupadas(sucursal);
		} catch (RemoteException e) {
			e.printStackTrace();
			throw new ConexionException();
		}
	}

	public MesaOcupacionView abrirMesa(String sucursal, int cantPersonas, int idEmpleado) throws ConexionException,
			NoHayMesasDisponiblesException, BaseDeDatosException {
		try {
			return remoteObject.abrirMesa(sucursal, cantPersonas, idEmpleado);
		} catch (RemoteException e) {
			e.printStackTrace();
			throw new ConexionException();
		}
	}

	public void reservarMesa(String sucursal, int cantPersonas, Date fecha) throws ConexionException,
			NoHayMesasDisponiblesException, BaseDeDatosException {
		try {
			remoteObject.reservar(sucursal, cantPersonas, fecha);
		} catch (RemoteException e) {
			e.printStackTrace();
			throw new ConexionException();
		}
	}

}
