package ttps.filtros;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Locale.LanguageRange;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;

/**
 * Servlet Filter implementation class FiltroLenguajeCliente
 */
@WebFilter(
urlPatterns = "/*")
public class FiltroLenguajeCliente extends HttpFilter implements Filter {
       
    /**
     * @see HttpFilter#HttpFilter()
     */
	List<Locale> idiomasDisponible;
	
    public FiltroLenguajeCliente() {
        super();
        this.idiomasDisponible = List.of(new Locale("en","US"), new Locale("es","ES"), new Locale("es","US"));
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		HttpServletRequest req = (HttpServletRequest) request;
        
        // Obtengo el encabezado Accept-Language del navegador del cliente que hace la peticion
        String acceptLanguage = req.getHeader("Accept-Language");
        
        // Lo parseo para que la libreria LanguageRange le de un formato entendible
        List<LanguageRange> preferenciasIdioma = LanguageRange.parse(acceptLanguage);
        
        // Le paso la lista de idiomas disponibles y el formato entendible para que busque un idioma
        // elegible que este disponible en lo que le pase y que tenga mas preferencia por el cliente
        Locale idiomaPreferido = Locale.lookup(preferenciasIdioma, this.idiomasDisponible);
        
        // Establecer el lenguaje preferido como atributo en la solicitud
        request.setAttribute("lenguajePreferido", idiomaPreferido.toLanguageTag());
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
