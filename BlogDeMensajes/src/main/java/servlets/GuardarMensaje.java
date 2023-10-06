package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import clasesDAO.FactoryDAO;
import clasesDAO.MensajeDAO;
import clasesDeObjetosDelSistema.Mensaje;

/**
 * Servlet implementation class GuardarMensaje
 */
public class GuardarMensaje extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GuardarMensaje() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String msj = request.getParameter("mensaje");
		String email = request.getParameter("email");
		MensajeDAO mDAO = FactoryDAO.getMensajeDAO();
		mDAO.guardar(new Mensaje(msj, email));
		response.sendRedirect("http://localhost:8080/BlogDeMensajes/VisualizarMensajes");
	}

}
