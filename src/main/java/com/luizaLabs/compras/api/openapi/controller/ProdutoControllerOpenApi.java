package com.luizaLabs.compras.api.openapi.controller;

import org.springframework.hateoas.CollectionModel;

import com.luizaLabs.compras.api.exceptionHandler.Problem;
import com.luizaLabs.compras.api.model.input.ProdutoInput;
import com.luizaLabs.compras.api.model.model.ProdutoModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Produtos")
public interface ProdutoControllerOpenApi {
	
	
	@ApiOperation("Listar os produtos")
	@ApiResponses({
		@ApiResponse(code = 404, message = "Produto não encontrado", response = Problem.class)
    })
	CollectionModel<ProdutoModel> listar();
	
	@ApiOperation("Buscar produto por ID")
	@ApiResponses({
		@ApiResponse(code = 400, message = "ID de produto inválido", response = Problem.class),
			
	})
	ProdutoModel buscar(
			@ApiParam(value = "ID de um produto", example = "1", required = true) Long produtoId);
	
	
	@ApiOperation("Cadastra um produto")
	@ApiResponses({
		@ApiResponse(code = 201, message = "Produto cadastrado"),
     })
	ProdutoModel adicionar(
			@ApiParam(value = "Representação de um produto", name = "corpo", required = true) ProdutoInput produtoInput);
	
	@ApiOperation("Atualiza um produto")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Produto atualizado"),
	    @ApiResponse(code = 404, message = "Produto não encontrado", response = Problem.class)
	})
	ProdutoModel atualizar(
			@ApiParam(value = "ID de um produto", example = "1", required = true) Long produtoId,
			@ApiParam(value = "Representação de um produto", name = "corpo", required = true) ProdutoInput produtoInput);
	
		
	@ApiOperation("Excluir um produto")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Produto excluída com sucesso", response = Problem.class),
		@ApiResponse(code = 400, message = "ID de produto inválido", response = Problem.class),
		@ApiResponse(code = 404, message = "ID de produto não encontrado", response = Problem.class)
	})
	void excluir(@ApiParam(value = "ID de um produto", example = "1", required = true) Long Id);
	
}
