package com.xyinc.service.impl;

import br.com.caelum.vraptor.ioc.Component;

import com.xyinc.dao.UsuarioDAO;
import com.xyinc.model.Usuario;
import com.xyinc.service.UsuarioService;

/**
 * @author wellington.fernandes 
 * Implementacao dos servicos do usuario
 */
@Component
public class UsuarioServiceImpl implements UsuarioService {

	private UsuarioDAO usuarioDAO;

	// Construtor
	public UsuarioServiceImpl(UsuarioDAO usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}

	// Grava um novo usuario no banco
	public Boolean save(Usuario usuario) {
		try {
			this.usuarioDAO.save(usuario);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	// Verifica se ja existe o nome do usuario que esta sendo gravado no banco
	public Usuario getUserByUsername(String username) {
		try {
			return this.usuarioDAO.getUserByUsername(username);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// Verifica usuario e senha existe para que seja feito o login
	public Usuario getUserByLogin(String username, String password) {
		try {
			return this.usuarioDAO.getUserByLogin(username, password);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
