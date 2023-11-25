package ttps.spring.controllers;

import java.util.Date;
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
		Usuarios usuario = userService.crearUsuario(
				user.getUsername(), user.getFirst_name(), user.getLast_name(), user.getEmail(), user.getPassword());
		 if (usuario != null) {
			 return new ResponseEntity<Usuarios>(HttpStatus.CREATED);
		 }
		 return new ResponseEntity("Usuario registrado",HttpStatus.BAD_REQUEST);
	}
	
	@PostMapping("/login")
	public ResponseEntity<Map<String, String>> login(@RequestBody Map<String, String> datos){
		Usuarios usuario = userService.recuperarPorEmailYPassword(datos.get("email"), datos.get("password"));
		if (usuario != null) {
			return new ResponseEntity("Login correcto",HttpStatus.ACCEPTED);
		}
		return new ResponseEntity("Login incorrecto",HttpStatus.BAD_REQUEST);
	}

	@PostMapping("/crearGastoPersona")
	public ResponseEntity<String> crearGastoPersona (@RequestParam Long idCat,
													 @RequestParam Date fecha,
													 @RequestParam double monto,
													 @RequestParam Long idUser,
													 @RequestParam Long idFD,
													 @RequestParam int fdV,
													 @RequestParam Long idGrupoPersona){
		userService.crearGastoPersona(idCat, fecha, monto, idUser, idFD, fdV, idGrupoPersona);
		return new ResponseEntity<String>("Gasto con persona creado correctamente", HttpStatus.ACCEPTED);
	}
	
}
