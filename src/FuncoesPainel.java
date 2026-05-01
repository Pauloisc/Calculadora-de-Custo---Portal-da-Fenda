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
            configurarBuscaDropdown(dropdownsPersoT1[i], opcoesPerso);
            dropdownsPersoT2[i] = new JComboBox<>(opcoesPerso);
            configurarBuscaDropdown(dropdownsPersoT2[i], opcoesPerso);
            dropdownsConeT1[i] = new JComboBox<>(opcoesCone);
            dropdownsConeT2[i] = new JComboBox<>(opcoesCone);
        }
    }

    public static void BordasTexto (JPanel time1, JPanel time2, JPanel custoT1, JPanel custoT2,
                                    JLabel labelCustoT1, JLabel labelCustoT2, JLabel labelCustoTotal){
        time1.setBorder(BorderFactory.createTitledBorder("Time 1"));
        time2.setBorder(BorderFactory.createTitledBorder("Time 2"));
        custoT1.add(labelCustoT1);
        custoT2.add(labelCustoT2);
        custoT2.add(labelCustoTotal);
    }

    // CRIA UM SLOT PARA CADA COLUNA DE CADA TIME, REFERENTE A PERSONAGEM -> EIDOLON -> CONE DE LUZ -> SOBREPOSICAO
    public static void CriarSlots(JComboBox<String>[] dropdownsPersoT1, JComboBox<String>[] dropdownsPersoT2,
            JComboBox<String>[] dropdownsConeT1, JComboBox<String>[] dropdownsConeT2,
            JSpinner[] spinnersEidolonT1, JSpinner[] spinnersEidolonT2, JSpinner[] spinnersConeT1,
            JSpinner[] spinnersConeT2, JPanel time1, JPanel time2, JLabel[] labelsImagemT1, JLabel[] labelsImagemT2) {
        for (int i = 0; i < 4; i++) {
            labelsImagemT1[i] = new JLabel();
            labelsImagemT2[i] = new JLabel();
            labelsImagemT1[i].setAlignmentX(Component.CENTER_ALIGNMENT);
            dropdownsPersoT1[i].setAlignmentX(Component.CENTER_ALIGNMENT);
            labelsImagemT2[i].setAlignmentX(Component.CENTER_ALIGNMENT);
            dropdownsPersoT2[i].setAlignmentX(Component.CENTER_ALIGNMENT);
            labelsImagemT1[i].setPreferredSize(new Dimension(120, 120));
            labelsImagemT2[i].setPreferredSize(new Dimension(120, 120));
            JPanel slot1 = new JPanel();
            slot1.setLayout(new BoxLayout(slot1, BoxLayout.Y_AXIS));
            JPanel slot2 = new JPanel();
            slot2.setLayout(new BoxLayout(slot2, BoxLayout.Y_AXIS));
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

    public static double calcularExtraCones(JComboBox<String>[] dropdownsCone, JSpinner[] spinnersCone) {
        double extra = 0;
        for (int j = 0; j < 4; j++) {
            String nomeCone = (String) dropdownsCone[j].getSelectedItem();
            int sobreposicao = (int) spinnersCone[j].getValue();
            if (nomeCone.contains("T5")) {
                extra += 1.0 + (0.25 * sobreposicao);
            }
            else if (nomeCone.contains("T3")) {
                extra -= 0.5;
            }
            else if (nomeCone.contains("Nada")) {
                extra -= 1;
            }
        }
        return extra;
    }

    public static double valorCustoTotal(List<Personagem> listaPersonagens,JComboBox<String>[] dropdownsPersoT1,
                                         JSpinner[] spinnersEidolonT1,JComboBox<String>[] dropdownsPersoT2,
                                         JSpinner[] spinnersEidolonT2,JComboBox<String>[] dropdownsConeT1,
                                         JSpinner[] spinnersConeT1, JComboBox<String>[] dropdownsConeT2,
                                         JSpinner[] spinnersConeT2, JLabel labelCustoTotal) {

        double personagensT1 = calcularCusto(dropdownsPersoT1, spinnersEidolonT1, listaPersonagens);
        double personagensT2 = calcularCusto(dropdownsPersoT2, spinnersEidolonT2, listaPersonagens);
        double conesT1 = calcularExtraCones(dropdownsConeT1, spinnersConeT1);
        double conesT2 = calcularExtraCones(dropdownsConeT2, spinnersConeT2);
        double custoTimes = personagensT1 + personagensT2 + conesT1 + conesT2;
        if (custoTimes < 0) {
            labelCustoTotal.setForeground(Color.magenta);
        } else if (custoTimes >= 0 && custoTimes <= 2) {
            labelCustoTotal.setForeground(Color.BLUE);
        } else if (custoTimes > 2 && custoTimes <= 4) {
            labelCustoTotal.setForeground(new Color(46, 204, 113));
        } else if (custoTimes > 4 && custoTimes <= 6) {
            labelCustoTotal.setForeground(Color.ORANGE);
        } else if (custoTimes > 6 && custoTimes <= 8) {
            labelCustoTotal.setForeground(Color.RED);
        } else {
            labelCustoTotal.setForeground(Color.RED.darker());
        }
        return custoTimes;
    }

    public static void custoComposicoes(List<Personagem> listaPersonagens, JComboBox<String>[] dropdownsPersoT1,
                                        JSpinner[] spinnersEidolonT1, JComboBox<String>[] dropdownsConeT1, JSpinner[] spinnersConeT1,
                                        JLabel labelCustoT1, JComboBox<String>[] dropdownsPersoT2, JSpinner[] spinnersEidolonT2,
                                        JComboBox<String>[] dropdownsConeT2, JSpinner[] spinnersConeT2, JLabel labelCustoT2,
                                        JLabel labelCustoTotal, JLabel[] labelsImagemT1, JLabel[] labelsImagemT2) {

        for (int i = 0; i < 4; i++) {
            double soma = 0.0;
            int finalI = i;
            dropdownsPersoT1[i].addActionListener(e -> {
                double total = calcularCusto(dropdownsPersoT1, spinnersEidolonT1, listaPersonagens);
                total += calcularExtraCones(dropdownsConeT1, spinnersConeT1);
                labelCustoT1.setText("Custo: " + total);
                labelCustoTotal.setText("Custo Total: " + valorCustoTotal(listaPersonagens, dropdownsPersoT1, spinnersEidolonT1,
                        dropdownsPersoT2, spinnersEidolonT2,dropdownsConeT1, spinnersConeT1, dropdownsConeT2, spinnersConeT2, labelCustoTotal));
                String nomeSelecionado = (String) dropdownsPersoT1[finalI].getSelectedItem();
                atualizarRetrato(nomeSelecionado, labelsImagemT1[finalI]);
            });

            spinnersEidolonT1[i].addChangeListener(e -> {
                double total = calcularCusto(dropdownsPersoT1, spinnersEidolonT1, listaPersonagens);
                total += calcularExtraCones(dropdownsConeT1, spinnersConeT1);
                labelCustoT1.setText("Custo: " + total);
                labelCustoTotal.setText("Custo Total: " + valorCustoTotal(listaPersonagens, dropdownsPersoT1, spinnersEidolonT1,
                        dropdownsPersoT2, spinnersEidolonT2,dropdownsConeT1, spinnersConeT1, dropdownsConeT2, spinnersConeT2, labelCustoTotal));
            });

            dropdownsConeT1[i].addActionListener(e -> {
                double total = calcularCusto(dropdownsPersoT1, spinnersEidolonT1, listaPersonagens);
                total += calcularExtraCones(dropdownsConeT1, spinnersConeT1);
                labelCustoT1.setText("Custo: " + total);
                labelCustoTotal.setText("Custo Total: " + valorCustoTotal(listaPersonagens, dropdownsPersoT1, spinnersEidolonT1,
                        dropdownsPersoT2, spinnersEidolonT2,dropdownsConeT1, spinnersConeT1, dropdownsConeT2, spinnersConeT2, labelCustoTotal));
            });

            spinnersConeT1[i].addChangeListener(e -> {
                double total = calcularCusto(dropdownsPersoT1, spinnersEidolonT1, listaPersonagens);
                total += calcularExtraCones(dropdownsConeT1, spinnersConeT1);
                labelCustoT1.setText("Custo: " + total);
                labelCustoTotal.setText("Custo Total: " + valorCustoTotal(listaPersonagens, dropdownsPersoT1, spinnersEidolonT1,
                        dropdownsPersoT2, spinnersEidolonT2,dropdownsConeT1, spinnersConeT1, dropdownsConeT2, spinnersConeT2, labelCustoTotal));
            });
            dropdownsPersoT2[i].addActionListener(e -> {
                double total = calcularCusto(dropdownsPersoT2, spinnersEidolonT2, listaPersonagens);
                total += calcularExtraCones(dropdownsConeT2, spinnersConeT2);
                labelCustoT2.setText("Custo: " + total);
                labelCustoTotal.setText("Custo Total: " + valorCustoTotal(listaPersonagens, dropdownsPersoT1, spinnersEidolonT1,
                        dropdownsPersoT2, spinnersEidolonT2,dropdownsConeT1, spinnersConeT1, dropdownsConeT2, spinnersConeT2, labelCustoTotal));
                String nomeSelecionado = (String) dropdownsPersoT2[finalI].getSelectedItem();
                atualizarRetrato(nomeSelecionado, labelsImagemT2[finalI]);
            });

            spinnersEidolonT2[i].addChangeListener(e -> {
                double total = calcularCusto(dropdownsPersoT2, spinnersEidolonT2, listaPersonagens);
                total += calcularExtraCones(dropdownsConeT2, spinnersConeT2);
                labelCustoT2.setText("Custo: " + total);
                labelCustoTotal.setText("Custo Total: " + valorCustoTotal(listaPersonagens, dropdownsPersoT1, spinnersEidolonT1,
                        dropdownsPersoT2, spinnersEidolonT2,dropdownsConeT1, spinnersConeT1, dropdownsConeT2, spinnersConeT2, labelCustoTotal));
            });

            dropdownsConeT2[i].addActionListener(e -> {
                double total = calcularCusto(dropdownsPersoT2, spinnersEidolonT2, listaPersonagens);
                total += calcularExtraCones(dropdownsConeT2, spinnersConeT2);
                labelCustoT2.setText("Custo: " + total);
                labelCustoTotal.setText("Custo Total: " + valorCustoTotal(listaPersonagens, dropdownsPersoT1, spinnersEidolonT1,
                        dropdownsPersoT2, spinnersEidolonT2,dropdownsConeT1, spinnersConeT1, dropdownsConeT2, spinnersConeT2, labelCustoTotal));
            });

            spinnersConeT2[i].addChangeListener(e -> {
                double total = calcularCusto(dropdownsPersoT2, spinnersEidolonT2, listaPersonagens);
                total += calcularExtraCones(dropdownsConeT2, spinnersConeT2);
                labelCustoT2.setText("Custo: " + total);
                labelCustoTotal.setText("Custo Total: " + valorCustoTotal(listaPersonagens, dropdownsPersoT1, spinnersEidolonT1,
                        dropdownsPersoT2, spinnersEidolonT2,dropdownsConeT1, spinnersConeT1, dropdownsConeT2, spinnersConeT2, labelCustoTotal));
            });
        }
    }

    public static void configurarBuscaDropdown(JComboBox<String> dropdownPerso, String[] listaPersonagens) {
        dropdownPerso.setEditable(true);
        JTextField textoEditor = (JTextField) dropdownPerso.getEditor().getEditorComponent();

        textoEditor.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent e) {
                String digitado = textoEditor.getText();

                dropdownPerso.removeAllItems();

                for (String item : listaPersonagens) {
                    if (item.toLowerCase().contains(digitado.toLowerCase())) {
                        dropdownPerso.addItem(item);
                    }
                }
                textoEditor.setText(digitado);
                dropdownPerso.setPopupVisible(true);
            }
        });
    }

    public static void atualizarRetrato(String nome, JLabel quadro){
        String caminho = "Fotos personagens/" + nome + ".png";
        ImageIcon iconePersonagem = new ImageIcon("Fotos personagens/" + nome + ".png");
        Image imagemOriginal = iconePersonagem.getImage();
        Image imagemPequena = imagemOriginal.getScaledInstance(120, 120, Image.SCALE_SMOOTH);
        ImageIcon fotoFinal = new ImageIcon(imagemPequena);
        quadro.setIcon(fotoFinal);
        quadro.revalidate();
        quadro.repaint();
    }

}