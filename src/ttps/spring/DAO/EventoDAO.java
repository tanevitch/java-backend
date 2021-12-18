package ttps.spring.DAO;

import java.util.List;

import ttps.spring.model.Evento;
import ttps.spring.model.Usuario;

public interface EventoDAO extends BaseDAO<Evento>{
	public List<Evento> buscarEventoPorNombre(String nombre);

	public List<Evento> buscarEventoPorUsuario(Usuario user); 
}
