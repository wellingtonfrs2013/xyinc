package com.xyinc.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.xyinc.R;
import com.xyinc.async.UsuarioAsync;
import com.xyinc.model.Usuario;
import com.xyinc.service.UsuarioService;
import com.xyinc.service.impl.UsuarioServiceImpl;

/**
 * @author wellington.fernandes
 * Tela de cadastro de um novo usuario
 */
public class UsuarioActivity extends Activity {

	private String TAG = UsuarioActivity.class.getName();

	private EditText edtNome;
	private EditText edtUsuario;
	private EditText edtSenha;
	private Gson gson;
	private UsuarioService usuarioService;
	
	public UsuarioActivity(){
		super();
		this.gson = new Gson();
		this.usuarioService = new UsuarioServiceImpl();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_insert_usuario);

		Log.d(TAG, "Entrou no metodo on create UsuarioActivity");

		this.edtNome = (EditText) findViewById(R.id.edtNomeUsuario);
		this.edtUsuario = (EditText) findViewById(R.id.edtUsuario);
		this.edtSenha = (EditText) findViewById(R.id.edtSenha);
	}
	
	public void onClickCadastrarUsuario(View v) {
		if(validaCampos()){
			final Usuario usuario = new Usuario();
	
			usuario.setNome(edtNome.getText().toString());
			usuario.setUsuario(edtUsuario.getText().toString());
			usuario.setSenha(edtSenha.getText().toString());
	
			new UsuarioAsync(this, new ProgressDialog(this), gson, usuarioService,usuario).execute(usuario);
		}
	}
	
	public void onClickCancelarVoltar(View v){
		Intent intent  = new Intent(this, LoginActivity.class);
    	startActivity(intent);
    	finish();
	}
	
	public Boolean validaCampos(){
		Boolean retorno = false;
		
		if(edtNome.getText().toString().equals("") || edtNome.getText().toString() == null){
			Toast toast= Toast.makeText(this, R.string.nome_obrigatorio, Toast.LENGTH_LONG);  
			toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 0);
			toast.show();
		}
		
		else if(edtSenha.getText().toString().equals("") || edtSenha.getText().toString() == null){
			Toast toast= Toast.makeText(this, R.string.senha_obrigatorio, Toast.LENGTH_LONG);  
			toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 0);
			toast.show();
		}
		
		else if(edtUsuario.getText().toString().equals("") || edtUsuario.getText().toString() == null){
			Toast toast= Toast.makeText(this, R.string.usuario_obrigatorio, Toast.LENGTH_LONG);  
			toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 0);
			toast.show();
			
		}else{
			retorno = true;
		}
		
		return retorno;
	}
	
 
	public void onUsuarioSave(boolean success, String mensagem) {
		if (success) {
			Toast toast= Toast.makeText(this, mensagem, Toast.LENGTH_LONG);  
			toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 0);
			toast.show();
			limparCampos();
		} else {
			Toast toast= Toast.makeText(this, mensagem, Toast.LENGTH_LONG);  
			toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 0);
			toast.show();
			limparCampos();
		}
	}
	
	public void limparCampos(){
		edtNome.setText("");
		edtSenha.setText("");
		edtUsuario.setText("");
	}
}
