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

import com.luizaLabs.compras.api.assemblers.assembler.ClienteModelAssembler;
import com.luizaLabs.compras.api.assemblers.disassembler.ClienteInputDisassembler;
import com.luizaLabs.compras.api.model.input.ClienteInput;
import com.luizaLabs.compras.api.model.model.ClienteModel;
import com.luizaLabs.compras.api.openapi.controller.ClienteControllerOpenApi;
import com.luizaLabs.compras.api.resourceHateoas.ResourceUriHelper;
import com.luizaLabs.compras.domain.exception.ClienteNaoEncontradoException;
import com.luizaLabs.compras.domain.exception.NegocioException;
import com.luizaLabs.compras.domain.model.Cliente;
import com.luizaLabs.compras.domain.service.CadastroClienteService;

@RestController
@RequestMapping(path = "/clientes", produces = MediaType.APPLICATION_JSON_VALUE)
public class ClienteController implements ClienteControllerOpenApi {

	@Autowired
	private CadastroClienteService cadastroCliente;

	@Autowired
	private ClienteModelAssembler clienteModelAssembler;

	@Autowired
	private ClienteInputDisassembler clienteInputDisassembler;
	
	
	@Override
	@GetMapping
	public CollectionModel<ClienteModel> listar() {
		List<Cliente> todosClientes = cadastroCliente.todos();
		return clienteModelAssembler.toCollectionModel(todosClientes);
	}
	
	@Override
	@GetMapping("/{Id}")
	public ClienteModel buscar(@PathVariable Long Id) {

		try {
			
			Cliente cliente = cadastroCliente.buscarOuFalhar(Id);
			return clienteModelAssembler.toModel(cliente);
		} catch (ClienteNaoEncontradoException e) {
			throw new NegocioException(e.getMessage());
		}
	}

		@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ClienteModel adicionar(@RequestBody @Valid ClienteInput clienteInput) {

		try {
			
			Cliente cliente = clienteInputDisassembler.toDomainObject(clienteInput);
			cliente = cadastroCliente.salvar(cliente);
			ClienteModel clienteModel = clienteModelAssembler.toModel(cliente);
			ResourceUriHelper.addUriInResponseHeader(clienteModel.getId());
			return clienteModel;
			

		} catch (ClienteNaoEncontradoException e) {
			throw new NegocioException(e.getMessage());
		}
	}
	
	@PutMapping("/{clienteId}")
	public ClienteModel atualizar(@PathVariable Long clienteId, @RequestBody @Valid ClienteInput clienteInput) {
		
		try {
			
			Cliente clienteAtual = cadastroCliente.buscarOuFalhar(clienteId);
			clienteInputDisassembler.copyToDomainObject(clienteInput, clienteAtual);
			clienteAtual = cadastroCliente.salvar(clienteAtual);
			return clienteModelAssembler.toModel(clienteAtual);
		} catch (ClienteNaoEncontradoException e) {
			throw new NegocioException(e.getMessage());
		}
	}

	@DeleteMapping("/{Id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluir(@PathVariable Long Id) {
		cadastroCliente.excluir(Id);

	}

}
