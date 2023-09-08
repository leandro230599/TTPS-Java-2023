package ttps.filtros;

import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class FiltroLogDeAccesos
 */
@WebFilter(
urlPatterns = "/*")
public class FiltroLogDeAccesos implements Filter {

    /**
     * Default constructor. 
     */
    public FiltroLogDeAccesos() {
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
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res=(HttpServletResponse) response;
		Calendar cal = new GregorianCalendar();
		System.out.println(Locale.getDefault().getLanguage());
		System.out.println(req.getLocale());
		System.out.println(req.getRemoteAddr()+" - "
						  	+cal.get(Calendar.DAY_OF_MONTH)+"/"
						  	+cal.get(Calendar.MONTH)+"/"
						  	+cal.get(Calendar.YEAR)+" "
						  	+cal.get(Calendar.HOUR)+":"
						  	+cal.get(Calendar.MINUTE)+" - "
						  	+req.getMethod()+" "+req.getRequestURI()+" "+req.getProtocol()+" - "
						  	+req.getHeader("User-Agent"));
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
