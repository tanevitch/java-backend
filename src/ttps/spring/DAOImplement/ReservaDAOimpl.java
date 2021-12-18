package ttps.spring.DAOImplement;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import ttps.spring.DAO.ReservaDAO;
import ttps.spring.model.Reserva;
import ttps.spring.model.Usuario;

@Repository
public class ReservaDAOimpl extends BaseDAOimpl<Reserva> implements ReservaDAO{
	public ReservaDAOimpl() {
		super(Reserva.class);
	}

	@Override
	public List<Reserva> buscarReservaPorUsuario(Usuario usuario) {
		Query consulta = this.getEntityManager().createQuery("select e from Reserva e WHERE e.usuario = :usuario");
		consulta.setParameter("usuario", usuario);
		return (List<Reserva>)consulta.getResultList();	
	}
}
