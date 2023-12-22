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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.luizaLabs.compras.api.assemblers.assembler.CompraModelAssembler;
import com.luizaLabs.compras.api.assemblers.disassembler.CompraInputDisassembler;
import com.luizaLabs.compras.api.model.input.CompraInput;
import com.luizaLabs.compras.api.model.model.CompraModel;
import com.luizaLabs.compras.api.openapi.controller.CompraControllerOpenApi;
import com.luizaLabs.compras.domain.exception.CompraNaoEncontradaException;
import com.luizaLabs.compras.domain.exception.NegocioException;
import com.luizaLabs.compras.domain.model.Compra;
import com.luizaLabs.compras.domain.service.CadastroCompraService;
import com.luizaLabs.compras.domain.service.EmissaoCompraService;

@RestController
@RequestMapping(value = "/compras", produces = MediaType.APPLICATION_JSON_VALUE)
public class CompraController implements CompraControllerOpenApi{

	@Autowired
	private CadastroCompraService cadastroCompra;
	
	@Autowired
	private EmissaoCompraService emissaoCompra;
	
	
	@Autowired
	private CompraModelAssembler compraModelAssembler;
	
	@Autowired
	private CompraInputDisassembler compraInputDisassembler;
	
	@Override
	@GetMapping
	public CollectionModel<CompraModel> listar() {
		List<Compra> todosCompras = cadastroCompra.todos();
		return compraModelAssembler.toCollectionModel(todosCompras);
	}
	
	@Override
	@GetMapping("/{id}")
	public CompraModel buscar(@PathVariable Long id) {
		
		try {
			Compra compra = cadastroCompra.buscarOuFalhar(id);
			return compraModelAssembler.toModel(compra);
		} catch (CompraNaoEncontradaException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}
	
	@DeleteMapping("/{Id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluir(@PathVariable Long Id) {
		cadastroCompra.excluir(Id);

	}
	
	@PostMapping("/efetuar-compra")
	@ResponseStatus(HttpStatus.CREATED)
	public CompraModel efetuarCompra(@Valid @RequestBody CompraInput compraInput) {

		Compra novaCompra = compraInputDisassembler.toDomainObject(compraInput);
        novaCompra = emissaoCompra.emitir(novaCompra);
        return compraModelAssembler.toModel(novaCompra);
	    
	}
	
}
