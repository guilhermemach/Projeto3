package br.com.challenge.log;

/**
 * Classe que dispoe de utilitario de log do sistema (erro e info).
 * 
 * @author Guilherme
 *
 */
public class Logger {

	public static void error(String msg, Object classe, Throwable t) {
		org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(classe.getClass().getName());
		if (t != null) {
			log.error(msg, t);
		} else {
			log.error(msg);
		}
	}

	public static void info(String msg, Object classe) {
		org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(classe.getClass().getName());
		log.info(msg);
	}
}