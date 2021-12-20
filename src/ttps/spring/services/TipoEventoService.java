package ttps.spring.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ttps.spring.DAO.TipoEventoDAO;
import ttps.spring.model.TipoEvento;

@Service
@Transactional
public class TipoEventoService {
	@Autowired
	TipoEventoDAO tipoEventoDAOImpl;

	public List<TipoEvento> listar() {
		return tipoEventoDAOImpl.listar();
	}

}
