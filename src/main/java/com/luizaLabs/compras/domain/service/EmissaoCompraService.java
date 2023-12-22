package com.luizaLabs.compras.domain.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luizaLabs.compras.domain.exception.CompraNaoEncontradaException;
import com.luizaLabs.compras.domain.exception.NegocioException;
import com.luizaLabs.compras.domain.model.Cliente;
import com.luizaLabs.compras.domain.model.Compra;
import com.luizaLabs.compras.domain.model.Produto;
import com.luizaLabs.compras.domain.repository.CompraRepository;

@Service
public class EmissaoCompraService {
	
	@Autowired
	private CompraRepository compraRepository;

	@Autowired
	private CadastroClienteService cadastroCliente;

	@Autowired
	private CadastroProdutoService cadastroProduto;

	@Transactional
	public Compra salvar(Compra compra) {
		return compraRepository.save(compra);
		
	}
	
	@Transactional
	public Compra emitir(Compra compra) {
		validarCompra(compra);
		
		return compraRepository.save(compra);
	}

	private void validarCompra(Compra compra) {
				
		Cliente cliente = cadastroCliente.buscarOuFalhar(compra.getCliente().getId());
		Produto produto = cadastroProduto.buscarOuFalhar(compra.getProduto().getId());
		
		compra.setCliente(cliente);
		compra.setProduto(produto);
		
		produto.setQuantidadeEmEstoque(calcularQuantidadeEmEstoqueAtualizada(produto, compra));
		produto.setQuantidadeProdutosVendidos(calcularQuantidadeProdutosVendidosAtualizada(produto, compra));
		produto.setLucroTotal(calcularLucroTotal(produto, compra));
		
		compra.setValorTotal(produto.getPreco().multiply(BigDecimal.valueOf(compra.getQuantidade())));
		compra.setValorComDesconto(compra.getValorTotal().multiply(BigDecimal.valueOf(compra.getQuantidade()))
				.subtract(produto.getPreco()
				.multiply(BigDecimal.valueOf(compra.getQuantidade()))
				.multiply(compra.getPorcentagemDesconto().divide(BigDecimal.valueOf(100)))));
				
		try {
	        if (compra.getValorComDesconto().compareTo(compra.getProduto().getValorCusto()) < 0) {
	            throw new NegocioException("O valor total da compra não pode ser menor que o valor "
	            		+ "de custo do produto.");
	        }
	        
	        if (compra.getQuantidade() > compra.getProduto().getQuantidadeEmEstoque()) {
	            throw new NegocioException("A quantidade de produtos em estoque é insuficiente para "
	            		+ "permitir esta venda!");
	        }
	       
	    } catch (CompraNaoEncontradaException e) {
	        throw new NegocioException(e.getMessage(), e);
	    }
		
	}
	
	public BigDecimal calcularValorTotal(Compra compra) {
	    
		if (compra.getProduto() != null && compra.getQuantidade() != null) {
	    
	    	BigDecimal valorProduto = compra.getProduto().getPreco();
	        BigDecimal valorSemDesconto = valorProduto.multiply(BigDecimal.valueOf(compra.getQuantidade()));
	        BigDecimal desconto = valorSemDesconto.multiply(compra.getPorcentagemDesconto().divide(BigDecimal.valueOf(100)));
	        return desconto;
	    } else {
	    	return BigDecimal.ZERO;
	    }
	}

	private Integer calcularQuantidadeProdutosVendidosAtualizada(Produto produto, Compra compra) {
		return produto.getQuantidadeProdutosVendidos() + compra.getQuantidade();
	}
	
	private Integer calcularQuantidadeEmEstoqueAtualizada(Produto produto, Compra compra) {
		return compra.getProduto().getQuantidadeEmEstoque() - compra.getQuantidade();
	}

	private BigDecimal calcularLucroTotal(Produto produto, Compra compra) {
		return produto.getPreco().subtract(compra.getProduto().getValorCusto())
				.multiply(BigDecimal.valueOf(compra.getProduto().getQuantidadeEmEstoque()));
	}
	
	public Compra buscarOuFalhar(Long id) {
		return compraRepository.findById(id)
			.orElseThrow(() -> new CompraNaoEncontradaException(id));
	}
}
