package com.xyinc.model;

/**
 * @author wellington.fernandes
 * Model PontoReferencia
 */
public class PontoReferencia {
	
	private String nome;
	private Integer coordenadaX;
	private Integer coordenadaY;
	private Long idUsuario;
	
	//Construtor vazio
	public PontoReferencia() {
		super();
	}

	//Construtor 
	public PontoReferencia(String nome, Integer coordenadaX,
			Integer coordenadaY, Long idUsuario) {
		super();
		this.nome = nome;
		this.coordenadaX = coordenadaX;
		this.coordenadaY = coordenadaY;
		this.idUsuario = idUsuario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getCoordenadaX() {
		return coordenadaX;
	}

	public void setCoordenadaX(Integer coordenadaX) {
		this.coordenadaX = coordenadaX;
	}

	public Integer getCoordenadaY() {
		return coordenadaY;
	}

	public void setCoordenadaY(Integer coordenadaY) {
		this.coordenadaY = coordenadaY;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

}
