package com.luizaLabs.compras.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luizaLabs.compras.domain.exception.ClienteNaoEncontradoException;
import com.luizaLabs.compras.domain.exception.EntidadeEmUsoException;
import com.luizaLabs.compras.domain.exception.NegocioException;
import com.luizaLabs.compras.domain.model.Cliente;
import com.luizaLabs.compras.domain.repository.ClienteRepository;

@Service
public class CadastroClienteService {
	
	private static final String CLIENTE_EM_USO = "Cliente de código %d não pode ser excluído, pois está em uso";
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	
	public Optional<Cliente> porId(Long Id) {
		return clienteRepository.findById(Id);
		
	}

	public List<Cliente> todos(){
		return clienteRepository.findAll();
	}
	
	@Transactional
	public Cliente salvar(Cliente cliente) {
		
		clienteRepository.detach(cliente);
		
		Optional<Cliente> clienteExistente = clienteRepository.findByEmail(cliente.getEmail());
		
		if(clienteExistente.isPresent() && !clienteExistente.get().equals(cliente)) {
			throw new NegocioException(
					String.format("Já existe cliente cadastrado com o e-mail %s", cliente.getEmail()));
 			
		}

		return clienteRepository.save(cliente);
		
	}
	@Transactional
	public void excluir(Long Id) {
		
		try {
			clienteRepository.deleteById(Id);
			clienteRepository.flush();
			
		}catch(EmptyResultDataAccessException e) {
			throw new ClienteNaoEncontradoException(Id);
			
		}catch(DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format(CLIENTE_EM_USO, Id));
		}
	}
  
	public Cliente buscarOuFalhar(Long Id) {

		return clienteRepository.findById(Id)
				.orElseThrow(() -> new ClienteNaoEncontradoException(Id));

	}
}
