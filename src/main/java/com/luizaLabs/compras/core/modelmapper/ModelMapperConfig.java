package com.luizaLabs.compras.core.modelmapper;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.luizaLabs.compras.api.model.input.CompraInput;
import com.luizaLabs.compras.domain.model.Compra;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        var modelMapper = new ModelMapper();

        modelMapper.getConfiguration().setAmbiguityIgnored(true);

        modelMapper.createTypeMap(Compra.class, CompraInput.class)
            .addMapping(Compra::getCliente, CompraInput::setCliente)
        	.addMapping(Compra::getProduto, CompraInput::setProduto);
        
        return modelMapper;
    }
}