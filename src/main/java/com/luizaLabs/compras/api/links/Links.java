package com.luizaLabs.compras.api.links;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.TemplateVariable;
import org.springframework.hateoas.TemplateVariable.VariableType;
import org.springframework.hateoas.TemplateVariables;
import org.springframework.hateoas.UriTemplate;
import org.springframework.stereotype.Component;

import com.luizaLabs.compras.api.controller.ClienteController;
import com.luizaLabs.compras.api.controller.CompraController;
import com.luizaLabs.compras.api.controller.ProdutoController;

@Component
public class Links {
	
	public static final TemplateVariables PAGINACAO_VARIABLES = new TemplateVariables(
			new TemplateVariable("page", VariableType.REQUEST_PARAM),
			new TemplateVariable("size", VariableType.REQUEST_PARAM),
			new TemplateVariable("sort", VariableType.REQUEST_PARAM));

	public static final TemplateVariables PROJECAO_VARIABLES = new TemplateVariables(
			new TemplateVariable("projecao", VariableType.REQUEST_PARAM));

	public Link linkToCompras(String rel) {
		return linkTo(CompraController.class).withRel(rel);
	}
	
	public Link linkToComprasFiltro(String rel) {
		TemplateVariables filtroVariables = new TemplateVariables(
				new TemplateVariable("clienteId", VariableType.REQUEST_PARAM),
				new TemplateVariable("dataCriacaoInicio", VariableType.REQUEST_PARAM),
				new TemplateVariable("dataCriacaoFim", VariableType.REQUEST_PARAM));

		String comprasUrl = linkTo(CompraController.class).toUri().toString();

		return new Link(UriTemplate.of(comprasUrl,
				PAGINACAO_VARIABLES.concat(filtroVariables)), rel);
	}


	public Link linkToCliente(Long clienteId, String rel) {
		return linkTo(methodOn(ClienteController.class)
				.buscar(clienteId)).withRel(rel);
	}

	public Link linkToCliente(Long clienteId) {
		return linkToCliente(clienteId, IanaLinkRelations.SELF.value());
	}

	public Link linkToClientes(String rel) {
		return linkTo(ClienteController.class).withRel(rel);
	}

	public Link linkToClientes() {
		return linkToClientes(IanaLinkRelations.SELF.value());
	}

	public Link linkToProdutos(String rel) {
		return linkTo(ProdutoController.class).withRel(rel);
	}
	
	public Link linkToProduto(Long produtoId, String rel) {
		return linkTo(methodOn(ProdutoController.class)
				.buscar(produtoId))
				.withRel(rel);
	}

	public Link linkToProduto(Long produtoId) {
		return linkToProduto(produtoId, IanaLinkRelations.SELF.value());
	}

	public Link linkToListaProdutos(String rel) {
		return linkTo(methodOn(ProdutoController.class)
				.listar()).withRel(rel);
	}
}
