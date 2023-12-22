package com.luizaLabs.compras.api.openapi.model;

import java.util.List;

import org.springframework.hateoas.Links;

import com.luizaLabs.compras.api.model.model.CompraResumoModel;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel("ComprasResumoModel")
@Data
public class ComprasResumoModelOpenApi {
	
    private ComprasResumoEmbeddedModelOpenApi _embedded;
    private Links _links;
    private PageModelOpenApi page;
    
    @ApiModel("ComprasResumoEmbeddedModel")
    @Data
    public class ComprasResumoEmbeddedModelOpenApi {
        
        private List<CompraResumoModel> compras;
        
    }  
	    
}  


