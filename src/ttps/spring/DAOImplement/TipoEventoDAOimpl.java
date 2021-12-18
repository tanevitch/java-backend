package ttps.spring.DAOImplement;

import org.springframework.stereotype.Repository;

import ttps.spring.DAO.BaseDAO;
import ttps.spring.DAO.TipoEventoDAO;
import ttps.spring.model.TipoEvento;

@Repository
public class TipoEventoDAOimpl  extends BaseDAOimpl<TipoEvento> implements TipoEventoDAO{
	public TipoEventoDAOimpl() {
	super(TipoEvento.class);
}
}
