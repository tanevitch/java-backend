package ttps.spring.services;

import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import ttps.spring.model.Usuario;

@Service
@Transactional
public class AuthorizationService {
	
	@Autowired
	UserService userService;
	
	public HttpStatus verificar(Map<String, String> credenciales) {
		Usuario userEncontrado = userService.buscarUsuarioPorMail(credenciales.get("mail"));
		if (userEncontrado == null || !userEncontrado.getContrasena().equals(credenciales.get("contrasena"))) {
			return HttpStatus.UNAUTHORIZED;
		}
		
		return HttpStatus.OK;
	}
}
