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

import br.com.challenge.controle.rs.CepRS;
import br.com.challenge.entidade.Cep;
import br.com.challenge.entidade.Municipio;
import br.com.challenge.service.CepService;
import br.com.challenge.type.Estado;
import static org.mockito.Mockito.*;


/**
 * Classe de teste para o rest/controle CepRS.
 *  
 * @author Guilherme
 *
 */
@ContextConfiguration(locations = { "classpath:/applicationContextTest.xml", "classpath:/dispatcher-servlet-test.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class CepRSTest {

	@InjectMocks
	@Autowired
	private CepRS cepRS;

	@Mock
	private CepService service;

	private MockMvc mockRest;

	@Before
	public void setUp() {
		this.mockRest = MockMvcBuilders.standaloneSetup(cepRS).build();
		MockitoAnnotations.initMocks(this);
	}

	/**
	 * Teste da estrutura do xml/json da busca do CEP.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetCepByCodTest() throws Exception {

		/* Objeto a ser mocado */
		Cep cep = new Cep();
		cep.setBairro("bairro teste");
		cep.setNumeroCep("09909090");
		cep.setRua("rua teste");
		cep.setMunicipio(new Municipio("São Paulo", Estado.PR));

		/* mock do objeto na pesquisa do controle. */
		when(service.getCepByCod(cep.getNumeroCep())).thenReturn(cep);

		mockRest.perform(get("/cep/{cep}", cep.getNumeroCep()).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.rua").value(cep.getRua()))
				.andExpect(jsonPath("$.numeroCep").value(cep.getNumeroCep()))
				.andExpect(jsonPath("$.bairro").value(cep.getBairro()))
				.andExpect(jsonPath("$.municipio.$.descricao").value(cep.getMunicipio().getDescricao()))
				.andExpect(jsonPath("$.municipio.$.uf").value(cep.getMunicipio().getUf().name()));
	}
}
