package ttps.spring.DAOImplement;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import ttps.spring.DAO.ServicioDAO;
import ttps.spring.model.Evento;
import ttps.spring.model.Servicio;
import ttps.spring.model.Usuario;

@Repository
public class ServicioDAOimpl extends BaseDAOimpl<Servicio> implements ServicioDAO{
		public ServicioDAOimpl() {
		super(Servicio.class);
	}
		
	@Override
	public List<Servicio> listar(){
		Query consulta = this.getEntityManager().createQuery("select s from Servicio s where s.borrado = false");
		return (List<Servicio>)consulta.getResultList();	
	}

	@Override
	public List<Servicio> buscarServicioPorNombre(Usuario usuario, String nombre) {
		Query consulta = this.getEntityManager().createQuery("select e from Servicio e where e.usuario != :usuario and e.nombre like concat('%',:nombre,'%') and e.borrado = 0");
		consulta.setParameter("nombre", nombre)
		.setParameter("usuario", usuario);
		return (List<Servicio>)consulta.getResultList();
				
	}

	
	@Override
	public List<Servicio> buscarServicioPorCategoria(Usuario usuario, String categoria) {
		Query consulta = this.getEntityManager().createQuery("select e from Servicio e INNER JOIN TipoServicio ts ON e.tipoServicio=ts.id WHERE ts.id = :categoria and e.usuario != :usuario and e.borrado = 0");
		consulta.setParameter("categoria", Long.parseLong(categoria))
		.setParameter("usuario", usuario);
		return (List<Servicio>)consulta.getResultList();
	}
	
	@Override
	public List<Servicio> buscarServicioPorUsuario(Usuario usuario) {
		Query consulta = this.getEntityManager().createQuery("select e from Servicio e WHERE e.usuario = :usuario and e.borrado = 0");
		consulta.setParameter("usuario", usuario);
		return (List<Servicio>)consulta.getResultList();	
		
	}
	
	@Override
	public List<Servicio> buscarServicioPorNombreYCategoria(Usuario usuario, String nombreServicio, String categoria) {
		Query consulta = this.getEntityManager().createQuery("select e from Servicio e INNER JOIN TipoServicio ts ON e.tipoServicio=ts.id WHERE ts.id = :categoria and e.usuario != :usuario and e.borrado = 0 and e.nombre like concat('%',:nombre,'%')");
		consulta.setParameter("categoria", Long.parseLong(categoria))
		.setParameter("nombre", nombreServicio)
		.setParameter("usuario", usuario);
		return (List<Servicio>)consulta.getResultList();
	}

	@Override
	public List<Servicio> buscarServiciosQueNoSonDelUsuario(Usuario usuario) {
		Query consulta = this.getEntityManager().createQuery("select e from Servicio e WHERE e.usuario != :usuario and e.borrado = 0");
		consulta.setParameter("usuario", usuario);
		return (List<Servicio>)consulta.getResultList();	
		
	}
	
	@Override
	public List serviciosMejorPuntuados(){
		Query consulta = this.getEntityManager().createQuery("select avg(p.nota) as puntuacion, e, e.usuario from Servicio e INNER JOIN Puntuacion p ON e.id = p.servicio WHERE e.borrado = 0 GROUP BY e.id order by puntuacion desc");
		consulta.setMaxResults(5);
		return consulta.getResultList();	
	}
	
	@Override 
	public List<Servicio> buscarServiciosDeUsuarioEvento(Usuario usuario, Evento evento) {
		Query consulta = this.getEntityManager().createQuery("select s from Servicio s INNER JOIN Reserva r ON s.id = r.servicio where r.usuario = :usuario and r.evento = :evento and s.borrado = 0");
		consulta.setParameter("evento", evento)
		.setParameter("usuario", usuario);
		return (List<Servicio>)consulta.getResultList();
				
	}
	
	

}
