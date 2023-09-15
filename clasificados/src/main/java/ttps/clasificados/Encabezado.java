package ttps.clasificados;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Encabezado
 */
@WebServlet("/Encabezado")
public class Encabezado extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    // ServletContext contexto = this.getServletContext();
    SitioClasificado sitio;
	
    public Encabezado() {
        super();
        // sitio = (SitioClasificado) this.getServletContext().getAttribute("sitioObject");
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ServletContext contexto = request.getServletContext();
		SitioClasificado sitio = (SitioClasificado) contexto.getAttribute("sitioObject");
		PrintWriter out = response.getWriter();
        // System.out.println("PROBANDO "+sitio.getEmail());
        out.println("<header> <h1> Probando INCLUDE "+sitio.getName()+"</h1> </header>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
