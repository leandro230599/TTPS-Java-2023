package ttps.spring.controllers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.annotation.JsonFormat;

import ttps.spring.model.Categoria;
import ttps.spring.model.FormaDividir;
import ttps.spring.model.Usuarios;
import ttps.spring.clasesDTO.CategoriasDTO;
import ttps.spring.clasesDTO.CredencialesDTO;
import ttps.spring.clasesDTO.FormaDividirDTO;
import ttps.spring.services.FormaDividirService;
import ttps.spring.services.TokenService;

@RestController
@RequestMapping(value="/formadividir", produces = MediaType.APPLICATION_JSON_VALUE)
public class FormaDividirController {
	
	@Autowired
	private FormaDividirService fdService;
	@Autowired
	private TokenService tokenService;
	// Respuesta JSON
	Map<String, String> resp = new HashMap<>();
	
	@CrossOrigin(origins="http://localhost:4200")
    @GetMapping("/gastos")
	public ResponseEntity<Object> getCategoriasGrupo(){
		List<FormaDividir> formasDividir = fdService.getFormasDividir();
		if (formasDividir==null) {
			resp.put("mensaje", "Formas de dividir gastos no cargadas");
			return new ResponseEntity<>(resp,HttpStatus.BAD_REQUEST);
		} else {
			List<FormaDividirDTO> fdsDTO = new ArrayList<>();
			for(FormaDividir fd : formasDividir) {
				FormaDividirDTO fdDTO = new FormaDividirDTO(
															fd.getId(),
															fd.getNombre());
				fdsDTO.add(fdDTO);
			}
				return new ResponseEntity<>(fdsDTO, HttpStatus.ACCEPTED);
		}
	}

}
