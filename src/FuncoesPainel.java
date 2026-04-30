import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;

public class FuncoesPainel {
    public static void PreencherDropdowns(JComboBox<String>[] dropdownsPersoT1, JComboBox<String>[] dropdownsPersoT2,
                                          JComboBox<String>[] dropdownsConeT1, JComboBox<String>[] dropdownsConeT2,
                                          String[] opcoesPerso, String[] opcoesCone){
        for (int i = 0; i<4; i++){
            dropdownsPersoT1[i] = new JComboBox<>(opcoesPerso);
            dropdownsPersoT2[i] = new JComboBox<>(opcoesPerso);
            dropdownsConeT1[i] = new JComboBox<>(opcoesCone);
            dropdownsConeT2[i] = new JComboBox<>(opcoesCone);
        }
    }

    public static void BordasTexto (JPanel time1, JPanel time2, JPanel custoT1, JPanel custoT2,
                                    JLabel labelCustoT1, JLabel labelCustoT2){
        time1.setBorder(BorderFactory.createTitledBorder("Time 1"));
        time2.setBorder(BorderFactory.createTitledBorder("Time 2"));
        custoT1.add(labelCustoT1);
        custoT2.add(labelCustoT2);
    }

    // CRIA UM SLOT PARA CADA COLUNA DE CADA TIME, REFERENTE A PERSONAGEM -> EIDOLON -> CONE DE LUZ -> SOBREPOSICAO
    public static void CriarSlots(JComboBox<String>[] dropdownsPersoT1, JComboBox<String>[] dropdownsPersoT2,
            JComboBox<String>[] dropdownsConeT1, JComboBox<String>[] dropdownsConeT2,
            JSpinner[] spinnersEidolonT1, JSpinner[] spinnersEidolonT2, JSpinner[] spinnersConeT1,
            JSpinner[] spinnersConeT2, JPanel time1, JPanel time2, JLabel[] labelsImagemT1, JLabel[] labelsImagemT2) {
        for (int i = 0; i < 4; i++) {
            labelsImagemT1[i] = new JLabel();
            labelsImagemT2[i] = new JLabel();
            JPanel slot1 = new JPanel(new GridLayout(5, 1));
            JPanel slot2 = new JPanel(new GridLayout(5, 1));
            slot1.add(labelsImagemT1[i]);
            slot2.add(labelsImagemT2[i]);
            slot1.add(dropdownsPersoT1[i]);
            slot2.add(dropdownsPersoT2[i]);
            slot1.add(spinnersEidolonT1[i] = new JSpinner(new SpinnerNumberModel(0, 0, 6, 1)));
            slot2.add(spinnersEidolonT2[i] = new JSpinner(new SpinnerNumberModel(0, 0, 6, 1)));
            slot1.add(dropdownsConeT1[i]);
            slot2.add(dropdownsConeT2[i]);
            slot1.add(spinnersConeT1[i] = new JSpinner(new SpinnerNumberModel(0, 0, 5, 1)));
            slot2.add(spinnersConeT2[i] = new JSpinner(new SpinnerNumberModel(0, 0, 5, 1)));
            time1.add(slot1);
            time2.add(slot2);
        }
    }

    // ADICIONA AS INFORMAÇÕES DE CADA TIME NO PAINEL
    public static void InfoTimesPainel (JPanel ladoT1, JPanel ladoT2, JPanel time1, JPanel time2, JPanel meuPainel,
                                        JPanel custoT1, JPanel custoT2){
        ladoT1.add(time1, BorderLayout.NORTH);
        ladoT1.add(custoT1, BorderLayout.WEST);
        ladoT2.add(time2, BorderLayout.NORTH);
        ladoT2.add(custoT2, BorderLayout.WEST);
        meuPainel.add(ladoT1);
        meuPainel.add(ladoT2);
    }

    // CALCULO DO CUSTO (CORAÇÃO DO SOFTWARE)
    public static double calcularCusto(JComboBox<String>[] dropdownsPerso, JSpinner[] spinnersEidolon,
                                       List<Personagem> listaPersonagens){
        double custo = 0.0;
        for (int i = 0; i<4; i++){
            String nomeSelecionado = (String) dropdownsPerso[i].getSelectedItem();
            for (Personagem PersoSelecionado : listaPersonagens){
                if ((PersoSelecionado.getNome()).equals(nomeSelecionado)){
                    custo = custo + PersoSelecionado.getCustoBase() + (PersoSelecionado.getCustoEidolon() * (int) spinnersEidolon[i].getValue());
                }
            }
        }
        return custo;
    }

    public static void custoPersonagem(JComboBox<String>[] dropdownsPerso, JSpinner[] spinnersEidolon,
                                           List<Personagem> listaPersonagens, JLabel labelCusto) {
        for (int i = 0; i < 4; i++) {
            dropdownsPerso[i].addActionListener(e -> {
                double novoCusto = calcularCusto(dropdownsPerso, spinnersEidolon, listaPersonagens);
                labelCusto.setText("Custo: " + novoCusto);
            });

            spinnersEidolon[i].addChangeListener(e -> {
                double novoCusto = calcularCusto(dropdownsPerso, spinnersEidolon, listaPersonagens);
                labelCusto.setText("Custo: " + novoCusto);
            });
        }
    }

    public static void custoTotal(JComboBox<String>[] dropdownsPerso, JSpinner[] spinnersEidolon,
                                  List<Personagem> listaPersonagens, JComboBox<String>[] dropdownsCone,
                                  JSpinner[] spinnersCone, JLabel labelCusto) {

        for (int i = 0; i < 4; i++) {
            dropdownsPerso[i].addActionListener(e -> {
                double total = calcularCusto(dropdownsPerso, spinnersEidolon, listaPersonagens);
                total += calcularExtraCones(dropdownsCone, spinnersCone);
                labelCusto.setText("Custo: " + total);
            });

            spinnersEidolon[i].addChangeListener(e -> {
                double total = calcularCusto(dropdownsPerso, spinnersEidolon, listaPersonagens);
                total += calcularExtraCones(dropdownsCone, spinnersCone);
                labelCusto.setText("Custo: " + total);
            });

            dropdownsCone[i].addActionListener(e -> {
                double total = calcularCusto(dropdownsPerso, spinnersEidolon, listaPersonagens);
                total += calcularExtraCones(dropdownsCone, spinnersCone);
                labelCusto.setText("Custo: " + total);
            });

            spinnersCone[i].addChangeListener(e -> {
                double total = calcularCusto(dropdownsPerso, spinnersEidolon, listaPersonagens);
                total += calcularExtraCones(dropdownsCone, spinnersCone);
                labelCusto.setText("Custo: " + total);
            });
        }
    }

    public static double calcularExtraCones(JComboBox<String>[] dropdownsCone, JSpinner[] spinnersCone) {
        double extra = 0;
        for (int j = 0; j < 4; j++) {
            String nomeCone = (String) dropdownsCone[j].getSelectedItem();
            int sobreposicao = (int) spinnersCone[j].getValue();
            if (nomeCone.contains("T5")) {
                extra += 1.0 + (0.25 * sobreposicao);
            }
            else if (nomeCone.contains("T3")) {
                extra -= 1.0;
            }
        }
        return extra;
    }


}