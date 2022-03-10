package ttps.spring.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ttps.spring.model.Evento;
import ttps.spring.model.Usuario;
import ttps.spring.services.EventoService;
import ttps.spring.services.UserService;

@CrossOrigin
@RestController
@RequestMapping("/api/eventos")
public class EventoRestController {
	@Autowired
	EventoService eventoService;
	@Autowired	
	UserService usuarioService;
	
	@GetMapping(path="/usuario/{id}")
	public ResponseEntity<List<Evento>> getEventos(@PathVariable("id") long id){
		Usuario user= usuarioService.recuperarPorId(id);
		if (user == null) {
			return new ResponseEntity("Usuario con id "+ id + " no encontrado", HttpStatus.NOT_FOUND);		
		}
		List<Evento> eventos = eventoService.listarPorUsuario(user);
		if(eventos.isEmpty()) {
			return new ResponseEntity("No hay resultados", HttpStatus.NO_CONTENT);
		}
		
		
		return new ResponseEntity<List<Evento>>(eventos, HttpStatus.OK);
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Evento> getEventoPorId(@PathVariable("id") long id){
		Evento evento = eventoService.recuperarPorId(id);
		if (evento == null) {
			return new ResponseEntity("Evento con id "+ id + " no encontrado", HttpStatus.NOT_FOUND);		
		}
		return new ResponseEntity<Evento>(evento, HttpStatus.OK);
	}
	
	@PostMapping("")
	public ResponseEntity<Evento> crear(@RequestBody Evento eventoNuevo){
		if (eventoNuevo.hasEmptyFields()){
			 return new ResponseEntity("Todos los campos son requeridos", HttpStatus.BAD_REQUEST);
		 }
		
		if (eventoNuevo.hasInvalidFields()) {
			return new ResponseEntity("Campos inválidos", HttpStatus.BAD_REQUEST);
		}
		
		ResponseEntity codigoRta =	eventoService.guardar(eventoNuevo);
		if (codigoRta.getStatusCode() != HttpStatus.OK) {
			return codigoRta;
		}
		return new ResponseEntity<Evento>(eventoNuevo, HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Evento> modificar(@PathVariable("id") long id, @RequestBody Evento eventoMod){
		
		if (eventoMod.hasEmptyFields()) {
			return new ResponseEntity("Todos los campos son requeridos", HttpStatus.BAD_REQUEST);
		}
		
		if (eventoMod.hasInvalidFields()) {
			return new ResponseEntity("Todos los campos deben tener como máximo 255 caracteres", HttpStatus.BAD_REQUEST);
		}
		
		ResponseEntity codigoRta = eventoService.editar(eventoMod, id);
		if (codigoRta.getStatusCode() != HttpStatus.OK) {
			return codigoRta;
		}
		
		Evento service = eventoService.recuperarPorId(id);
		return new ResponseEntity<Evento>(service, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	 public ResponseEntity<Evento> borrar(@PathVariable("id") long id) {
	 Evento evento = eventoService.recuperarPorId(id);
	 if (evento == null) {
	 	return new ResponseEntity("Evento con id "+ id + " no encontrado", HttpStatus.NOT_FOUND);
	 }
	 
	 eventoService.borrar(evento);
	 return new ResponseEntity<Evento>(HttpStatus.OK);
	 }
}
