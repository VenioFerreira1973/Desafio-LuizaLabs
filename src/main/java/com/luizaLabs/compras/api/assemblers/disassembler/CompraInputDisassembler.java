package com.luizaLabs.compras.api.assemblers.disassembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.luizaLabs.compras.api.model.input.CompraInput;
import com.luizaLabs.compras.domain.model.Compra;

@Component
public class CompraInputDisassembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public Compra toDomainObject(CompraInput compraInput) {
		
		return modelMapper.map(compraInput, Compra.class);
	}
	
	public void copyToDomainObject(CompraInput compraInput, Compra compra) {
		
		modelMapper.map(compraInput, compra);
	}

}
