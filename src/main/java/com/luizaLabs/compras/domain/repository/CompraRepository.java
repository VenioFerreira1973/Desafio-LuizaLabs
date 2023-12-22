package com.luizaLabs.compras.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.luizaLabs.compras.domain.model.Compra;

public interface CompraRepository extends CustomJpaRepository<Compra, Long>,
	JpaSpecificationExecutor<Compra>{
	
	Optional<Compra> findById(Long id);
	
	@Query("from Compra c join fetch c.cliente join fetch c.produto p")
	List<Compra> findAll();
	

}
