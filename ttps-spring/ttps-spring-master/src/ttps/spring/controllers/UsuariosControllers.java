package ttps.spring.controllers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.annotation.JsonFormat;

import ttps.spring.model.Usuarios;
import ttps.spring.services.UsuariosService;

@RestController
@RequestMapping(value="/usuario", produces = MediaType.APPLICATION_JSON_VALUE)
public class UsuariosControllers {
	
	@Autowired
	private UsuariosService userService;
	
	@CrossOrigin(origins="http://localhost:4200")
	@PostMapping("/registrarUsuario")
	public ResponseEntity<Usuarios> registrarUsuario(@RequestBody Usuarios user){
		Usuarios usuario = userService.crearUsuario(
				user.getUsername(), user.getFirst_name(), user.getLast_name(), user.getEmail(), user.getPassword());
		 if (usuario != null) {
			 return new ResponseEntity<Usuarios>(usuario, HttpStatus.CREATED);
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
	public ResponseEntity<String> crearGastoPersona (@RequestBody Map<String, Object> datos){
		Long idCat =  Long.valueOf((Integer)datos.get("idCat"));
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy"); 
	    Date fecha = new Date();
	    try {
			fecha = df.parse((String)datos.get("fecha"));
		} catch (ParseException e) {
			e.printStackTrace();
			System.out.println("Error parseando fecha");
		}
	    double monto = (Integer) datos.get("monto");
	    Long idUser = Long.valueOf((Integer)datos.get("idUser"));
	    Long idFD = Long.valueOf((Integer)datos.get("idFD"));
	    int fdV = (Integer) datos.get("fdV");
	    Long idGrupoPersona = Long.valueOf((Integer)datos.get("idGrupoPersona"));
		userService.crearGastoPersona(idCat, fecha, monto, idUser, idFD, fdV, idGrupoPersona);
		return new ResponseEntity<String>("Gasto con una persona creado correctamente", HttpStatus.ACCEPTED);
	}
	
	@PostMapping("/editarGastoPersona")
	public ResponseEntity<String> editarGastoPersona (@RequestBody Map<String, Object> datos){
		Long idCat =  Long.valueOf((Integer)datos.get("idCat"));
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy"); 
	    Date fecha = new Date();
	    try {
			fecha = df.parse((String)datos.get("fecha"));
		} catch (ParseException e) {
			e.printStackTrace();
			System.out.println("Error parseando fecha");
		}
	    double monto = (Integer) datos.get("monto");
	    Long idUser = Long.valueOf((Integer)datos.get("idUser"));
	    Long idFD = Long.valueOf((Integer)datos.get("idFD"));
	    int fdV = (Integer) datos.get("fdV");
	    Long idGrupoPersona = Long.valueOf((Integer)datos.get("idGrupoPersona"));
	    Long idGasto = Long.valueOf((Integer)datos.get("idGasto"));
		userService.editarGastoPersona(idCat, fecha, monto, idUser, idFD, fdV, idGrupoPersona, idGasto);
		return new ResponseEntity<String>("Gasto con persona actualizado correctamente", HttpStatus.ACCEPTED);
	}
	
}
