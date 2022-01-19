package ttps.spring.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import ttps.spring.DAO.PuntuacionDAO;
import ttps.spring.model.Puntuacion;

@Service
@Transactional
public class PuntuacionService {
	@Autowired
	private PuntuacionDAO puntuacionDAOImpl;

	public ResponseEntity guardar(Puntuacion puntuacionNueva) {

		puntuacionDAOImpl.guardar(puntuacionNueva);		
		return new ResponseEntity(HttpStatus.OK);
	}

}
