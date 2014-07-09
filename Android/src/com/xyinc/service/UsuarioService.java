package com.xyinc.service;

import java.util.List;

import org.apache.http.NameValuePair;

import android.content.Context;

import com.google.gson.Gson;
import com.xyinc.model.Usuario;
import com.xyinc.view.LoginView;


/**
 * @author wellington.fernandes
 * Interfaces dos servicos do usuario
 */
public interface UsuarioService {
	
	public Boolean validaUsuario(Context context , String usuario);
	public Boolean save(Context context, Gson gson, Usuario usuario);
	public LoginView login(Context context, Gson gson,  List<NameValuePair> data);

}
