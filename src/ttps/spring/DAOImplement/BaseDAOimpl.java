package ttps.spring.DAOImplement;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import ttps.spring.DAO.BaseDAO;
import ttps.spring.model.Usuario;

@Transactional
public class BaseDAOimpl<T> implements BaseDAO<T>{	
	
	private EntityManager entityManager;

	
	 @PersistenceContext
	 public void setEntityManager(EntityManager em){
	 this.entityManager = em;
	 }
	
	 public EntityManager getEntityManager() {
	 return entityManager;
	 }
	 
	protected Class<T> persistentClass;

	public BaseDAOimpl(Class<T> clase) {
		persistentClass = clase;
	}
	 
	@Override
	public List<T> listar() {
		Query consulta = this.getEntityManager().createQuery("from "+ persistentClass.getSimpleName()+ " e");
		return (List<T>)consulta.getResultList();
	
	}

	@Override
	public void guardar(T entidad) {
		this.getEntityManager().persist(entidad);
	}

	@Override
	public T editar(T entidad) {
		this.getEntityManager().merge(entidad);
		return entidad;
	}

	@Override
	public void eliminar(long id) {
		T object = this.getEntityManager().find(persistentClass, id);
		this.getEntityManager().detach(object);;
	}

	@Override
	public T recuperarPorId(long id) {
		
		 Query consulta = this.getEntityManager().createQuery("select e from " + persistentClass.getSimpleName()+ " e where e.id =:identificador");
		 consulta.setParameter("identificador", id);
		 return (T)consulta.getResultList().stream().findFirst().orElse(null);
		
	}

	
}
