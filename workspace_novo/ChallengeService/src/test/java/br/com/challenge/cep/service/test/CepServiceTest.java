package br.com.challenge.cep.service.test;

import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.challenge.dao.CepDAO;
import br.com.challenge.exception.CepRequestException;
import br.com.challenge.service.CepService;

/**
 * Classe de testes de validação comportamental da classe CepServico.
 * 
 * @author Guilherme
 *
 */
@ContextConfiguration(locations = { "classpath:/applicationContextHsqlEmbedded.xml",
		"classpath:/applicationContext.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class CepServiceTest {

	// Create Mock
	@Mock
	private CepDAO cepDaoMock;

	@InjectMocks
	@Autowired
	private CepService cepService;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	/**
	 * Test comportamental do chamado do método getCepByCod. As repetições é
	 * para o tratamento do preenchimento do zero da direta para esquerda.
	 * 
	 * @throws CepRequestException
	 */
	@Test
	public void testGetCepByCodRepeteCiclo9() throws CepRequestException {
		cepService.getCepByCod("12345678");
		verify(cepDaoMock, times(9)).getCepByCod(anyString());
	}

	/**
	 * Test comportamental do chamado do método getCepByCod. As repetições é
	 * para o tratamento do preenchimento do zero da direta para esquerda.
	 * 
	 * @throws CepRequestException
	 */
	@Test
	public void testGetCepByCodRepeteCiclo1() throws CepRequestException {
		String sequencia = "00000000";
		cepService.getCepByCod(sequencia);
		verify(cepDaoMock, times(1)).getCepByCod(sequencia);
	}

	/**
	 * Test comportamental do chamado do método getCepByCod. As repetições é
	 * para o tratamento do preenchimento do zero da direta para esquerda.
	 * 
	 * @throws CepRequestException
	 */
	@Test
	public void testGetCepByCodRepeteCiclo5() throws CepRequestException {
		cepService.getCepByCod("00001234");
		verify(cepDaoMock, times(1)).getCepByCod("00001234");
		verify(cepDaoMock, times(1)).getCepByCod("00001230");
		verify(cepDaoMock, times(1)).getCepByCod("00001200");
		verify(cepDaoMock, times(1)).getCepByCod("00001000");
		verify(cepDaoMock, times(1)).getCepByCod("00000000");

	}
}