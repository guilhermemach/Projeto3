package br.com.challenge3.stream;

/**
 * Interface Stream
 * 
 * @author Guilherme
 *
 */
public interface Stream {

	/**
	 * Informa se existe algo para processar.
	 */
	public boolean hasNext();

	/**
	 * Retorna o proximo caracter para tratamento.
	 */
	public char getNext();

}
