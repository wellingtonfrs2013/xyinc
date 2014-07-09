package com.xyinc.enums;


/**
 * @author wellington.fernandes
 * Enum com as URLs utilizada para chamar os servicos
 */
public enum ServicosURL {
	
	//Url do servidor
	URL_BASE_SERVICO("http://192.168.0.108:8085/XYIncWeb/"),
	
	//Chama o servico de login
	LOGIN(URL_BASE_SERVICO.getUrl().concat("usuario/login")),
	
	//Chama o servico para cadastrar um novo usuario
	CADASTRAR_USUARIO(URL_BASE_SERVICO.getUrl().concat("usuario/cadastrar")),
	
	// Chama o servico para validar se usuario que esta sendo cadastrado existe
	VALIDA_USUARIO(URL_BASE_SERVICO.getUrl().concat("usuario/validarUsuario")),
	
	//Chama o servico para cadastrar um novo ponto de referencia
	CADASTRAR_PONTO_REFERNCIA(URL_BASE_SERVICO.getUrl().concat("poi/cadastrar")),
	
	// Chama o servico para listar todos os pontos de referenciia
	LISTAR_PONTO_REFERENCIA(URL_BASE_SERVICO.getUrl().concat("poi/listarPoi")),
	
	// Chama o servico para validar se o ponto de referenica que esta sendo cadastrado existe
	VALIDA_PONTO_REFERENCIA(URL_BASE_SERVICO.getUrl().concat("poi/validarPoi")),
	
	// Chama o servico para buscar os pontos de referencia com base na referencia passada
	BUSCA_PONTO_REFERENCIA(URL_BASE_SERVICO.getUrl().concat("poi/listarPoiByDistance"));
	
	private String descricao;

	public String getUrl() {
		return descricao;
	}
	
	private ServicosURL(String descricao) {
		this.descricao = descricao;
	}
}
