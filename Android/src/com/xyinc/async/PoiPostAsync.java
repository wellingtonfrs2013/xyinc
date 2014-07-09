package com.xyinc.async;

import android.app.ProgressDialog;
import android.os.AsyncTask;

import com.google.gson.Gson;
import com.xyinc.activity.PoiCadastrarActivity;
import com.xyinc.model.PontoReferencia;
import com.xyinc.service.PoiService;

public class PoiPostAsync extends AsyncTask<PontoReferencia, String, Boolean> {
	
	private PontoReferencia pontoReferencia;
	private ProgressDialog progressDialog;
	private PoiCadastrarActivity activity;
	private PoiService poiService;
	private Gson gson;
	private String mensagem;

	// Construtor
	public PoiPostAsync(PoiCadastrarActivity activity, ProgressDialog progressDialog, Gson gson, PoiService poiService, PontoReferencia pontoReferencia) {
		this.activity = activity;
		this.progressDialog = progressDialog;
		this.gson = gson;
		this.poiService = poiService;
		this.pontoReferencia = pontoReferencia;
	}

	@Override
	protected void onPreExecute() {
		progressDialog.setMessage("Salvando Ponto de Referencia...");
		progressDialog.setCancelable(false);
		progressDialog.show();
	}

	// Backgroud
	@Override
	protected Boolean doInBackground(PontoReferencia...params) {
		this.pontoReferencia = params[0];
		return save(this.pontoReferencia);
	}
	
	public Boolean save(PontoReferencia pontoReferencia) {
		Boolean sucesso = false;
		Boolean validado = poiService.validaPOI(activity,pontoReferencia.getNome(), pontoReferencia.getIdUsuario());
		try {
			if (validado == false) {
				sucesso = poiService.save(activity, gson, pontoReferencia);
				this.mensagem = "Ponto de Referência gravado com sucesso";
			} else {
				this.mensagem = "Este ponto de referencia já existe";
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
		this.activity.onPoiSave(result, this.mensagem);
	}

}
