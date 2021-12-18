package ttps.spring.DAOImplement;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ttps.spring.DAO.BaseDAO;
import ttps.spring.DAO.UsuarioDAO;
import ttps.spring.model.Servicio;
import ttps.spring.model.Usuario;

@Repository
public class UsuarioDAOimpl extends BaseDAOimpl<Usuario> implements UsuarioDAO{
	

	public UsuarioDAOimpl() {
		super(Usuario.class);
	}
	
	public Usuario buscarUsuarioPorMail(String email) {
		 Query consulta = this.getEntityManager().
				createQuery("select u from Usuario u where u.mail =:email");
		 consulta.setParameter("email", email);
		return (Usuario)consulta.getResultList().stream().findFirst().orElse(null);
	}
	
	
	


}
