package excessoes;

public class EMuitasPalavras extends Exception {

    int quant_palavras;

    public EMuitasPalavras(int quant_palavras) {
        this.quant_palavras = quant_palavras;
    }

    public int getQuant_palavras() {
        return quant_palavras;
    }

}
