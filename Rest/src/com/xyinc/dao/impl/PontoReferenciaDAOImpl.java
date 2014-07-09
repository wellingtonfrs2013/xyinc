package com.xyinc.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import br.com.caelum.vraptor.ioc.Component;

import com.xyinc.dao.PontoReferenciaDAO;
import com.xyinc.model.PontoReferencia;

/**
 * @author wellington.fernandes 
 * Implementacao da Interface de persistencia e listagem do ponto de referencia
 */
@Component
public class PontoReferenciaDAOImpl implements PontoReferenciaDAO {

	private Session session;

	// Construtor
	public PontoReferenciaDAOImpl(Session session) {
		this.session = session;
	}

	// Grava um ponto de referencia no banco
	public void save(PontoReferencia ponto) {
		Transaction tx = session.beginTransaction();
		this.session.save(ponto);
		tx.commit();
	}
	
	// Verifica se ja existe o nome o ponto de referencia que esta sendo gravado no banco
	public PontoReferencia getPontoReferenciaByNome(String nome, Long idUsuario) {
		return (PontoReferencia) this.session.createCriteria(PontoReferencia.class)
				.add(Restrictions.eq("nome", nome)).add(Restrictions.eq("idUsuario", idUsuario)).uniqueResult();
	}

	// Lista todos os pontos de referencia
	@SuppressWarnings("unchecked")
	public List<PontoReferencia> listAll(Long idUsuario) {
		return session.createCriteria(PontoReferencia.class)
				.add(Restrictions.eq("idUsuario", idUsuario)).list();
	}
}
