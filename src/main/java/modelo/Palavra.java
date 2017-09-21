package modelo;

import java.util.HashMap;

public class Palavra {

    private HashMap<Integer, Letra> palavra;

    public Palavra() {
        palavra = new HashMap<Integer, Letra>();
    }

    public Palavra(String palavra) {
        this.palavra = new HashMap<Integer, Letra>();
        for (int i = 0; i < palavra.length(); i++) {
            Letra letra = new Letra(palavra.charAt(i));
            this.palavra.put(i, letra);
        }
    }

    public Palavra(HashMap<Integer, Letra> palavra) {
        this.palavra = palavra;
    }

    public HashMap<Integer, Letra> getPalavra() {
        return palavra;
    }

    public void setPalavra(HashMap<Integer, Letra> palavra) {
        this.palavra = palavra;
    }

    public String toString() {
        char[] palavra1 = new char[palavra.size()];
        for (int i = 0; i < palavra.size(); i++) {
            palavra1[i] = palavra.get(i).getLetra();
        }
        String resp = new String(palavra1);
        return resp;
    }

}
