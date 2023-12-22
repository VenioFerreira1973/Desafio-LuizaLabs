package com.luizaLabs.compras.api.openapi.model;

import java.util.List;

import org.springframework.hateoas.Links;

import com.luizaLabs.compras.api.model.model.ClienteModel;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel("ClientesModel")
@Data
public class ClientesModelOpenApi {

	private ClientesEmbeddedModelOpenApi _embedded;
	private Links _links;

	@ApiModel("ClientesEmbeddedModel")
	@Data
	public class ClientesEmbeddedModelOpenApi {

		private List<ClienteModel> clientes;

	}
}
