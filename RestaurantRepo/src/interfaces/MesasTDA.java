package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

import dto.MesaView;
import excepciones.MesaYaExisteException;

public interface MesasTDA extends Remote {
	public void agregarMesa(MesaView mesa) throws MesaYaExisteException, RemoteException;
}
