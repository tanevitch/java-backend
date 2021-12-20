package ttps.spring.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ttps.spring.model.AspectoPuntuacion;
import ttps.spring.model.TipoServicio;
import ttps.spring.services.TipoServicioService;

@CrossOrigin
@RestController
@RequestMapping("/api/categorias_servicios")
public class TipoServicioRestController {
	
	@Autowired
	TipoServicioService tipoServicioService;
	
	@GetMapping(path="")
	public ResponseEntity<List<TipoServicio>> servicios(){
		List<TipoServicio> tipos = tipoServicioService.listar();
		if(tipos.isEmpty()) {
			return new ResponseEntity("No hay resultados", HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<TipoServicio>>(tipos, HttpStatus.OK);
	}
		
	@GetMapping(path="/{id}/aspectos_puntuacion")
	public ResponseEntity<List<AspectoPuntuacion>> aspectosPuntuacion(@PathVariable("id") long id){
		TipoServicio tipo = tipoServicioService.recuperarTipoServicioPorId(id);
		if(tipo == null) {
			return new ResponseEntity("Tipo de servicio con id "+id+" no existe", HttpStatus.NOT_FOUND);
		}
		List<AspectoPuntuacion> aspectos = tipoServicioService.obtenerAspectosAValorar(tipo);
		return new ResponseEntity<List<AspectoPuntuacion>>(aspectos, HttpStatus.OK);
	}
}
