package com.luizaLabs.compras.api.assemblers.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.luizaLabs.compras.api.controller.ClienteController;
import com.luizaLabs.compras.api.links.Links;
import com.luizaLabs.compras.api.model.model.ClienteModel;
import com.luizaLabs.compras.domain.model.Cliente;

@Component
public class ClienteModelAssembler extends RepresentationModelAssemblerSupport<Cliente, ClienteModel>{
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private Links links;
	
	
	public ClienteModelAssembler() {
		super(ClienteController.class, ClienteModel.class);
	}

	@Override
	public ClienteModel toModel(Cliente cliente) {
		
		ClienteModel clienteModel = createModelWithId(cliente.getId(), cliente);
		
		modelMapper.map(cliente, clienteModel);
		
		clienteModel.add(links.linkToClientes("clientes"));
			
		return clienteModel; 
	}
	
	@Override
	public CollectionModel<ClienteModel> toCollectionModel(Iterable<? extends Cliente> entities) {
		return super.toCollectionModel(entities)
				.add(links.linkToClientes());
	}

}
