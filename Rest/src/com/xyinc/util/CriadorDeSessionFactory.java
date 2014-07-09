package com.xyinc.util;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import br.com.caelum.vraptor.ioc.ApplicationScoped;
import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.ComponentFactory;

/**
 * @author wellington 
 * Cria fabrica de sessao
 */
@SuppressWarnings("restriction")
@Component
@ApplicationScoped
public class CriadorDeSessionFactory implements
		ComponentFactory<SessionFactory> {

	private SessionFactory factory;

	// Instancia uma nova sessao com as configuracoes do hibernate
	@PostConstruct
	public void abre() {
		factory = new Configuration().configure().buildSessionFactory();
	}

	// Pega uma instacia da fabrica de sessao
	public SessionFactory getInstance() {
		return this.factory;
	}

	// fecha a fabrica de sessao
	@PreDestroy
	public void fecha() {
		this.factory.close();
	}

}
