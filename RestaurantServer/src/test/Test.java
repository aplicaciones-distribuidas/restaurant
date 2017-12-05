package test;

import controlador.Controlador;
import server.Server;

public class Test {
	public static void main(String[] args) {

		try {
			Controlador.getInstancia().cargarDatos();
		} catch (Exception e) {
			System.err.print("Error cargando datos");
			System.err.printf("Mensaje: %s\n", e.getMessage());
			e.printStackTrace();
		}

		try {
			System.out.println("Sucursales:");
			for (Object s : Controlador.getInstancia().getSucursales())
				System.out.println(s.toString());

			System.out.println("Sectores Salon:");
			for (Object s : Controlador.getInstancia().getSectoresSalon())
				System.out.println(s.toString());

			System.out.println("Mesas:");
			for (Object m : Controlador.getInstancia().getMesas())
				System.out.println(m.toString());

			System.out.println("Mesas disponibles para 5 personas en la sucursal 'Belgrano':");
			for (Object m : Controlador.getInstancia().getMesasDisponibles("Belgrano", 5))
				System.out.println(m.toString());
		} catch (Exception e) {
			System.err.println("Error corriendo tests");
			System.err.printf("Mensaje: %s\n", e.getMessage());
			e.printStackTrace();
		}

		new Server();
	}
}
