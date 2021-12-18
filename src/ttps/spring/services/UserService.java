package ttps.spring.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import ttps.spring.DAO.UsuarioDAO;
import ttps.spring.model.Usuario;

@Service
@Transactional
public class UserService {
	@Autowired
	UsuarioDAO usuarioDAOImpl;
	
	public List<Usuario> listar() {
		return usuarioDAOImpl.listar();
	}
	
	public Usuario recuperarPorId(long id) {
		return usuarioDAOImpl.recuperarPorId(id);
	}
	
	public Usuario buscarUsuarioPorMail(String email) {
		return usuarioDAOImpl.buscarUsuarioPorMail(email);
	}
	
	public ResponseEntity crear(Usuario userNuevo) {
		Usuario user = usuarioDAOImpl.buscarUsuarioPorMail(userNuevo.getMail());
		if (user != null) {
			 System.out.println("Ya existe un usuario con mail " + userNuevo.getMail());
			return new ResponseEntity("Ya existe un usuario con mail " + userNuevo.getMail(), HttpStatus.CONFLICT); 
		}
		usuarioDAOImpl.guardar(userNuevo);
		return new ResponseEntity(HttpStatus.OK);
	}
	
	public ResponseEntity editar(Usuario userMod, long id) {
		
		Usuario user = usuarioDAOImpl.recuperarPorId(id);
		if (user == null) {
			 return new ResponseEntity("El usuario con id "+id+" es inválido", HttpStatus.BAD_REQUEST);		
		}
		 
		user.setNombre(userMod.getNombre());
		user.setApellido(userMod.getApellido());
		user.setContrasena(userMod.getContrasena());
		usuarioDAOImpl.editar(user);
		return new ResponseEntity(HttpStatus.OK);
	}
	
}
