package ttps.filters;

import jakarta.servlet.Filter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

/**
 * Servlet Filter implementation class FiltroStockRemeras
 */
public class FiltroStockRemeras extends HttpFilter implements Filter {
       
    /**
     * @see HttpFilter#HttpFilter()
     */
	private FilterConfig config;
	
    public FiltroStockRemeras() {
        super();
        // TODO Auto-generated constructor stub
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
		HttpServletResponse res= (HttpServletResponse) response;
		Integer cantRemera = (Integer) config.getServletContext().getAttribute("cantidadRemeras");
		if (cantRemera > 0) {
			chain.doFilter(request, response);			
		} else {
			res.sendRedirect("/TTPS2023_ENTREGABLE1_LopezLeandro/error.html");
			
		}

		// pass the request along the filter chain
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		this.config = fConfig;
	}

}
