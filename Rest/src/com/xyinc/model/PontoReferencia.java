package com.xyinc.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author wellington.fernandes 
 * Entidade Ponto de Referencia
 */
@Entity
@Table(name = "pontoReferencia")
public class PontoReferencia implements Serializable {

	/**
	 * Serial
	 */
	private static final long serialVersionUID = -2814748183055344288L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String nome;
	private Integer coordenadaX;
	private Integer coordenadaY;
	private Long idUsuario;
	
	// Construtor
	public PontoReferencia(){
	}

	public PontoReferencia(Long id, String nome, Integer coordenadaX,
			Integer coordenadaY, Long idUsuario) {
		super();
		this.id = id;
		this.nome = nome;
		this.coordenadaX = coordenadaX;
		this.coordenadaY = coordenadaY;
		this.idUsuario = idUsuario;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
