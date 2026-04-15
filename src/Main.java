import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        JFrame janela = new JFrame("Portal da Fenda");
        janela.setSize(1000, 600);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //LISTAGEM DOS PERSONAGENS
        String[] opcoesPerso = Utils.lerLinhasDoArquivo("ListaPersonagens.txt");
        JComboBox<String>[] dropdownsT1 = new JComboBox[opcoesPerso.length];
        JComboBox<String>[] dropdownsT2 = new JComboBox[opcoesPerso.length];
        for (int i = 0; i<4; i++){
            dropdownsT1[i] = new JComboBox<>(opcoesPerso);
            dropdownsT2[i] = new JComboBox<>(opcoesPerso);
        }

        // LISTAGEM DAS EIDOLONS
        JSpinner[] spinnersEidolonT1 = new JSpinner[4];
        JSpinner[] spinnersEidolonT2 = new JSpinner[4];

        // CRIAÇÃO DOS PAINÉIS DE SELEÇÃO
        JPanel meuPainel = new JPanel(new GridLayout(1, 2));
        JPanel time1 = new JPanel(new GridLayout(1, 4));
        JPanel time2 = new JPanel(new GridLayout(1, 4));

        time1.setBorder(BorderFactory.createTitledBorder("Time 1"));
        time2.setBorder(BorderFactory.createTitledBorder("Time 2"));

        // CRIA UM SLOT PARA CADA COLUNA DE CADA TIME, REFERENTE A PERSONAGEM -> EIDOLON -> CONE DE LUZ -> SOBREPOSICAO
        for (int i = 0; i<4; i++){
            JPanel slot1 = new JPanel(new GridLayout(2, 1));
            JPanel slot2 = new JPanel(new GridLayout(2, 1));
            slot1.add(dropdownsT1[i]);
            slot2.add(dropdownsT2[i]);
            slot1.add(spinnersEidolonT1[i] = new JSpinner(new SpinnerNumberModel(0, 0, 6, 1)));
            slot2.add(spinnersEidolonT1[i] = new JSpinner(new SpinnerNumberModel(0, 0, 6, 1)));
            time1.add(slot1);
            time2.add(slot2);
        }
    //  String selecionado = (String) dropdowns[i].getSelectedItem();
        meuPainel.add(time1);
        meuPainel.add(time2);

        janela.add(meuPainel, BorderLayout.NORTH);
        janela.setVisible(true);
    }
}