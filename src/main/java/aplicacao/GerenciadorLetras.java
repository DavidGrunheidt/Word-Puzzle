package aplicacao;

import java.util.Random;

import interfaces.IGerenciadorLetras;
import modelo.Letra;

public class GerenciadorLetras implements IGerenciadorLetras {

    protected char[] alfachar;
    private static GerenciadorLetras instancia;
    private Letra[] alfaletra;

    public GerenciadorLetras() {
        alfachar = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
        alfaletra = new Letra[26];
        for (int i = 0; i < alfachar.length; i++) {
            alfaletra[i] = new Letra(alfachar[i]);
        }
    }

    public static IGerenciadorLetras getGerenciadorLetras() {
        if (GerenciadorLetras.instancia == null) {
            GerenciadorLetras.instancia = new GerenciadorLetras();
        }
        return GerenciadorLetras.instancia;
    }

    public Letra retornaLetraRandom(int posX, int posY) {
        Random operador = new Random();
        Letra letra = alfaletra[operador.nextInt(26)];
        letra.setPosX(posX);
        letra.setPosY(posY);
        return letra;
    }

    public Letra[] getAlfaletra() {
        return alfaletra;
    }

    public void setAlfaletra(Letra[] alfaletra) {
        this.alfaletra = alfaletra;
    }

    public char[] getAlfachar() {
        return alfachar;
    }

}
