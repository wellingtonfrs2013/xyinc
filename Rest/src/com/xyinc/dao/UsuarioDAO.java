package com.xyinc.dao;

import com.xyinc.model.Usuario;

/**
 * @author wellington.fernandes
 * Interface para os metodos de persistencia e listagem do usuario
 */
public interface UsuarioDAO {
	
	public void save(Usuario usuario);
	public Usuario getUserByUsername(String username);
	public Usuario getUserByLogin(String username, String password);

}
