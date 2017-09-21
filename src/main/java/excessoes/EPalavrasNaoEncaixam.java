package excessoes;

public class EPalavrasNaoEncaixam extends Exception {

    int quant_palavras, tamX, tamY;

    public EPalavrasNaoEncaixam(int quant_palavras, int tamX, int tamY) {
        this.quant_palavras = quant_palavras;
        this.tamX = tamX;
        this.tamY = tamY;
    }

    public int getQuant_palavras() {
        return quant_palavras;
    }

    public int getTamX() {
        return tamX;
    }

    public int getTamY() {
        return tamY;
    }

}
