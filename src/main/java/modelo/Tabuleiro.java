package modelo;

public class Tabuleiro {

    private Letra[][] matrizletras;
    private int tamX = 10, tamY = 10;

    public Tabuleiro() {
        matrizletras = new Letra[this.tamX][this.tamY];
    }

    public Tabuleiro(int tamX, int tamY) {
        this.tamX = tamX;
        this.tamY = tamY;
        matrizletras = new Letra[this.tamX][this.tamY];
    }

    public Letra[][] getMatrizletras() {
        return matrizletras;
    }

    public void setMatrizletras(Letra[][] matrizletras) {
        this.matrizletras = matrizletras;
    }

    public int getTamX() {
        return tamX;
    }

    public int getTamY() {
        return tamY;
    }

}
