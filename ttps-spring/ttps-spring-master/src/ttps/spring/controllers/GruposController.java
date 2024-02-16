package ttps.spring.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ttps.spring.clasesDTO.GastoDTO;
import ttps.spring.clasesDTO.GrupoDTO;
import ttps.spring.model.Gasto;
import ttps.spring.model.Grupos;
import ttps.spring.model.Usuarios;
import ttps.spring.services.GruposService;
import ttps.spring.services.TokenService;
import ttps.spring.services.UsuariosService;

@CrossOrigin(origins="http://localhost:8080")
@RestController
@RequestMapping(value="/grupo", produces = MediaType.APPLICATION_JSON_VALUE)
public class GruposController {
	
	@Autowired
	private GruposService grupoService;
	@Autowired
	private UsuariosService userService;
    @Autowired
    private TokenService tokenService;
    
    // Respuesta JSON
	Map<String, String> resp = new HashMap<>();
	
	@PostMapping("/crearGrupo")
	public ResponseEntity<Object> login(@RequestBody Map<String, String> datos,
										@RequestHeader(HttpHeaders.AUTHORIZATION) String token){
		Usuarios user = userService.recuperarPorUsername(tokenService.decodeJwtToUser(token));
		grupoService.crearGrupo(
				datos.get("nombre"), 
				Long.parseLong(datos.get("categoria_id")), 
				user.getId()
		);
        resp.put("mensaje", "Grupo creado");
		return new ResponseEntity<>(resp,HttpStatus.CREATED);
	}
	
	@CrossOrigin(origins="http://localhost:4200")
	@PutMapping("/actualizar/{grupo_id}")
	public ResponseEntity<Map<String, String>> actualizar(@RequestBody Map<String, Object> datos,
														  @PathVariable Long grupo_id,
														  @RequestHeader(HttpHeaders.AUTHORIZATION) String token){
		Usuarios user = userService.recuperarPorUsername(tokenService.decodeJwtToUser(token));
		Grupos grupo = grupoService.recuperarPorId(grupo_id);
		if ((user!=null) && (user.getGrupos().contains(grupo))) {
			grupoService.actualizarGrupo(
					grupo_id,
					(String) datos.get("nombre"),
					Long.parseLong((String) datos.get("idCat"))
					);
			resp.put("mensaje", "Los datos del grupo se actualizaron correctamente");
			return new ResponseEntity<>(resp,HttpStatus.ACCEPTED);
		} else {
			resp.put("mensaje", "No se han podido actualizar los datos del grupo");
			return new ResponseEntity<>(resp,HttpStatus.BAD_REQUEST);		
		}
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
	public ResponseEntity<Object> crearGastoGrupo (@RequestHeader(HttpHeaders.AUTHORIZATION) String token,
												   @RequestBody Map<String, Object> datos){
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy"); 
		DateFormat dfentrada = new SimpleDateFormat("yyyy-MM-dd");
		Date fecha = new Date();
		Date fechaAux = new Date();
		try {
			fechaAux = dfentrada.parse((String)datos.get("fecha"));
			String fechaString = df.format(fechaAux);
			fecha = df.parse(fechaString);
		} catch (ParseException e) {
			e.printStackTrace();
			System.out.println("Error parseando fecha");
		}
		Long idCat =  Long.valueOf(Integer.valueOf((String)datos.get("idCat")));		
		double monto = (Integer) datos.get("monto");
		Long idFD = Long.valueOf(Integer.valueOf((String)datos.get("idFD")));
		int fdV = (Integer) datos.get("fdV");
		Long idGrupoPersona = Long.valueOf((Integer)datos.get("idGrupoPersona"));		
		
		Usuarios user = userService.recuperarPorUsername(tokenService.decodeJwtToUser(token));
		Grupos grupo = grupoService.recuperarPorId(idGrupoPersona);
		
		if(user!=null) {
			if (user.perteneceAGrupo(grupo)) {
				grupoService.crearGastoGrupo(idCat, fecha, monto, user.getId(), idFD, fdV, idGrupoPersona);
				resp.put("mensaje", "Gasto en grupo creado correctamente");
				return new ResponseEntity<>(resp, HttpStatus.ACCEPTED);				
			}
		}
		resp.put("mensaje", "No se pudo crear el gasto en el grupo");
		return new ResponseEntity<>(resp, HttpStatus.BAD_REQUEST);		
	
	}
	
	@PutMapping("/editarGastoGrupo")
	public ResponseEntity<Object> editarGastoGrupo (@RequestHeader(HttpHeaders.AUTHORIZATION) String token,
													@RequestBody Map<String, Object> datos){
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy"); 
		DateFormat dfentrada = new SimpleDateFormat("yyyy-MM-dd");
		Date fecha = new Date();
		Date fechaAux = new Date();
		Map<String, Object> gasto = (Map<String, Object>) datos.get("gasto");
		try {
			fechaAux = dfentrada.parse((String)gasto.get("fecha"));
			String fechaString = df.format(fechaAux);
			fecha = df.parse(fechaString);
		} catch (ParseException e) {
			e.printStackTrace();
			System.out.println("Error parseando fecha");
		}
		Long idCat =  Long.valueOf(Integer.valueOf((String)gasto.get("idCat")));		
		double monto = (Integer) gasto.get("monto");
		Long idFD = Long.valueOf(Integer.valueOf((String)gasto.get("idFD")));
	    int fdV = (Integer) gasto.get("fdV");
	    Long idGrupoPersona = Long.valueOf((Integer)gasto.get("idGrupoPersona"));
	    Long idGasto = Long.valueOf((Integer)datos.get("idGasto"));
	    
		Usuarios user = userService.recuperarPorUsername(tokenService.decodeJwtToUser(token));
		Grupos grupo = grupoService.recuperarPorId(idGrupoPersona);
		
		if(user!=null) {
			if(user.perteneceAGrupo(grupo)) {
				grupoService.editarGastoGrupo(idCat, fecha, monto, user.getId(), idFD, fdV, idGrupoPersona, idGasto);
				resp.put("mensaje", "Gasto en grupo actualizado correctamente");
				return new ResponseEntity<>(resp, HttpStatus.ACCEPTED);				
			}
		}
		resp.put("mensaje", "No se pudo actualizar el gasto en el grupo");
		return new ResponseEntity<>(resp, HttpStatus.BAD_REQUEST);		
	}
	
	@PostMapping("/agregarAGrupo")
	public ResponseEntity<Object> agregarAGrupo(@RequestHeader(HttpHeaders.AUTHORIZATION) String token,
												@RequestBody Map<String, String> datos){
		
		Usuarios user = userService.recuperarPorUsername(tokenService.decodeJwtToUser(token));
		Grupos grupo = grupoService.recuperarPorId(Long.valueOf(Integer.valueOf((String)datos.get("id"))));
		
		if(user!=null) {
			if (user.perteneceAGrupo(grupo)) {
				String username = datos.get("username");				
				Usuarios user2 = userService.recuperarPorUsername(username);
				if (user2!=null) {
					if (!user2.perteneceAGrupo(grupo)) {
						if (grupoService.agregarAGrupo(user2, grupo.getId())) {
							resp.put("mensaje", "Usuario agregado al grupo exitosamente");
							return new ResponseEntity<>(resp,HttpStatus.ACCEPTED);
						}
					}
					resp.put("mensaje", "El usuario ya esta en el grupo");
					return new ResponseEntity<>(resp,HttpStatus.BAD_REQUEST);						
				}
				resp.put("mensaje", "El usuario no existe");
				return new ResponseEntity<>(resp, HttpStatus.BAD_REQUEST);
			}
		}
		resp.put("mensaje", "No se puede agregar al usuario al grupo");
		return new ResponseEntity<>(resp, HttpStatus.BAD_REQUEST);
		
	}
	
	@PostMapping("/salirseDeGrupo")
	public ResponseEntity<Object> salirseDeGrupo(@RequestBody Long idUser, @RequestBody Long idGrupo){
		if (grupoService.salirseDeGrupo(idUser, idGrupo)) {
			resp.put("mensaje", "Te saliste del grupo exitosamente");
			return new ResponseEntity<>(resp,HttpStatus.ACCEPTED);
		}
		resp.put("mensaje", "Salida de grupo fallida");
		return new ResponseEntity<>(resp,HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping("/listarGastosGrupo")
	public ResponseEntity<Object> listarGastosGrupo (@RequestHeader(HttpHeaders.AUTHORIZATION) String token,
												@RequestHeader("grupoId") Long idGrupo){
		Grupos grupo = grupoService.recuperarPorId(idGrupo);
		if (grupo==null) {
			resp.put("mensaje", "No se encontro el grupo");
			return new ResponseEntity<>(resp,HttpStatus.BAD_REQUEST);
		} else {
			if (!grupo.getGastoGrupal().isEmpty()) {
				return new ResponseEntity<>(grupo.getGastoGrupal(), HttpStatus.ACCEPTED);
			}
			resp.put("mensaje", "Grupo no tiene gastos");
			return new ResponseEntity<>(resp,HttpStatus.NOT_FOUND);			
		}
	}
	
	@CrossOrigin(origins="http://localhost:4200")
	@GetMapping("/listarGrupos")
	public ResponseEntity<List<GrupoDTO>> getGruposUser(@RequestHeader(HttpHeaders.AUTHORIZATION) String token){
		Usuarios user = userService.recuperarPorUsername(tokenService.decodeJwtToUser(token));
		List<Grupos> grupos = grupoService.getGrupos(user.getId());
		List<GrupoDTO> gruposDTO = new ArrayList<>();
		for (Grupos grupo : grupos) {
			GrupoDTO grupoDTO = new GrupoDTO(
												grupo.getId(),
												grupo.getNombre(),
												grupo.getCategoria().getId()
											);
			gruposDTO.add(grupoDTO);
		}
		return new ResponseEntity<List<GrupoDTO>>(gruposDTO, HttpStatus.ACCEPTED);
	}
	
	@CrossOrigin(origins="http://localhost:4200")
	@GetMapping("/detallesGrupo/{grupo_id}")
	public ResponseEntity<Object> getDetallesGrupo(@RequestHeader(HttpHeaders.AUTHORIZATION) String token,
													@PathVariable Long grupo_id){
		Usuarios user = userService.recuperarPorUsername(tokenService.decodeJwtToUser(token));
		Grupos grupo = grupoService.recuperarPorId(grupo_id);
		if ((user!=null) && (user.getGrupos().contains(grupo))) {
			Map<Object, Object> datos = new HashMap<>();
			datos.put("nombre", grupo.getNombre());
			datos.put("categoria_id",grupo.getCategoria().getId());
			datos.put("grupo_id",grupo.getId());
			datos.put("miembros",grupo.getMiembros());
			List<GastoDTO> gastosDTO = new ArrayList<>();
			for (Gasto gasto : grupo.getGastoGrupal()) {
				java.sql.Date fecha = new java.sql.Date(gasto.getFecha().getTime());
				GastoDTO gastoDTO = new GastoDTO(	gasto.getId(),
													gasto.getUserGasto(),
													fecha,
													gasto.getMonto(),
													gasto.getCategoria(),
													gasto.getTipo(),
													gasto.getFormaDividir(),
													gasto.getFDValor()
												);
				gastosDTO.add(gastoDTO);
			}
			datos.put("gastos", gastosDTO);
			//datos.put("gastos", grupo.getGastoGrupal());
			return new ResponseEntity<>(datos,HttpStatus.ACCEPTED);
		} else {
			resp.put("mensaje", "El usuario no pertenece al grupo");
			return new ResponseEntity<>(resp,HttpStatus.BAD_REQUEST);		
		}
	}
	
}
