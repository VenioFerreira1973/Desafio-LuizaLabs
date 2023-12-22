package com.luizaLabs.compras.api.assemblers.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.luizaLabs.compras.api.controller.CompraController;
import com.luizaLabs.compras.api.links.Links;
import com.luizaLabs.compras.api.model.model.CompraModel;
import com.luizaLabs.compras.domain.model.Compra;

@Component
public class CompraModelAssembler extends RepresentationModelAssemblerSupport<Compra, CompraModel> {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private Links links;
	

	public CompraModelAssembler() {
		super(CompraController.class, CompraModel.class);
	}

	@Override
	public CompraModel toModel(Compra compra) {

		CompraModel compraModel = createModelWithId(compra.getId(), compra);
		
		modelMapper.map(compra, compraModel);
		
		compraModel.add(links.linkToCompras("compras"));

		return compraModel;
	}	

	@Override
	public CollectionModel<CompraModel> toCollectionModel(Iterable<? extends Compra> entities) {
		return super.toCollectionModel(entities).add(linkTo(CompraController.class).withSelfRel());
	}

}
