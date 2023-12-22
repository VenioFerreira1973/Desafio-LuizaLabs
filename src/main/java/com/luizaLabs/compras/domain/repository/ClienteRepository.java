package com.luizaLabs.compras.domain.repository;

import java.util.Optional;

import com.luizaLabs.compras.domain.model.Cliente;

public interface ClienteRepository extends CustomJpaRepository<Cliente, Long>{
	
	Optional<Cliente> findByEmail(String email);
}
