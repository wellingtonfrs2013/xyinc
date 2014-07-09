package com.xyinc.service.impl;

import java.io.IOException;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.net.ParseException;
import android.util.Log;

import com.google.gson.Gson;
import com.xyinc.enums.ServicosURL;
import com.xyinc.model.Usuario;
import com.xyinc.service.UsuarioService;
import com.xyinc.utils.HttpHelpImpl;
import com.xyinc.utils.IHttpHelp;
import com.xyinc.view.LoginView;


/**
 * @author wellington.fernandes
 * Implementa dos servicos ligado ao usuario
 */
public class UsuarioServiceImpl implements UsuarioService {
	
	private final String TAG = this.getClass().getName();
	private IHttpHelp httpHelp; 
	
	public UsuarioServiceImpl(){
		this.httpHelp = new HttpHelpImpl();
	}
	
	// Validar se o novo usuario que esta sendo cadastrado existe
	public Boolean validaUsuario(Context context, String usuario) {
		
		Boolean validado = false;
		try {
			
			JSONObject json = new JSONObject();

			String url = new StringBuilder().append(ServicosURL.VALIDA_USUARIO.getUrl()).append("/").append(usuario).toString();

			json = this.httpHelp.getJson(url, context);

			if (json != null && json.length() > 0) {
				try {
					validado = json.getBoolean("boolean");
				} catch (JSONException e) {
					Log.e("JSON Parser", "Erro ao parsear dados " + e.toString());
				}
			}

		} catch (IOException ex) {
			Log.e(TAG, "Erro ao validar usuario.", ex);
		}
		return validado;
	}
	
	// Gravar um novo usuario
	public Boolean save(Context context, Gson gson, Usuario usuario) {
		
		Boolean sucesso = false;
		JSONObject jsonObject = new JSONObject();

		String url = ServicosURL.CADASTRAR_USUARIO.getUrl();

		try {
			
			jsonObject.put("nome", usuario.getNome());
			jsonObject.put("usuario", usuario.getUsuario());
			jsonObject.put("senha", usuario.getSenha());
			sucesso = this.httpHelp.postJson(url, "usuario", jsonObject,context);
			
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return sucesso;
	}
	
	// Efetuar login
	public LoginView login(Context context, Gson gson,  List<NameValuePair> data) {
		
		JSONObject retorno = new JSONObject();

		String url = ServicosURL.LOGIN.getUrl();
		LoginView loginView = new LoginView();

		try {
		
			retorno = this.httpHelp.jsonPostParameters(url, data, context);
			JSONObject loginViewOject = retorno.getJSONObject("loginView");
			loginView.setToken(loginViewOject.getLong("token"));
			loginView.setSucesso(loginViewOject.getBoolean("sucesso"));
			loginView.setErro(loginViewOject.getString("erro"));
			
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return loginView;
	}

}
