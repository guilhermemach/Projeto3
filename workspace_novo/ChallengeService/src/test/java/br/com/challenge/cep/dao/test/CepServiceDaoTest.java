package br.com.challenge.cep.dao.test;

import javax.sql.DataSource;

import org.dbunit.database.DatabaseDataSourceConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.challenge.entidade.Cep;
import br.com.challenge.exception.CepRequestException;
import br.com.challenge.service.CepService;
import br.com.challenge.type.Estado;

@ContextConfiguration(locations = { "classpath:/applicationContextHsqlEmbedded.xml",
		"classpath:/applicationContext.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class CepServiceDaoTest {

	@Autowired
	CepService service;

	@Autowired
	DataSource dataSource;

	/**
	 * Configuração e carga do DBunit para excecuçaõ dos testes.
	 * 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		IDataSet dataSet = new FlatXmlDataSetBuilder().build(this.getClass().getResource("/cep-dataset.xml"));
		IDatabaseConnection dbConn = new DatabaseDataSourceConnection(dataSource);
		DatabaseOperation.CLEAN_INSERT.execute(dbConn, dataSet);
	}

	/**
	 * Teste de busca do CEP 12345678 qual foi carregado via DBunit.
	 * 
	 * @throws CepRequestException
	 */
	@Test
	public void testGetCepDAOByCep_12345678() throws CepRequestException {
		Cep cep = service.getCepByCod("12345678");
		Assert.assertEquals("centro", cep.getBairro());
		Assert.assertEquals("12345678", cep.getNumeroCep());
		Assert.assertEquals("rua sao paulo", cep.getRua());
		Assert.assertEquals("São Paulo", cep.getMunicipio().getDescricao());
		Assert.assertEquals(Estado.SP, cep.getMunicipio().getUf());
	}

	/**
	 * Teste de busca do CEP 87654321 qual foi carregado via DBunit.
	 * 
	 * @throws CepRequestException
	 */
	@Test
	public void testGetCepDAOByCep_87654321() throws CepRequestException {
		Cep cep = service.getCepByCod("87654321");
		Assert.assertEquals("centro", cep.getBairro());
		Assert.assertEquals("87654321", cep.getNumeroCep());
		Assert.assertEquals("rua rio de janeiro", cep.getRua());
		Assert.assertEquals("Rio de Janeiro", cep.getMunicipio().getDescricao());
		Assert.assertEquals(Estado.RJ, cep.getMunicipio().getUf());
	}

	/**
	 * Teste de busca do CEP 00000000 qual foi carregado via DBunit.
	 * 
	 * @throws CepRequestException
	 */
	@Test
	public void testGetCepDAOByCep_00000000() throws CepRequestException {
		Cep cep = service.getCepByCod("00000000");
		Assert.assertEquals("centro", cep.getBairro());
		Assert.assertEquals("00000000", cep.getNumeroCep());
		Assert.assertEquals("rua rio de janeiro", cep.getRua());
		Assert.assertEquals("Rio de Janeiro", cep.getMunicipio().getDescricao());
		Assert.assertEquals(Estado.RJ, cep.getMunicipio().getUf());
	}

	/**
	 * Teste de busca do CEP 10101010 qual não existe na base e por fins de
	 * regra ele retorna o cep 00000000 que foi antes gravado via dbunit.
	 * 
	 * @throws CepRequestException
	 */
	@Test
	public void testGetCepDAOByCep_Desconhecido() throws CepRequestException {
		Cep cep = service.getCepByCod("10101010");
		Assert.assertEquals("centro", cep.getBairro());
		Assert.assertEquals("00000000", cep.getNumeroCep());
		Assert.assertEquals("rua rio de janeiro", cep.getRua());
		Assert.assertEquals("Rio de Janeiro", cep.getMunicipio().getDescricao());
		Assert.assertEquals(Estado.RJ, cep.getMunicipio().getUf());
	}
}
