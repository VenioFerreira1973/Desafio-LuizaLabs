package com.luizaLabs.compras.domain.exception;

public class CompraNaoEncontradaException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;

	public CompraNaoEncontradaException(Long id) {
		super(String.format("Não existe um cadastro de compra com código %d", id));
	}

}
