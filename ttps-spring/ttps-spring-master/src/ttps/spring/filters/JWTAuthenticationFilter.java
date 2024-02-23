package ttps.spring.filters;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import io.jsonwebtoken.io.IOException;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ttps.spring.services.TokenService; 

@WebFilter(filterName = "jwt-auth-filter", urlPatterns = "*")
public class JWTAuthenticationFilter implements Filter {
	
	 @Override
	 public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException, java.io.IOException{
		 HttpServletRequest req = (HttpServletRequest) request;
		 // El login del usuarios es publico
		 if ("/ttps-spring/login/auth".equals(req.getRequestURI()) || HttpMethod.OPTIONS.matches(req.getMethod())) {
			 chain.doFilter(request, response);
			 return;
		 }
		 if ("/ttps-spring/usuario/registrarUsuario".equals(req.getRequestURI()) || HttpMethod.OPTIONS.matches(req.getMethod())) {
			 chain.doFilter(request, response);
			 return;
		 }
		 String token = req.getHeader(HttpHeaders.AUTHORIZATION);
		 if (token == null || !TokenService.validateToken(token)) {
			 HttpServletResponse res = (HttpServletResponse) response;
			 res.setStatus(HttpStatus.FORBIDDEN.value());
			 return;
		 }
		 chain.doFilter(request, response);
	 }
}
