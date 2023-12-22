package com.luizaLabs.compras.api.openapi.model;

import java.util.List;

import org.springframework.hateoas.Links;

import com.luizaLabs.compras.api.model.model.ProdutoModel;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel("ProdutosModel")
@Data
public class ProdutosModelOpenApi {

	private ProdutosEmbeddedModel _embedded;
	private Links _links;
	
	@ApiModel("ProdutosEmbeddedModel")
	@Data
	public class ProdutosEmbeddedModel {
		private List<ProdutoModel> produtos;
	}
}
