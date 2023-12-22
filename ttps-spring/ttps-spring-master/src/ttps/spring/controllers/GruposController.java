package ttps.spring.controllers;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ttps.spring.clasesDTO.GastoDTO;
import ttps.spring.model.Gasto;
import ttps.spring.model.Grupos;
import ttps.spring.model.Usuarios;
import ttps.spring.services.GruposService;
import ttps.spring.services.UsuariosService;

@CrossOrigin(origins="http://localhost:8080")
@RestController
@RequestMapping(value="/grupo", produces = MediaType.APPLICATION_JSON_VALUE)
public class GruposController {
	
	@Autowired
	private GruposService grupoService;
	@Autowired
	private UsuariosService userService;
	
	@PostMapping("/crearGrupo")
	public ResponseEntity<Map<String, String>> login(@RequestBody Map<String, String> datos){
		
		grupoService.crearGrupo(
				datos.get("nombre"), 
				Long.parseLong(datos.get("idCat")), 
				Long.parseLong(datos.get("idUser"))
		);

		return new ResponseEntity("Grupo creado",HttpStatus.CREATED);
	}
	
	@PostMapping("/actualizar")
	public ResponseEntity<Map<String, String>> actualizar(@RequestBody Map<String, Object> datos){
		grupoService.actualizarGrupo(
				Long.valueOf((Integer) datos.get("idGrupo")),
				(String) datos.get("nombre"),
				Long.valueOf((Integer)datos.get("idCat"))
		);
		return new ResponseEntity("Grupo actualizado",HttpStatus.ACCEPTED);
	}
	
	/*@PostMapping("/crearGastoGrupo")
	public ResponseEntity<String> crearGastoGrupo (@RequestBody Long idCat,
												     @RequestBody Date fecha,
												     @RequestBody double monto,
												     @RequestBody Long idUser,
												     @RequestBody Long idFD,
												     @RequestBody int fdV,
												     @RequestBody Long idGrupoPersona){
		grupoService.crearGastoGrupo(idCat, fecha, monto, idUser, idFD, fdV, idGrupoPersona);
		return new ResponseEntity<String>("Gasto en grupo creado correctamente", HttpStatus.ACCEPTED);
	}*/
	
	@PostMapping("/crearGastoGrupo")
	public ResponseEntity<String> crearGastoGrupo (@RequestBody Map<String, Object> datos){
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
		grupoService.crearGastoGrupo(idCat, fecha, monto, idUser, idFD, fdV, idGrupoPersona);
		return new ResponseEntity<String>("Gasto en grupo creado correctamente", HttpStatus.ACCEPTED);
	}
	
	@PostMapping("/editarGastoGrupo")
	public ResponseEntity<String> editarGastoGrupo (@RequestBody Map<String, Object> datos){
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
		grupoService.editarGastoGrupo(idCat, fecha, monto, idUser, idFD, fdV, idGrupoPersona, idGasto);
		return new ResponseEntity<String>("Gasto en grupo actualizado correctamente", HttpStatus.ACCEPTED);
	}
	
	@PostMapping("/agregarAGrupo")
	public ResponseEntity<String> agregarAGrupo(@RequestBody Map<String, String> datos){
		String username = datos.get("username");
		Long idGrupo = Long.parseLong(datos.get("idGrupo"));
		Usuarios user = userService.recuperarPorUsername(username);
		if (user!=null) {
			if (grupoService.agregarAGrupo(user, idGrupo)) {
				return new ResponseEntity<String>("Usuario agregado al grupo exitosamente",HttpStatus.ACCEPTED);
			}
			return new ResponseEntity<String>("El usuario ya esta en el grupo",HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>("El usuario no existe",HttpStatus.BAD_REQUEST);
	}
	
	@PostMapping("/salirseDeGrupo")
	public ResponseEntity<String> salirseDeGrupo(@RequestBody Long idUser, @RequestBody Long idGrupo){
		if (grupoService.salirseDeGrupo(idUser, idGrupo)) {
			return new ResponseEntity<String>("Te saliste del grupo exitosamente",HttpStatus.ACCEPTED);
		}
		return new ResponseEntity<String>("Salida de grupo fallida",HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping("/listarGastosGrupo/{idGrupo}")
	public ResponseEntity<?> listarGastosGrupo (@PathVariable Long idGrupo){
		Grupos grupo = grupoService.recuperarPorId(idGrupo);
		if (grupo==null) {
			return new ResponseEntity<>("No se encontro el grupo",HttpStatus.BAD_REQUEST);
		} else {
			if (!grupo.getGastoGrupal().isEmpty()) {
				return new ResponseEntity<>(grupo.getGastoGrupal(), HttpStatus.ACCEPTED);
			}
			return new ResponseEntity<>("Grupo no tiene gastos",HttpStatus.NOT_FOUND);			
		}
	}
	
}
