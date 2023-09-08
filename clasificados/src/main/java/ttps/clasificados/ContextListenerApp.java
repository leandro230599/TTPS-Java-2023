package ttps.clasificados;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ContextListenerApp implements ServletContextListener{
	
	SitioClasificado sitio;
	
	public void contextInitialized(ServletContextEvent sce) {
		String name = sce.getServletContext().getInitParameter("name");
		String email = sce.getServletContext().getInitParameter("email");
		int nroTel = Integer.valueOf(sce.getServletContext().getInitParameter("nroTel"));
		sitio = new SitioClasificado(name,email,nroTel);
		ServletContext contexto = sce.getServletContext();
		contexto.setAttribute("sitioObject", sitio);
		System.out.println(sitio.getName());
		System.out.println(sitio.getEmail());
	}

}
