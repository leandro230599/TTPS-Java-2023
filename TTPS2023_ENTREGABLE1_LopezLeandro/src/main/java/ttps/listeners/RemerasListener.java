package ttps.listeners;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

/**
 * Application Lifecycle Listener implementation class RemerasListener
 *
 */
public class RemerasListener implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public RemerasListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce)  { 
         int cantRemeras = Integer.valueOf(
        		 						  sce.getServletContext()
        		 						  	.getInitParameter("cantidadRemeras")
        		 						  );
         ServletContext contexto = sce.getServletContext();
         contexto.setAttribute("cantidadRemeras", cantRemeras);
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce)  { 
         // TODO Auto-generated method stub
    }
	
}
