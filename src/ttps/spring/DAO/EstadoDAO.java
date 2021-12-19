package ttps.spring.DAO;

import ttps.spring.model.Estado;

public interface EstadoDAO extends BaseDAO<Estado> {
	public Estado buscarEstadoPorNombre(String nombre);
}
