package business_delegate;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;

import dto.MesaView;
import excepciones.BaseDeDatosException;
import excepciones.ConexionException;
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
}
