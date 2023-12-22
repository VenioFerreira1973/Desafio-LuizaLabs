package com.luizaLabs.compras.api.model.model;

import java.math.BigDecimal;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "produtos")
@Setter
@Getter
public class ProdutoModel extends RepresentationModel<ProdutoModel>{
	
	@ApiModelProperty(example = "1")
	private Long id;

	@ApiModelProperty(example = "Celular")
	private String nome;

	@ApiModelProperty(example = "Celular muito legal")
	private String descricao;

	@ApiModelProperty(example = "1200.50")
	private BigDecimal preco;
	
	@ApiModelProperty(example = "1")
	private Integer quantidadeProdutosVendidos;
    
	@ApiModelProperty(example = "1")
	private Integer quantidadeEmEstoque;
	
	@ApiModelProperty(example = "12.50")
    private BigDecimal lucroTotal;

}
