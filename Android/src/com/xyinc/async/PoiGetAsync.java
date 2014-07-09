package com.xyinc.async;

import java.util.ArrayList;
import java.util.List;

import android.app.ProgressDialog;
import android.os.AsyncTask;

import com.google.gson.Gson;
import com.xyinc.activity.PoiListarActivity;
import com.xyinc.service.PoiService;
import com.xyinc.view.PontoReferenciaView;

public class PoiGetAsync extends AsyncTask<String, String, Boolean> {
	
	private PontoReferenciaView pontoReferenciaView;
	private ProgressDialog progressDialog;
	private PoiListarActivity activity;
	private PoiService poiService;
	private Gson gson;
	private List<PontoReferenciaView> listPoi = new ArrayList<PontoReferenciaView>();

	// Construtor
	public PoiGetAsync(PoiListarActivity activity, ProgressDialog progressDialog, Gson gson, PoiService poiService, PontoReferenciaView pontoReferenciaView) {
		this.activity = activity;
		this.progressDialog = progressDialog;
		this.gson = gson;
		this.poiService = poiService;
		this.pontoReferenciaView = pontoReferenciaView;
	}

	@Override
	protected void onPreExecute() {
		progressDialog.setMessage("Buscando Ponto de Referencia...");
		progressDialog.setCancelable(false);
		progressDialog.show();
	}

	// Backgroud
	@Override
	protected Boolean doInBackground(String...params) {
		if(this.pontoReferenciaView.getDistancia() == null){
			return this.listAllPoi();
		}else{
			return this.listPoiByDistance();
		}
		
	}
	
	public Boolean listAllPoi() {
		Boolean sucesso = false;
		this.listPoi = poiService.listAllPoi(activity,gson,this.pontoReferenciaView);
		try {
			if (this.listPoi.size() > 0) {
				sucesso = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return sucesso;
	}
	
	public Boolean listPoiByDistance() {
		Boolean sucesso = false;
		this.listPoi = poiService.listPoiByDistance(activity,gson,this.pontoReferenciaView);
		try {
			if (this.listPoi.size() > 0) {
				sucesso = true;
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
		this.activity.onPoiList(result, this.listPoi);
	}

}
