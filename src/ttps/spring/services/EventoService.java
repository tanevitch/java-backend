package ttps.spring.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import ttps.spring.DAO.TipoEventoDAO;
import ttps.spring.DAO.EventoDAO;
import ttps.spring.model.Evento;
import ttps.spring.model.TipoEvento;
import ttps.spring.model.Usuario;

@Service
@Transactional
public class EventoService {
	@Autowired
	private EventoDAO eventoDAOImpl;
	@Autowired
	private TipoEventoDAO tipoEventoDAOImpl;
	@Autowired
	private UserService usuarioService;
	
	public List<Evento> listarPorUsuario(Usuario user) {
		return eventoDAOImpl.buscarEventoPorUsuario(user);
	}

	public Evento recuperarPorId(long id) {
		return eventoDAOImpl.recuperarPorId(id);
	}

	public ResponseEntity guardar(Evento eventoNuevo) {
		Usuario user = usuarioService.recuperarPorId(eventoNuevo.getUsuario().getId());
		TipoEvento te = tipoEventoDAOImpl.recuperarPorId(eventoNuevo.getTipoEvento().getId());

		if (te == null) {
			return new ResponseEntity("El tipo de evento con id "+eventoNuevo.getTipoEvento().getId()+" es inválido", HttpStatus.BAD_REQUEST);		
		}
		
		
		eventoNuevo.setTipoEvento(te);
		eventoNuevo.setUsuario(user);
		eventoDAOImpl.guardar(eventoNuevo);		
		return new ResponseEntity(HttpStatus.OK);
	}

	public void borrar(Evento evento) {
		evento.setBorrado(true);
		eventoDAOImpl.editar(evento);
		
	}

	public ResponseEntity editar(Evento eventoMod, long id) {
		// TODO Auto-generated method stub
		return null;
	}




}
