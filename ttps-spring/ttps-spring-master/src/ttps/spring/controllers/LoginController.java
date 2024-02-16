package ttps.spring.controllers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.annotation.JsonFormat;

import ttps.spring.model.Usuarios;
import ttps.spring.clasesDTO.CredencialesDTO;
import ttps.spring.services.UsuariosService;
import ttps.spring.services.TokenService;

@RestController
@RequestMapping(value="/login", produces = MediaType.APPLICATION_JSON_VALUE)
public class LoginController {
	
	@Autowired
	private UsuariosService userService;
	
    @Autowired
    private TokenService tokenService;
    
    // un dia
    private final int EXPIRATION_IN_SEC = 1000;
    
    // Respuesta JSON
	Map<String, String> resp = new HashMap<>();
	
	@CrossOrigin(origins="http://localhost:4200")
	@PostMapping("/auth")
	public ResponseEntity<Object> logearUser(@RequestBody Map<String, String> userAndPass){
		Usuarios usuario = userService.recuperarPorEmailYPassword(
																	userAndPass.get("mail"), 
																	userAndPass.get("password")
																 );
		if (usuario != null) {
			String token = tokenService.generateToken(usuario.getUsername(), EXPIRATION_IN_SEC);
			return new ResponseEntity<>(new CredencialesDTO(
																token, 
																EXPIRATION_IN_SEC, 
																usuario.getUsername()
																), HttpStatus.ACCEPTED
												);
		}
        resp.put("mensaje", "Email o password incorrectos");
		return new ResponseEntity<>(resp,HttpStatus.BAD_REQUEST);
	}
	/*
	@CrossOrigin(origins="http://localhost:4200")
	@GetMapping("/validate/{token}")
	public ResponseEntity<Map<String,Object>> validarToken(@PathVariable("token") String token){
		System.out.println("ENTRO");
		boolean resp = TokenService.validateToken(token);
		Map<String,Object> datos = new HashMap<>();
		datos.put("token", resp);
		return new ResponseEntity<>(datos, HttpStatus.ACCEPTED);
	}*/
	
	@CrossOrigin(origins="http://localhost:4200")
	@GetMapping("/validate")
	public String validarToken(@RequestHeader(HttpHeaders.AUTHORIZATION) String token){
		boolean resp = TokenService.validateToken(token);
		return "Hola "+token;
	}
	
	
	
}
