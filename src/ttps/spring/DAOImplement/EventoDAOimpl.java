package ttps.spring.DAOImplement;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import ttps.spring.DAO.EventoDAO;
import ttps.spring.model.Evento;

@Repository
public class EventoDAOimpl extends BaseDAOimpl<Evento> implements EventoDAO{
		public EventoDAOimpl() {
		super(Evento.class);
	}
		
	public List<Evento> buscarEventoPorNombre(String nombre) {
		Query consulta = this.getEntityManager().
				createQuery("select e from Evento e where e.nombre like concat('%',:nombre,'%')");
		 consulta.setParameter("nombre", nombre);
		 return (List<Evento>)consulta.getResultList();
		
	}
}
