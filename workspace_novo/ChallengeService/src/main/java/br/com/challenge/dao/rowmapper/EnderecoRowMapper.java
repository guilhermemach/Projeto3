package br.com.challenge.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import br.com.challenge.entidade.Endereco;
import br.com.challenge.entidade.Municipio;
import br.com.challenge.type.Estado;

/**
 * Rowmapper de endereço.
 * 
 * @author Guilherme
 *
 */
public class EnderecoRowMapper implements RowMapper<Endereco>{
	
	/**
	 * Mapeamento dos dados de resultset de endereco.
	 */
	public Endereco mapRow(ResultSet rs, int rowNum) throws SQLException {	
		
		Endereco endereco = new Endereco();
		endereco.setCep(rs.getString("cep_cod"));
		endereco.setRua(rs.getString("rua"));
		endereco.setId(rs.getInt("id"));
		endereco.setBairro(rs.getString("bairro"));
		endereco.setComplemento(rs.getString("complemento"));
		endereco.setNumero(rs.getInt("numero"));
		endereco.setMunicipio( new Municipio(rs.getString("municipio_desc"), Estado.valueOf(rs.getString("estado"))));
		
		return endereco;
	}
}