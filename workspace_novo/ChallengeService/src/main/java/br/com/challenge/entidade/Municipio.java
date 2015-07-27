package br.com.challenge.entidade;

import br.com.challenge.type.Estado;

/**
 * Classe que representa a entidade Municipio negocial e para parser xml/json.
 * 
 * @author Guilherme
 *
 */
public class Municipio {

	private String descricao;
	private Estado uf;
	
	public Municipio(){		
	}
	
	public Municipio(String descricao, Estado uf){
		this.uf = uf;
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Estado getUf() {
		return uf;
	}
	public void setUf(Estado uf) {
		this.uf = uf;
	}

	@Override
	public String toString() {
		return "Municipio [descricao=" + descricao + ", uf=" + uf + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descricao == null) ? 0 : descricao.toLowerCase().hashCode());
		result = prime * result + ((uf == null) ? 0 : uf.name().toLowerCase().hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Municipio other = (Municipio) obj;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.toLowerCase().equals(other.descricao.toLowerCase()))
			return false;
		if (!uf.name().toLowerCase().equals(other.uf.name().toLowerCase()))
			return false;
		return true;
	}
	
	
}
