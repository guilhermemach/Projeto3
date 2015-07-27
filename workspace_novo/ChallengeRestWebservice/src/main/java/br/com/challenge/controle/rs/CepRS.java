package br.com.challenge.controle.rs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.challenge.entidade.Cep;
import br.com.challenge.exception.CepRequestException;
import br.com.challenge.service.CepService;



/**
 * Websercice disponivel para consumo da operação de busca de CEP.
 * 
 * @author Guilherme
 */
@Controller
@RequestMapping("/cep")
public class CepRS {

	@Autowired
	private CepService service;
		
	/**
	 * Busca de CEP pelo codigo.
	 * @param cep
	 * @return
	 * @throws CepRequestException
	 */
    @RequestMapping(value = "{cep}", method = RequestMethod.GET)
    public @ResponseBody Cep getCep(@PathVariable String cep) throws CepRequestException {    	
        return service.getCepByCod(cep);
    }
}
