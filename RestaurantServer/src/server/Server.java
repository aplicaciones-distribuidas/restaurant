package server;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

import interfaces.NegocioTDA;
import rmi.NegocioManager;

public class Server {

	NegocioTDA remoteObject;

	public Server() {
		start();
	}

	private void start() {
		try {
			LocateRegistry.createRegistry(1099);
			remoteObject = new NegocioManager();
			Naming.rebind("//localhost/restaurant", remoteObject);
			System.out.println("Binded to //localhost/restaurant");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
