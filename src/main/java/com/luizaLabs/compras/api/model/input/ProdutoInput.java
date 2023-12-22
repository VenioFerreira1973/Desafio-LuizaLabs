package com.luizaLabs.compras.api.model.input;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoInput {
		
	@ApiModelProperty(example = "Celular xpto", required = true)
	@NotBlank
	private String nome;

	@ApiModelProperty(example = "Celular legal pra caramba", required = true)
	@NotBlank
	private String descricao;

	@ApiModelProperty(example = "1200.50", required = true)
	@NotNull
	@PositiveOrZero
	private BigDecimal preco;
	
	@ApiModelProperty(example = "1200.50", required = true)
	@NotNull
	@PositiveOrZero
	private BigDecimal valorCusto;
	
	
	@ApiModelProperty(example = "12.50", required = true)
	@NotNull
	@PositiveOrZero
	private BigDecimal margemLucro;
	
	@ApiModelProperty(example = "10", required = true)
	@NotNull
	@PositiveOrZero
	private Integer quantidadeProdutosVendidos;
	
	@ApiModelProperty(example = "10", required = true)
	@NotNull
	@PositiveOrZero
	private Integer quantidadeEmEstoque;
	
	
	
}
