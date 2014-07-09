package com.xyinc.controller;

import static br.com.caelum.vraptor.view.Results.json;
import br.com.caelum.vraptor.Consumes;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

import com.xyinc.model.Usuario;
import com.xyinc.service.UsuarioService;
import com.xyinc.views.LoginView;

/**
 * @author wellington.fernandes 
 * Controller do usuario
 */
@Resource
public class UsuarioController {

	private UsuarioService usuarioService;
	private Result result;

	// Construtor
	public UsuarioController(UsuarioService usuarioService, Result result) {
		this.usuarioService = usuarioService;
		this.result = result;
	}

	// Grava usuario no banco
	@Post("/usuario/cadastrar")
	@Consumes(value = { "application/json", "application/x-www-form-urlencoded" })
	public void save(Usuario usuario) {
		Boolean resultado = this.usuarioService.save(usuario);
		result.use(json()).from(resultado).serialize();
	}

	// Validacao do usuario por nome
	@Get("/usuario/validarUsuario/{usuario}")
	public void getUserByUsername(String usuario) {
		Usuario user = new Usuario();
		user = this.usuarioService.getUserByUsername(usuario);
		if (user != null) {
			result.use(json()).from(true).serialize();
		} else {
			result.use(json()).from(false).serialize();
		}
	}

	// Logar usuario pelo android
	@Post("/usuario/login")
	@Consumes(value = { "application/json", "application/x-www-form-urlencoded" })
	public void logar(String usuario, String senha) {
		try {
			if (usuario == null || usuario.isEmpty() || senha == null|| senha.isEmpty()) {

				result.use(json()).from(new LoginView("Usuario e Senha Obrigatorio.", false, 0L)).serialize();
			}

			final Usuario usuarioBD = this.usuarioService.getUserByLogin(usuario, senha);

			if (usuarioBD == null) {

				result.use(json()).from(new LoginView("Usuario ou senha Invalida.",false, 0L)).serialize();

			} else {

				result.use(json()).from(new LoginView("", true, usuarioBD.getId())).serialize();
				result.nothing();
			}

		} catch (Exception e) {
			result.use(json()).from(new LoginView("Erro ao validar usuario.", false, 0L)).serialize();
		}
	}

}
