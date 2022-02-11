package ttps.spring.DAO;

import java.util.List;

import ttps.spring.model.Evento;
import ttps.spring.model.Puntuacion;
import ttps.spring.model.Servicio;
import ttps.spring.model.Usuario;

public interface PuntuacionDAO extends BaseDAO<Puntuacion>{

	public List<Puntuacion> buscarCalificacionPorServicioEventoyUsuario(long servicioId, long usuarioId);
}
