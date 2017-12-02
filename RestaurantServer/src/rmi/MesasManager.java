package rmi;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import controlador.Controlador;
import dto.MesaView;
import excepciones.BaseDeDatosException;
import interfaces.NegocioTDA;

public class MesasManager extends UnicastRemoteObject implements NegocioTDA, Serializable {
	public MesasManager() throws RemoteException {
		super();
	}

	private static final long serialVersionUID = 3548218673814294625L;

	public void agregarMesa(MesaView mesa) throws BaseDeDatosException, RemoteException {
		Controlador.getInstancia().agregarMesa(mesa.getNumero());
		System.out.println("Mesa agregada: " + mesa.getNumero()); // TODO: remove
	}
}
