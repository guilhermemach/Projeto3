package br.com.challenge.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exce��o de negocio para valida��o de CEP.
 * 
 * @author Guilherme
 *
 */
@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="CEP n�o localizado")
public class CepInexistenteException extends Exception {

	private static final long serialVersionUID = 1L;
	private String msg;

	public CepInexistenteException(String msg) {
		super(msg);
		this.msg = msg;
	}

	public String getMessage() {
		return msg;
	}
}
