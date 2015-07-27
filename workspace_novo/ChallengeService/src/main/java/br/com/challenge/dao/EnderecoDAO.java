package br.com.challenge.dao;

import br.com.challenge.entidade.Endereco;

/**
 * Inteface das operação (CRUD) do endereço do cliente.
 * 
 * @author Guilherme
 *
 */
public interface EnderecoDAO {
	
	public void createEndereco(Endereco endereco);
	
	public Endereco getEnderecoById(Integer id);
	
	public void updateEndereco(Endereco endereco);
	
	public void deleteEnderecoById(Integer id);
	
	public Integer getUltimoId();

}
