package com.luizaLabs.compras.domain.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.domain.AbstractAggregateRoot;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@Entity
public class Compra extends AbstractAggregateRoot<Compra> {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Cliente cliente;

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false)
    private Produto produto;
	
	@CreationTimestamp
	private OffsetDateTime dataCriacao;

	@Column(nullable = false)
	private Integer quantidade;
	
	@Column(nullable = false)
	private BigDecimal porcentagemDesconto;

	@Column
	private BigDecimal valorTotal;

	@Column
	private BigDecimal valorComDesconto;
	
		
}