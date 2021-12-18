package ttps.spring.DAO;

import java.util.List;

import ttps.spring.model.Servicio;
import ttps.spring.model.Usuario;

public interface ServicioDAO extends BaseDAO<Servicio>{
	public List<Servicio> buscarServicioPorNombre(String nombre);
	public List<Servicio> buscarServicioPorCategoria(String categoria);
	public List<Servicio> buscarServicioPorUsuario(Usuario usuario);
	public List<Servicio> buscarServiciosQueNoSonDelUsuario(Usuario usuario);	
}
