package ttps.spring.DAOImplement;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import ttps.spring.DAO.EstadoDAO;
import ttps.spring.model.Estado;
@Repository
public class EstadoDAOimpl extends BaseDAOimpl<Estado> implements EstadoDAO{
	public EstadoDAOimpl() {
	super(Estado.class);
}

	@Override
	public Estado buscarEstadoPorNombre(String nombre) {
		Query consulta = this.getEntityManager().createQuery("select e from Estado e where e.nombre like concat('%',:nombre,'%')");
		consulta.setParameter("nombre", nombre);
		return (Estado)consulta.getResultList().stream().findFirst().orElse(null);
	}
}
