package br.com.challenge.entidade;

/**
 * Classe que representa a entidade Endereco negocial e para parser xml/json.
 * 
 * @author Guilherme
 *
 */
public class Endereco {

	private Integer id;
	private String cep;
	private Integer numero;
	private String bairro;
	private String complemento;
	private String rua;
	private Municipio municipio;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public Integer getNumero() {
		return numero;
	}
	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	@Override
	public String toString() {
		return "Endereco [id=" + id + ", cep=" + cep + ", numero=" + numero + ", bairro=" + bairro + ", complemento="
				+ complemento + "]";
	}
	public String getRua() {
		return rua;
	}
	public void setRua(String rua) {
		this.rua = rua;
	}
	public Municipio getMunicipio() {
		return municipio;
	}
	public void setMunicipio(Municipio municipio) {
		this.municipio = municipio;
	}	
	
	
	
}
