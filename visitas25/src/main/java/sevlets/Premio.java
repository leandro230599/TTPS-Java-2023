package sevlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Premio
 */
public class Premio extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private int contador;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Premio() {
        super();
        contador = 0;
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html><body>");
		out.println("<h1> Hola que tal " + request.getParameter("fname") + " GET </h1>");
		out.print("</body></html>");
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		contador += 1;
		ServletConfig conf = getServletConfig();
		String mensaje = conf.getInitParameter("mensaje");
		PrintWriter out = response.getWriter();
		out.println("<html><body>");
		out.println("<h1>"+ mensaje.replace("@",request.getParameter("fname")).replace("#",Integer.toString(contador))+"</h1>");
		out.print("</body></html>");
		out.close();
	}

}
