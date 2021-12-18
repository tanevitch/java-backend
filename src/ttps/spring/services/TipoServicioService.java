package ttps.spring.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ttps.spring.DAO.TipoServicioDAO;
import ttps.spring.model.TipoServicio;

@Service
@Transactional
public class TipoServicioService {
	@Autowired
	TipoServicioDAO tipoServicioDAOImpl;
	
	public List<TipoServicio> listar() {
		return tipoServicioDAOImpl.listar();
	}
}
