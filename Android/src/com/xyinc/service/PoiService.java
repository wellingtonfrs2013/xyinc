package com.xyinc.service;

import java.util.List;

import android.content.Context;

import com.google.gson.Gson;
import com.xyinc.model.PontoReferencia;
import com.xyinc.view.PontoReferenciaView;


/**
 * @author wellington.fernandes
 * Interfaces dos servicos do ponto de referencia
 */
public interface PoiService {
	
	public Boolean validaPOI(Context context , String nomePonto, Long idUsuario);
	public Boolean save(Context context, Gson gson, PontoReferencia pontoReferencia);
	public List<PontoReferenciaView> listAllPoi(Context context, Gson gson, PontoReferenciaView pontoReferenciaView);
	public List<PontoReferenciaView> listPoiByDistance(Context context, Gson gson, PontoReferenciaView pontoReferenciaView);

}
