package aplicacao;

import java.io.IOException;

import excessoes.EMuitasPalavras;
import excessoes.EPalavrasNaoEncaixam;
import interfaces.IGerenciadorLetras;
import interfaces.IGerenciadorPalavras;
import interfaces.IGerenciadorTabuleiro;
import modelo.Tabuleiro;

public class Controle {

    private IGerenciadorPalavras gerenciadorPalavras;
    private IGerenciadorLetras gerenciadorLetras;
    private IGerenciadorTabuleiro gerenciadorTabuleiro;

    public Controle() {
        this.gerenciadorLetras = GerenciadorLetras.getGerenciadorLetras();
        this.gerenciadorPalavras = GerenciadorPalavras.getGerenciadorPalavras();
        this.gerenciadorTabuleiro = GerenciadorTabuleiro.getGerenciadorTabuleiro();
    }

    public void EmbaralahaMatriz(int tamanho, String arquivo, int quant_palavras, int strategy) throws EPalavrasNaoEncaixam, EMuitasPalavras, IOException{
        gerenciadorTabuleiro.modificarTamanhoMatriz(tamanho);
        gerenciadorTabuleiro.embaralhaMatriz(strategy, gerenciadorPalavras.retornaXPalavrasRandom(gerenciadorTabuleiro.getTabuleiro().getTamX(), arquivo, quant_palavras), gerenciadorLetras);
    }

    public void marcarOuDesmarcarLetra(int linha, int coluna, boolean estado) {
        gerenciadorTabuleiro.marcarOuDesmarcarLetra(linha, coluna, estado);
    }

    public boolean verificaSeSÛTodasAsLetrasDeTodasAsPalavrasEstaoSelecionadas() {
        boolean resp = false;
        if (gerenciadorTabuleiro.verificaSeLetrasRandomNaoEstaoSelecionadas()) {
            if (gerenciadorTabuleiro.verificaSeTodasAsLetrasDasPalavrasEstaoSelecionadas()) {
                resp = true;
            }
        }
        return resp;
    }

    public Tabuleiro getTabuleiro() {
        return gerenciadorTabuleiro.getTabuleiro();
    }

    public IGerenciadorTabuleiro getGerenciadorTabuleiro() {
        return gerenciadorTabuleiro;
    }

    public void setTabuleiro() {
        gerenciadorTabuleiro.setTabuleiro(new Tabuleiro(0, 0));
    }

}
