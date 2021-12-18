package ttps.spring.DAOImplement;
import org.springframework.stereotype.Repository;

import ttps.spring.DAO.TipoServicioDAO;
import ttps.spring.model.TipoServicio;

@Repository
public class TipoServicioDAOimpl extends BaseDAOimpl<TipoServicio> implements TipoServicioDAO {
		public TipoServicioDAOimpl() {
		super(TipoServicio.class);
	}
}
