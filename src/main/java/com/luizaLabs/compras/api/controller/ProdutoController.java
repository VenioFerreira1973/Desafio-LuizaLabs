package com.luizaLabs.compras.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.luizaLabs.compras.api.assemblers.assembler.ProdutoModelAssembler;
import com.luizaLabs.compras.api.assemblers.disassembler.ProdutoInputDisassembler;
import com.luizaLabs.compras.api.model.input.ProdutoInput;
import com.luizaLabs.compras.api.model.model.ProdutoModel;
import com.luizaLabs.compras.api.openapi.controller.ProdutoControllerOpenApi;
import com.luizaLabs.compras.api.resourceHateoas.ResourceUriHelper;
import com.luizaLabs.compras.domain.exception.NegocioException;
import com.luizaLabs.compras.domain.exception.ProdutoNaoEncontradoException;
import com.luizaLabs.compras.domain.model.Produto;
import com.luizaLabs.compras.domain.service.CadastroProdutoService;

@RestController
@RequestMapping(path = "/produtos", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProdutoController implements ProdutoControllerOpenApi {

	@Autowired
	CadastroProdutoService cadastroProduto;

	@Autowired
	private ProdutoModelAssembler produtoModelAssembler;

	@Autowired
	private ProdutoInputDisassembler produtoInputDisassembler;

	@Override
	@GetMapping
	public CollectionModel<ProdutoModel> listar() {
		List<Produto> todosProdutos = cadastroProduto.todos();
		return produtoModelAssembler.toCollectionModel(todosProdutos);
	}

	@Override
	@GetMapping("/{produtoId}")
	public ProdutoModel buscar(@PathVariable Long produtoId) {

		try {
			Produto produto = cadastroProduto.buscarOuFalhar(produtoId);
			return produtoModelAssembler.toModel(produto);
		} catch (ProdutoNaoEncontradoException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}

	@Override
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ProdutoModel adicionar(@RequestBody @Valid ProdutoInput produtoInput) {

		try {
			Produto produto = produtoInputDisassembler.toDomainObject(produtoInput);
			produto = cadastroProduto.salvar(produto);
			ProdutoModel produtoModel = produtoModelAssembler.toModel(produto);
			ResourceUriHelper.addUriInResponseHeader(produtoModel.getId());
			return produtoModelAssembler.toModel(produto);

		} catch (ProdutoNaoEncontradoException e) {
			throw new NegocioException(e.getMessage(), e);
		}

	}

	@PutMapping("/{productId}")
	public ProdutoModel atualizar(@PathVariable Long produtoId, @RequestBody @Valid ProdutoInput produtoInput) {

		try {

			Produto produtoAtual = cadastroProduto.buscarOuFalhar(produtoId);
			produtoInputDisassembler.copyToDomainObject(produtoInput, produtoAtual);
			return produtoModelAssembler.toModel(cadastroProduto.salvar(produtoAtual));
		} catch (ProdutoNaoEncontradoException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}

	@DeleteMapping("/{Id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluir(@PathVariable Long Id) {
		cadastroProduto.excluir(Id);

	}

	@GetMapping("/relatorio")
	public CollectionModel<ProdutoModel> listarRelatorio() {
		List<Produto> todosProdutos = cadastroProduto.todos();
		return produtoModelAssembler.toCollectionModel(todosProdutos);
	}

}
