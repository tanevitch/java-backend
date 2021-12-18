package ttps.spring.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ttps.spring.model.Reserva;
import ttps.spring.model.Servicio;
import ttps.spring.model.Usuario;
import ttps.spring.services.EventoService;
import ttps.spring.services.ReservaService;
import ttps.spring.services.ServicioService;
import ttps.spring.services.UserService;

@CrossOrigin
@RestController
@RequestMapping("/api/reservas")
public class ReservaRestController {
	@Autowired
	ReservaService reservaService;
	@Autowired
	ServicioService servicioService;
	@Autowired	
	UserService usuarioService;
	@Autowired
	EventoService eventoService;
	
	@GetMapping("/usuario/{id}")
	public ResponseEntity<List<Reserva>> listarPorUsuario(@PathVariable("id") long id){
		Usuario user = usuarioService.recuperarPorId(id);
		if (user == null) {
			return new ResponseEntity("Usuario con id "+id+"no encontrado", HttpStatus.NOT_FOUND);
		}
		List<Reserva> reservas = reservaService.listarPorUsuarioId(user);
		if(reservas.isEmpty()) {
			return new ResponseEntity("No hay resultados", HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Reserva>>(reservas, HttpStatus.OK);
	}	
	
	@PostMapping("")
	public ResponseEntity<Reserva> crear(@RequestBody Reserva reservaNueva){
		if (reservaNueva.hasEmptyFields()){
			 return new ResponseEntity("Todos los campos son requeridos", HttpStatus.BAD_REQUEST);
		 }
		
		ResponseEntity codigoRta =	reservaService.guardar(reservaNueva);
		if (codigoRta.getStatusCode() != HttpStatus.OK) {
			return codigoRta;
		}
		return new ResponseEntity<Reserva>(reservaNueva, HttpStatus.CREATED);
	}
}
