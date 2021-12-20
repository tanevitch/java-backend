package ttps.spring.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ttps.spring.model.TipoEvento;
import ttps.spring.services.TipoEventoService;
import ttps.spring.services.TipoServicioService;

@CrossOrigin
@RestController
@RequestMapping("/api/tipo_eventos")
public class TipoEventoRestController {

	@Autowired
	TipoEventoService tipoEventoService;
	
	@GetMapping(path="")
	public ResponseEntity<List<TipoEvento>> tipos(){
		List<TipoEvento> tipos = tipoEventoService.listar();
		if(tipos.isEmpty()) {
			return new ResponseEntity("No hay resultados", HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<TipoEvento>>(tipos, HttpStatus.OK);
	}

}
