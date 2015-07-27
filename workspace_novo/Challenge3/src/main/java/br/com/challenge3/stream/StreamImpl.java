package br.com.challenge3.stream;

import java.util.Iterator;
import java.util.List;

import br.com.challenge3.exception.CaracterNaoLocalizadoException;

/**
 * Implementação da interface Stream.
 * 
 * @author Guilherme
 *
 */
public class StreamImpl implements Stream{
	
	
	Iterator<Character> list;
		
	/**
	 * Recebo como parametro uma lista de caracteres para tratar ele como Interator.
	 * @param caracteres
	 */
	public StreamImpl(List<Character> caracteres) throws CaracterNaoLocalizadoException{		
		
		if(caracteres == null){
			throw new CaracterNaoLocalizadoException("Parametro enviado invalido.");
		}

		list = caracteres.iterator();
	}
	
	/**
	 * Informa se existe algo para processar.
	 */
	public boolean hasNext(){
		return list.hasNext();		
	}
	
	/**
	 * Retorna o proximo caracter para tratamento.
	 */
	public char getNext(){
		return list.next();			
	}

}
