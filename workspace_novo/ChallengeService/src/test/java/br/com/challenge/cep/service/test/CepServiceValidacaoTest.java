package br.com.challenge.cep.service.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.challenge.entidade.Cep;
import br.com.challenge.exception.CepRequestException;
import br.com.challenge.service.CepService;

/**
 * Classe de testes de validação da classe CepServico.
 * 
 * @author Guilherme
 *
 */
@ContextConfiguration(locations = {"classpath:/applicationContextHsqlEmbedded.xml","classpath:/applicationContext.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class CepServiceValidacaoTest {
	

	@Autowired
	CepService service; 	
	
	/**
	 * Teste de validação de Cep inexistente. 
	 * 
	 * @throws CepRequestException
	 */
	@Test	
	public void testGetCepDAOByCepValidaCheckNull() throws CepRequestException {
		Cep cep = service.getCepByCod("12345678");
		Assert.assertNull(cep);
	}
	
	/**
	 * Teste de validação de do input para o CEP. 
	 * 
	 * @throws CepRequestException
	 */
	@Test(expected = CepRequestException.class)	
	public void testGetCepDAOByCepValidaInputMaior() throws CepRequestException {
		service.getCepByCod("1234567843432432");		
	}
	
	/**
	 * Teste de validação de do input para o CEP. 
	 * 
	 * @throws CepRequestException
	 */
	@Test(expected = CepRequestException.class)	
	public void testGetCepDAOByCepValidaInputMenor() throws CepRequestException {
		service.getCepByCod("12333");		
	}
	
	/**
	 * Teste de validação de caracteres diferente de numeros. 
	 * 
	 * @throws CepRequestException
	 */
	@Test(expected = CepRequestException.class)	
	public void testGetCepDAOByCepValidaInputInvalidoCaracter() throws CepRequestException {
		service.getCepByCod("BCDEFG&*");		
	}
}
