package com.luizaLabs.compras.api.model.model;

import java.math.BigDecimal;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "compras")
@Setter
@Getter
public class CompraModel extends RepresentationModel<CompraModel>{
	
	private ClienteModel cliente;
	
	private ProdutoModel produto;
	
	@ApiModelProperty(example = "1", required = true)
	private Integer quantidade;
	
	@ApiModelProperty(example = "12.50", required = true)
	private BigDecimal valorTotal;
	
	@ApiModelProperty(example = "10.50", required = true)
	private BigDecimal porcentagemDesconto;
	
	@ApiModelProperty(example = "12.50", required = true)
	private BigDecimal valorComDesconto;
	
	
}
