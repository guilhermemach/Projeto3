package br.com.challenge.dao;

import java.util.HashMap;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import br.com.challenge.dao.rowmapper.CepRowMapper;
import br.com.challenge.entidade.Cep;
import br.com.challenge.log.Logger;

/**
 * Implementação do DAO da busca de CEP.
 * 
 * @author Guilherme
 *
 */
@Repository
public class CepDAOImpl implements CepDAO {

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	/**
	 * Metodo da camada de persistencia que retorna o CEP através da busca pelo codigo.
	 */
	public Cep getCepByCod(String cod) {

		Cep cep = null;

		try {
			String sql = "select * from cep c inner join municipio m on (m.id = c.id_mun) where c.cep_cod = :cepcod ";

			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("cepcod", cod);

			cep = jdbcTemplate.queryForObject(sql, parameters, new CepRowMapper());
		} catch (EmptyResultDataAccessException e) {
			/* log*/
			Logger.error(e.getMessage(), this, e);
		}
		return cep;
	}

}
