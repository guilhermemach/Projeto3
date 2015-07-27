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
 * Implementação da Interface de Serviço de operações de endereço.
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
	 * Metodo de serviço retorna endereço por id.
	 */
	public Endereco getEnderecoById(Integer id) {
		return dao.getEnderecoById(id);
	}

	/**
	 * Metodo de serviço cria endereço.
	 */
	@Transactional
	public Endereco createEndereco(Endereco endereco)
			throws CepInexistenteException, CepRequestException, EnderecoRequestException {

		/* validação de input */
		validaEndereco(endereco);

		/* verifica se o cep informado existe. */
		Cep cep = cepService.getCepByCod(endereco.getCep());

		if (cep == null) {
			throw new CepInexistenteException("CEP não localizado.");
		}

		/*
		 * validação de consistência de cidade estado conforme o CEP informado
		 */
		validaCepEndereco(endereco, cep);

		endereco.setMunicipio(cep.getMunicipio());
		endereco.setCep(cep.getNumeroCep());
		dao.createEndereco(endereco);

		endereco.setId(dao.getUltimoId());
		return endereco;
	}

	/**
	 * Metodo de serviço que atualiza endereço.
	 */
	@Transactional
	public Endereco updateEndereco(Endereco endereco, Integer id)
			throws CepInexistenteException, CepRequestException, EnderecoRequestException {

		/* validação de input */
		validaEndereco(endereco);

		/* verifica se o cep informado existe. */
		Cep cep = cepService.getCepByCod(endereco.getCep());

		if (cep == null) {
			throw new CepInexistenteException("CEP não localizado.");
		}

		/*
		 * validação de consistência de cidade estado conforme o CEP informado
		 */
		validaCepEndereco(endereco, cep);

		endereco.setId(id);
		dao.updateEndereco(endereco);
		return endereco;
	}

	/**
	 * Metodo de serviço que deleta endereço por ID.
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
			throw new EnderecoRequestException("Campos numero, rua, cep, municipio e estado são obrigatórios.");
		}
	}

	/**
	 * Metodo que valida os dados de CEP do endereço fornecido.
	 * @param endereco Endereco
	 * @param cep Cep
	 * @throws EnderecoRequestException
	 */
	private void validaCepEndereco(Endereco endereco, Cep cep) throws EnderecoRequestException {
		if (!cep.getMunicipio().equals(endereco.getMunicipio())) {
			throw new EnderecoRequestException("Os dados informados de municio e cidade não conferem com CEP.");
		}
	}
}
