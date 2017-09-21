package modelo;

public class Letra {

    private char letra;
    private int posX, posY;
    private boolean selecionado;

    public Letra() {
        this.letra = '-';
        this.selecionado = false;
    }

    public Letra(char letra, int posX, int posY) {
        this.letra = letra;
        this.posX = posX;
        this.posY = posY;
        this.selecionado = false;
    }

    public Letra(char letra) {
        this.letra = letra;
    }

    public char getLetra() {
        return letra;
    }

    public void setLetra(char letra) {
        this.letra = letra;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public boolean isSelecionado() {
        return selecionado;
    }

    public void setSelecionado(boolean selecionado) {
        this.selecionado = selecionado;
    }

}
