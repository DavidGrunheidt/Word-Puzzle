package interfaces;

import modelo.Letra;

public interface IGerenciadorLetras {

    public Letra retornaLetraRandom(int posX, int posY);

    public char[] getAlfachar();

}
