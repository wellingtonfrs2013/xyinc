package com.xyinc.util;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.ComponentFactory;

/**
 * @author wellington 
 * Criador de Sessao
 */
@SuppressWarnings("restriction")
@Component
public class CriadorDeSession implements ComponentFactory<Session> {
	private final SessionFactory factory;
	private Session session;

	// Construtor
	public CriadorDeSession(SessionFactory factory) {
		this.factory = factory;
	}

	// Abre uma sessao
	@PostConstruct
	public void abre() {
		this.session = factory.openSession();
	}

	// Pega a sessao
	public Session getInstance() {
		return this.session;
	}

	// Fecha sessao
	@PreDestroy
	public void fecha() {
		this.session.close();
	}
}