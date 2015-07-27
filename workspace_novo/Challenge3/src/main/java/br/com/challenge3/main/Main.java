package br.com.challenge3.main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import br.com.challenge3.exception.CaracterNaoLocalizadoException;
import br.com.challenge3.stream.Stream;

public class Main {

	/**
	 * Metodo que retorna o proximo caracter que n�o se repete na lista.
	 * @param stream
	 * @return char
	 * @throws CaracterNaoLocalizadoException
	 */
	public static char firstChar(Stream stream) throws CaracterNaoLocalizadoException {

		if(stream == null){
			throw new CaracterNaoLocalizadoException("Parametro enviado invalido.");
		}
		
		/* carrega o buffer inicial. */
		int tamanhoBuffer = 127;
		char[] arrayChar = new char[tamanhoBuffer];
		int i = 0;

		/* faz o redimensionamento da lista considerando o tamanho m�ximo. */
		while (stream.hasNext()) {

			if (i >= tamanhoBuffer) {
				tamanhoBuffer = tamanhoBuffer + tamanhoBuffer + 1;
				char[] temp = new char[tamanhoBuffer];
				System.arraycopy(arrayChar, 0, temp, 0, i);
				arrayChar = temp;
				temp = null;
			}
			arrayChar[i++] = stream.getNext();
		}

		/* faz uma copia da lista sem intera��o */
		char[] resultArray = new char[i];
		System.arraycopy(arrayChar, 0, resultArray, 0, i);
		Arrays.sort(resultArray);

		List<Character> naoRepetidoList = new ArrayList<Character>();

		/*
		 * regra para comparar caracter na lista ordenada e gravar somente os
		 * que n�o se repetem em outra lista separada.
		 */
		
		if(resultArray.length == 1){
			return resultArray[0];
		}
		for (int a = 0; a < resultArray.length; a++) {

			if (a == 0) {				
				if (resultArray[a] == resultArray[a + 1]) {
					a++;
				} else {
					naoRepetidoList.add(resultArray[a]);
				}

			} else if (a > 0 && a < resultArray.length - 1) {
				if (resultArray[a] == resultArray[a + 1] ) {
					a++;
				} else if (resultArray[a] != resultArray[a - 1]) {
					naoRepetidoList.add(resultArray[a]);
				}
			} else {
				if (resultArray[a] != resultArray[a - 1]) {
					naoRepetidoList.add(resultArray[a]);
				}
			}
		}

		int result = -1;

		/*
		 * faz a verifica��o somente de cada caracter avulso repetido na lista
		 * original para garantir a ordem.
		 */
		for (char character : arrayChar) {
			if ((result = Collections.binarySearch(naoRepetidoList, character)) > -1) {
				return naoRepetidoList.get(result);
			}
		}

		/*
		 * Caso n�o localize nenhum caracter que n�o se repeta ent�o lan�a uma
		 * mensagem.
		 */
		throw new CaracterNaoLocalizadoException("N�o foi localizado caracter repetido no buffer.");
	}

}
