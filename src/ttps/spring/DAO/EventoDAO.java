package ttps.spring.DAO;

import java.util.List;

import ttps.spring.model.Evento;

public interface EventoDAO extends BaseDAO<Evento>{
	public List<Evento> buscarEventoPorNombre(String nombre); 
}
