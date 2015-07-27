package br.com.challenge.endereco.dao.test;

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

@ContextConfiguration(locations = { "classpath:/applicationContextHsqlEmbedded.xml",
		"classpath:/applicationContext.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class EnderecoServiceDaoTest {

	@InjectMocks
	@Autowired
	EnderecoService service;

	@Mock
	CepService cepServiceMock;

	@Autowired
	DataSource dataSource;

	/**
	 * Configuração e carga do dbunit para execução dos testes.
	 * 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		IDataSet dataSet = new FlatXmlDataSetBuilder().build(this.getClass().getResource("/endereco-dataset.xml"));
		IDatabaseConnection dbConn = new DatabaseDataSourceConnection(dataSource);
		DatabaseOperation.CLEAN_INSERT.execute(dbConn, dataSet);
		
		/* mockito init */
		 MockitoAnnotations.initMocks(this);
	}

	/**
	 * Teste do método getEndereço por ID.
	 * 
	 * @throws CepRequestException
	 */
	@Test
	public void testGetEnderecoById_1() throws CepRequestException {
		Endereco endereco = service.getEnderecoById(1);
		Assert.assertEquals("Bairro1", endereco.getBairro());
		Assert.assertEquals("87654321", endereco.getCep());
		Assert.assertEquals("rua são paulo", endereco.getRua());
		Assert.assertEquals("São Paulo", endereco.getMunicipio().getDescricao());
		Assert.assertEquals(Estado.SP, endereco.getMunicipio().getUf());
		Assert.assertEquals("complemento1", endereco.getComplemento());
		Assert.assertEquals(new Integer(1), endereco.getNumero());
	}


	/**
	 * Teste do método getEndereço por ID (codigo de diferente cadastro).
	 * 
	 * @throws CepRequestException
	 */
	@Test
	public void testGetEnderecoById_2() throws CepRequestException {
		Endereco endereco = service.getEnderecoById(2);
		Assert.assertNull(endereco.getBairro());
		Assert.assertEquals("11111111", endereco.getCep());
		Assert.assertEquals("rua rio de janeiro", endereco.getRua());
		Assert.assertEquals("Rio de Janeiro", endereco.getMunicipio().getDescricao());
		Assert.assertEquals(Estado.RJ, endereco.getMunicipio().getUf());
		Assert.assertNull(endereco.getComplemento());
		Assert.assertEquals(new Integer(4), endereco.getNumero());
	}

	/**
	 * Teste do método getEndereço por ID (codigo de diferente cadastro).
	 * 
	 * @throws CepRequestException
	 */
	@Test
	public void testCreateEndereco() 
			throws CepRequestException, CepInexistenteException, EnderecoRequestException{
	
		Cep cep = new Cep();
		cep.setNumeroCep("88888888");
			
		Municipio m = new Municipio();
		m.setDescricao("Salvador");
		m.setUf(Estado.BA);
		
		cep.setMunicipio(m);
			
		Endereco endereco = new Endereco();
		endereco.setBairro("bairro teste");
		endereco.setComplemento("complemento teste");
		endereco.setRua("rua teste");
		endereco.setNumero(888);
		endereco.setCep("88888888");
		endereco.setMunicipio(m);
		
		/* mock o serviço que valida a busca do cep. */
		when(cepServiceMock.getCepByCod(endereco.getCep())).thenReturn(cep);
		
		Endereco enderecoResult = service.createEndereco(endereco);
		
		Integer id = enderecoResult.getId();
		
		Assert.assertNotNull(id);
		
		Endereco enderecoResultGet = service.getEnderecoById(id);
		Assert.assertEquals("88888888", enderecoResultGet.getCep());
		Assert.assertEquals("rua teste", enderecoResultGet.getRua());
		Assert.assertEquals("Salvador", enderecoResultGet.getMunicipio().getDescricao());
		Assert.assertEquals(Estado.BA, enderecoResultGet.getMunicipio().getUf());
		Assert.assertEquals("complemento teste", enderecoResultGet.getComplemento());
		Assert.assertEquals("bairro teste", enderecoResultGet.getBairro());
		Assert.assertEquals(new Integer(888), endereco.getNumero());		
	}
	
	/**
	 * Teste de atualização de endereço.
	 * 
	 * @throws CepRequestException
	 */
	@Test
	public void testUpdateEndereco() throws CepRequestException, CepInexistenteException, EnderecoRequestException{
		
		Cep cep = new Cep();
		cep.setNumeroCep("11111111");
		Municipio m = new Municipio();
		m.setDescricao("Rio de Janeiro");
		m.setUf(Estado.RJ);
		cep.setMunicipio(m);
		
		Endereco endereco = new Endereco();
		endereco.setId(3);
		endereco.setBairro("bairro teste alterado");
		endereco.setComplemento("complemento teste alterado");
		endereco.setRua("rua alterada");
		endereco.setNumero(111);
		endereco.setCep("11111111");
		endereco.setMunicipio(m);
		
		/* mock o serviço que valida a busca do cep. */
		when(cepServiceMock.getCepByCod(cep.getNumeroCep())).thenReturn(cep);
		
		/* Estou alterando o id 1 que no dbunit foi carregado com dados de São Paulo */ 
		Endereco enderecoResult = service.updateEndereco(endereco, 3);
		
		Integer id = enderecoResult.getId();
		
		Assert.assertNotNull(id);
		
		Endereco enderecoResultGet = service.getEnderecoById(id);
		Assert.assertEquals("11111111", enderecoResultGet.getCep());
		Assert.assertEquals("rua alterada", enderecoResultGet.getRua());
		Assert.assertEquals("Rio de Janeiro", enderecoResultGet.getMunicipio().getDescricao());
		Assert.assertEquals(Estado.RJ, enderecoResultGet.getMunicipio().getUf());
		Assert.assertEquals("complemento teste alterado", enderecoResultGet.getComplemento());
		Assert.assertEquals(new Integer(111), endereco.getNumero());
	}	
	
	/**
	 * Teste de remoção de endereço.
	 * 
	 * @throws CepRequestException
	 */
	@Test
	public void testDeleteEndereco(){
		Integer id = 1;
		service.deleteEnderecoById(id);		
		Assert.assertNull(service.getEnderecoById(id));
	}
}
