package ttps.spring.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import ttps.spring.DAO.EstadoDAO;
import ttps.spring.DAO.ReservaDAO;
import ttps.spring.model.Estado;
import ttps.spring.model.Evento;
import ttps.spring.model.Reserva;
import ttps.spring.model.Servicio;
import ttps.spring.model.TipoServicio;
import ttps.spring.model.Usuario;

@Service
@Transactional
public class ReservaService {
	@Autowired
	private UserService usuarioService;
	@Autowired
	private EventoService eventoService;
	@Autowired
	private ServicioService servicioService;
	@Autowired
	private ReservaDAO reservaDAOImpl;
	@Autowired
	private EstadoDAO estadoDAOImpl;
	
	public List<Reserva> listarPorUsuarioId(Usuario user) {
		return reservaDAOImpl.buscarReservaPorUsuario(user);
	}
	
	public ResponseEntity guardar(Reserva reservaNueva) {
		Usuario user = usuarioService.recuperarPorId(reservaNueva.getUsuario().getId());
		Servicio servicio = servicioService.recuperarPorId(reservaNueva.getServicio().getId());
		Evento evento = eventoService.recuperarPorId(reservaNueva.getEvento().getId());
		if (user == null) {
			return new ResponseEntity("El user con id "+reservaNueva.getUsuario().getId()+" es inválido", HttpStatus.BAD_REQUEST);		
		}
		if (servicio == null) {
			return new ResponseEntity("El servicio con id "+reservaNueva.getServicio().getId()+" es inválido", HttpStatus.BAD_REQUEST);		
		}
		if (evento == null) {
			return new ResponseEntity("El evento con id "+reservaNueva.getEvento().getId()+" es inválido", HttpStatus.BAD_REQUEST);		
		}
		
		if (servicio.esOfrecidoPor(user)) {
			return new ResponseEntity("No puede reservar un servicio ofrecido por sí mismo", HttpStatus.BAD_REQUEST);		

		}
		
		if (! evento.perteneceA(user)) {
			return new ResponseEntity("Solo pueden registrarse reservas para eventos propios", HttpStatus.BAD_REQUEST);		
		}
		Estado estado = estadoDAOImpl.buscarEstadoPorNombre(Estado.SINCONFIRMAR);
		reservaNueva.setEstado(estado);
		reservaNueva.setServicio(servicio);
		reservaNueva.setUsuario(user);
		reservaNueva.setEvento(evento);
		reservaDAOImpl.guardar(reservaNueva);		
		return new ResponseEntity(HttpStatus.OK);
	}

	public List<Reserva> listarPorServicio(Servicio servicio) {
		Estado estado = estadoDAOImpl.buscarEstadoPorNombre(Estado.SINCONFIRMAR);
		return reservaDAOImpl.buscarReservaPorServicio(servicio, estado);
	}

	public Reserva recuperarPorId(long id) {
		return reservaDAOImpl.recuperarPorId(id);
	}

	public ResponseEntity cambiarEstado(Reserva reserva, Estado estado) {
		reserva.setEstado(estado);
		reservaDAOImpl.editar(reserva);
		return new ResponseEntity(HttpStatus.OK);
	}

}
