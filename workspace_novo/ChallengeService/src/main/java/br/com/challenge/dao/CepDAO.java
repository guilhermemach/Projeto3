package br.com.challenge.dao;

/**
 * Interface para busca do CEP.
 */
import br.com.challenge.entidade.Cep;

public interface CepDAO {

	public Cep getCepByCod(String cod);		

}
