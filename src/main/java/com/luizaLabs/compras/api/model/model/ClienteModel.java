package com.luizaLabs.compras.api.model.model;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "clientes")
@Setter
@Getter
public class ClienteModel extends RepresentationModel<ClienteModel>{
	
	@ApiModelProperty(example = "1")
	private Long id;
	
	@ApiModelProperty(example = "Vênio Ferreira")
	private String nome;
	
	@ApiModelProperty(example = "venio@gmail.com")
	private String email;
	
}
