package br.com.challenge.controle.rs.test;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import br.com.challenge.controle.rs.EnderecoRS;
import br.com.challenge.entidade.Endereco;
import br.com.challenge.entidade.Municipio;
import br.com.challenge.service.EnderecoService;
import br.com.challenge.type.Estado;
import static org.mockito.Mockito.*;


/**
 * Classe de teste para o rest/controle EnderecoRS.
 *  
 * @author Guilherme
 *
 */
@ContextConfiguration(locations = { "classpath:/applicationContextTest.xml", "classpath:/dispatcher-servlet-test.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class EnderecoRSTest {

	@InjectMocks
	@Autowired
	private EnderecoRS enderecoRS;

	@Mock
	private EnderecoService service;

	private MockMvc mockRest;

	/**
	 * Configuração do mock MVC.
	 */
	@Before
	public void setUp() {
		this.mockRest = MockMvcBuilders.standaloneSetup(enderecoRS).build();
		MockitoAnnotations.initMocks(this);
	}

	/**
	 * Teste da estrutura do xml/json da busca do Endereço.
	 * @throws Exception
	 */
	@Test
	public void testGetEnderecoById() throws Exception {

		/* Objeto a ser mocado */
		Endereco endereco = new Endereco();
		endereco.setBairro("bairro teste");
		endereco.setComplemento("complemento");
		endereco.setNumero(123);
		endereco.setCep("12345678");
		endereco.setRua("rua teste");
		endereco.setMunicipio(new Municipio("São Paulo", Estado.PR));
		Integer id = 1;

		/* mock do objeto na pesquisa do controle. */
		when(service.getEnderecoById(id)).thenReturn(endereco);

		mockRest.perform(get("/endereco/{id}", id)				
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$.rua").value(endereco.getRua()))
				.andExpect(jsonPath("$.cep").value(endereco.getCep()))
				.andExpect(jsonPath("$.bairro").value(endereco.getBairro()))
				.andExpect(jsonPath("$.rua").value(endereco.getRua()))
				.andExpect(jsonPath("$.numero").value(endereco.getNumero()))
				.andExpect(jsonPath("$.complemento").value(endereco.getComplemento()))
				.andExpect(jsonPath("$.municipio.$.descricao").value(endereco.getMunicipio().getDescricao()))
				.andExpect(jsonPath("$.municipio.$.uf").value(endereco.getMunicipio().getUf().name()));
	}	
}
