package com.xyinc.service;

import com.xyinc.model.Usuario;

/**
 * @author wellington.fernandes
 * Inteface dos servicos do usuario
 */
public interface UsuarioService {
	
	public Boolean save(Usuario usuario);
	public Usuario getUserByUsername(String username);
	public Usuario getUserByLogin(String username, String password);

}
