package ttps.clasificados;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private List<Usuario> usuarios;

    /**
     * Default constructor. 
     */
    public Login() {
    	this.usuarios = new ArrayList<Usuario>();
    	this.usuarios.add(new Usuario("user1","123",false));
    	this.usuarios.add(new Usuario("user2","1234",false));
    	this.usuarios.add(new Usuario("user3","12345",true));
    	this.usuarios.add(new Usuario("user4","123456",false));
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
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		Usuario user = usuarios.stream()
								.filter(u -> u.getUsername().equals(username))
								.findFirst()
								.orElse(null);
		/*
		if ((user == null) || !user.getPassword().equals(password)) {
			response.sendRedirect("http://localhost:8080/clasificados/error.html");
			return;
		}
		if (user.isEsAdmin()) {
			response.sendRedirect("http://localhost:8080/clasificados/administrador.html");
		} else {
			response.sendRedirect("http://localhost:8080/clasificados/publicador.html");
		} */
		request.setAttribute("usuario", user);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Menu");
		if (dispatcher != null) {
			dispatcher.forward(request, response);
		}
			
	}

}
