package ttps.spring.DAOImplement;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import ttps.spring.DAO.AspectoPuntuacionDAO;
import ttps.spring.model.AspectoPuntuacion;
import ttps.spring.model.Servicio;
import ttps.spring.model.TipoServicio;

@Repository
public class AspectoPuntuacionDAOimpl extends BaseDAOimpl<AspectoPuntuacion> implements AspectoPuntuacionDAO{

	public AspectoPuntuacionDAOimpl() {
		super(AspectoPuntuacion.class);
	}

	@Override
	public List<AspectoPuntuacion> buscarPorTipoDeServicio(TipoServicio tiposervicio) {
		Query consulta = this.getEntityManager().createQuery("select a from AspectoPuntuacion a WHERE a.tipoServicio = :tiposervicio");
		consulta.setParameter("tiposervicio", tiposervicio);
		return (List<AspectoPuntuacion>)consulta.getResultList();
	}

}
