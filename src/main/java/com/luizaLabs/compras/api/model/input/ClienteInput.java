package com.luizaLabs.compras.api.model.input;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteInput {

	@ApiModelProperty(example = "João da Silva", required = true)
	@NotBlank
	private String nome;
	
	@ApiModelProperty(example = "joao.ger@gmail.com", required = true)
	@NotBlank
	@Email
	private String email;

}
