package ttps.spring.DAOImplement;

import org.springframework.stereotype.Repository;

import ttps.spring.DAO.FormaDePagoDAO;
import ttps.spring.model.FormaDePago;

@Repository
public class FormaPagoDAOimpl extends BaseDAOimpl<FormaDePago> implements FormaDePagoDAO {

	public FormaPagoDAOimpl() {
		super(FormaDePago.class);
	}

}
