package test;

import java.util.List;

import controlador.Controlador;
import dao.EmpleadoDAO;
import dto.MesaView;
import negocio.Empleado;
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
			for (MesaView m : Controlador.getInstancia().getMesas())
				System.out.println("numero: " + m.getNumero() + ", identificador: " + m.getId());

			System.out.println("Mesas disponibles para 5 personas en la sucursal 'Belgrano':");
			for (MesaView m : Controlador.getInstancia().getMesasDisponibles("Belgrano", 5))
				System.out.println("numero: " + m.getNumero() + ", identificador: " + m.getId());
			
			List<Empleado> empleados = EmpleadoDAO.getInstancia().getAll(); 
			
			Controlador.getInstancia().abrirMesa("Belgrano", 4, empleados.get(0).getId());
		} catch (Exception e) {
			System.err.println("Error corriendo tests");
			System.err.printf("Mensaje: %s\n", e.getMessage());
			e.printStackTrace();
		}

		new Server();
	}
}
