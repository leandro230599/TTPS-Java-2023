package ttps.spring.controllers;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ttps.spring.model.Usuarios;
import ttps.spring.services.GruposService;

@CrossOrigin(origins="http://localhost:8080")
@RestController
@RequestMapping(value="/grupo", produces = MediaType.APPLICATION_JSON_VALUE)
public class GruposController {
	
	@Autowired
	private GruposService grupoService;
	
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
	public ResponseEntity<Map<String, String>> actualizar(@RequestBody Map<String, String> datos){
		grupoService.actualizarGrupo(
				Long.parseLong(datos.get("idGrupo")),
				datos.get("nombre"),
				Long.parseLong(datos.get("idCat"))
		);
		return new ResponseEntity("Grupo actualizado",HttpStatus.ACCEPTED);
	}
	
	@PostMapping("/crearGastoGrupo")
	public ResponseEntity<String> crearGastoGrupo (@RequestParam Long idCat,
													 @RequestParam Date fecha,
													 @RequestParam double monto,
													 @RequestParam Long idUser,
													 @RequestParam Long idFD,
													 @RequestParam int fdV,
													 @RequestParam Long idGrupoPersona){
		grupoService.crearGastoGrupo(idCat, fecha, monto, idUser, idFD, fdV, idGrupoPersona);
		return new ResponseEntity<String>("Gasto en grupo creado correctamente", HttpStatus.ACCEPTED);
	}
}
