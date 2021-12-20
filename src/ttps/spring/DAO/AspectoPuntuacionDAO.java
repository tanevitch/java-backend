package ttps.spring.DAO;

import java.util.List;

import ttps.spring.model.AspectoPuntuacion;
import ttps.spring.model.TipoServicio;

public interface AspectoPuntuacionDAO extends BaseDAO<AspectoPuntuacion>{

	List<AspectoPuntuacion> buscarPorTipoDeServicio(TipoServicio tiposervicio);

}
