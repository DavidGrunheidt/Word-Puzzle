package visao;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

import aplicacao.Controle;
import excessoes.EMuitasPalavras;
import excessoes.EPalavrasNaoEncaixam;
import interfaces.IControle;
import javax.swing.BoxLayout;

public class Visao implements IControle {

    private JFrame janela = new JFrame("Caça palavras By: David Grunheidt");
    private JPanel paineldotabuleiro = new JPanel(), paineldosbotoes = new JPanel();
    private GridLayout layoutdotabuleiro, layoutdosbotoesinicial = new GridLayout(7, 1);
    FlowLayout layoutdosbotoesjogando = new FlowLayout(FlowLayout.CENTER, 10, 10);
    private JLabel dificuldadetext = new JLabel("Defina a dificuldade:"), tamanhotext = new JLabel("Defina o tamanho:"), quant_palavrastext = new JLabel("Defina a quant. de palavras:");
    private JTextField quant_palavras = new JTextField();
    private JComboBox<String> selecaoDoTamanho = new JComboBox<String>(), selecaoDaDificuldade = new JComboBox<String>();
    private JButton start = new JButton("Iniciar"), stop = new JButton("Voltar Ao Menu Inicial"), testWin = new JButton("Achei Todas As Palavras");
    private JToggleButton[][] tabuleiroDeLetras;

    protected Controle controle;

    public void init(Controle controle) {
        this.controle = controle;
        this.mostraTelaInicial();

        start.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                mudaParaPainelDoTabuleiro("Lista-de-Palavras.txt", getTamanho(), getStrategy(), quant_palavras.getText());
            }

        });

        stop.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                controle.setTabuleiro();
                mostraTelaInicial();
            }
        });

        testWin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (controle.verificaSeSóTodasAsLetrasDeTodasAsPalavrasEstaoSelecionadas()) {
                    JOptionPane.showMessageDialog(null, "Parabéns! Você achou a(s) " + controle.getGerenciadorTabuleiro().getPalavrasDoTabuleiro().size() + " palavras escondidas no tabuleiro", "FIM DO JOGO!", JOptionPane.INFORMATION_MESSAGE);
                    mostraTelaInicial();
                } else {
                    JOptionPane.showMessageDialog(null, "Você não achou todas as palavras!", "Erro!", JOptionPane.ERROR_MESSAGE);
                }

            }
        });

    }

    public void mostraTelaInicial() {
        janela.remove(paineldotabuleiro);
        janela.remove(paineldosbotoes);
        
        paineldosbotoes.removeAll();
        paineldotabuleiro.removeAll();
        tabuleiroDeLetras = new JToggleButton[0][0];

        this.insereDificuldadesNaCaixaDeSelecao();
        this.insereTamanhosNaCaixaDeSelecao();

        paineldosbotoes.add(tamanhotext);
        paineldosbotoes.add(selecaoDoTamanho);
        paineldosbotoes.add(dificuldadetext);
        paineldosbotoes.add(selecaoDaDificuldade);
        paineldosbotoes.add(quant_palavrastext);
        paineldosbotoes.add(quant_palavras);
        paineldosbotoes.add(start);
        paineldosbotoes.setLayout(layoutdosbotoesinicial);
        
        janela.add(paineldosbotoes);
        janela.setLayout(new GridLayout(1, 1));
        janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        janela.pack();
        janela.setVisible(true);
    }

    public void mudaParaPainelDoTabuleiro(String arquivo, int tamanho, int strategy, String quant_palavras) {
        try {
            controle.EmbaralahaMatriz(tamanho, arquivo, Integer.parseInt(quant_palavras), strategy);

            this.criaInterfaceGraficaDoTabuleiro(controle.getTabuleiro().getTamX(), controle.getTabuleiro().getTamY());

            janela.remove(paineldosbotoes);

            paineldosbotoes.removeAll();
            paineldosbotoes.add(testWin);
            paineldosbotoes.add(stop);
            paineldosbotoes.setLayout(layoutdosbotoesjogando);

            janela.add(paineldotabuleiro);
            janela.add(paineldosbotoes);
            janela.setLayout(new BoxLayout(janela.getContentPane(), BoxLayout.Y_AXIS));
            janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            janela.pack();
            janela.setVisible(true);
            for (int i = 0; i < controle.getGerenciadorTabuleiro().getPalavrasDoTabuleiro().size(); i++) {
                System.out.print("\n" + controle.getGerenciadorTabuleiro().getPalavrasDoTabuleiro().get(i));
            }
        } catch (EMuitasPalavras e) {
            JOptionPane.showMessageDialog(null, "Não é possivel iniciar um jogo com esta quantidade de palavras(" + e.getQuant_palavras() + ") para este tamanho , Tente Iniciar Novamente!", "Não foi possivel inicializar o jogo:", JOptionPane.ERROR_MESSAGE);
            this.mostraTelaInicial();
        } catch (EPalavrasNaoEncaixam e) {
            JOptionPane.showMessageDialog(null, "Durante mil ciclos o aplicativo tentou encaixar as" + e.getQuant_palavras() + " palavras selecionadas randomicamente no tabuleiro e não conseguiu, Tente Iniciar Novamente!", "Não foi possivel inicializar o jogo:", JOptionPane.ERROR_MESSAGE);
            this.mostraTelaInicial();
        } catch (IOException e) {
             JOptionPane.showMessageDialog(null, "Lista de palavras não encontrada!", "Não foi possivel inicializar o jogo:", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Você não digitou um numero valido de palavras", "Não foi possivel inicializar o jogo:", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void criaInterfaceGraficaDoTabuleiro(int tamX, int tamY) {
        tabuleiroDeLetras = new JToggleButton[tamY][tamX];
        layoutdotabuleiro = new GridLayout(tamY, tamX);
        for (int i = 0; i < tamY; i++) {
            for (int j = 0; j < tamX; j++) {
                this.criaUmUnicoBotao(i, j);
            }
        }
        paineldotabuleiro.setLayout(layoutdotabuleiro);
    }

    public void criaUmUnicoBotao(int linha, int coluna) {
        tabuleiroDeLetras[linha][coluna] = new JToggleButton("" + controle.getTabuleiro().getMatrizletras()[linha][coluna].getLetra());
        tabuleiroDeLetras[linha][coluna].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                controle.marcarOuDesmarcarLetra(linha, coluna, tabuleiroDeLetras[linha][coluna].isSelected());
            }
        });
        paineldotabuleiro.add(tabuleiroDeLetras[linha][coluna]);
    }

    public void insereTamanhosNaCaixaDeSelecao() {
        selecaoDoTamanho.removeAllItems();
        selecaoDoTamanho.addItem("Pequeno");
        selecaoDoTamanho.addItem("Medio");
        selecaoDoTamanho.addItem("Grande");
        selecaoDoTamanho.addItem("Muito Grande");
    }

    public void insereDificuldadesNaCaixaDeSelecao() {
        selecaoDaDificuldade.removeAllItems();
        selecaoDaDificuldade.addItem("Facil");
        selecaoDaDificuldade.addItem("Medio");
        selecaoDaDificuldade.addItem("Dificil");
        selecaoDaDificuldade.addItem("Muito Dificil");
    }

    public int getTamanho() throws NullPointerException {
        int tamanho = 0;
        if (selecaoDoTamanho.getSelectedItem() != null) {
            switch (selecaoDoTamanho.getSelectedItem().toString()) {
                case ("Pequeno"):
                    tamanho = 0;
                    break;
                case ("Medio"):
                    tamanho = 1;
                    break;
                case ("Grande"):
                    tamanho = 2;
                    break;
                case ("Muito Grande"):
                    tamanho = 3;
                    break;
            }
            return tamanho;
        } else {
            throw new NullPointerException();
        }
    }

    public int getStrategy() throws NullPointerException {
        int strategy = 0;
        if (selecaoDaDificuldade.getSelectedItem() != null) {
            switch (selecaoDaDificuldade.getSelectedItem().toString()) {
                case ("Facil"):
                    strategy = 0;
                    break;
                case ("Medio"):
                    strategy = 1;
                    break;
                case ("Dificil"):
                    strategy = 2;
                    break;
                case ("Muito Dificil"):
                    strategy = 3;
                    break;
            }
            return strategy;
        } else {
            throw new NullPointerException();
        }
    }

}
