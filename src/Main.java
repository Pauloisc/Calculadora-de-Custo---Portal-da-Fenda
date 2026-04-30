import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        JFrame janela = new JFrame("Portal da Fenda");
        janela.setSize(1000, 600);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //LISTAGEM DOS PERSONAGENS
        java.util.List<Personagem> listaPersonagens = Utils.lerPersonagens("ListaPersonagens.txt");
        String[] opcoesPerso = new String[listaPersonagens.size()];
        for (int i = 0; i < listaPersonagens.size(); i++) {
            opcoesPerso[i] = listaPersonagens.get(i).getNome();
        }

        // VETORES QUE GUARDARÃO INFORMAÇÕES
        String[] opcoesCone = Utils.lerLinhasDoArquivo("ListaCones.txt");
        JComboBox<String>[] dropdownsPersoT1 = new JComboBox[4];
        JComboBox<String>[] dropdownsPersoT2 = new JComboBox[4];
        JComboBox<String>[] dropdownsConeT1 = new JComboBox[4];
        JComboBox<String>[] dropdownsConeT2 = new JComboBox[4];
        JLabel[] labelsImagemT1 = new JLabel[4];
        JLabel[] labelsImagemT2 = new JLabel[4];

        // PREENCHER CAIXAS DE SELEÇÃO DO PERSONAGEMM E CONE
        FuncoesPainel.PreencherDropdowns(dropdownsPersoT1, dropdownsPersoT2, dropdownsConeT1, dropdownsConeT2, opcoesPerso, opcoesCone);

        // SPINNERS DE EIDOLON E SOBREPOSICAO
        JSpinner[] spinnersEidolonT1 = new JSpinner[4];
        JSpinner[] spinnersEidolonT2 = new JSpinner[4];
        JSpinner[] spinnersConeT1 = new JSpinner[4];
        JSpinner[] spinnersConeT2 = new JSpinner[4];

        // JLABELS PARA NOMEAR CUSTO
        JLabel labelCustoT1 = new JLabel("Custo: 0.0");
        JLabel labelCustoT2 = new JLabel("Custo: 0.0");

        // ATUALIZACAO EM TEMPO REAL DO CUSTO


        // CRIAÇÃO DOS PAINÉIS DE SELEÇÃO
        JPanel meuPainel = new JPanel(new GridLayout(1, 2));
        JPanel time1 = new JPanel(new GridLayout(1, 4));
        JPanel time2 = new JPanel(new GridLayout(1, 4));
        JPanel custoT1 = new JPanel();
        JPanel custoT2 = new JPanel();

        // PAINEIS INTERMEDIARIOS
        JPanel ladoT1 = new JPanel(new BorderLayout());
        JPanel ladoT2 = new JPanel(new BorderLayout());

        // CRIAÇÃO DA BORDA E TEXTOS
        FuncoesPainel.BordasTexto (time1, time2, custoT1, custoT2, labelCustoT1, labelCustoT2);

        // CRIA UM SLOT PARA CADA COLUNA DE CADA TIME, REFERENTE A PERSONAGEM -> EIDOLON -> CONE DE LUZ -> SOBREPOSICAO
        FuncoesPainel.CriarSlots(dropdownsPersoT1, dropdownsPersoT2, dropdownsConeT1, dropdownsConeT2, spinnersEidolonT1,
                spinnersEidolonT2, spinnersConeT1, spinnersConeT2, time1, time2, labelsImagemT1, labelsImagemT2);

        // ADICIONA AS INFORMAÇÕES DO TIME NO PAINEL PRINCIPAL
        FuncoesPainel.InfoTimesPainel (ladoT1,ladoT2, time1, time2, meuPainel, custoT1, custoT2);

        // ADICIONA O CALCULO DO CUSTO EM TEMPO REAL
        FuncoesPainel.custoTotal(dropdownsPersoT1, spinnersEidolonT1, listaPersonagens, dropdownsConeT1, spinnersConeT1, labelCustoT1);
        FuncoesPainel.custoTotal(dropdownsPersoT2, spinnersEidolonT2, listaPersonagens, dropdownsConeT2, spinnersConeT2, labelCustoT2);

        // CONFIGS DA JANELA
        janela.add(meuPainel, BorderLayout.NORTH);
        janela.setVisible(true);
    }
}