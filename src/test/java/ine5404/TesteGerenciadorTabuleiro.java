package ine5404;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import aplicacao.GerenciadorTabuleiro;
import strategies.EmbaralhadorDificil;
import strategies.EmbaralhadorFacil;
import strategies.EmbaralhadorMedio;
import strategies.EmbaralhadorMuitoDificil;

public class TesteGerenciadorTabuleiro {
	
	GerenciadorTabuleiro gerenciadorTabuleiro;
	
	@Before
	public void iniciaTeste(){
		gerenciadorTabuleiro = new GerenciadorTabuleiro();
	}
	
	@Test
	public void testaTamanho() {
		gerenciadorTabuleiro.modificarTamanhoMatriz(0);
		assertTrue((gerenciadorTabuleiro.getTabuleiro().getTamX() == 10) && (gerenciadorTabuleiro.getTabuleiro().getTamY() == 10));
		gerenciadorTabuleiro.modificarTamanhoMatriz(1);
		assertTrue((gerenciadorTabuleiro.getTabuleiro().getTamX() == 15) && (gerenciadorTabuleiro.getTabuleiro().getTamY() == 15));
		gerenciadorTabuleiro.modificarTamanhoMatriz(2);
		assertTrue((gerenciadorTabuleiro.getTabuleiro().getTamX() == 20) && (gerenciadorTabuleiro.getTabuleiro().getTamY() == 20));
		gerenciadorTabuleiro.modificarTamanhoMatriz(3);
		assertTrue((gerenciadorTabuleiro.getTabuleiro().getTamX() == 25) && (gerenciadorTabuleiro.getTabuleiro().getTamY() == 25));
	}
	

}
