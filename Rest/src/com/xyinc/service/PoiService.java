package com.xyinc.service;

import java.util.List;

import com.xyinc.model.PontoReferencia;

/**
 * @author wellington.fernandes
 * Inteface dos servicos do ponto de referencia
 */
public interface PoiService {
	
	public Boolean save(PontoReferencia pontoReferencia);
	public PontoReferencia getPontoReferenciaByNome(String nome, Long idUsuario);
	public List<PontoReferencia> listAll(Long idUsuario);

}
