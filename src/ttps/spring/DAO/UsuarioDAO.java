package ttps.spring.DAO;

import ttps.spring.model.Usuario;

public interface UsuarioDAO extends BaseDAO<Usuario> {
	public Usuario buscarUsuarioPorMail(String email);
}
