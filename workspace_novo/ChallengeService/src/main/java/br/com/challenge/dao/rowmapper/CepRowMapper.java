package br.com.challenge.dao.rowmapper;

/**
 * Rowmaper de Cep.
 */
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import br.com.challenge.entidade.Cep;
import br.com.challenge.entidade.Municipio;
import br.com.challenge.type.Estado;

public class CepRowMapper implements RowMapper<Cep>{
	
	/**
	 * Mapeamento dos dados de resultset de CEP.
	 */
	public Cep mapRow(ResultSet rs, int rowNum) throws SQLException {
		Cep cep = new Cep();
		cep.setNumeroCep(rs.getString("cep_cod"));
		cep.setRua(rs.getString("rua"));
		cep.setBairro(rs.getString("bairro"));
		Municipio mun = new Municipio(rs.getString("municipio_desc"),
				Estado.valueOf(rs.getString("estado")));
		cep.setMunicipio(mun);
		return cep;
	}
}