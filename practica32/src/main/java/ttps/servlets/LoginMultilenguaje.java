package ttps.servlets;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginMultilenguaje
 */
@WebServlet("/login")
public class LoginMultilenguaje extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginMultilenguaje() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String idioma = (String) request.getAttribute("lenguajePreferido");
		if (idioma.length()>2) {
			idioma = idioma.substring(0,2);
		}
		// Ruta del archivo de configuración (ajusta esto según tu estructura de proyecto)
        String filePath = "/home/leandro/eclipse-workspaceJEE/practica32/src/main/resources/textos_"+idioma+".properties";

        // Obtén la ruta real del archivo en el servidor
        String realPath = getServletContext().getRealPath(filePath);
        System.out.println("UBICACION "+realPath);

        // Lee el archivo de configuración y almacena los valores en un mapa
        Map<String, String> configMap = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("=");
                if (parts.length == 2) {
                    configMap.put(parts[0], parts[1]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<html><head><title>"+configMap.get("titulo")+"</title></head><body>");
        out.println("<h1>"+configMap.get("titulo")+"</h1>");
        out.println("<form method=\"post\" action=\"LoginServlet\">");
        out.println(configMap.get("labelusuario")+": <input type=\"text\" name=\"username\"><br>");
        out.println(configMap.get("labelpassword")+": <input type=\"password\" name=\"password\"><br>");
        out.println("<input type=\"submit\" value="+configMap.get("labelbutton")+">");
        out.println("</form>");
        out.println("</body></html>");

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
