package ttps.clasificados;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Menu
 */
@WebServlet("/Menu")
public class Menu extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Menu() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Usuario user = (Usuario) request.getAttribute("usuario");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String msjAdmin = "Listar Usuarios Publicadores, ABM Administradores, Ver Estadísticas";
		String msjPubli = "Actualizar Datos de Contacto, ABM de Publicaciones, Contestar Consultas";
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html><head>");
		if ((user == null) || !user.getPassword().equals(password)) {
			out.println("<title> Ocurrió un Error </title>");
			out.println("</head><body>");
			out.println("<a href=\"http://localhost:8080/clasificados/login.html\">Pagina de login</a>");
			out.println("</body></html>");
		} else {
			out.println("<title> "+(user.isEsAdmin() ? "Administrador" : "Publicador")+" </title>");
			out.println("</head><body>");
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Encabezado");
			if (dispatcher != null) {
				dispatcher.include(request, response); 
			}
			out.println("<h1> "+(user.isEsAdmin() ? "Administrador" : "Publicador")+" </h1>");
			out.println("<h4> "+(user.isEsAdmin() ? msjAdmin : msjPubli)+" </h4>");
			out.println("</body></html>");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
