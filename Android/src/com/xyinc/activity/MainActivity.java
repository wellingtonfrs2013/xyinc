package com.xyinc.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.xyinc.R;

/**
 * @author wellington.fernandes 
 * Tela principal apos efetuar o login
 */
public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void onClickSobre(View v) {
	 	Intent intent  = new Intent(this, SobreActivity.class);
    	startActivity(intent);
    	finish();
	}
	
	public void onClickCadastrarPoi(View v) {
		Intent intent  = new Intent(this, PoiCadastrarActivity.class);
    	startActivity(intent);
    	finish();
	}
	
	public void onClickListarPoi(View v) {
		Intent intent  = new Intent(this, PoiListarActivity.class);
    	startActivity(intent);
    	finish();
	}
	
	public void onClickSair(View v){
		sair();
	}
	
	@Override
	public void onBackPressed()
	{
	     sair();
	}
	
	public void sair(){
		AlertDialog.Builder builder = new AlertDialog.Builder(this);     
        builder.setMessage("Deseja sair?")            
        .setCancelable(false)        
        .setIcon(android.R.drawable.ic_dialog_alert) 
        .setTitle("Atenção:") 
                 
        .setPositiveButton("Sim", new DialogInterface.OnClickListener() {               
            public void onClick(DialogInterface dialog, int id) {
            	finish();
            }            
        })            
   
        .setNegativeButton("Não", new DialogInterface.OnClickListener() {                
            public void onClick(DialogInterface dialog, int id) {                     
                dialog.cancel();
            }            
        });     
        AlertDialog alert = builder.create();     
        alert.show();
	}
	
}
