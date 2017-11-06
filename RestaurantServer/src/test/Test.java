package test;

import controlador.Controlador;
import server.Server;

public class Test {
	public static void main(String[] args) {
		try {
			Controlador.getInstancia().cargarDatos();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		try {
			System.out.println("Mesas:\n");
			for (Object m : Controlador.getInstancia().getMesas())
				System.out.println(m.toString());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		new Server();
	}
}
