package com.luizaLabs.compras.api.openapi.controller;

import org.springframework.hateoas.CollectionModel;

import com.luizaLabs.compras.api.exceptionHandler.Problem;
import com.luizaLabs.compras.api.model.input.CompraInput;
import com.luizaLabs.compras.api.model.model.CompraModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Compras")
public interface CompraControllerOpenApi {
	
	@ApiOperation("Listar compras")
	public CollectionModel<CompraModel> listar();
	
	@ApiOperation("Buscar uma compra pelo ID")
	@ApiResponses({
		@ApiResponse(code = 400, message = "ID da compra inválido", response = Problem.class),
		@ApiResponse(code = 404, message = "Compra não encontrada", response = Problem.class)
	})
	CompraModel buscar(
	            @ApiParam(value = "Id de uma compra", example = "1", required = true)
	            Long id);   
	
	@ApiOperation("Excluir uma compra")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Compra excluída com sucesso", response = Problem.class),
		@ApiResponse(code = 400, message = "ID de compra inválido", response = Problem.class),
		@ApiResponse(code = 404, message = "ID de compra não encontrado", response = Problem.class)
	})
	void excluir(@ApiParam(value = "ID de uma compra", example = "1", required = true) Long Id);
	
	@ApiOperation("Efetuar uma compra")
	@ApiResponses({
		@ApiResponse(code = 201, message = "Compra efetuada com sucesso", response = Problem.class),
	})
	public CompraModel efetuarCompra(
				@ApiParam(value = "Representação de uma nova compra", name = "corpo", required = true) 
				CompraInput compraInput);
}
