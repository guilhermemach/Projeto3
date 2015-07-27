package br.com.challenge.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exceção de negocio para validação de CEP.
 * 
 * @author Guilherme
 *
 */
@ResponseStatus(value=HttpStatus.BAD_REQUEST, reason="CEP informado não está corretamente formatado.")
public class CepRequestException extends Exception {

	private static final long serialVersionUID = 1L;
	private String msg;

	public CepRequestException(String msg) {
		super(msg);
		this.msg = msg;
	}

	public String getMessage() {
		return msg;
	}
}
