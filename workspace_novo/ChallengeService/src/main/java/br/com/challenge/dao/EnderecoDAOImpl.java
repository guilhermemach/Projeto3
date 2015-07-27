package br.com.challenge.dao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import br.com.challenge.dao.rowmapper.EnderecoRowMapper;
import br.com.challenge.entidade.Endereco;
import br.com.challenge.log.Logger;

/**
 * Implementação da interface de persistencia do DAO de Endereço.
 * 
 * @author Guilherme
 *
 */
@Repository
public class EnderecoDAOImpl implements EnderecoDAO {
	
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	
	public void createEndereco(Endereco endereco){

		/* log */
		Logger.info("createEndereco - "+ endereco.toString(), this);

		String sql = "insert into endereco (cep_cod, numero, bairro, complemento, rua) values (:cep, :numero, :bairro, :complemento, :rua)";
 
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("cep", endereco.getCep());
		parameters.put("numero", endereco.getNumero());
		parameters.put("bairro", endereco.getBairro());
		parameters.put("rua", endereco.getRua());
		parameters.put("complemento", endereco.getComplemento());
		
		jdbcTemplate.update(sql, parameters);		
	}
	
	
	/**
	 * Método de persistência que adiciona um Endereço.
	 */
	public Endereco getEnderecoById(Integer id){
		String sql = "select e.id, c.cep_cod, e.bairro, e.rua, e.complemento,"
				+ " e.numero, m.municipio_desc, m.estado from endereco e inner"
				+ " join cep c on (e.cep_cod = c.cep_cod) inner join"
				+ " municipio m on (m.id = c.id_mun) where e.id = :id ";

		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("id", id);
		
		Endereco endereco = null;
		
		try{		
			endereco=  jdbcTemplate.queryForObject(sql, parameters, new EnderecoRowMapper());
		
		} catch (EmptyResultDataAccessException e) {
			/* log */
			Logger.error(e.getMessage(), this, e);
		}
		return endereco;
	}
	
	
	/**
	 * Método de persistência que atualiza um Endereço.
	 */
	public void updateEndereco(Endereco endereco){
		/* log */
		Logger.info("updateEndereco - "+ endereco.toString(), this);

		String sql = "update endereco set  cep_cod = :cep, numero = :numero, rua = :rua, bairro = :bairro, complemento = :complemento where id = :id";
		 
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("cep", endereco.getCep());
		parameters.put("numero", endereco.getNumero());
		parameters.put("bairro", endereco.getBairro());
		parameters.put("rua", endereco.getRua());
		parameters.put("complemento", endereco.getComplemento());
		parameters.put("id", endereco.getId());
		
		jdbcTemplate.update(sql, parameters);			
	}
	
	/**
	 * Método de persistência que deleta um Endereço por id.
	 */
	public void deleteEnderecoById(Integer id){

		String sql = "delete from endereco where id = :id";
 
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("id", id);
				
		jdbcTemplate.update(sql, parameters);		
	}
	
	/**
	 * Metodo de persisência que retorna o ultimo registro de Endereço na base de dados.
	 */
	public Integer getUltimoId(){
		String sql = "select max(id) from endereco";
		return jdbcTemplate.queryForObject(sql,  new HashMap<String, Object>(), Integer.class);		
	}	

}
