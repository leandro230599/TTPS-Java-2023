package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import clasesDAO.FactoryDAO;
import clasesDAO.MensajeDAO;
import clasesDeObjetosDelSistema.Mensaje;

/**
 * Servlet implementation class VisualizarMensajes
 */
public class VisualizarMensajes extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public VisualizarMensajes() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		MensajeDAO mDAO = FactoryDAO.getMensajeDAO();
		List<Mensaje> listaMensaje = mDAO.cargar();
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html><body>");
		if (!listaMensaje.isEmpty()) {
			for (Mensaje mensaje : listaMensaje) {
				out.println("<h3> " + mensaje.getTexto() + " de: "+mensaje.getEmail()+"</h3>");
			}			
		} else {
			out.println("<h3> No hay mensaje</h3>");
		}
		out.println("<a href=\"http://localhost:8080/BlogDeMensajes/agregarMensaje.html\">Agregar mensaje</a>");
		out.print("</body></html>");
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
