package ttps.spring.DAO;

import java.util.List;

import ttps.spring.model.Evento;
import ttps.spring.model.Servicio;
import ttps.spring.model.Usuario;

public interface ServicioDAO extends BaseDAO<Servicio>{
	public List<Servicio> buscarServicioPorNombre(Usuario usuario, String nombre);
	public List serviciosMejorPuntuados();
	public List<Servicio> buscarServiciosDeUsuarioEvento(Usuario usuario, Evento evento);
	public List<Servicio> buscarServicioPorCategoria(Usuario usuario, String categoria);
	public List<Servicio> buscarServicioPorNombreYCategoria(Usuario usuario, String nombre, String categoria);
	public List<Servicio> buscarServicioPorUsuario(Usuario usuario);
	public List<Servicio> buscarServiciosQueNoSonDelUsuario(Usuario usuario);	
}
