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
import com.xyinc.async.PoiPostAsync;
import com.xyinc.model.PontoReferencia;
import com.xyinc.service.PoiService;
import com.xyinc.service.impl.PoiServiceImpl;

/**
 * @author wellington.fernandes
 * Tela de cadastrar POI
 */
public class PoiCadastrarActivity extends Activity {
	
	private String TAG = PoiCadastrarActivity.class.getName();

	private EditText edtNomeReferencia;
	private EditText edtCoordenadaX;
	private EditText edtCoordenadaY;
	private Gson gson;
	private PoiService poiService;
	
	public PoiCadastrarActivity(){
		super();
		this.gson = new Gson();
		this.poiService = new PoiServiceImpl();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_insert_pontos);

		Log.d(TAG, "Entrou no metodo on create PoiCadastrarActivity");

		this.edtNomeReferencia = (EditText) findViewById(R.id.edtNomeReferencia);
		this.edtCoordenadaX = (EditText) findViewById(R.id.edtCoordenadaX);
		this.edtCoordenadaY = (EditText) findViewById(R.id.edtCoordenadaY);

	}
	
	public void onClickCancelarVoltar(View v){
		Intent intent  = new Intent(this, MainActivity.class);
    	startActivity(intent);
    	finish();
	}
	
	public void onClickCadastrarPoi(View v) {
		if(validaCampos()){
			final PontoReferencia pontoReferencia = new PontoReferencia();
	
			pontoReferencia.setNome(edtNomeReferencia.getText().toString());
			pontoReferencia.setCoordenadaX(Integer.parseInt(edtCoordenadaX.getText().toString()));
			pontoReferencia.setCoordenadaY(Integer.parseInt(edtCoordenadaY.getText().toString()));
			pontoReferencia.setIdUsuario(LoginActivity.getInstance().getAuthenticatedUserId());
	
			new PoiPostAsync(this, new ProgressDialog(this), gson, poiService,pontoReferencia).execute(pontoReferencia);
		}
	}
	
	public Boolean validaCampos(){
		Boolean retorno = false;
		
		if(edtNomeReferencia.getText().toString().equals("") || edtNomeReferencia.getText().toString() == null){
			Toast toast= Toast.makeText(this, R.string.nome_obrigatorio, Toast.LENGTH_LONG);  
			toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 0);
			toast.show();
		}
		
		else if(edtCoordenadaY.getText().toString().equals("") || edtCoordenadaY.getText().toString() == null){
			Toast toast= Toast.makeText(this, R.string.coordenadax_obrigatorio, Toast.LENGTH_LONG);  
			toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 0);
			toast.show();
		}
		
		else if(edtCoordenadaX.getText().toString().equals("") || edtCoordenadaX.getText().toString() == null){
			Toast toast= Toast.makeText(this, R.string.coordenaday_obrigatorio, Toast.LENGTH_LONG);  
			toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 0);
			toast.show();
			
		}else{
			retorno = true;
		}
		
		return retorno;
	}
	
	public void limparCampos(){
		edtNomeReferencia.setText("");
		edtCoordenadaY.setText("");
		edtCoordenadaY.setText("");
	}
	
 
	public void onPoiSave(boolean success, String mensagem) {
		if (success) {
			Toast toast= Toast.makeText(this, mensagem, Toast.LENGTH_LONG);  
			toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 0);
			toast.show();
			limparCampos();

		} else {
			Toast toast= Toast.makeText(this, mensagem, Toast.LENGTH_LONG);  
			toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 0);
			toast.show();
		}
	}
}
