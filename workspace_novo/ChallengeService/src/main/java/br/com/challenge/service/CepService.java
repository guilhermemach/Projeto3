package br.com.challenge.service;

/**
 * Interface de Serviço de busca de CPF.
 */
import br.com.challenge.entidade.Cep;
import br.com.challenge.exception.CepRequestException;


public interface CepService {

	public Cep getCepByCod(String cod) throws CepRequestException;
	
	
}
