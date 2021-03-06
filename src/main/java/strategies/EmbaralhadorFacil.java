package strategies;

import java.util.HashMap;
import java.util.Random;
import excessoes.EPalavrasNaoEncaixam;
import interfaces.IEmbaralhador;
import interfaces.IGerenciadorLetras;
import modelo.Letra;
import modelo.Palavra;
import modelo.Tabuleiro;

public class EmbaralhadorFacil implements IEmbaralhador {

    protected static EmbaralhadorFacil instancia;
    private Tabuleiro tabuleiroDoEmbaralhador;

    public EmbaralhadorFacil() {
        tabuleiroDoEmbaralhador = new Tabuleiro();
    }

    public static IEmbaralhador getEmbaralhadorFacil() {
        if (EmbaralhadorFacil.instancia == null) {
            EmbaralhadorFacil.instancia = new EmbaralhadorFacil();
        }
        return EmbaralhadorFacil.instancia;
    }

    public void embaralhaMatrizDoTabuleiro(IGerenciadorLetras gerenciadorletras) {
        Letra[][] matrizletras = tabuleiroDoEmbaralhador.getMatrizletras();
        for (int i = 0; i < tabuleiroDoEmbaralhador.getTamY(); i++) {
            for (int j = 0; j < tabuleiroDoEmbaralhador.getTamX(); j++) {
                Letra letra = gerenciadorletras.retornaLetraRandom(i, j);
                if (matrizletras[i][j] == null) {
                    matrizletras[i][j] = letra;
                }
            }
        }
        tabuleiroDoEmbaralhador.setMatrizletras(matrizletras);
    }

    public HashMap<Integer, Palavra> definePosicoesDasPalavrasNoTabuleiro(HashMap<Integer, Palavra> palavrasRandom) throws EPalavrasNaoEncaixam{
        HashMap<Integer, Palavra> palavrasDoTabuleiro = (HashMap<Integer, Palavra>) palavrasRandom.clone();
        Letra[][] matrizletras = tabuleiroDoEmbaralhador.getMatrizletras();
        Random operador = new Random();
        int posinicialX, posinicialY, conterro = 0;
        for (int i = 0; i < palavrasRandom.size(); i++) {
            HashMap<Integer, Letra> palavra = palavrasDoTabuleiro.get(i).getPalavra();
            int direcao = operador.nextInt(2);
            if (direcao == 0) {
                if (palavra.size() < tabuleiroDoEmbaralhador.getTamX()) {
                    posinicialX = operador.nextInt(tabuleiroDoEmbaralhador.getTamX() - palavra.size());
                } else {
                    posinicialX = 0;
                }
                posinicialY = operador.nextInt(tabuleiroDoEmbaralhador.getTamY());
                if (this.verificaPosicoesNaMatriz(palavra, posinicialX, posinicialY, direcao)) {
                    for (int aux = 0; aux < palavra.size(); aux++) {
                        palavra.replace(aux, new Letra(palavra.get(aux).getLetra(), posinicialX + aux, posinicialY));
                        matrizletras[posinicialY][posinicialX + aux] = palavra.get(aux);
                        conterro = 0;
                    }
                    palavrasDoTabuleiro.replace(i, new Palavra(palavra));
                } else {
                    i--;
                    conterro++;
                }
            } else {
                if (palavra.size() < tabuleiroDoEmbaralhador.getTamY()) {
                    posinicialY = operador.nextInt(tabuleiroDoEmbaralhador.getTamY() - palavra.size());
                } else {
                    posinicialY = 0;
                }
                posinicialX = operador.nextInt(tabuleiroDoEmbaralhador.getTamX());
                if (this.verificaPosicoesNaMatriz(palavra, posinicialX, posinicialY, direcao)) {
                    for (int aux = 0; aux < palavra.size(); aux++) {
                        palavra.replace(aux, new Letra(palavra.get(aux).getLetra(), posinicialX, posinicialY + aux));
                        matrizletras[posinicialY + aux][posinicialX] = palavra.get(aux);
                        conterro = 0;
                    }
                    palavrasDoTabuleiro.replace(i, new Palavra(palavra));
                } else {
                    i--;
                    conterro++;
                }
            }
            if (conterro == 1000) {
                throw new EPalavrasNaoEncaixam(palavrasDoTabuleiro.size(), tabuleiroDoEmbaralhador.getTamX(), tabuleiroDoEmbaralhador.getTamY());
            }
        }
        this.tabuleiroDoEmbaralhador.setMatrizletras(matrizletras);
        return palavrasDoTabuleiro;
    }

    public boolean verificaPosicoesNaMatriz(HashMap<Integer, Letra> palavra, int posinicialX, int posinicialY, int direcao) {
        Letra[][] matrizletras = tabuleiroDoEmbaralhador.getMatrizletras();
        boolean resp = false;
        int contador = 0;
        if (direcao == 0) {
            for (int i = 0; i < palavra.size(); i++) {
                if ((matrizletras[posinicialY][posinicialX + i] == null)) {
                    contador++;
                } else if ((matrizletras[posinicialY][posinicialX + i].getLetra() == palavra.get(i).getLetra())) {
                    contador++;
                }
            }
            if (contador == palavra.size()) {
                resp = true;
            }
        } else {
            for (int i = 0; i < palavra.size(); i++) {
                if ((matrizletras[posinicialY + i][posinicialX] == null)) {
                    contador++;
                } else if ((matrizletras[posinicialY + i][posinicialX].getLetra() == palavra.get(i).getLetra())) {
                    contador++;
                }
            }
            if (contador == palavra.size()) {
                resp = true;
            }
        }
        return resp;
    }

    public Tabuleiro getTabuleiroDoEmbaralhador() {
        return tabuleiroDoEmbaralhador;
    }

    public void setTabuleiroDoEmbaralhador(Tabuleiro tabuleiroDoEmbaralhador) {
        this.tabuleiroDoEmbaralhador = tabuleiroDoEmbaralhador;
    }

}
