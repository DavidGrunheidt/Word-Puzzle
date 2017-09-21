import aplicacao.Controle;
import interfaces.IControle;
import visao.Visao;

public class Main {

    public static void main(String[] args) {
        IControle visao = new Visao();
        visao.init(new Controle());
    }

}
