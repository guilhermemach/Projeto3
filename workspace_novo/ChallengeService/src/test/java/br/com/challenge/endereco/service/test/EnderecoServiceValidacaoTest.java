package br.com.challenge.endereco.service.test;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.mockito.Mockito.*;

import br.com.challenge.entidade.Cep;
import br.com.challenge.entidade.Endereco;
import br.com.challenge.entidade.Municipio;
import br.com.challenge.exception.CepInexistenteException;
import br.com.challenge.exception.CepRequestException;
import br.com.challenge.exception.EnderecoRequestException;
import br.com.challenge.service.CepService;
import br.com.challenge.service.EnderecoService;
import br.com.challenge.type.Estado;

/**
 * Classe de testes de validação da classe EnderecoServico.
 * 
 * @author Guilherme
 *
 */
@ContextConfiguration(locations = { "classpath:/applicationContextHsqlEmbedded.xml",
		"classpath:/applicationContext.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class EnderecoServiceValidacaoTest {

	@InjectMocks
	@Autowired
	EnderecoService service;

	@Mock
	CepService cepServiceMock;

	/**
	 * Configuração do mockito.
	 * 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {		
		 MockitoAnnotations.initMocks(this);
	}

	/**
	 * Validação de argumento nulo para creação de endereço.
	 * 
	 * @throws CepInexistenteException
	 * @throws CepRequestException
	 * @throws EnderecoRequestException
	 */
	@Test(expected = EnderecoRequestException.class)
	public void testCreateEnderecoValidaObjetoNulo() throws CepInexistenteException, CepRequestException, EnderecoRequestException {
		service.createEndereco(null);
	}
	
	/**
	 * Validação de cadastro de endereço sem municipio.
	 * 
	 * @throws CepInexistenteException
	 * @throws CepRequestException
	 * @throws EnderecoRequestException
	 */
	@Test(expected = EnderecoRequestException.class)
	public void testCreateEnderecoValidaSemMunicipio() throws CepInexistenteException, CepRequestException, EnderecoRequestException {
		Endereco endereco = new Endereco();
		endereco.setCep("11111111");
		endereco.setRua("rua");
		endereco.setNumero(1);
		service.createEndereco(endereco);
	}
	
	/**
	 * Validação de cadastro de endereço sem CEP.
	 *	
	 * @throws CepInexistenteException
	 * @throws CepRequestException
	 * @throws EnderecoRequestException
	 */
	@Test(expected = EnderecoRequestException.class)
	public void testCreateEnderecoValidaSemCep() throws CepInexistenteException, CepRequestException, EnderecoRequestException {
		Endereco endereco = new Endereco();
		endereco.setRua("rua");
		endereco.setNumero(1);
		endereco.setMunicipio(new Municipio("Recife", Estado.PE));
		service.createEndereco(endereco);
	}
	
	/**
	 * Validação de cadastro de endereço sem rua.
	 *	
	 * @throws CepInexistenteException
	 * @throws CepRequestException
	 * @throws EnderecoRequestException
	 */
	@Test(expected = EnderecoRequestException.class)
	public void testCreateEnderecoValidaSemRua() throws CepInexistenteException, CepRequestException, EnderecoRequestException {
		Endereco endereco = new Endereco();
		endereco.setCep("11111111");
		endereco.setNumero(1);
		endereco.setMunicipio(new Municipio("Recife", Estado.PE));
		service.createEndereco(endereco);
	}
	
	/**
	 * Validação de cadastro de endereço sem Numero.
	 *	
	 * @throws CepInexistenteException
	 * @throws CepRequestException
	 * @throws EnderecoRequestException
	 */
	@Test(expected = EnderecoRequestException.class)
	public void testCreateEnderecoValidaSemNumero() throws CepInexistenteException, CepRequestException, EnderecoRequestException {
		Endereco endereco = new Endereco();
		endereco.setCep("11111111");
		endereco.setRua("rua");
		endereco.setMunicipio(new Municipio("Recife", Estado.PE));
		service.createEndereco(endereco);
	}
	
	/**
	 * Validação de dados inconsistentes (municipio / CEP).
	 *	
	 * @throws CepInexistenteException
	 * @throws CepRequestException
	 * @throws EnderecoRequestException
	 */
	@Test(expected = EnderecoRequestException.class)
	public void testCreateEnderecoValidaEstadoDiferente() throws CepInexistenteException, CepRequestException, EnderecoRequestException {
		Endereco endereco = new Endereco();
		endereco.setCep("11111111");
		endereco.setRua("rua");
		endereco.setNumero(1);
		endereco.setMunicipio(new Municipio("Fortaleza", Estado.CE));
		
		/* mock */
		Cep cep= new Cep();
		cep.setMunicipio(new Municipio("Fortaleza", Estado.PE));
		when(cepServiceMock.getCepByCod("11111111")).thenReturn(cep);
		
		service.createEndereco(endereco);
	}
	
	/**
	 * Validação de dados inconsistentes (municipio da busca do CEP / com o municipio informado pelo usuario).
	 *	
	 * @throws CepInexistenteException
	 * @throws CepRequestException
	 * @throws EnderecoRequestException
	 */
	@Test(expected = EnderecoRequestException.class)
	public void testCreateEnderecoValidaMunicipioDiferente() throws CepInexistenteException, CepRequestException, EnderecoRequestException {
		Endereco endereco = new Endereco();
		endereco.setCep("11111111");
		endereco.setRua("rua");
		endereco.setNumero(1);
		endereco.setMunicipio(new Municipio("Fortaleza", Estado.CE));
		
		/* mock */
		Cep cep= new Cep();
		cep.setMunicipio(new Municipio("Outra cidade", Estado.CE));
		when(cepServiceMock.getCepByCod("11111111")).thenReturn(cep);
		
		service.createEndereco(endereco);
	}
	
	/**
	 * Validação de dados inconsistentes (estado da busca do CEP / com o estado informado pelo usuario).
	 *	
	 * @throws CepInexistenteException
	 * @throws CepRequestException
	 * @throws EnderecoRequestException
	 */
	@Test(expected = EnderecoRequestException.class)
	public void testCreateEnderecoValidaMunicipioEstadoDiferente() throws CepInexistenteException, CepRequestException, EnderecoRequestException {
		Endereco endereco = new Endereco();
		endereco.setCep("11111111");
		endereco.setRua("rua");
		endereco.setNumero(1);
		endereco.setMunicipio(new Municipio("Fortaleza", Estado.CE));
		
		/* mock */
		Cep cep= new Cep();
		cep.setMunicipio(new Municipio("Maceio", Estado.AL));
		when(cepServiceMock.getCepByCod("11111111")).thenReturn(cep);
		
		service.createEndereco(endereco);
	}

}
