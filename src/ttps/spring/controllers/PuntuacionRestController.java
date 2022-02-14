package ttps.spring.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

import ttps.spring.model.AspectoPuntuacion;
import ttps.spring.model.Evento;
import ttps.spring.model.Puntuacion;
import ttps.spring.model.Reserva;
import ttps.spring.model.Servicio;
import ttps.spring.model.Usuario;
import ttps.spring.services.EventoService;
import ttps.spring.services.PuntuacionService;
import ttps.spring.services.ReservaService;
import ttps.spring.services.ServicioService;
import ttps.spring.services.UserService;

@CrossOrigin
@RestController
@RequestMapping("/api/puntuacion")
public class PuntuacionRestController {
	@Autowired
	ServicioService servicioService;
	@Autowired	
	UserService usuarioService;
	@Autowired	
	ReservaService reservaService;
	@Autowired	
	PuntuacionService puntuacionService;
	@Autowired	
	EventoService eventoService;
		
	
	@GetMapping("/{id}")
	public ResponseEntity fueCalificado(@PathVariable("id") long id){
		Reserva reserva = reservaService.recuperarPorId(id);
		if (reserva == null) {
			return new ResponseEntity("Reserva con id "+ id + " no encontrada", HttpStatus.NOT_FOUND);		
		}
		
		List<Puntuacion> puntuacionesBD = puntuacionService.buscarCalificacionPorServicioEventoyUsuario(reserva.getServicio().getId(), reserva.getUsuario().getId(), reserva.getEvento().getId());
		return new ResponseEntity(!puntuacionesBD.isEmpty(), HttpStatus.OK);
	}
	
	@GetMapping("/servicio/{id}")
	public ResponseEntity puntuacionesServicio(@PathVariable("id") long id){
		Servicio servicio = servicioService.recuperarPorId(id);
		if (servicio == null) {
			return new ResponseEntity("Servicio con id "+ id + " no encontrado", HttpStatus.NOT_FOUND);		
		}
		
		List<Puntuacion> puntuacionesBD = puntuacionService.buscarCalificacionPorServicio(id);
		for (Puntuacion puntuacion : puntuacionesBD) {
			System.out.println(puntuacion.getId()+ " bd");
		}
		//Este es para hacer el group by, porque si hago select p, avg(p.nota) en la query como satan manda, no anda
		Map<Object, Double> ap = 
				puntuacionesBD.stream()
			          .collect(Collectors.groupingBy(e -> e.getAspectoPuntuacion().getId(),
			                                         Collectors.averagingDouble(Puntuacion::getNota)));
		
		// Y una vez que tenes el group by, como te quedó en map, lo pasás a lista así mandás todo el objeto de aspecto puntuación (al pedo, capaz podías devolver el nombre nomás pero bue)
		List<Puntuacion> puntuaciones = new ArrayList<Puntuacion>();
		for (Map.Entry<Object, Double> entry : ap.entrySet()) {
			Object key = entry.getKey();
			System.out.println(key);
			puntuaciones.add(
					new Puntuacion(entry.getValue(),
							puntuacionesBD.stream().filter(p -> p.getAspectoPuntuacion().getId()==(long)key).findFirst().get().getAspectoPuntuacion()
						)
					);
		}
		
		return new ResponseEntity(puntuaciones, HttpStatus.OK);
	}
	
	@GetMapping("/promedio/{id}")
	public ResponseEntity promedioPuntuacionServicio(@PathVariable("id") long id){
		Servicio servicio = servicioService.recuperarPorId(id);
		if (servicio == null) {
			return new ResponseEntity("Servicio con id "+ id + " no encontrado", HttpStatus.NOT_FOUND);		
		}
		
		List<Puntuacion> puntuacionesBD = puntuacionService.buscarCalificacionPorServicio(id);
		Double promedio = puntuacionesBD.isEmpty()? null : puntuacionesBD.stream().mapToDouble(x -> x.getNota()).average().getAsDouble();
		return new ResponseEntity(promedio, HttpStatus.OK);
	}
	
	@PostMapping("/{id}")
	public ResponseEntity puntuar(@PathVariable("id") long id, @RequestBody List<Puntuacion> puntuaciones){
		
		System.out.println("Entre en puntuaciones");
		Servicio servicio = servicioService.recuperarPorId(id);
		if (servicio == null) {
			return new ResponseEntity("Servicio con id "+id+"no encontrado", HttpStatus.NOT_FOUND);
		}
		
		Usuario user = usuarioService.recuperarPorId(puntuaciones.get(0).getUsuario().getId());
		if (user == null) {
			return new ResponseEntity("Usuario con id "+puntuaciones.get(0).getUsuario().getId()+"no encontrado", HttpStatus.NOT_FOUND);
		}
		
		
		Evento evento = eventoService.recuperarPorId(puntuaciones.get(0).getEvento().getId());
		if (evento == null) {
			return new ResponseEntity("Evento con id "+puntuaciones.get(0).getEvento().getId()+"no encontrado", HttpStatus.NOT_FOUND);
		}
		
		List<Puntuacion> puntuacionesBD = puntuacionService.buscarCalificacionPorServicioEventoyUsuario(id,user.getId(), evento.getId());
		
		if(!puntuacionesBD.isEmpty()) {
			return new ResponseEntity("Ya puntuaste este servicio para el evento "+evento.getNombre(),HttpStatus.BAD_REQUEST);
		}
			
		for (Puntuacion p : puntuaciones) {
			p.setServicio(servicio);
			puntuacionService.guardar(p);
		}
		
		return new ResponseEntity<Servicio>(servicio, HttpStatus.OK);
		
	}
}
