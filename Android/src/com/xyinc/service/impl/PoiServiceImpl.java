package com.xyinc.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.net.ParseException;
import android.util.Log;

import com.google.gson.Gson;
import com.xyinc.enums.ServicosURL;
import com.xyinc.model.PontoReferencia;
import com.xyinc.service.PoiService;
import com.xyinc.utils.HttpHelpImpl;
import com.xyinc.utils.IHttpHelp;
import com.xyinc.view.PontoReferenciaView;

/**
 * @author wellington.fernandes
 * Implementa dos servicos ligado ao ponto de Referencia
 */
public class PoiServiceImpl implements PoiService {
	
	private final String TAG = this.getClass().getName();
	private IHttpHelp httpHelp; 
	
	public PoiServiceImpl(){
		this.httpHelp = new HttpHelpImpl();
	}
	
	// Validar se o novo ponto de referencia que esta sendo cadastrado existe
	public Boolean validaPOI(Context context , String nomePonto, Long idUsuario){
		
		Boolean validado = false;
		try {
			
			JSONObject json = new JSONObject();

			String url = new StringBuilder().append(ServicosURL.VALIDA_PONTO_REFERENCIA.getUrl()).append("/").append(nomePonto).append("/").append(idUsuario).toString();

			json = this.httpHelp.getJson(url, context);

			if (json != null && json.length() > 0) {
				try {
					validado = json.getBoolean("boolean");
				} catch (JSONException e) {
					Log.e("JSON Parser", "Erro ao parsear dados " + e.toString());
				}
			}

		} catch (IOException ex) {
			Log.e(TAG, "Erro ao validar ponto de referencia.", ex);
		}
		return validado;
	}
	
	// Gravar um novo Ponto de Referencia
	public Boolean save(Context context, Gson gson, PontoReferencia pontoReferencia) {
		
		Boolean sucesso = false;
		JSONObject jsonObject = new JSONObject();

		String url = ServicosURL.CADASTRAR_PONTO_REFERNCIA.getUrl();

		try {
			
			jsonObject.put("nome", pontoReferencia.getNome());
			jsonObject.put("coordenadaX", pontoReferencia.getCoordenadaX());
			jsonObject.put("coordenadaY", pontoReferencia.getCoordenadaY());
			jsonObject.put("idUsuario", pontoReferencia.getIdUsuario());
			sucesso = this.httpHelp.postJson(url, "pontoReferencia", jsonObject,context);
			
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
	
	// Lista todos os pontos de referencia
	public List<PontoReferenciaView> listAllPoi(Context context, Gson gson, PontoReferenciaView pontoReferenciaView) {

		JSONObject retorno = new JSONObject();

		String url = ServicosURL.LISTAR_PONTO_REFERENCIA.getUrl().concat("/").concat(pontoReferenciaView.getIdusuario().toString());

		List<PontoReferenciaView> poiList = new ArrayList<PontoReferenciaView>();

		try {

			retorno = this.httpHelp.getJson(url, context);
			JSONArray pontos = retorno.getJSONArray("list");

			for (int i = 0; i < pontos.length(); i++) {
				PontoReferenciaView pontoReferencia = new PontoReferenciaView();
				pontoReferencia.setNome(pontos.getJSONObject(i).getString("nome"));
				pontoReferencia.setCoordenadaX(pontos.getJSONObject(i).getInt("coordenadaX"));
				pontoReferencia.setCoordenadaY(pontos.getJSONObject(i).getInt("coordenadaY"));
				poiList.add(pontoReferencia);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return poiList;
	}


	// Lista pontos de referencia baseado na busca
	public List<PontoReferenciaView> listPoiByDistance(Context context, Gson gson, PontoReferenciaView pontoReferenciaView) {

		JSONObject retorno = new JSONObject();

		String url = ServicosURL.BUSCA_PONTO_REFERENCIA.getUrl()
				.concat("/").concat(pontoReferenciaView.getDistancia().toString())
				.concat("/").concat(pontoReferenciaView.getCoordenadaX().toString())
				.concat("/").concat(pontoReferenciaView.getCoordenadaY().toString())
				.concat("/").concat(pontoReferenciaView.getIdusuario().toString());

		List<PontoReferenciaView> poiList = new ArrayList<PontoReferenciaView>();

		try {

			retorno = this.httpHelp.getJson(url, context);
			JSONArray pontos = retorno.getJSONArray("list");

			for (int i = 0; i < pontos.length(); i++) {
				PontoReferenciaView pontoReferencia = new PontoReferenciaView();
				pontoReferencia.setNome(pontos.getJSONObject(i).getString("nome"));
				pontoReferencia.setCoordenadaX(pontos.getJSONObject(i).getInt("coordenadaX"));
				pontoReferencia.setCoordenadaY(pontos.getJSONObject(i).getInt("coordenadaY"));
				pontoReferencia.setDistancia(pontos.getJSONObject(i).getInt("distancia"));
				poiList.add(pontoReferencia);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return poiList;
	}
}
