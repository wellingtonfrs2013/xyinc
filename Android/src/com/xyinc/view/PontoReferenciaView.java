package com.xyinc.view;


/**
 * @author wellington.fernandes 
 * View Ponto Referencia
 */
public class PontoReferenciaView{

	private Long idusuario;
	private Integer distancia;
	private Integer coordenadaX;
	private Integer coordenadaY;
	private String nome;

	// Construtor vazio
	public PontoReferenciaView() {
		super();
	}

	public PontoReferenciaView(Long idusuario, Integer distancia,
			Integer coordenadaX, Integer coordenadaY, String nome) {
		super();
		this.idusuario = idusuario;
		this.distancia = distancia;
		this.coordenadaX = coordenadaX;
		this.coordenadaY = coordenadaY;
		this.nome = nome;
	}

	public Long getIdusuario() {
		return idusuario;
	}

	public void setIdUsuario(Long idusuario) {
		this.idusuario = idusuario;
	}

	public Integer getDistancia() {
		return distancia;
	}

	public void setDistancia(Integer distancia) {
		this.distancia = distancia;
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

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
