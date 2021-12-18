package ttps.spring.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ttps.spring.DAO.UsuarioDAO;
import ttps.spring.model.Servicio;
import ttps.spring.model.Usuario;
import ttps.spring.services.AuthorizationService;
import ttps.spring.services.TokenService;
import ttps.spring.services.UserService;

@CrossOrigin
@RestController
public class AuthRestController {
	
	@Autowired	
	private UserService usuarioService;
	
	@Autowired
	private AuthorizationService authService;
	
	@Autowired
	private TokenService tokenService;
	private final int EXPIRATION_IN_SEC= 3600;
	
	@PostMapping("/login")
	public ResponseEntity<Map<String, String>> login(@RequestBody Map<String, String> credenciales){
		if (credenciales.get("mail") == "" || credenciales.get("contrasena") == "") {
			return new ResponseEntity("El email y la contraseña no pueden estar vacios", HttpStatus.BAD_REQUEST);
		}
		HttpStatus codigoRta = authService.verificar(credenciales);
		if (codigoRta != HttpStatus.OK) {
			return new ResponseEntity("Email o contraseña incorrecta", codigoRta);
		}
		String token = tokenService.generarToken(credenciales.get("mail"), EXPIRATION_IN_SEC);
		Map<String, String> data = new HashMap<String, String>();
		Usuario user = usuarioService.buscarUsuarioPorMail(credenciales.get("mail"));
		data.put("user_id", Long.toString(user.getId()));
		data.put("email", user.getMail());
		data.put("token", token);
		return new ResponseEntity<Map<String, String>>(data, HttpStatus.OK);
	}
	
	@PostMapping("/registrarse")
	public ResponseEntity<Usuario> registrarse(@RequestBody Usuario userNuevo){
		 if (userNuevo.hasEmptyFields()) {
			 return new ResponseEntity("Todos los campos son requeridos", HttpStatus.BAD_REQUEST);
		 }
		 
		 ResponseEntity codigoRta = usuarioService.crear(userNuevo);
		 if (codigoRta.getStatusCode() != HttpStatus.OK) {
			 return codigoRta;
		 }
		 return new ResponseEntity<Usuario>(userNuevo, HttpStatus.CREATED);
	}
	
}
