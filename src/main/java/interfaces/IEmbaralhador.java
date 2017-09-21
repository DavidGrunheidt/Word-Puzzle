package interfaces;

import java.util.HashMap;
import excessoes.EPalavrasNaoEncaixam;
import modelo.Palavra;
import modelo.Tabuleiro;

public interface IEmbaralhador {

    public void embaralhaMatrizDoTabuleiro(IGerenciadorLetras gerenciadorletras);

    public HashMap<Integer, Palavra> definePosicoesDasPalavrasNoTabuleiro(HashMap<Integer, Palavra> palavrasRandom) throws EPalavrasNaoEncaixam;

    public Tabuleiro getTabuleiroDoEmbaralhador();

    public void setTabuleiroDoEmbaralhador(Tabuleiro tabuleiroDoEmbaralhador);

}
