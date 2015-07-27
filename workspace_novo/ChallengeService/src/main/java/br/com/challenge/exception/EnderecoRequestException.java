package br.com.challenge.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exce��o de negocio para valida��o de Endere�o.
 * 
 * @author Guilherme
 *
 */
@ResponseStatus(value=HttpStatus.BAD_REQUEST, reason="Campos numero, rua, cep, municipio e estado s�o obrigat�rios.")
public class EnderecoRequestException extends Exception {

	private static final long serialVersionUID = 1L;
	private String msg;

	public EnderecoRequestException(String msg) {
		super(msg);
		this.msg = msg;
	}

	public String getMessage() {
		return msg;
	}
}
