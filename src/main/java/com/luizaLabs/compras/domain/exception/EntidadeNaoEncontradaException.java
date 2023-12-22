package com.luizaLabs.compras.domain.exception;

public abstract class EntidadeNaoEncontradaException extends NegocioException {// extends ResponseStatusException{

	private static final long serialVersionUID = 1L;

	public EntidadeNaoEncontradaException(String mensagem) {
		super(mensagem);
	}

	/*
	 * código comentado seria utilizado se fosse extender ResponseStatusException
	 * public EntidadeNaoEncontradaException(HttpStatus status, String mensagem) {
	 * super(status, mensagem); }
	 * 
	 * public EntidadeNaoEncontradaException(String mensagem) {
	 * this(HttpStatus.NOT_FOUND, mensagem); }
	 */

}
