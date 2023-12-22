package com.luizaLabs.compras.api.model.input;

import java.math.BigDecimal;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompraInput {

	
	@Valid
	@ApiModelProperty(example = "1", required = true)
	@NotNull
	private ClienteIdInput cliente;
	
	
	@Valid
	@ApiModelProperty(example = "1", required = true)
	@NotNull
	private ProdutoIdInput produto;
	
	@ApiModelProperty(example = "1", required = true)
	@NotNull
	@PositiveOrZero
	private Integer quantidade;
	
	@ApiModelProperty(example = "12.50", required = true)
	@NotNull
	@PositiveOrZero
	private BigDecimal porcentagemDesconto;
	
	/*@ApiModelProperty(example = "12.50", required = true)
	@NotNull
	@PositiveOrZero
	private BigDecimal valorTotal;
	*/
}
