package strategies;

import java.util.HashMap;
import java.util.Random;
import excessoes.EPalavrasNaoEncaixam;
import interfaces.IEmbaralhador;
import interfaces.IGerenciadorLetras;
import modelo.Letra;
import modelo.Palavra;
import modelo.Tabuleiro;

public class EmbaralhadorDificil implements IEmbaralhador {

    protected static EmbaralhadorDificil instancia;
    private Tabuleiro tabuleiroDoEmbaralhador;

    public EmbaralhadorDificil() {
        tabuleiroDoEmbaralhador = new Tabuleiro();
    }

    public static IEmbaralhador getEmbaralhadorDificil() {
        if (EmbaralhadorDificil.instancia == null) {
            EmbaralhadorDificil.instancia = new EmbaralhadorDificil();
        }
        return EmbaralhadorDificil.instancia;
    }

    public void embaralhaMatrizDoTabuleiro(IGerenciadorLetras gerenciadorletras) {
        Letra[][] matrizletras = tabuleiroDoEmbaralhador.getMatrizletras();
        for (int i = 0; i < tabuleiroDoEmbaralhador.getTamY(); i++) {
            for (int j = 0; j < tabuleiroDoEmbaralhador.getTamX(); j++) {
                Letra letra = gerenciadorletras.retornaLetraRandom(i, j);
                if (this.verificaSeLetrasPertoNaoSaoIguais(letra, matrizletras, i, j)) {
                    matrizletras[i][j] = letra;
                } else if (matrizletras[i][j] == null) {
                    j--;
                }
            }
        }
        tabuleiroDoEmbaralhador.setMatrizletras(matrizletras);
    }

    public HashMap<Integer, Palavra> definePosicoesDasPalavrasNoTabuleiro(HashMap<Integer, Palavra> palavrasRandom) throws EPalavrasNaoEncaixam {
        Letra[][] matrizletras = tabuleiroDoEmbaralhador.getMatrizletras();
        Random operador = new Random();
        HashMap<Integer, Palavra> palavrasDoTabuleiro = (HashMap<Integer, Palavra>) palavrasRandom.clone();
        int posinicialX, posinicialY, conterro = 0;
        for (int i = 0; i < palavrasRandom.size(); i++) {
            HashMap<Integer, Letra> palavra = palavrasDoTabuleiro.get(i).getPalavra();
            int direcao = operador.nextInt(3);
            if (direcao == 0) {
                int sentido = operador.nextInt(2);
                if (sentido == 0) {
                    if (palavra.size() < tabuleiroDoEmbaralhador.getTamX()) {
                        posinicialX = operador.nextInt(tabuleiroDoEmbaralhador.getTamX() - palavra.size());
                    } else {
                        posinicialX = 0;
                    }
                    posinicialY = operador.nextInt(tabuleiroDoEmbaralhador.getTamY());
                    if (this.verificaPosicoesNaMatriz(palavra, posinicialX, posinicialY, direcao, sentido)) {
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
                    if (palavra.size() < tabuleiroDoEmbaralhador.getTamX()) {
                        posinicialX = palavra.size() + operador.nextInt(tabuleiroDoEmbaralhador.getTamX() - palavra.size());
                    } else {
                        posinicialX = palavra.size();
                    }
                    posinicialY = operador.nextInt(tabuleiroDoEmbaralhador.getTamY());
                    if (this.verificaPosicoesNaMatriz(palavra, posinicialX, posinicialY, direcao, sentido)) {
                        for (int aux = 0; aux < palavra.size(); aux++) {
                            palavra.replace(aux, new Letra(palavra.get(aux).getLetra(), posinicialX - aux, posinicialY));
                            matrizletras[posinicialY][posinicialX - aux] = palavra.get(aux);
                            conterro = 0;
                        }
                        palavrasDoTabuleiro.replace(i, new Palavra(palavra));
                    } else {
                        i--;
                        conterro++;
                    }
                }
            } else {
                if (direcao == 1) {
                    if (palavra.size() < tabuleiroDoEmbaralhador.getTamY()) {
                        posinicialY = operador.nextInt(tabuleiroDoEmbaralhador.getTamY() - palavra.size());
                    } else {
                        posinicialY = 0;
                    }
                    posinicialX = operador.nextInt(tabuleiroDoEmbaralhador.getTamX());
                    if (this.verificaPosicoesNaMatriz(palavra, posinicialX, posinicialY, direcao, 0)) {
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
                } else {
                    if ((palavra.size() < tabuleiroDoEmbaralhador.getTamY()) && (palavra.size() < tabuleiroDoEmbaralhador.getTamX())) {
                        posinicialX = operador.nextInt(tabuleiroDoEmbaralhador.getTamX() - palavra.size());
                        posinicialY = operador.nextInt(tabuleiroDoEmbaralhador.getTamY() - palavra.size());
                    } else {
                        posinicialX = 0;
                        posinicialY = 0;
                    }
                    if (this.verificaPosicoesNaMatriz(palavra, posinicialX, posinicialY, direcao, 0)) {
                        for (int aux = 0; aux < palavra.size(); aux++) {
                            palavra.replace(aux, new Letra(palavra.get(aux).getLetra(), posinicialX + aux, posinicialY + aux));
                            matrizletras[posinicialY + aux][posinicialX + aux] = palavra.get(aux);
                            conterro = 0;
                        }
                        palavrasDoTabuleiro.replace(i, new Palavra(palavra));
                    } else {
                        i--;
                        conterro++;
                    }
                }
            }
            if (conterro == 1000) {
                throw new EPalavrasNaoEncaixam(palavrasDoTabuleiro.size(), tabuleiroDoEmbaralhador.getTamX(), tabuleiroDoEmbaralhador.getTamY());
            }
        }
        this.tabuleiroDoEmbaralhador.setMatrizletras(matrizletras);
        return palavrasDoTabuleiro;
    }

    public boolean verificaPosicoesNaMatriz(HashMap<Integer, Letra> palavra, int posinicialX, int posinicialY, int direcao, int sentido) {
        Letra[][] matrizletras = tabuleiroDoEmbaralhador.getMatrizletras();
        boolean resp = false;
        int contador = 0;
        if (direcao == 0) {
            if (sentido == 0) {
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
            } else if (sentido == 1) {
                for (int i = 0; i < palavra.size(); i++) {
                    if ((matrizletras[posinicialY][posinicialX - i] == null)) {
                        contador++;
                    } else if ((matrizletras[posinicialY][posinicialX - i].getLetra() == palavra.get(i).getLetra())) {
                        contador++;
                    }
                }
                if (contador == palavra.size()) {
                    resp = true;
                }
            }
        } else if (direcao == 1) {
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
        } else {
            for (int i = 0; i < palavra.size(); i++) {
                if ((matrizletras[posinicialY + i][posinicialX + i] == null)) {
                    contador++;
                } else if ((matrizletras[posinicialY + i][posinicialX + i].getLetra() == palavra.get(i).getLetra())) {
                    contador++;
                }
            }
            if (contador == palavra.size()) {
                resp = true;
            }
        }
        return resp;
    }

    public boolean verificaSeLetrasPertoNaoSaoIguais(Letra letra, Letra[][] matrizletras, int i, int j) {
        boolean resp = false;
        if (matrizletras[i][j] == null) {
            if (i == 0) {
                if (j == 0) {
                    if (matrizletras[i][j + 1] == null) {
                        if (matrizletras[i + 1][j] == null) {
                            resp = true;
                        } else if (matrizletras[i + 1][j].getLetra() != letra.getLetra()) {
                            resp = true;
                        }
                    } else if (matrizletras[i][j + 1].getLetra() != letra.getLetra()) {
                        resp = true;
                    }
                } else if (j > 0 && j <= tabuleiroDoEmbaralhador.getTamX() - 2) {
                    if (matrizletras[i][j - 1].getLetra() != letra.getLetra()) {
                        if (matrizletras[i + 1][j] == null) {
                            if (matrizletras[i][j + 1] == null) {
                                resp = true;
                            } else if (matrizletras[i][j + 1].getLetra() != letra.getLetra()) {
                                resp = true;
                            }
                        } else if (matrizletras[i + 1][j].getLetra() != letra.getLetra()) {
                            resp = true;
                        }
                    }
                } else if (j == tabuleiroDoEmbaralhador.getTamX() - 1) {
                    if (matrizletras[i][j - 1].getLetra() != letra.getLetra()) {
                        if (matrizletras[i + 1][j] == null) {
                            resp = true;
                        } else if (matrizletras[i + 1][j].getLetra() != letra.getLetra()) {
                            resp = true;
                        }
                    }
                }
            } else if (i > 0 && i <= tabuleiroDoEmbaralhador.getTamY() - 2) {
                if (j == 0) {
                    if (matrizletras[i - 1][j].getLetra() != letra.getLetra()) {
                        if (matrizletras[i][j + 1] == null) {
                            if (matrizletras[i + 1][j] == null) {
                                resp = true;
                            } else if (matrizletras[i + 1][j].getLetra() != letra.getLetra()) {
                                resp = true;
                            }
                        } else if (matrizletras[i][j + 1].getLetra() != letra.getLetra()) {
                            resp = true;
                        }
                    }
                } else if (j > 0 && j <= tabuleiroDoEmbaralhador.getTamX() - 2) {
                    if (matrizletras[i - 1][j].getLetra() != letra.getLetra()) {
                        if (matrizletras[i][j - 1].getLetra() != letra.getLetra()) {
                            if (matrizletras[i][j + 1] == null) {
                                if (matrizletras[i + 1][j] == null) {
                                    resp = true;
                                } else if (matrizletras[i + 1][j].getLetra() != letra.getLetra()) {
                                    resp = true;
                                }
                            } else if (matrizletras[i][j + 1].getLetra() != letra.getLetra()) {
                                resp = true;
                            }
                        }
                    }
                } else if (j == tabuleiroDoEmbaralhador.getTamX() - 1) {
                    if (matrizletras[i - 1][j].getLetra() != letra.getLetra()) {
                        if (matrizletras[i][j - 1].getLetra() != letra.getLetra()) {
                            if (matrizletras[i + 1][j] == null) {
                                resp = true;
                            } else if (matrizletras[i + 1][j].getLetra() == letra.getLetra()) {
                                resp = true;
                            }
                        }
                    }
                }
            } else if (i == tabuleiroDoEmbaralhador.getTamX() - 1) {
                if (j == 0) {
                    if (matrizletras[i - 1][j].getLetra() != letra.getLetra()) {
                        if (matrizletras[i][j + 1] == null) {
                            resp = true;
                        } else if (matrizletras[i][j + 1].getLetra() != letra.getLetra()) {
                            resp = true;
                        }
                    }
                } else if (j > 0 && j <= tabuleiroDoEmbaralhador.getTamX() - 2) {
                    if (matrizletras[i - 1][j].getLetra() != letra.getLetra()) {
                        if (matrizletras[i][j - 1].getLetra() != letra.getLetra()) {
                            if (matrizletras[i][j + 1] == null) {
                                resp = true;
                            } else if (matrizletras[i][j + 1].getLetra() != letra.getLetra()) {
                                resp = true;
                            }
                        }
                    }
                } else if (j == tabuleiroDoEmbaralhador.getTamX() - 1) {
                    if (matrizletras[i - 1][j].getLetra() != letra.getLetra()) {
                        if (matrizletras[i][j - 1].getLetra() != letra.getLetra()) {
                            resp = true;
                        }
                    }
                }
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
