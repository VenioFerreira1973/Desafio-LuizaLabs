package com.luizaLabs.compras.domain.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Produto {

	@Id
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	
	@Column(nullable = false)
	private String nome;
	
	@Column(nullable = false)
	private String descricao;
	
	@Column(nullable = false)
	private BigDecimal preco;
	
	@Column(nullable = false)
	private BigDecimal valorCusto;
	
	@Column(nullable = false)
	private BigDecimal margemLucro;
	
	
	@Column(nullable = false)
	private Integer quantidadeProdutosVendidos;
	
	@Column(nullable = false)
	private Integer quantidadeEmEstoque;
	
	@Column(nullable = false)
    private BigDecimal lucroTotal;
	
}
