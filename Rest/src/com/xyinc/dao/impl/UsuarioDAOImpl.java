package com.xyinc.dao.impl;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.xyinc.dao.UsuarioDAO;
import com.xyinc.model.Usuario;

import br.com.caelum.vraptor.ioc.Component;

/**
 * @author wellington.fernandes 
 * Implementacao para os metodos de persistencia e listagem do usuario
 */
@Component
public class UsuarioDAOImpl implements UsuarioDAO {

	private Session session;

	// Construtor
	public UsuarioDAOImpl(Session session) {
		this.session = session;
	}

	// Gravar um novo usuario no banco
	public void save(Usuario usuario) {
		Transaction tx = session.beginTransaction();
		this.session.save(usuario);
		tx.commit();
	}

	// Verifica se ja existe o nome do usuario que esta sendo gravado no banco
	public Usuario getUserByUsername(String username) {
		return (Usuario) this.session.createCriteria(Usuario.class)
				.add(Restrictions.eq("usuario", username)).uniqueResult();
	}

	// Verifica usuario e senha existe para que seja feito o login
	public Usuario getUserByLogin(String username, String password) {
		return (Usuario) this.session.createCriteria(Usuario.class)
				.add(Restrictions.eq("usuario", username))
				.add(Restrictions.eq("senha", password)).uniqueResult();
	}

}
