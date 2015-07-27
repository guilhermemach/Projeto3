package br.com.challenge.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exce��o de negocio para valida��o de Endere�o.
 * 
 * @author Guilherme
 *
 */
@ResponseStatus(value=HttpStatus.BAD_REQUEST, reason="Os dados informados de municio e cidade n�o conferem com CEP. ")
public class EnderecoIncorretoException extends Exception {

	private static final long serialVersionUID = 1L;
	private String msg;

	public EnderecoIncorretoException(String msg) {
		super(msg);
		this.msg = msg;
	}

	public String getMessage() {
		return msg;
	}
}
