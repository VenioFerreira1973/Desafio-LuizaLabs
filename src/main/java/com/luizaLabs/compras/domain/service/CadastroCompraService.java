package com.luizaLabs.compras.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luizaLabs.compras.domain.exception.CompraNaoEncontradaException;
import com.luizaLabs.compras.domain.model.Compra;
import com.luizaLabs.compras.domain.repository.CompraRepository;

@Service
public class CadastroCompraService {

	@Autowired
	private CompraRepository compraRepository;

	
	public List<Compra> todos(){
		return compraRepository.findAll();
	}
	
	@Transactional
	public Compra salvar(Compra compra) {
		return compraRepository.save(compra);
		
	}
	
	@Transactional
	public void excluir(Long Id) {
		
		try {
			compraRepository.deleteById(Id);
			compraRepository.flush();
			
		}catch(EmptyResultDataAccessException e) {
			throw new CompraNaoEncontradaException(Id);
			
		}
	}
	
	public Compra buscarOuFalhar(Long id) {
		return compraRepository.findById(id)
			.orElseThrow(() -> new CompraNaoEncontradaException(id));
	}
	
}
