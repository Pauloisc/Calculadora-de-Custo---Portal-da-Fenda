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

        String[] opcoesCone = Utils.lerLinhasDoArquivo("ListaCones.txt");
        JComboBox<String>[] dropdownsPersoT1 = new JComboBox[4];
        JComboBox<String>[] dropdownsPersoT2 = new JComboBox[4];
        JComboBox<String>[] dropdownsConeT1 = new JComboBox[4];
        JComboBox<String>[] dropdownsConeT2 = new JComboBox[4];

        for (int i = 0; i<4; i++){
            dropdownsPersoT1[i] = new JComboBox<>(opcoesPerso);
            dropdownsPersoT2[i] = new JComboBox<>(opcoesPerso);
            dropdownsConeT1[i] = new JComboBox<>(opcoesCone);
            dropdownsConeT2[i] = new JComboBox<>(opcoesCone);
        }

        // SPINNERS DE EIDOLON E SOBREPOSICAO
        JSpinner[] spinnersEidolonT1 = new JSpinner[4];
        JSpinner[] spinnersEidolonT2 = new JSpinner[4];
        JSpinner[] spinnersConeT1 = new JSpinner[4];
        JSpinner[] spinnersConeT2 = new JSpinner[4];

        // CRIAÇÃO DOS PAINÉIS DE SELEÇÃO
        JPanel meuPainel = new JPanel(new GridLayout(1, 2));
        JPanel time1 = new JPanel(new GridLayout(1, 4));
        JPanel time2 = new JPanel(new GridLayout(1, 4));

        time1.setBorder(BorderFactory.createTitledBorder("Time 1"));
        time2.setBorder(BorderFactory.createTitledBorder("Time 2"));

        // CRIA UM SLOT PARA CADA COLUNA DE CADA TIME, REFERENTE A PERSONAGEM -> EIDOLON -> CONE DE LUZ -> SOBREPOSICAO
        Utils.CriarSlots(dropdownsPersoT1, dropdownsPersoT2, dropdownsConeT1, dropdownsConeT2, spinnersEidolonT1, spinnersEidolonT2, spinnersConeT1, spinnersConeT2, time1, time2);
    //  String selecionado = (String) dropdowns[i].getSelectedItem();
        meuPainel.add(time1);
        meuPainel.add(time2);

        janela.add(meuPainel, BorderLayout.NORTH);
        janela.setVisible(true);
    }
}