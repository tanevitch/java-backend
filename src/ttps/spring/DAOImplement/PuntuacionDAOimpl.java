package ttps.spring.DAOImplement;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import ttps.spring.DAO.PuntuacionDAO;
import ttps.spring.model.Puntuacion;
import ttps.spring.model.Servicio;

@Repository
public class PuntuacionDAOimpl extends BaseDAOimpl<Puntuacion> implements PuntuacionDAO{

	public PuntuacionDAOimpl() {
		super(Puntuacion.class);
	}
	
	public List<Puntuacion> buscarCalificacionPorServicioEventoyUsuario(long servicioId, long usuarioId, long eventoId){
		Query consulta = this.getEntityManager().
				createQuery("select p from Puntuacion p WHERE p.servicio.id = :servicioId and p.usuario.id = :usuarioId and p.evento.id = :eventoId")
		 .setParameter("servicioId", servicioId)
		 .setParameter("usuarioId", usuarioId)
		 .setParameter("eventoId", eventoId);
		 return (List<Puntuacion>)consulta.getResultList();	

	}

	public List<Puntuacion> buscarCalificacionPorServicio(long servicioId) {
		Query consulta = this.getEntityManager().
				createQuery("select p from Puntuacion p WHERE p.servicio.id = :servicioId")
		 .setParameter("servicioId", servicioId);
		 return (List<Puntuacion>)consulta.getResultList();	
	}

}