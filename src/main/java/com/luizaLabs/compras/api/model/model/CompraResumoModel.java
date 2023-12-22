package com.luizaLabs.compras.api.model.model;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import com.luizaLabs.compras.api.model.input.ClienteInput;
import com.luizaLabs.compras.api.model.input.ProdutoInput;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;


@Relation(collectionRelation = "compras")
@Setter
@Getter
public class CompraResumoModel extends RepresentationModel<CompraResumoModel>{
	
	private List<ClienteInput> cliente;
	
	private List<ProdutoInput> produto;
	
	@ApiModelProperty(example = "1", required = true)
	private Integer quantidade;
	
	@ApiModelProperty(example = "12.50", required = true)
	private BigDecimal valorTotal;
	
	@ApiModelProperty(example = "10.50", required = true)
	private BigDecimal porcentagemDesconto;
	
	@ApiModelProperty(example = "12.50", required = true)
	private BigDecimal valorComDesconto;
	
	
}
