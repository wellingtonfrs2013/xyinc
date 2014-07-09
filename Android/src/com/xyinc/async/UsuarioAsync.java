package com.xyinc.async;

import android.app.ProgressDialog;
import android.os.AsyncTask;

import com.google.gson.Gson;
import com.xyinc.activity.UsuarioActivity;
import com.xyinc.model.Usuario;
import com.xyinc.service.UsuarioService;

public class UsuarioAsync extends AsyncTask<Usuario, String, Boolean> {
	
	private Usuario usuario;
	private ProgressDialog progressDialog;
	private UsuarioActivity activity;
	private UsuarioService usuarioService;
	private Gson gson;
	private String mensagem;

	// Construtor
	public UsuarioAsync(UsuarioActivity activity, ProgressDialog progressDialog, Gson gson, UsuarioService usuarioService, Usuario usuario) {
		this.activity = activity;
		this.progressDialog = progressDialog;
		this.gson = gson;
		this.usuarioService = usuarioService;
		this.usuario = usuario;
	}

	@Override
	protected void onPreExecute() {
		progressDialog.setMessage("Salvando Usuario...");
		progressDialog.setCancelable(false);
		progressDialog.show();
	}

	// Backgroud
	@Override
	protected Boolean doInBackground(Usuario...params) {
		this.usuario = params[0];
		return save(this.usuario);
	}
	
	public Boolean save(Usuario usuario) {
		Boolean sucesso = false;
		Boolean validado = usuarioService.validaUsuario(activity,usuario.getUsuario());
		try {
			if (validado == false) {
				sucesso = usuarioService.save(activity, gson, usuario);
				this.mensagem = "Usuário gravado com sucesso";
			} else {
				this.mensagem = "Este usuário já existe";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return sucesso;
	}

	// Recupera o resultado
	@Override
	protected void onPostExecute(Boolean result) {
		progressDialog.dismiss();
		this.activity.onUsuarioSave(result, this.mensagem);
	}

}
