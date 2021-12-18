package ttps.spring.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ttps.spring.DAO.FormaDePagoDAO;
import ttps.spring.model.FormaDePago;

@Service
@Transactional
public class FormaDePagoService {
	@Autowired
	private FormaDePagoDAO formaPagoDAOImpl;
	
	public List<FormaDePago> obtenerTodas() {
		return formaPagoDAOImpl.listar();
	}

}
