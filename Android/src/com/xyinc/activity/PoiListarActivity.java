package com.xyinc.activity;

import java.util.List;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.xyinc.R;
import com.xyinc.adapter.ListPoiAdapter;
import com.xyinc.async.PoiGetAsync;
import com.xyinc.service.PoiService;
import com.xyinc.service.impl.PoiServiceImpl;
import com.xyinc.view.PontoReferenciaView;

/**
 * @author wellington.fernandes
 * Tela Listar POI
 */
public class PoiListarActivity extends Activity {
	
	private ListView lvView;
	private EditText edtRefX;
	private EditText edtRefY;
	private EditText edtDist;
	private Gson gson;
	List<PontoReferenciaView> listPoi;
	private PoiService poiService;
	private PontoReferenciaView pontoReferenciaView;
	
	public PoiListarActivity(){
		super();
		this.gson = new Gson();
		this.poiService = new PoiServiceImpl();
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_listar_pontos);
	 
	    lvView = (ListView) findViewById(R.id.lvPontos);
	    
	    this.edtDist = (EditText)findViewById(R.id.edtDistancia);
	    this.edtRefX = (EditText)findViewById(R.id.edtRefX);
	    this.edtRefY = (EditText)findViewById(R.id.edtRefY);
	 
	}
	
	public void onClickPesquisar(View v) {
			this.pontoReferenciaView = new PontoReferenciaView();
		
			if(validaCamposListAll()){
				pontoReferenciaView.setDistancia(null);
				pontoReferenciaView.setCoordenadaX(null);
				pontoReferenciaView.setCoordenadaY(null);
				pontoReferenciaView.setIdUsuario(LoginActivity.getInstance().getAuthenticatedUserId());
				
			}else{
				if(validaCampos()){
					pontoReferenciaView.setDistancia(Integer.parseInt(edtDist.getText().toString()));
					pontoReferenciaView.setCoordenadaX(Integer.parseInt(edtRefX.getText().toString()));
					pontoReferenciaView.setCoordenadaY(Integer.parseInt(edtRefY.getText().toString()));
					pontoReferenciaView.setIdUsuario(LoginActivity.getInstance().getAuthenticatedUserId());
				}
			}
			
			if((validaCamposListAll() || validaCampos()) == true){
				new PoiGetAsync(this, new ProgressDialog(this), gson, poiService, pontoReferenciaView).execute("");
			}
			
	}
	
	public void onClickVoltar(View v){
		Intent intent  = new Intent(this, MainActivity.class);
    	startActivity(intent);
    	finish();
	}
	
	public Boolean validaCamposListAll(){
		Boolean retorno = false;
		
		if(edtDist.getText().toString().equals("") && edtRefX.getText().toString().equals("") && edtRefY.getText().toString().equals("")){
			
			retorno = true;
		}
		
		return retorno;
	}
	
	public Boolean validaCampos(){
		Boolean retorno = false;
		
		if((edtDist.getText().toString().equals("") || edtRefX.getText().toString().equals("") || edtRefY.getText().toString().equals(""))){
			
			Toast toast= Toast.makeText(this, R.string.campo_buscar_obrigatorio, Toast.LENGTH_LONG);  
			toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 0);
			toast.show();
			this.listPoi = null;
			lvView.setAdapter(null);
		}
		else{
			retorno = true;
		}
		
		return retorno;
	}
	
	public void onPoiList(boolean success, List<PontoReferenciaView> listPontoReferencias) {
		if (success) {
			this.listPoi = listPontoReferencias;
			lvView.setAdapter(new ListPoiAdapter(this, this.listPoi));
		} else {
			Toast toast= Toast.makeText(this, R.string.sem_resultado, Toast.LENGTH_LONG);  
			toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 0);
			toast.show();
			this.listPoi = null;
			lvView.setAdapter(null);
		}
	}

}
