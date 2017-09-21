package aplicacao;

import java.util.HashMap;
import excessoes.EPalavrasNaoEncaixam;
import interfaces.IEmbaralhador;
import interfaces.IGerenciadorLetras;
import interfaces.IGerenciadorTabuleiro;
import modelo.Letra;
import modelo.Palavra;
import modelo.Tabuleiro;

public class GerenciadorTabuleiro implements IGerenciadorTabuleiro {

    protected static GerenciadorTabuleiro instancia;
    HashMap<Integer, Palavra> palavrasDoTabuleiro;
    private Tabuleiro tabuleiro;

    public GerenciadorTabuleiro() {
        tabuleiro = new Tabuleiro();
        palavrasDoTabuleiro = new HashMap<Integer, Palavra>();
    }

    public GerenciadorTabuleiro(int tamX, int tamY) {
        tabuleiro = new Tabuleiro(tamX, tamY);
    }

    public static IGerenciadorTabuleiro getGerenciadorTabuleiro() {
        if (GerenciadorTabuleiro.instancia == null) {
            GerenciadorTabuleiro.instancia = new GerenciadorTabuleiro();
        }
        return GerenciadorTabuleiro.instancia;
    }

    public void modificarTamanhoMatriz(int tamanho) {
        if (tamanho == 0) {
            tabuleiro = new Tabuleiro(10, 10);
        } else if (tamanho == 1) {
            tabuleiro = new Tabuleiro(16, 16);
        } else if (tamanho == 2) {
            tabuleiro = new Tabuleiro(20, 20);
        } else if (tamanho == 3) {
            tabuleiro = new Tabuleiro(26, 26);
        }
    }

    public void embaralhaMatriz(int strategy, HashMap<Integer, Palavra> palavrasRandom, IGerenciadorLetras gerenciadorletras) throws EPalavrasNaoEncaixam{
        EmbaralhadorFactory embaralhadorfactory = new EmbaralhadorFactory(strategy);
        IEmbaralhador embaralhador = embaralhadorfactory.getEmbaralhador();
        embaralhador.setTabuleiroDoEmbaralhador(tabuleiro);
        palavrasDoTabuleiro = embaralhador.definePosicoesDasPalavrasNoTabuleiro(palavrasRandom);
        embaralhador.embaralhaMatrizDoTabuleiro(gerenciadorletras);
        tabuleiro = embaralhador.getTabuleiroDoEmbaralhador();
    }

    public void marcarOuDesmarcarLetra(int linha, int coluna, boolean estado) {
        Letra[][] matrizLetras = tabuleiro.getMatrizletras();
        matrizLetras[linha][coluna].setSelecionado(estado);
        if (this.verificaSePosicoesXeYSaoDeUmaLetraDePalavra(linha, coluna)) {
            for (int i = 0; i < palavrasDoTabuleiro.size(); i++) {
                for (int j = 0; j < palavrasDoTabuleiro.get(i).getPalavra().size(); j++) {
                    if (palavrasDoTabuleiro.get(i).getPalavra().get(j).getPosX() == coluna) {
                        if (palavrasDoTabuleiro.get(i).getPalavra().get(j).getPosY() == linha) {
                            HashMap<Integer, Letra> palavra = palavrasDoTabuleiro.get(i).getPalavra();
                            Letra letra = palavra.get(j);
                            letra.setSelecionado(estado);
                            palavra.replace(j, letra);
                            palavrasDoTabuleiro.replace(i, new Palavra(palavra));
                        }
                    }
                }
            }
        }
        tabuleiro.setMatrizletras(matrizLetras);
    }

    public boolean verificaSeTodasAsLetrasDasPalavrasEstaoSelecionadas() {
        boolean resp = false;
        int contPalavras = 0;
        for (int i = 0; i < palavrasDoTabuleiro.size(); i++) {
            int contLetras = 0;
            for (int j = 0; j < palavrasDoTabuleiro.get(i).getPalavra().size(); j++) {
                if (palavrasDoTabuleiro.get(i).getPalavra().get(j).isSelecionado()) {
                    contLetras++;
                } else {
                    break;
                }
            }
            if (contLetras == palavrasDoTabuleiro.get(i).getPalavra().size()) {
                contPalavras++;
            } else {
                break;
            }
        }
        if (contPalavras == palavrasDoTabuleiro.size()) {
            resp = true;
        }
        return resp;
    }

    public boolean verificaSeLetrasRandomNaoEstaoSelecionadas() {
        boolean resp = true;
        Letra[][] matrizLetras = tabuleiro.getMatrizletras();
        for (int i = 0; resp && i < tabuleiro.getTamY(); i++) {
            for (int j = 0; j < tabuleiro.getTamX(); j++) {
                if (!this.verificaSePosicoesXeYSaoDeUmaLetraDePalavra(i, j)) {
                    if (matrizLetras[i][j].isSelecionado()) {
                        resp = false;
                        break;
                    }
                }
            }
        }
        return resp;
    }

    public boolean verificaSePosicoesXeYSaoDeUmaLetraDePalavra(int linha, int coluna) {
        boolean resp = false;
        for (int i = 0; !resp && i < palavrasDoTabuleiro.size(); i++) {
            for (int j = 0; j < palavrasDoTabuleiro.get(i).getPalavra().size(); j++) {
                if (palavrasDoTabuleiro.get(i).getPalavra().get(j).getPosX() == coluna) {
                    if (palavrasDoTabuleiro.get(i).getPalavra().get(j).getPosY() == linha) {
                        resp = true;
                        break;
                    }
                }
            }
        }
        return resp;
    }

    public Tabuleiro getTabuleiro() {
        return tabuleiro;
    }

    public void setTabuleiro(Tabuleiro tabuleiro) {
        this.tabuleiro = tabuleiro;
    }

    public HashMap<Integer, Palavra> getPalavrasDoTabuleiro() {
        return palavrasDoTabuleiro;
    }

}
