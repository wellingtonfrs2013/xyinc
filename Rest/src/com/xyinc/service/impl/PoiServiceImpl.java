package com.xyinc.service.impl;

import java.util.List;

import br.com.caelum.vraptor.ioc.Component;

import com.xyinc.dao.PontoReferenciaDAO;
import com.xyinc.model.PontoReferencia;
import com.xyinc.service.PoiService;

/**
 * @author wellington.fernandes 
 * Implementacao dos servicos do usuario
 */
@Component
public class PoiServiceImpl implements PoiService {

	private PontoReferenciaDAO pontoReferenciaDAO;

	// Construtor
	public PoiServiceImpl(PontoReferenciaDAO pontoReferenciaDAO) {
		this.pontoReferenciaDAO = pontoReferenciaDAO;
	}

	// Grava um novo ponto de referencia no banco
	public Boolean save(PontoReferencia pontoReferencia) {
		try {
			this.pontoReferenciaDAO.save(pontoReferencia);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	// Verifica se ja existe o nome do ponto de refencia que esta sendo gravado no banco
	public PontoReferencia getPontoReferenciaByNome(String nome, Long idUsuario) {
		try {
			return this.pontoReferenciaDAO.getPontoReferenciaByNome(nome, idUsuario);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// Lista todos os pontos de referencia
	public List<PontoReferencia> listAll(Long idUsuario) {
		try {
			return this.pontoReferenciaDAO.listAll(idUsuario);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
