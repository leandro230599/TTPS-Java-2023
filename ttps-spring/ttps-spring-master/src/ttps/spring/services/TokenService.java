package ttps.spring.services;

import ttps.spring.clasesDAO.UsuariosDAO;
import ttps.spring.model.Usuarios;

import java.security.Key;
import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;


@Service
public class TokenService {
	
	final static Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
	
	public String generateToken (String username, int seconds) {
		Date exp = getExpiration(new Date(), seconds);
		return Jwts.builder().setSubject(username).signWith(key).setExpiration(exp).compact();
	}
	
    private Date getExpiration(Date date, int segundos) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date); // Configuramos la fecha que se recibe
        calendar.add(Calendar.SECOND, segundos);

        return calendar.getTime();
    }
    
    public static boolean validateToken(String token) {

        String prefix = "Bearer";
        try {
            if (token.startsWith(prefix)) {
                token = token.substring(prefix.length()).trim();
            }

            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            return true;
        } catch (ExpiredJwtException exp) {
            System.out.println("El Token es valido, pero expiro su tiempo de validez");
            return false;
        } catch (JwtException e) {
            // Algo salio mal en la verificacion
            System.out.println("Error: " + e.getMessage());
            return false;
        }

    }
    
   public String decodeJwtToUser (String token) {
    	String prefix = "Bearer";
        if (token.startsWith(prefix)) {
            token = token.substring(prefix.length()).trim();
        }

        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }
	
	
}
