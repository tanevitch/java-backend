package ttps.spring.controllers;

import org.springframework.web.bind.annotation.*;

import ttps.spring.services.UserService;
import ttps.spring.model.Usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.List;

import javax.transaction.Transactional;

@CrossOrigin
@RestController
@RequestMapping("/api/usuarios")
public class UsuarioRestController {
	
	@Autowired	
	private UserService usuarioService;
	
	@GetMapping(path="")
	public ResponseEntity<List<Usuario>> listAllUser(){
		List<Usuario> users = usuarioService.listar();
		if(users.isEmpty()) {
			return new ResponseEntity("No hay resultados", HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Usuario>>(users, HttpStatus.OK);
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Usuario> getUser(@PathVariable("id") long id){
		Usuario user = usuarioService.recuperarPorId(id);
		if (user == null) {
			return new ResponseEntity("Usuario con id "+ id + " no encontrado", HttpStatus.NOT_FOUND);		
		}
		return new ResponseEntity<Usuario>(user, HttpStatus.OK);
	}
	
	@PostMapping("")
	public ResponseEntity<Usuario> createUser(@RequestBody Usuario userNuevo){
		 if (userNuevo.hasEmptyFields()) {
			 return new ResponseEntity("Todos los campos son requeridos", HttpStatus.BAD_REQUEST);
		 }
		 
		 if (userNuevo.hasInvalidFields()) {
			 return new ResponseEntity("Todos los campos tienen como limite 255 caracteres", HttpStatus.BAD_REQUEST);
		 }
 
		 
		 ResponseEntity codigoRta = usuarioService.crear(userNuevo);
		 if (codigoRta.getStatusCode() != HttpStatus.OK) {
			 return codigoRta;
		 }
		 return new ResponseEntity<Usuario>(userNuevo, HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Usuario> updateUser(@PathVariable("id") long id, @RequestBody Usuario userMod){
		if(userMod.getContrasena().equals("")) {
			 Usuario userOriginal = usuarioService.recuperarPorId(id);
			 userMod.setContrasena(userOriginal.getContrasena());
		 }
		 if (userMod.hasEmptyFields()) {
			 return new ResponseEntity("Todos los campos son requeridos", HttpStatus.BAD_REQUEST);
		 }

		 if (userMod.hasInvalidFields()) {
			 return new ResponseEntity("Todos los campos tienen como limite 255 caracteres", HttpStatus.BAD_REQUEST);
		 }
		 ResponseEntity codigoRta = usuarioService.editar(userMod, id);
		 if (codigoRta.getStatusCode() != HttpStatus.OK) {
			 return codigoRta; 
		 }
		 
		 Usuario user = usuarioService.recuperarPorId(id);
		
   		 return new ResponseEntity<Usuario>(user, HttpStatus.OK);
	}
}