package com.xyinc.activity;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.xyinc.R;
import com.xyinc.async.LoginAsync;
import com.xyinc.service.UsuarioService;
import com.xyinc.service.impl.UsuarioServiceImpl;
import com.xyinc.view.LoginView;

/**
 * @author wellington.fernandes 
 * Activity Tela Login
 */
public class LoginActivity extends Activity {

	private static LoginActivity singleton;

	private Long authenticatedUserId = null;
	
	private EditText edtUsuario;
	private EditText edtSenha;
	
	private Gson gson;
	private UsuarioService usuarioService;
	
	public LoginActivity() {
		super();
		this.gson = new Gson();
		this.usuarioService = new UsuarioServiceImpl();
	}

	@SuppressWarnings("static-access")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		this.singleton = this;

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		this.edtUsuario = (EditText) findViewById(R.id.edtUsuario);
		this.edtSenha = (EditText) findViewById(R.id.edtSenha);
	}
	
	public void onClickCadastrar(View v){
		Intent it = new Intent(getApplicationContext(),UsuarioActivity.class);
		startActivity(it);
		finish();
	}
	
	public void onClickLogin(View v){
		if(validaCampos()){
			doLogin();
		}
	}

	private void doLogin() {
		
		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);

		nameValuePairs.add(new BasicNameValuePair("usuario", edtUsuario.getText().toString()));
		nameValuePairs.add(new BasicNameValuePair("senha", edtSenha.getText().toString()));

		new LoginAsync(this, new ProgressDialog(this), gson, usuarioService, nameValuePairs, new LoginView()).execute("");

	}

	public Long getAuthenticatedUserId() {
		return authenticatedUserId;
	}

	public void setAuthenticatedUserId(Long authenticatedUserId) {
		this.authenticatedUserId = authenticatedUserId;
	}

	public static LoginActivity getInstance() {
		return singleton;
	}
	
	public Boolean validaCampos(){
		Boolean retorno = false;
		
		if(edtUsuario.getText().toString().equals("") || edtUsuario.getText().toString() == null){
			Toast toast= Toast.makeText(this, R.string.usuario_obrigatorio, Toast.LENGTH_LONG);  
			toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 0);
			toast.show();
		}
		
		else if(edtSenha.getText().toString().equals("") || edtSenha.getText().toString() == null){
			Toast toast= Toast.makeText(this, R.string.senha_obrigatorio, Toast.LENGTH_LONG);  
			toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 0);
			toast.show();
		}else{
			retorno = true;
		}
		
		return retorno;
	}
	
	@Override
	public void onBackPressed() {
		finish();
	}
	
	public void onUsuarioLogin(LoginView loginView) {
		if (loginView.getSucesso()) {
			this.authenticatedUserId = loginView.getToken();
			Intent intent  = new Intent(this, MainActivity.class);
	    	intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
	    	intent.putExtra("idUsuario", this.authenticatedUserId);
	    	startActivity(intent);
		} else {
			Toast toast= Toast.makeText(this, R.string.login_nao_existe, Toast.LENGTH_LONG);  
			toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 0);
			toast.show();
		}
	}
}
