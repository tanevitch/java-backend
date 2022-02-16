package ttps.spring.controllers;

import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ttps.spring.model.Puntuacion;
import ttps.spring.model.Servicio;
import ttps.spring.model.Usuario;
import ttps.spring.services.PuntuacionService;
import ttps.spring.services.ServicioService;
import ttps.spring.services.UserService;

@CrossOrigin
@RestController
@RequestMapping("/api/servicios")
public class ServicioRestController {

	
	@Autowired
	ServicioService servicioService;
	@Autowired	
	UserService usuarioService;
	@Autowired	
	PuntuacionService puntuacionService;
	
	@GetMapping(path="")
	public ResponseEntity<List<Servicio>> servicios(){
		List<Servicio> services = servicioService.listar();
		
		if(services.isEmpty()) {
			return new ResponseEntity("No hay resultados", HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<List<Servicio>>(services, HttpStatus.OK);
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Servicio> servicio(@PathVariable("id") long id){
		Servicio service = servicioService.recuperarPorId(id);
		if (service == null) {
			return new ResponseEntity("Servicio con id "+ id + " no encontrado", HttpStatus.NOT_FOUND);		
		}
		return new ResponseEntity<Servicio>(service, HttpStatus.OK);
	}
	
	
	@PostMapping("")
	public ResponseEntity<Servicio> crear(@RequestBody Servicio serviceNuevo){
		if (serviceNuevo.hasEmptyFields()){
			 return new ResponseEntity("Todos los campos son requeridos", HttpStatus.BAD_REQUEST);
		 }
		
		if (serviceNuevo.hasInvalidFields()) {
			return new ResponseEntity("Todos los campos deben tener como máximo 255 caracteres", HttpStatus.BAD_REQUEST);
		}
		ResponseEntity codigoRta =	servicioService.guardar(serviceNuevo);
		if (codigoRta.getStatusCode() != HttpStatus.OK) {
			return codigoRta;
		}
		return new ResponseEntity<Servicio>(serviceNuevo, HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Servicio> modificar(@PathVariable("id") long id, @RequestBody Servicio serviceMod){
		
		if (serviceMod.hasEmptyFields()) {
			return new ResponseEntity("Todos los campos son requeridos", HttpStatus.BAD_REQUEST);
		}
		
		if (serviceMod.hasInvalidFields()) {
			return new ResponseEntity("Todos los campos deben tener como máximo 255 caracteres", HttpStatus.BAD_REQUEST);
		}
		
		ResponseEntity codigoRta = servicioService.editar(serviceMod, id);
		if (codigoRta.getStatusCode() != HttpStatus.OK) {
			return codigoRta;
		}
		
		Servicio service = servicioService.recuperarPorId(id);
		return new ResponseEntity<Servicio>(service, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	 public ResponseEntity<Servicio> borrar(@PathVariable("id") long id) {
	 Servicio servicio = servicioService.recuperarPorId(id);
	 if (servicio == null) {
	 	return new ResponseEntity("Servicio con id "+id+"no encontrado", HttpStatus.NOT_FOUND);
	 }
	 
	 servicioService.borrar(servicio);
	 return new ResponseEntity<Servicio>(HttpStatus.OK);
	 }
	

	@GetMapping("/usuario/{id}")
	public ResponseEntity<List<Servicio>> listarPorUsuario(@PathVariable("id") long id){
		Usuario user = usuarioService.recuperarPorId(id);
		if (user == null) {
			return new ResponseEntity("Usuario con id "+id+"no encontrado", HttpStatus.NOT_FOUND);
		}
		List<Servicio> services = servicioService.listarPorUsuarioId(user);
		
		if(services.isEmpty()) {
			return new ResponseEntity("No hay resultados", HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Servicio>>(services, HttpStatus.OK);
	}	
	
	@GetMapping("/excepto_usuario/{id}")
	public ResponseEntity<List<Servicio>> listarExceptoUsuario(@PathVariable("id") long id){
		Usuario user = usuarioService.recuperarPorId(id);
		
		List<Servicio> services = servicioService.buscarServiciosQueNoSonDelUsuario(user);
		if(services.isEmpty()) {
			return new ResponseEntity("No hay resultados", HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Servicio>>(services, HttpStatus.OK);
	}
	

	@GetMapping("/por_nombre/{name}")
	public ResponseEntity<List<Servicio>> listarPorNombre(@PathVariable("name") String name){
		List<Servicio> services = servicioService.buscarServicioPorNombre(name);
		if(services.isEmpty()) {
			return new ResponseEntity("No hay resultados", HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Servicio>>(services, HttpStatus.OK);
	}
	
	@GetMapping("/por_categoria/{name}")
	public ResponseEntity<List<Servicio>> listarPorCategoria(@PathVariable("name") String name){
		System.out.println("llegué a servicioRestController");

		List<Servicio> services = servicioService.buscarServicioPorCategoria(name);
		if(services.isEmpty()) {
			return new ResponseEntity("No hay resultados", HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Servicio>>(services, HttpStatus.OK);
		
	}
	
}
