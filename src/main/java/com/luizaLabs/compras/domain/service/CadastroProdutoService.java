package com.luizaLabs.compras.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luizaLabs.compras.domain.exception.EntidadeEmUsoException;
import com.luizaLabs.compras.domain.exception.ProdutoNaoEncontradoException;
import com.luizaLabs.compras.domain.model.Produto;
import com.luizaLabs.compras.domain.repository.ProdutoRepository;

@Service
public class CadastroProdutoService {

	private static final String PRODUTO_EM_USO = "Produto de código %d não pode ser excluído, pois está em uso";
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	public Optional<Produto> porId(Long Id) {
		return produtoRepository.findById(Id);
		
	}

	public List<Produto> todos(){
		return produtoRepository.findAll();
	}
	
	@Transactional
	public Produto salvar(Produto produto) {
		return produtoRepository.save(produto);
		
	}
	@Transactional
	public void excluir(Long Id) {
		
		try {
			produtoRepository.deleteById(Id);
			produtoRepository.flush();
			
		}catch(EmptyResultDataAccessException e) {
			throw new ProdutoNaoEncontradoException(Id);
			
		}catch(DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format(PRODUTO_EM_USO, Id));
		}
	}
	
	public Produto buscarOuFalhar(Long Id) {

		return produtoRepository.findById(Id)
				.orElseThrow(() -> new ProdutoNaoEncontradoException(Id));
	}
	
}
