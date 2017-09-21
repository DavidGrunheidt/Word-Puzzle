package interfaces;

import java.util.HashMap;
import excessoes.EPalavrasNaoEncaixam;
import modelo.Palavra;
import modelo.Tabuleiro;

public interface IGerenciadorTabuleiro {

    public void embaralhaMatriz(int strategy, HashMap<Integer, Palavra> palavrasRandom, IGerenciadorLetras gerenciadorletras) throws EPalavrasNaoEncaixam;

    public Tabuleiro getTabuleiro();

    public void setTabuleiro(Tabuleiro tabuleiro);

    public void modificarTamanhoMatriz(int tamanho);

    public void marcarOuDesmarcarLetra(int linha, int coluna, boolean estado);

    public boolean verificaSeLetrasRandomNaoEstaoSelecionadas();

    public boolean verificaSeTodasAsLetrasDasPalavrasEstaoSelecionadas();

    public HashMap<Integer, Palavra> getPalavrasDoTabuleiro();

}
