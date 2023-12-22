package com.luizaLabs.compras.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luizaLabs.compras.api.links.Links;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class RootEntryPointController {
	
	@Autowired
	private Links links;
	
	
	@GetMapping
	public RootEntryPointModel root() {
		var rootEntryPointModel = new RootEntryPointModel();
		rootEntryPointModel.add(links.linkToCompras("compras"));
	    
	    rootEntryPointModel.add(links.linkToClientes("clientes"));
	    
	    rootEntryPointModel.add(links.linkToProdutos("produtos"));
	  
		return rootEntryPointModel;
	}
	
	
	private static class RootEntryPointModel extends RepresentationModel<RootEntryPointModel>{
		
	}

}
