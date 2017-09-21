package ine5404;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import aplicacao.GerenciadorLetras;

public class TesteGerenciadorLetras {
	
	@Test
	public void testaGeradorLetrasRandomicas() {
		GerenciadorLetras gerenciadorLetras = new GerenciadorLetras();
		assertTrue(this.comparaChars(gerenciadorLetras.retornaLetraRandom(0,0).getLetra(), gerenciadorLetras.getAlfachar()));
	}
	
	public boolean comparaChars(char character, char[] arraychar) {
		boolean resp = false;
		for (int i = 0; i < arraychar.length; i++) {
			if (character == arraychar[i]) {
				resp = true;
				i = arraychar.length;
			}
		}
		return resp;
	}
	
	

}
