package server;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

import interfaces.MesasTDA;
import rmi.MesasManager;

public class Server {
	MesasTDA mesasRemoteObject;

	public Server() {
		start();
	}

	private void start() {
		try {
			LocateRegistry.createRegistry(1099);
			mesasRemoteObject = new MesasManager();
			Naming.rebind("//localhost/mesas", mesasRemoteObject);
			System.out.println("Binded to //localhost/mesas");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
