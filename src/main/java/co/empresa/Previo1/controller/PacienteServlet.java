package co.empresa.Previo1.controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.empresa.Previo1.dao.PacienteDao;
import co.empresa.Previo1.dao.PacienteDaoPostgreSQL;
import co.empresa.Previo1.modelo.Paciente;

/**
 * Servlet implementation class UsuarioServlet
 */
@WebServlet("/")
public class PacienteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private PacienteDao pacienteDao;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PacienteServlet() {
		super();
	}

 
	 
	// inicializar el objeto usuarioDao
	public void init(ServletConfig config) throws ServletException {
		this.pacienteDao = new PacienteDaoPostgreSQL();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	
	//RECIBE LAS PETCIONES
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String action = request.getServletPath(); // capturar de la peticion el getServletPath 
		
		try {
			switch (action) {
			case "/new":
				showNewForm(request, response);
				break;
			case "/insert":
				try {
					insertarUsuario(request, response);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ServletException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case "/delete":
				eliminarUsuario(request, response);
				break;
			case "/edit":
				showEditForm(request, response);
				break;
			case "/update":
				try {
					actualizarUsuario(request, response);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ServletException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			default:
				listUsuarios(request, response);
				break;
			}
		} catch (SQLException e) {
			throw new ServletException(e);
		}

		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
	
	//LLAMAR AL NUEVO USUARIO, CADA QUE REGISTREMOS UN USUARIO 
	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RequestDispatcher dispatcher = request.getRequestDispatcher("paciente.jsp"); // LE PEDIMOS ACCESO A LA PAGINA 
		dispatcher.forward(request, response);// REDIRECCIONA HACIA EL RECURSO QUE SELECCIONEMOS
	}

	// EL REQUEST TRAE TODA LA INFORMACION DELONJETO USUARIO
	private void insertarUsuario(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException,ServletException, ParseException {
		String documento = request.getParameter("documento");
		String nombre = request.getParameter("nombre");
		String apellido = request.getParameter("apellido");
		String email = request.getParameter("email");
		String genero = request.getParameter("genero");
		String fechaNacimiento = request.getParameter("fechanacimiento");
		String telefono = request.getParameter("telefono");
		String direccion = request.getParameter("direccion");
		String peso = request.getParameter("peso");
		String estatura = request.getParameter("estatura");

		float floatPeso = Float.parseFloat(peso);
		float floatEstatura = Float.parseFloat(estatura);

		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		Date fecha = (Date) formato.parse(fechaNacimiento);
		
		
		Paciente paciente = new Paciente(documento,nombre,apellido,email,genero,fecha,telefono,direccion,floatPeso,floatEstatura);

		pacienteDao.insert(paciente);// INCERTA EN LA BASE DE DATOS
		response.sendRedirect("list"); // REDIRECCIONAMOS A UNA LOCALIZACIÓN
	}
	
	//EDITAR USUARIO 
	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Paciente pacienteActual = pacienteDao.select(id);
		request.setAttribute("paciente", pacienteActual);
		RequestDispatcher dispatcher = request.getRequestDispatcher("paciente.jsp");
		dispatcher.forward(request, response);
	}

	private void actualizarUsuario(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException,ServletException, ParseException {

		int id = Integer.parseInt(request.getParameter("id"));

		String documento = request.getParameter("documento");
		String nombre = request.getParameter("nombre");
		String apellido = request.getParameter("apellido");
		String email = request.getParameter("email");
		String genero = request.getParameter("genero");
		String fechaNacimiento = request.getParameter("fechanacimiento");
		String telefono = request.getParameter("telefono");
		String direccion = request.getParameter("direccion");
		String peso = request.getParameter("peso");
		String estatura = request.getParameter("estatura");
		

		float floatPeso = Float.parseFloat(peso);
		float floatEstatura = Float.parseFloat(estatura);

		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		Date fecha = (Date) formato.parse(fechaNacimiento);

		
		Paciente paciente = new Paciente(documento,nombre,apellido,email,genero,fecha,telefono,direccion,floatPeso,floatEstatura);

		
		pacienteDao.update(paciente);
		response.sendRedirect("list");
	}

	private void eliminarUsuario(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException ,ServletException{
		int id = Integer.parseInt(request.getParameter("id"));
		pacienteDao.delete(id);
		response.sendRedirect("list");
	}
	
	
	private void listUsuarios(HttpServletRequest request, HttpServletResponse response)
		    throws SQLException, IOException, ServletException {
		        List <Paciente> listPacientes = pacienteDao.selectAll();
		        request.setAttribute("listPacientes", listPacientes);
		        RequestDispatcher dispatcher = request.getRequestDispatcher("paciente.jsp");
		        dispatcher.forward(request, response);
		    }

}
