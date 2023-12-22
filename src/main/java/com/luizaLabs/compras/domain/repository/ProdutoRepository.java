package com.luizaLabs.compras.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.luizaLabs.compras.domain.model.Produto;

public interface ProdutoRepository extends CustomJpaRepository<Produto, Long>{
	
	@Query("from Produto where id = :id")
    Optional<Produto> findById(@Param("id") Long produtoId);
    
	Optional<Produto> findByNome(String nome);
		
}
