package br.com.challenge.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.challenge.dao.EnderecoDAO;
import br.com.challenge.entidade.Cep;
import br.com.challenge.entidade.Endereco;
import br.com.challenge.exception.CepInexistenteException;
import br.com.challenge.exception.CepRequestException;
import br.com.challenge.exception.EnderecoRequestException;

/**
 * Implementa��o da Interface de Servi�o de opera��es de endere�o.
 * 
 * @author Guilherme
 *
 */
@Service
public class EnderecoServiceImpl implements EnderecoService {

	@Autowired
	private EnderecoDAO dao;

	@Autowired
	private CepService cepService;

	/**
	 * Metodo de servi�o retorna endere�o por id.
	 */
	public Endereco getEnderecoById(Integer id) {
		return dao.getEnderecoById(id);
	}

	/**
	 * Metodo de servi�o cria endere�o.
	 */
	@Transactional
	public Endereco createEndereco(Endereco endereco)
			throws CepInexistenteException, CepRequestException, EnderecoRequestException {

		/* valida��o de input */
		validaEndereco(endereco);

		/* verifica se o cep informado existe. */
		Cep cep = cepService.getCepByCod(endereco.getCep());

		if (cep == null) {
			throw new CepInexistenteException("CEP n�o localizado.");
		}

		/*
		 * valida��o de consist�ncia de cidade estado conforme o CEP informado
		 */
		validaCepEndereco(endereco, cep);

		endereco.setMunicipio(cep.getMunicipio());
		endereco.setCep(cep.getNumeroCep());
		dao.createEndereco(endereco);

		endereco.setId(dao.getUltimoId());
		return endereco;
	}

	/**
	 * Metodo de servi�o que atualiza endere�o.
	 */
	@Transactional
	public Endereco updateEndereco(Endereco endereco, Integer id)
			throws CepInexistenteException, CepRequestException, EnderecoRequestException {

		/* valida��o de input */
		validaEndereco(endereco);

		/* verifica se o cep informado existe. */
		Cep cep = cepService.getCepByCod(endereco.getCep());

		if (cep == null) {
			throw new CepInexistenteException("CEP n�o localizado.");
		}

		/*
		 * valida��o de consist�ncia de cidade estado conforme o CEP informado
		 */
		validaCepEndereco(endereco, cep);

		endereco.setId(id);
		dao.updateEndereco(endereco);
		return endereco;
	}

	/**
	 * Metodo de servi�o que deleta endere�o por ID.
	 */
	@Transactional
	public void deleteEnderecoById(Integer id) {
		dao.deleteEnderecoById(id);
	}

	/**
	 * Valida os inputs do cadastro de endereco.
	 * 
	 * @param endereco
	 */
	private void validaEndereco(Endereco endereco) throws EnderecoRequestException {
		if (endereco == null || endereco.getCep() == null || endereco.getNumero() == null || endereco.getRua() == null ||
				endereco.getMunicipio() == null || endereco.getMunicipio().getDescricao() == null ||
				endereco.getMunicipio().getUf() == null) {
			throw new EnderecoRequestException("Campos numero, rua, cep, municipio e estado s�o obrigat�rios.");
		}
	}

	/**
	 * Metodo que valida os dados de CEP do endere�o fornecido.
	 * @param endereco Endereco
	 * @param cep Cep
	 * @throws EnderecoRequestException
	 */
	private void validaCepEndereco(Endereco endereco, Cep cep) throws EnderecoRequestException {
		if (!cep.getMunicipio().equals(endereco.getMunicipio())) {
			throw new EnderecoRequestException("Os dados informados de municio e cidade n�o conferem com CEP.");
		}
	}
}
