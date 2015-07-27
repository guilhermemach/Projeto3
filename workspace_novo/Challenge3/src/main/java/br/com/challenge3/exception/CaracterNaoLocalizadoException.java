package br.com.challenge3.exception;

/**
 * Exceção para enviar um erro quando a logica para buscar caracter não localizar nenhum repetido.
 *
 * @author Guilherme
 *
 */
public class CaracterNaoLocalizadoException extends Exception {

	private static final long serialVersionUID = 1L;
	private String msg;

	public CaracterNaoLocalizadoException(String msg) {
		super(msg);
		this.msg = msg;
	}

	public String getMessage() {
		return msg;
	}
}