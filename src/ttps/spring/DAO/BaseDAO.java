package ttps.spring.DAO;

import java.util.List;

public interface BaseDAO<T> {
	public void guardar(T base);
	
	public List<T> listar();
	
	public T editar(T base);
	
	public void eliminar(long id);
	
	public T recuperarPorId(long id);
	
}
