package br.com.challenge.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.challenge.dao.CepDAO;
import br.com.challenge.entidade.Cep;
import br.com.challenge.exception.CepRequestException;

/**
 * Implementação da Interface de Serviço de busca de CPF.
 * 
 * @author Guilherme
 *
 */
@Service
public class CepServiceImpl implements CepService {

	@Autowired
	private CepDAO dao;

	public Cep getCepByCod(String cod) throws CepRequestException {
		
		/* validação do input. */
		if(cod.length()!= 8 || !isSomenteNumeros(cod)){
			throw new CepRequestException("CEP informado não está corretamente formatado.");
		}		

		String sequencia = "00000000";
		int contador = 0;
		String novoCod = cod;
		Cep cep = null;

		if (cod.equals(sequencia)) {
			cep = dao.getCepByCod(novoCod);
		} else {
			while (!novoCod.equals(sequencia)) {

				novoCod = preparaNovoCep(novoCod, contador);
				cep = dao.getCepByCod(novoCod);

				if (cep != null) {
					break;
				}
				contador++;
			}
		}
		return cep;
	}

	/**
	 * Metodo que formata o cep no padrão definido. Ou seja ele vai preenchendo
	 * com zeros da direita para esquerda.
	 * 
	 * @param cod String
	 * @param contador Integer
	 * @return String
	 */
	private String preparaNovoCep(String cod, Integer contador) {

		StringBuilder novoCop = new StringBuilder(cod.substring(0, cod.length() - contador));

		while (novoCop.length() < 8) {
			novoCop.append("0");
		}
		return novoCop.toString();
	}
	
	/**
	 * Metodo que verifica se a string passada contem somente numeros.
	 * @param str String
	 * @return boolean
	 */
	private boolean isSomenteNumeros(String str) {  
	      
	    if (str == null || str.length() == 0)  
	        return false;  
	      
	    for (int i = 0; i < str.length(); i++) {  	  
	        if (!Character.isDigit(str.charAt(i)))  
	            return false;  
	    }  
	      
	    return true; 
	}
}
