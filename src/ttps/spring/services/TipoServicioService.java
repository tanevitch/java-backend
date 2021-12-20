package ttps.spring.services;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ttps.spring.DAO.AspectoPuntuacionDAO;
import ttps.spring.DAO.TipoServicioDAO;
import ttps.spring.model.AspectoPuntuacion;
import ttps.spring.model.TipoServicio;

@Service
@Transactional
public class TipoServicioService {
	@Autowired
	TipoServicioDAO tipoServicioDAOImpl;
	@Autowired
	AspectoPuntuacionDAO aspectoPuntuacionDAOImpl;
	
	public List<TipoServicio> listar() {
		return tipoServicioDAOImpl.listar();
	}
	
	public TipoServicio recuperarTipoServicioPorId(long id) {
		return tipoServicioDAOImpl.recuperarPorId(id);
	}
	
	public List<AspectoPuntuacion> obtenerAspectosAValorar(TipoServicio ts){
		return aspectoPuntuacionDAOImpl.buscarPorTipoDeServicio(ts);
		
	}
}
