package br.com.challenge.service;

import br.com.challenge.entidade.Endereco;
import br.com.challenge.exception.CepInexistenteException;
import br.com.challenge.exception.CepRequestException;
import br.com.challenge.exception.EnderecoRequestException;

public interface EnderecoService {

	public Endereco getEnderecoById(Integer id);

	public Endereco createEndereco(Endereco endereco)
			throws CepInexistenteException, CepRequestException, EnderecoRequestException ;

	public Endereco updateEndereco(Endereco endereco, Integer id)
			throws CepInexistenteException, CepRequestException, EnderecoRequestException ;
	
	public void deleteEnderecoById(Integer id);
}
