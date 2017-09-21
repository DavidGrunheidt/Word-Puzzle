package interfaces;

import excessoes.EMuitasPalavras;
import java.io.IOException;
import java.util.HashMap;

import modelo.Palavra;

public interface IGerenciadorPalavras {

    public HashMap<Integer, Palavra> retornaXPalavrasRandom(int tamanho, String arquivo, int quant_palavras) throws EMuitasPalavras, IOException;
}
