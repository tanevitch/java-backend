package ttps.spring.controllers;

import java.time.LocalDate;
import java.util.Date;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ttps.spring.DAO.EstadoDAO;
import ttps.spring.model.Estado;
import ttps.spring.model.Evento;
import ttps.spring.model.Reserva;
import ttps.spring.model.Servicio;
import ttps.spring.model.Usuario;
import ttps.spring.services.EventoService;
import ttps.spring.services.ReservaService;
import ttps.spring.services.ServicioService;
import ttps.spring.services.UserService;
import java.time.ZoneId;
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
	@Autowired
	private EstadoDAO estadoDAOImpl;
	
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
	
	@GetMapping("/servicio/{id}")
	public ResponseEntity<List<Reserva>> listarPorServicio(@PathVariable("id") long id,  @RequestParam String nombreEstado){
		Servicio servicio = servicioService.recuperarPorId(id);
		if (servicio == null) {
			return new ResponseEntity("Servicio con id "+id+" no encontrado", HttpStatus.NOT_FOUND);
		}
		
		Estado estado = estadoDAOImpl.buscarEstadoPorNombre(nombreEstado.toUpperCase());
		if (estado == null) {
			return new ResponseEntity("Estado "+nombreEstado+" no encontrado", HttpStatus.NOT_FOUND);
		}
		List<Reserva> reservas = reservaService.listarPorServicio(servicio, estado);
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
		
		Servicio servicio = servicioService.recuperarPorId(reservaNueva.getServicio().getId());
		if (servicio == null) {
			return new ResponseEntity("Servicio con id "+reservaNueva.getServicio().getId()+" no encontrado", HttpStatus.NOT_FOUND);
		}
		
		Evento evento = eventoService.recuperarPorId(reservaNueva.getEvento().getId());
		if (evento == null) {
			return new ResponseEntity("Evento con id "+reservaNueva.getEvento().getId()+" no encontrado", HttpStatus.NOT_FOUND);
		}
		
		if (reservaNueva.hasInvalidFields()) {
			return new ResponseEntity("Campos inv?lidos", HttpStatus.BAD_REQUEST);
		}
		
		if (reservaNueva.getFechaHora().before(evento.getFechaHora())) {
			return new ResponseEntity("No se puede reservar para antes de que empiece el evento", HttpStatus.BAD_REQUEST);
		}
		
		ResponseEntity codigoRta =	reservaService.guardar(reservaNueva);
		if (codigoRta.getStatusCode() != HttpStatus.OK) {
			return codigoRta;
		}
		return new ResponseEntity<Reserva>(reservaNueva, HttpStatus.CREATED);
	}
	
	@GetMapping("/cambiar_estado/{id}")
	public ResponseEntity<Reserva> confirmar(@PathVariable("id") long id, @RequestParam String nombreEstado){
		Reserva reserva = reservaService.recuperarPorId(id);
		if (reserva == null) {
			return new ResponseEntity("Reserva con id "+id+" no encontrada", HttpStatus.NOT_FOUND);
		}
		Estado estado = estadoDAOImpl.buscarEstadoPorNombre(nombreEstado.toUpperCase());
		if (estado == null) {
			return new ResponseEntity("Estado "+nombreEstado+" no encontrado", HttpStatus.NOT_FOUND);
		}
		
		if (reserva.getEstado().getNombre().equals(Estado.RECHAZADA) || reserva.getEstado().getNombre().equals(Estado.FINALIZADA) || reserva.getEstado().getNombre().equals(Estado.CANCELADA)) {
			return new ResponseEntity("No se puede cambiar el estado de una reserva cancelada, rechazada o finalizada", HttpStatus.BAD_REQUEST);
		}
			
		if (estado.getNombre().equals(Estado.FINALIZADA) && new Date().before(reserva.getFechaHora())) {
			return new ResponseEntity("No se puede marcar como finalizada una reserva antes de la fecha de la reserva", HttpStatus.BAD_REQUEST);
		}
		
		if (estado.getNombre().equals(Estado.SINCONFIRMAR) && reserva.getEstado().getNombre().equals(Estado.CONFIRMADA)) {
			return new ResponseEntity("No se puede marcar como sin confirmar una reserva confirmada", HttpStatus.BAD_REQUEST);
		}

		ResponseEntity codigoRta =	reservaService.cambiarEstado(reserva, estado);
		if (codigoRta.getStatusCode() != HttpStatus.OK) {
			return codigoRta;
		}
		return new ResponseEntity<Reserva>(HttpStatus.OK);
	}
	
}
