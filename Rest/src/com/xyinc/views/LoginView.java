package com.xyinc.views;

import java.io.Serializable;

/**
 * @author wellington.fernandes
 * View para validacao e token do login do usuario
 */
public class LoginView implements Serializable {

    /**
     * Serial
     */
    private static final long serialVersionUID = 287439966764407667L;

    private String erro;

    private Boolean sucesso;

    private Long token;

    public LoginView() {
	super();
    }

    public LoginView(String erro, Boolean sucesso, Long token) {
	super();
	this.erro = erro;
	this.sucesso = sucesso;
	this.token = token;
    }

    public String getErro() {
	return erro;
    }

    public void setErro(String erro) {
	this.erro = erro;
    }

    public Boolean getSucesso() {
	return sucesso;
    }

    public void setSucesso(Boolean sucesso) {
	this.sucesso = sucesso;
    }

    public Long getToken() {
	return token;
    }

    public void setToken(Long token) {
	this.token = token;
    }

}
