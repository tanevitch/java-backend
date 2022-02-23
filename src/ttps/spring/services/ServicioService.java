package ttps.spring.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import ttps.spring.DAO.ServicioDAO;
import ttps.spring.DAO.TipoServicioDAO;
import ttps.spring.model.Evento;
import ttps.spring.model.Servicio;
import ttps.spring.model.TipoServicio;
import ttps.spring.model.Usuario;

@Service
@Transactional
public class ServicioService {
	@Autowired
	private ServicioDAO servicioDAOImpl;
	@Autowired
	private UserService usuarioService;
	@Autowired
	private TipoServicioDAO tipoServicioDAOImpl;

	public List<Servicio> listar() {
		return servicioDAOImpl.listar();
	}
	
	public List mejorPuntuados() {
		return servicioDAOImpl.serviciosMejorPuntuados();
	}

	public Servicio recuperarPorId(long id) {
		return servicioDAOImpl.recuperarPorId(id);
	}

	public ResponseEntity guardar(Servicio serviceNuevo) {
		Usuario user = usuarioService.recuperarPorId(serviceNuevo.getUsuario().getId());
		TipoServicio ts = tipoServicioDAOImpl.recuperarPorId(serviceNuevo.getTipoServicio().getId());
		if (user == null) {
			return new ResponseEntity("El user con id "+serviceNuevo.getUsuario().getId()+" es inválido", HttpStatus.BAD_REQUEST);		
		}
		if (ts == null) {
			return new ResponseEntity("El tipo de servicio con id "+serviceNuevo.getTipoServicio().getId()+" es inválido", HttpStatus.BAD_REQUEST);		
		}
		
		
		serviceNuevo.setTipoServicio(ts);
		serviceNuevo.setUsuario(user);
		servicioDAOImpl.guardar(serviceNuevo);		
		return new ResponseEntity(HttpStatus.OK);
	}

	public ResponseEntity editar(Servicio serviceMod, long id) {
		System.out.println("Obteniendo Servicio con id " + id);
		Servicio service = this.recuperarPorId(id);
		if (service == null) {
			System.out.println("Servicio con id "+ id + " no encontrado");
			 return new ResponseEntity("El servicio con id "+id+" es inválido", HttpStatus.BAD_REQUEST);		
		}
		
				
		Usuario user = usuarioService.recuperarPorId(serviceMod.getUsuario().getId());
		TipoServicio ts = tipoServicioDAOImpl.recuperarPorId(serviceMod.getTipoServicio().getId());
		if (user == null) {
			return new ResponseEntity("El user con id "+serviceMod.getUsuario().getId()+" es inválido", HttpStatus.BAD_REQUEST);		
		}
		if (ts == null) {
			return new ResponseEntity("El tipo de servicio con id "+serviceMod.getTipoServicio().getId()+" es inválido", HttpStatus.BAD_REQUEST);		
		}
		
		service.setNombre(serviceMod.getNombre());
		service.setDescripcion(serviceMod.getDescripcion());
		service.setWhatsapp(serviceMod.getWhatsapp());
		service.setInstagram(serviceMod.getInstagram());
		service.setUrl(serviceMod.getUrl());
		service.setTwitter(serviceMod.getTwitter());
		service.setFoto(serviceMod.getFoto());
		
		service.setTipoServicio(ts);
		service.setUsuario(user);
		
		servicioDAOImpl.editar(service);
		return new ResponseEntity(HttpStatus.OK);
		
	}
	
	public void borrar(Servicio service) {
		service.setBorrado(true);
		servicioDAOImpl.editar(service);
	}
	

	public List<Servicio> listarPorUsuarioId(Usuario usuario) {
		return servicioDAOImpl.buscarServicioPorUsuario(usuario);
	}	

	public List<Servicio> buscarServiciosQueNoSonDelUsuario(Usuario usuario){
		return servicioDAOImpl.buscarServiciosQueNoSonDelUsuario(usuario);
	}
	
	public List<Servicio> buscarServicioPorNombre(Usuario user, String nombre){
		return servicioDAOImpl.buscarServicioPorNombre(user, nombre);
	}
	
	public List<Servicio> buscarServicioPorCategoria(Usuario user, String nombre){
		return servicioDAOImpl.buscarServicioPorCategoria(user, nombre);
	}
	
	public List<Servicio> buscarServiciosDeUsuarioEvento(Usuario user, Evento evento){
		return servicioDAOImpl.buscarServiciosDeUsuarioEvento(user, evento);
	}
	
	public List<Servicio> buscarServicioPorNombreYCategoria(Usuario user, String nombre, String categoria){
		return servicioDAOImpl.buscarServicioPorNombreYCategoria(user, nombre, categoria);
	}
}
