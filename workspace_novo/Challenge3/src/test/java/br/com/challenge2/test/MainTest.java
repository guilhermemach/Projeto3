package br.com.challenge2.test;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

import br.com.challenge3.exception.CaracterNaoLocalizadoException;
import br.com.challenge3.main.Main;
import br.com.challenge3.stream.Stream;
import br.com.challenge3.stream.StreamImpl;

/**
 * Classe de teste do logica Challenge3.
 * 
 * @author Guilherme
 *
 */
public class MainTest {
	
	/**
	 * Teste do envio do buffer vazio.
	 * @throws CaracterNaoLocalizadoException
	 */
	@Test(expected= CaracterNaoLocalizadoException.class)
	public void testBufferVazio() throws CaracterNaoLocalizadoException{		
		Stream stream = new StreamImpl(new ArrayList<Character>());
		Main.firstChar(stream);
	}
	
	/**
	 * Teste do envio do buffer nulo.
	 * @throws CaracterNaoLocalizadoException
	 */
	@Test(expected= CaracterNaoLocalizadoException.class)
	public void testBufferNullo() throws CaracterNaoLocalizadoException{		
		Stream stream = new StreamImpl(null);
		Main.firstChar(stream);
	}
	
	/**
	 * Teste do envio do argumento nulo.
	 * @throws CaracterNaoLocalizadoException
	 */
	@Test(expected= CaracterNaoLocalizadoException.class)
	public void testBufferObjetoNulo() throws CaracterNaoLocalizadoException{		
		Main.firstChar(null);
	}
	
	/**
	 * Teste do envio do unico caracter.
	 */
	@Test()
	public void testBufferSimplesCaracter() throws CaracterNaoLocalizadoException{
		Stream stream = new StreamImpl(new ArrayList<Character>(Arrays.asList('a')));
		Assert.assertEquals('a', Main.firstChar(stream));
	}
	
	/**
	 * Teste do envio do buffer onde o primeiro caracter não se repete.
	 */
	@Test()
	public void testBufferPrimeiroNaoRepete() throws CaracterNaoLocalizadoException{
		Stream stream = new StreamImpl(new ArrayList<Character>(Arrays.asList('A','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X')));
		Assert.assertEquals('A', Main.firstChar(stream));
	}
	
	/**
	 * Teste do envio do buffer onde o ultimo caracter não se repete.
	 */
	@Test()
	public void testBufferUltimoNaoRepete() throws CaracterNaoLocalizadoException{
		Stream stream = new StreamImpl(new ArrayList<Character>(Arrays.asList('X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','Z')));
		Assert.assertEquals('Z', Main.firstChar(stream));
	}
	
	/**
	 * Teste do envio do buffer onde o caracter no meio do texto nao se repete.
	 */
	@Test()
	public void testBufferNoMeioNaoRepete() throws CaracterNaoLocalizadoException{
		Stream stream = new StreamImpl(new ArrayList<Character>(Arrays.asList('A','A','A','A','A','B','A','A','A','A','A')));
		Assert.assertEquals('B', Main.firstChar(stream));
	}
	
	/**
	 * Teste do envio do buffer lower/upper case.
	 */
	@Test()
	public void testBufferUpperLowerCaseNaoRepete() throws CaracterNaoLocalizadoException{
		Stream stream = new StreamImpl(new ArrayList<Character>(Arrays.asList('J','a','a','J','A','B', 'b','x','X','5','4','6')));
		Assert.assertEquals('A', Main.firstChar(stream));
	}	
	
	/**
	 * Teste do envio do buffer somente numeros e numero 1 é o primeiro à não repetir.
	 */
	@Test()
	public void testBufferNumerosNaoRepete() throws CaracterNaoLocalizadoException{
		Stream stream = new StreamImpl(new ArrayList<Character>(Arrays.asList('0','0','1','7','6','4', '3','4','5','5','4','6')));
		Assert.assertEquals('1', Main.firstChar(stream));
	}
}
