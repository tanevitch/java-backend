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
	public List<Servicio> buscarServicioPorNombre(String nombre) {
		Query consulta = this.getEntityManager().createQuery("select e from Servicio e where e.nombre like concat('%',:nombre,'%') and e.borrado = 0");
		consulta.setParameter("nombre", nombre);
		return (List<Servicio>)consulta.getResultList();
				
	}

	
	@Override
	public List<Servicio> buscarServicioPorCategoria(String categoria) {
		System.out.println("llegué a ServicioDAOImpl");

		Query consulta = this.getEntityManager().createQuery("select e from Servicio e INNER JOIN TipoServicio ts ON e.tipoServicio=ts.id WHERE ts.nombre = :categoria");
		consulta.setParameter("categoria", categoria);
		return (List<Servicio>)consulta.getResultList();
	}
	
	@Override
	public List<Servicio> buscarServicioPorUsuario(Usuario usuario) {
		Query consulta = this.getEntityManager().createQuery("select e from Servicio e WHERE e.usuario = :usuario and e.borrado = 0");
		consulta.setParameter("usuario", usuario);
		return (List<Servicio>)consulta.getResultList();	
		
	}

	@Override
	public List<Servicio> buscarServiciosQueNoSonDelUsuario(Usuario usuario) {
		Query consulta = this.getEntityManager().createQuery("select e from Servicio e WHERE e.usuario != :usuario and e.borrado = 0");
		consulta.setParameter("usuario", usuario);
		return (List<Servicio>)consulta.getResultList();	
		
	}
	

}
