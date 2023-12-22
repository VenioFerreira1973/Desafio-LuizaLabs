package com.luizaLabs.compras.domain.exception;

public class NegocioException extends RuntimeException {// extends ResponseStatusException{

	private static final long serialVersionUID = 1L;

	public NegocioException(String mensagem) {
		super(mensagem);
	}
	
	public NegocioException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
	
	


}
