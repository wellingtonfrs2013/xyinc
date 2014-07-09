package com.xyinc.async;

import java.util.List;

import org.apache.http.NameValuePair;

import android.app.ProgressDialog;
import android.os.AsyncTask;

import com.google.gson.Gson;
import com.xyinc.activity.LoginActivity;
import com.xyinc.service.UsuarioService;
import com.xyinc.view.LoginView;


/**
 * @author wellington.fernandes
 * Requisicao do Login
 */
public class LoginAsync extends AsyncTask<String, String, Boolean> {
	private List<NameValuePair> data;
    private ProgressDialog progressDialog;
    private LoginActivity loginActivity;
    private Gson gson;
    private UsuarioService usuarioService;
    private LoginView loginView;
    
    //Construtor
    public LoginAsync(LoginActivity loginActivity, ProgressDialog progressDialog, Gson gson, UsuarioService usuarioService, List<NameValuePair> data, LoginView loginView) {
    	this.loginActivity = loginActivity;
        this.progressDialog = progressDialog;
        this.gson = gson;
        this.usuarioService = usuarioService;
        this.data = data;
        this.loginView = loginView;
    }
    
    @Override
	protected void onPreExecute(){
		progressDialog.setMessage("Logando...");
		progressDialog.setCancelable(false);
		progressDialog.show();
	}

    // Background
	@Override
	protected Boolean doInBackground(String... params) {
		return login().getSucesso();
	}
    
    public LoginView login() {
    	this.loginView = usuarioService.login(loginActivity, gson, data);
    	return this.loginView;
	}
    
    protected void onPostExecute(Boolean sucess) {
    	progressDialog.dismiss();
    	this.loginActivity.onUsuarioLogin(this.loginView);
    }
}
