package aplicacao;

import excessoes.EMuitasPalavras;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Random;

import interfaces.IGerenciadorPalavras;
import java.io.FileNotFoundException;
import modelo.Palavra;

public class GerenciadorPalavras implements IGerenciadorPalavras {

    protected static String[] palavrasstring;
    private static GerenciadorPalavras instancia;

    public GerenciadorPalavras() {
    }

    public static IGerenciadorPalavras getGerenciadorPalavras() {
        if (GerenciadorPalavras.instancia == null) {
            GerenciadorPalavras.instancia = new GerenciadorPalavras();
        }
        return GerenciadorPalavras.instancia;
    }

    public HashMap<Integer, Palavra> retornaXPalavrasRandom(int tamanho, String arquivo, int quant_palavras) throws EMuitasPalavras, IOException {
        if (quant_palavras <= tamanho) {
            HashMap<Integer, Palavra> palavrasmap = this.getPalavrasMapByListaPalavrasTXT(arquivo);
            HashMap<Integer, Palavra> palavras = new HashMap<Integer, Palavra>();
            for (int i = 0; i < quant_palavras; i++) {
                Random operador = new Random();
                Palavra palavra = palavrasmap.get(operador.nextInt(palavrasmap.size()));
                if ((palavras.containsValue(palavra)) || (palavra.getPalavra().size() >= tamanho) ) {
                    i--;
                } else {
                    palavras.put(i, palavra);
                }
            }
            return palavras;
        } else {
            throw new EMuitasPalavras(quant_palavras);
        }
    }

    public HashMap<Integer, Palavra> getPalavrasMapByListaPalavrasTXT(String arquivo) throws IOException {
        HashMap<Integer, Palavra> palavrasmap = new HashMap<Integer, Palavra>();
        @SuppressWarnings("resource")
        BufferedReader br = new BufferedReader(new FileReader(arquivo));
        String line;
        Palavra palavra;
        int contlinhas = 0;
        while ((line = br.readLine()) != null) {
            palavra = new Palavra(line);
            palavrasmap.put(contlinhas, palavra);
            contlinhas++;
        }
        return palavrasmap;
    }
}
