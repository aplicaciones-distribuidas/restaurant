package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Controller() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 * response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		// String opcion = request.getParameter("opcion");
		// if (opcion == null) {
		RequestDispatcher rd = request.getRequestDispatcher("/AltaAlumno.jsp");
		rd.forward(request, response);
		// }
		// else
		// if(opcion.equals("ver")){
		// String nom = request.getParameter("nombre");
		// String doc = request.getParameter("documento");
		// AlumnoDTO alumno = new AlumnoDTO(null,nom,doc);
		// try {
		// BusinessDelegate.getInstance().agregarAlumno(alumno);
		// } catch (AlumnoException e1) {
		// // TODO Auto-generated catch block
		// e1.printStackTrace();
		// } catch (SistemaException e1) {
		// // TODO Auto-generated catch block
		// e1.printStackTrace();
		// }
		// List<AlumnoDTO> alumnos = null;
		// try {
		// alumnos = BusinessDelegate.getInstance().getAllAlumnos();
		// request.setAttribute("alumnos", alumnos);
		// } catch (SistemaException e) {
		// System.out.println(e.getMessage());
		// }
		//
		// RequestDispatcher rd = request.getRequestDispatcher("/test.jsp");
		// rd.forward(request, response);
		// }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 * response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
