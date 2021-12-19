package ttps.spring.DAO;

import java.util.List;

import ttps.spring.model.Estado;
import ttps.spring.model.Reserva;
import ttps.spring.model.Servicio;
import ttps.spring.model.Usuario;

public interface ReservaDAO extends BaseDAO<Reserva>{
	public List<Reserva> buscarReservaPorUsuario(Usuario usuario);

	public List<Reserva> buscarReservaPorServicio(Servicio servicio, Estado estado);
}
