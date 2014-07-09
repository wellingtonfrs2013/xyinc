package com.xyinc.dao;

import java.util.List;

import com.xyinc.model.PontoReferencia;


/**
 * @author wellington.fernandes
 * Interface para os metodos de persistencia e listagem do ponto de referencia
 */
public interface PontoReferenciaDAO {
	
	public void save(PontoReferencia ponto);
	public PontoReferencia getPontoReferenciaByNome(String nome, Long idUsuario);
	public List<PontoReferencia> listAll(Long idUsuario);

}
