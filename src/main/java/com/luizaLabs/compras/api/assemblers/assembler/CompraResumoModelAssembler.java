package com.luizaLabs.compras.api.assemblers.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.luizaLabs.compras.api.controller.CompraController;
import com.luizaLabs.compras.api.links.Links;
import com.luizaLabs.compras.api.model.model.CompraResumoModel;
import com.luizaLabs.compras.domain.model.Compra;

@Component
public class CompraResumoModelAssembler extends RepresentationModelAssemblerSupport<Compra, CompraResumoModel>{
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private Links links;
	
	
	public CompraResumoModelAssembler() {
		super(CompraController.class, CompraResumoModel.class);
		
	}


	@Override
    public CompraResumoModel toModel(Compra compra) {
        CompraResumoModel compraModel = createModelWithId(compra.getId(), compra);
        
        modelMapper.map(compra, compraModel);
        
        compraModel.add(links.linkToCompras("compras"));
               
        return compraModel;
    }

}
