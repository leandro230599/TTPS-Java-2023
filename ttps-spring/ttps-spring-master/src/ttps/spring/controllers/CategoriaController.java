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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ttps.spring.clasesDTO.CategoriasDTO;
import ttps.spring.clasesDTO.GastoDTO;
import ttps.spring.clasesDTO.GrupoDTO;
import ttps.spring.model.Categoria;
import ttps.spring.model.Gasto;
import ttps.spring.model.Grupos;
import ttps.spring.model.Usuarios;
import ttps.spring.services.CategoriaService;
import ttps.spring.services.GruposService;
import ttps.spring.services.TokenService;
import ttps.spring.services.UsuariosService;

@CrossOrigin(origins="http://localhost:8080")
@RestController
@RequestMapping(value="/categoria", produces = MediaType.APPLICATION_JSON_VALUE)
public class CategoriaController {
	
	@Autowired
	private CategoriaService catService;
    
    // Respuesta JSON
    Map<String, String> resp = new HashMap<>();
    
    @CrossOrigin(origins="http://localhost:4200")
    @GetMapping("/obtenerCategoriaGrupos")
	public ResponseEntity<Object> getCategoriasGrupo(){
    	System.out.println("ENTRE CAT GRUPOS");
		List<Categoria> catGrupos = catService.getCategorias("Grupo");
		if (catGrupos==null) {
			resp.put("mensaje", "Categorias grupales no cargadas");
			return new ResponseEntity<>(resp,HttpStatus.BAD_REQUEST);
		} else {
			List<CategoriasDTO> catsDTO = new ArrayList<>();
			for(Categoria categoria : catGrupos) {
				CategoriasDTO catDTO = new CategoriasDTO(
															categoria.getId(),
															categoria.getImagenURL(),
															categoria.getNombre(),
															categoria.getTipo());
				catsDTO.add(catDTO);
			}
				return new ResponseEntity<>(catsDTO, HttpStatus.ACCEPTED);
		}
	}
    
    @GetMapping("/obtenerCategoriaGasto")
	public ResponseEntity<Object> getCategoriasGasto(){
		List<Categoria> catGrupos = catService.getCategorias("Gasto");
		if (catGrupos==null) {
			resp.put("mensaje", "Categorias de gastos no cargadas");
			return new ResponseEntity<>(resp,HttpStatus.BAD_REQUEST);
		} else {
			List<CategoriasDTO> catsDTO = new ArrayList<>();
			for(Categoria categoria : catGrupos) {
				CategoriasDTO catDTO = new CategoriasDTO(
															categoria.getId(),
															categoria.getImagenURL(),
															categoria.getNombre(),
															categoria.getTipo());
				catsDTO.add(catDTO);
			}
				return new ResponseEntity<>(catsDTO, HttpStatus.ACCEPTED);
		}
	}
    
	 
}
