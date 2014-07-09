package com.xyinc.controller;

import static br.com.caelum.vraptor.view.Results.json;

import java.util.ArrayList;
import java.util.List;

import br.com.caelum.vraptor.Consumes;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

import com.xyinc.model.PontoReferencia;
import com.xyinc.service.PoiService;
import com.xyinc.views.PontoReferenciaView;

/**
 * @author wellington.fernandes 
 * Classe de Controle para o Ponto de Referencia
 */
@Resource
public class PontoReferenciaController {

	private PoiService poiService;
	private Result result;

	// construtor
	public PontoReferenciaController(PoiService poiService, Result result) {
		this.poiService = poiService;
		this.result = result;
	}

	// Cadastrar Ponto de Referencia
	@Post("poi/cadastrar")
	@Consumes(value = { "application/json", "application/x-www-form-urlencoded" })
	public void save(PontoReferencia pontoReferencia) {
		Boolean resultado = this.poiService.save(pontoReferencia);
		result.use(json()).from(resultado).serialize();
	}
	
	// Validacao do ponto de referencia por nome
	@Get("poi/validarPoi/{nome}/{idUsuario}")
	public void getUserByNome(String nome, Long idUsuario) {
		PontoReferencia pontoReferencia = new PontoReferencia();
		pontoReferencia = this.poiService.getPontoReferenciaByNome(nome, idUsuario);
		if (pontoReferencia != null) {
			result.use(json()).from(true).serialize();
		} else {
			result.use(json()).from(false).serialize();
		}
	}
	
	// Listar todos os Pontos Cadastrado
	@Get("poi/listarPoi/{idUsuario}")
	public void listAll(Long idUsuario) {
		List<PontoReferencia> list = new ArrayList<PontoReferencia>();
		list = poiService.listAll(idUsuario); 
		result.use(json()).from(list).serialize();
	}

	// Listar os pontos de referencia baseado na busca
	@Get("poi/listarPoiByDistance/{distancia}/{x}/{y}/{idUsuario}")
	public void listPoiByDistance(Integer distancia, Integer x, Integer y, Long idUsuario) {

		List<PontoReferencia> list = new ArrayList<PontoReferencia>();
		list = poiService.listAll(idUsuario); 

		List<PontoReferenciaView> listResultado = new ArrayList<PontoReferenciaView>();

		for (int i = 0; i < list.size(); i++) {
			Integer d = this.distancia(x, y, list.get(i).getCoordenadaX(), list.get(i).getCoordenadaY());
			if (d <= distancia) {
				PontoReferenciaView view = new PontoReferenciaView();
				view.setDistancia(d);
				view.setNome(list.get(i).getNome());
				view.setCoordenadaX(list.get(i).getCoordenadaX());
				view.setCoordenadaY(list.get(i).getCoordenadaY());
				listResultado.add(view);
			}
		}
		result.use(json()).from(listResultado).serialize();
	}

	// Calcula a distancia entre o ponto de referencia e o ponto inserido no banco
	private Integer distancia(int xr, int yr, int xp, int yp) {
		int dx = xr - xp;
		int dy = yr - yp;
		return (int) Math.sqrt(dx * dx + dy * dy);
	}

}
