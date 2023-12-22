package com.luizaLabs.compras.api.assemblers.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import java.math.BigDecimal;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.luizaLabs.compras.api.controller.ProdutoController;
import com.luizaLabs.compras.api.links.Links;
import com.luizaLabs.compras.api.model.model.ProdutoModel;
import com.luizaLabs.compras.domain.model.Produto;

@Component
public class ProdutoModelAssembler extends RepresentationModelAssemblerSupport<Produto, ProdutoModel> {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private Links links;

	public ProdutoModelAssembler() {
		super(ProdutoController.class, ProdutoModel.class);
	}

	@Override
	public ProdutoModel toModel(Produto produto) {
		ProdutoModel produtoModel = createModelWithId(produto.getId(), produto);

		modelMapper.map(produto, produtoModel);

		produtoModel.setQuantidadeProdutosVendidos(produto.getQuantidadeProdutosVendidos());
		produtoModel.setQuantidadeEmEstoque(calcularQuantidadeEmEstoqueAtualizada(produto));
		produtoModel.setLucroTotal(calcularLucroTotal(produto));

		produtoModel.add(links.linkToProdutos("produtos"));
		
		return produtoModel;
	}

	private Integer calcularQuantidadeEmEstoqueAtualizada(Produto produto) {
		return produto.getQuantidadeEmEstoque() - produto.getQuantidadeProdutosVendidos();
	}

	private BigDecimal calcularLucroTotal(Produto produto) {
		return produto.getPreco().subtract(produto.getValorCusto())
				.multiply(BigDecimal.valueOf(produto.getQuantidadeProdutosVendidos()));
	}

	@Override
	public CollectionModel<ProdutoModel> toCollectionModel(Iterable<? extends Produto> entities) {
		return super.toCollectionModel(entities).add(linkTo(ProdutoController.class).withSelfRel());
	}

}
