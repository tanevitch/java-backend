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
	
	public List<Puntuacion> buscarCalificacionPorServicioEventoyUsuario(long servicioId, long usuarioId){
		Query consulta = this.getEntityManager().
				createQuery("select p from Puntuacion p WHERE p.servicio.id = :servicioId and p.usuario.id = :usuarioId")
		 .setParameter("servicioId", servicioId)
		 .setParameter("usuarioId", usuarioId);
		 return (List<Puntuacion>)consulta.getResultList();	

	}

}