package business_delegate;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import dto.MesaView;
import excepciones.BaseDeDatosException;
import excepciones.ConexionException;
import interfaces.MesasTDA;

public class BusinessDelegate {
	private static BusinessDelegate instancia;

	MesasTDA mesasRemoteObject;

	private BusinessDelegate() throws ConexionException {
		try {
			mesasRemoteObject = (MesasTDA) Naming.lookup("//localhost/mesas");
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

	public void agregarMesa(int numero) throws ConexionException, BaseDeDatosException {
		MesaView mesa = new MesaView();
		mesa.setNumero(numero);

		try {
			mesasRemoteObject.agregarMesa(mesa);
		} catch (RemoteException e) {
			e.printStackTrace();
			throw new ConexionException();
		}
	}
}
