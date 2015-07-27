package br.com.challenge.entidade;

/**
 * Classe que representa a entidade CEP negocial e para parser xml/json.
 * 
 * @author Guilherme
 *
 */
public class Cep {
	
	private String numeroCep;
	private Municipio municipio;
	private String rua;
	private String bairro;
	
	public String getNumeroCep() {
		return numeroCep;
	}
	public void setNumeroCep(String numeroCep) {
		this.numeroCep = numeroCep;
	}
	public Municipio getMunicipio() {
		return municipio;
	}
	public void setMunicipio(Municipio municipio) {
		this.municipio = municipio;
	}
	public String getRua() {
		return rua;
	}
	public void setRua(String rua) {
		this.rua = rua;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	@Override
	public String toString() {
		return "Cep [numeroCep=" + numeroCep + ", municipio=" + municipio + ", rua=" + rua + ", bairro=" + bairro + "]";
	}
	

}
