package br.com.challenge.controle.rs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.challenge.entidade.Endereco;
import br.com.challenge.exception.CepInexistenteException;
import br.com.challenge.exception.CepRequestException;
import br.com.challenge.exception.EnderecoRequestException;
import br.com.challenge.service.EnderecoService;

/**
 * Websercice disponivel para consumo das operações de Endereço (CRUD).
 * 
 * @author Guilherme
 */
@Controller
@RequestMapping("/endereco")
public class EnderecoRS {
	
	@Autowired
	private EnderecoService servico;
    	
	/**
	 * Busca de endereço por id.
	 * @param id
	 * @return Endereco
	 */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public @ResponseBody Endereco getEndereco(@PathVariable("id") Integer id) {
       return servico.getEnderecoById(id);
    }

    /**
     * Cria novo endereço.
     * 
     * @param endereco
     * @return Endereco
     * @throws CepInexistenteException
     * @throws CepRequestException
     * @throws EnderecoRequestException
     */
    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody Endereco createEndereco(@RequestBody Endereco endereco) throws CepInexistenteException, CepRequestException, EnderecoRequestException {   
    	return servico.createEndereco(endereco);   	
    }   
   
    /**
     * Metodo que deleta endereço por ID.
     * 
     * @param id
     * @return ResponseEntity<String>
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<String> deleteEndereco(@PathVariable("id") Integer id) {
    	servico.deleteEnderecoById(id);    	
    	return new ResponseEntity<String>(HttpStatus.OK);
    }
    
    /**
     * Metodo que atualiza endereço.
     * 
     * @param id
     * @return Endereco
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public @ResponseBody Endereco updateEndereco(@RequestBody Endereco endereco, 
    		@PathVariable("id") Integer id) throws CepInexistenteException, CepRequestException, EnderecoRequestException { 
    	Endereco enderecoResult  = servico.updateEndereco(endereco,id);
    	return enderecoResult;
    }
}