package ttps.spring.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import ttps.spring.model.Usuarios;
import ttps.spring.services.UsuariosService;

@CrossOrigin(origins="http://localhost:8080")
@RestController
@RequestMapping(value="/usuario", produces = MediaType.APPLICATION_JSON_VALUE)
public class UsuariosControllers {
	
	@Autowired
	private UsuariosService userService;
	
	
	@PostMapping("/registrarUsuario")
	public ResponseEntity<Usuarios> registrarUsuario(@RequestBody Usuarios user){
		System.out.println("ENTRO AL CONTROLLER");
		Usuarios usuario = userService.crearUsuario(
				user.getUsername(), user.getFirst_name(), user.getLast_name(), user.getEmail(), user.getPassword());
		 if (usuario != null) {
			 return new ResponseEntity<Usuarios>(HttpStatus.CREATED);
		 }
		 return new ResponseEntity("Usuario registrado",HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping("/prueba")
	public ResponseEntity<Usuarios> prueba(){
		return new ResponseEntity("Entro aca CONTROLLE PRUEBA", HttpStatus.ACCEPTED);
	}
	
	@PostMapping("/login")
	public ResponseEntity<Map<String, String>> login(@RequestBody Map<String, String> datos){
		Usuarios usuario = userService.recuperarPorEmailYPassword(datos.get("email"), datos.get("password"));
		if (usuario != null) {
			return new ResponseEntity("Login correcto",HttpStatus.ACCEPTED);
		}
		return new ResponseEntity("Login incorrecto",HttpStatus.BAD_REQUEST);
	}
	
}
