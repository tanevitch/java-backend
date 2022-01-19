package ttps.spring.DAOImplement;

import org.springframework.stereotype.Repository;

import ttps.spring.DAO.PuntuacionDAO;
import ttps.spring.model.Puntuacion;

@Repository
public class PuntuacionDAOimpl extends BaseDAOimpl<Puntuacion> implements PuntuacionDAO{

	public PuntuacionDAOimpl() {
		super(Puntuacion.class);
	}

}