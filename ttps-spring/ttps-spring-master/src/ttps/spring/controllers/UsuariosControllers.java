package ttps.spring.controllers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.annotation.JsonFormat;

import ttps.spring.model.Deuda;
import ttps.spring.model.Usuarios;
import ttps.spring.services.DeudaService;
import ttps.spring.services.TokenService;
import ttps.spring.services.UsuariosService;

@RestController
@RequestMapping(value="/usuario", produces = MediaType.APPLICATION_JSON_VALUE)
public class UsuariosControllers {
	
	@Autowired
	private UsuariosService userService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private DeudaService deudaService;
	
    // Respuesta JSON
	Map<String, String> resp = new HashMap<>();
	
	@CrossOrigin(origins="http://localhost:4200")
	@PostMapping("/registrarUsuario")
	public ResponseEntity<Object> registrarUsuario(@RequestBody Usuarios user){
		Usuarios usuario = userService.crearUsuario(
				user.getUsername(), user.getFirst_name(), user.getLast_name(), user.getEmail(), user.getPassword());
		 if (usuario != null) {
			 return new ResponseEntity<>(usuario, HttpStatus.CREATED);
		 }
		 resp.put("mensaje", "Usuario registrado");
		 return new ResponseEntity<>(resp,HttpStatus.BAD_REQUEST);
	}
	
	@PostMapping("/login")
	public ResponseEntity<Object> login(@RequestBody Map<String, String> datos){
		Usuarios usuario = userService.recuperarPorEmailYPassword(datos.get("email"), datos.get("password"));
		if (usuario != null) {
			resp.put("mensaje", "Login correcto");
			return new ResponseEntity<>(resp,HttpStatus.ACCEPTED);
		}
		resp.put("mensaje", "Login incorrecto");
		return new ResponseEntity<>(resp,HttpStatus.BAD_REQUEST);
	}
	
	@PostMapping("/crearGastoPersona")
	public ResponseEntity<Object> crearGastoPersona (@RequestBody Map<String, Object> datos){
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
		resp.put("mensaje", "Gasto con una persona creado correctamente");
		return new ResponseEntity<>(resp, HttpStatus.ACCEPTED);
	}
	
	@PostMapping("/editarGastoPersona")
	public ResponseEntity<Object> editarGastoPersona (@RequestBody Map<String, Object> datos){
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
		resp.put("mensaje", "Gasto con persona actualizado correctamente");
		return new ResponseEntity<>(resp, HttpStatus.ACCEPTED);
	}
	
	@PostMapping("/obtenerDeuda")
	public ResponseEntity<Object> obtenerDeuda (@RequestHeader(HttpHeaders.AUTHORIZATION) String token,
												@RequestBody Map<String, String> datos){
		Usuarios user = userService.recuperarPorUsername(tokenService.decodeJwtToUser(token));
		if (user!=null) {
			Long idGrupoPersona = Long.valueOf(Integer.valueOf((String)datos.get("idGrupoPersona")));
			Long idUser = Long.valueOf(Integer.valueOf((String)datos.get("idUser")));
			String tipo = (String)datos.get("tipo");
			List<Deuda> deudas = deudaService.getDeudas(idUser, idGrupoPersona,tipo);
			return new ResponseEntity<>(deudas, HttpStatus.ACCEPTED);
		}
		resp.put("mensaje", "No se puede obtener las deudas");
		return new ResponseEntity<>(resp, HttpStatus.BAD_REQUEST);
	}
	
}
