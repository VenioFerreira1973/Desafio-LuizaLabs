package com.luizaLabs.compras.api.openapi.controller;

import org.springframework.hateoas.CollectionModel;

import com.luizaLabs.compras.api.exceptionHandler.Problem;
import com.luizaLabs.compras.api.model.input.ClienteInput;
import com.luizaLabs.compras.api.model.model.ClienteModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Clientes")
public interface ClienteControllerOpenApi {
	
	
	@ApiOperation("Listar clientes")
	public CollectionModel<ClienteModel> listar();

	@ApiOperation("Buscar cliente pelo ID")
	@ApiResponses({
		@ApiResponse(code = 400, message = "ID de cliente inválido", response = Problem.class),
		@ApiResponse(code = 404, message = "ID de cliente não encontrado", response = Problem.class)
	})
	ClienteModel buscar(@ApiParam(value = "ID de um cliente", example = "1", required = true) Long Id);
	
	@ApiOperation("Cadastrar cliente")
	@ApiResponses({
		@ApiResponse(code = 201, message = "Cliente cadastrado com sucesso", response = Problem.class)
	})
	ClienteModel adicionar(
			@ApiParam(value = "Representação de um novo usuário com senha", name = "corpo", required = true) 
			ClienteInput clienteInput);
	
	
	@ApiOperation("Atualizar dados de um cliente")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Cliente atualizado com sucesso", response = Problem.class),
		@ApiResponse(code = 400, message = "ID de cliente inválido", response = Problem.class),
		@ApiResponse(code = 404, message = "ID de cliente não encontrado", response = Problem.class)
	})
	ClienteModel atualizar(
			@ApiParam(value = "ID de um cliente", example = "1", required = true) Long clienteId, 
			@ApiParam(value = "Representação de um novo cliente", name = "corpo", required = true) ClienteInput clienteInput);

	@ApiOperation("Excluir um cliente")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Cliente excluído com sucesso", response = Problem.class),
		@ApiResponse(code = 400, message = "ID de cliente inválido", response = Problem.class),
		@ApiResponse(code = 404, message = "ID de cliente não encontrado", response = Problem.class)
	})
	void excluir(@ApiParam(value = "ID de um cliente", example = "1", required = true) Long Id);
	

}
