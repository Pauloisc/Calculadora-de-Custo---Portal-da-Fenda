import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;
import javax.swing.border.TitledBorder;

public class FuncoesPainel {
    public static void AmplificarVisual (){
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
            UIManager.put("ComboBox.font", new Font("Segoe UI", Font.ITALIC, 13));
            UIManager.put("Spinner.font", new Font("Segoe UI", Font.BOLD, 10));
            UIManager.put("Button.font", new Font("Segoe UI", Font.BOLD, 10));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void AmplificarVisualBotoes (JButton botaoSalvarT1, JButton botaoSalvarT2,JButton botaoLimparT1,JButton botaoLimparT2){
        botaoSalvarT1.setBackground(new Color(34, 207, 31));
        botaoSalvarT2.setBackground(new Color(34, 207, 31));
        botaoLimparT1.setBackground(new Color(183, 28, 28));
        botaoLimparT2.setBackground(new Color(183, 28, 28));
        botaoLimparT1.setForeground(Color.WHITE);
        botaoLimparT2.setForeground(Color.WHITE);
    }
    public static void PreencherDropdowns(JComboBox<String>[] dropdownsPersoT1, JComboBox<String>[] dropdownsPersoT2,
                                          JComboBox<String>[] dropdownsConeT1, JComboBox<String>[] dropdownsConeT2,
                                          String[] opcoesPerso, String[] opcoesCone){
        for (int i = 0; i<4; i++){
            dropdownsPersoT1[i] = new JComboBox<>(opcoesPerso);
            dropdownsPersoT1[i].setSelectedItem("");
            configurarBuscaDropdown(dropdownsPersoT1[i], opcoesPerso);
            dropdownsPersoT2[i] = new JComboBox<>(opcoesPerso);
            dropdownsPersoT2[i].setSelectedItem("");
            configurarBuscaDropdown(dropdownsPersoT2[i], opcoesPerso);
            dropdownsConeT1[i] = new JComboBox<>(opcoesCone);
            dropdownsConeT2[i] = new JComboBox<>(opcoesCone);
        }
    }

    public static void PosicionarBotoes (JPanel time1, JPanel time2, JPanel custoT1, JPanel custoT2,
                                    JLabel labelCustoT1, JLabel labelCustoT2, JLabel labelCustoTotal,
                                    JSpinner[] spinnerCustoTotalAdicional, JButton botaoSalvarT1, JButton botaoCarregarT1,
                                         JButton botaoSalvarT2, JButton botaoCarregarT2, JButton botaoLimparT1,
                                         JButton botaoLimparT2){
        TitledBorder bordaT1 = BorderFactory.createTitledBorder("Time 1");
        bordaT1.setTitleColor(Color.WHITE);
        time1.setBorder(bordaT1);
        TitledBorder bordaT2 = BorderFactory.createTitledBorder("Time 2");
        bordaT2.setTitleColor(Color.WHITE);
        time2.setBorder(bordaT2);

        custoT1.add(labelCustoT1, BorderLayout.WEST);
        JPanel painelBotoesT1 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        painelBotoesT1.add(botaoLimparT1);
        painelBotoesT1.add(botaoCarregarT1);
        painelBotoesT1.add(botaoSalvarT1);
        custoT1.add(painelBotoesT1, BorderLayout.EAST);

        JPanel subPainel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        subPainel.add(labelCustoTotal);
        subPainel.add(spinnerCustoTotalAdicional[0]);
        JPanel painelBotoesT2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        painelBotoesT2.add(botaoLimparT2);
        painelBotoesT2.add(botaoCarregarT2);
        painelBotoesT2.add(botaoSalvarT2);
        JPanel linhaInfosT2 = new JPanel(new BorderLayout());
        linhaInfosT2.add(labelCustoT2, BorderLayout.WEST);
        linhaInfosT2.add(painelBotoesT2, BorderLayout.EAST);
        custoT2.add(linhaInfosT2);
        custoT2.add(subPainel);

        painelBotoesT1.setOpaque(false);
        painelBotoesT2.setOpaque(false);
        subPainel.setOpaque(false);
        linhaInfosT2.setOpaque(false);
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
            slot1.add(spinnersConeT1[i] = new JSpinner(new SpinnerNumberModel(1, 1, 5, 1)));
            slot2.add(spinnersConeT2[i] = new JSpinner(new SpinnerNumberModel(1, 1, 5, 1)));
            time1.add(slot1);
            time2.add(slot2);
            slot1.setBackground(new Color(32, 34, 40));
            slot2.setBackground(new Color(32, 34, 40));
            labelsImagemT1[i].setHorizontalAlignment(SwingConstants.CENTER);
            labelsImagemT2[i].setHorizontalAlignment(SwingConstants.CENTER);
        }
    }

    // ADICIONA AS INFORMAÇÕES DE CADA TIME NO PAINEL
    public static void InfoTimesPainel (JPanel ladoT1, JPanel ladoT2, JPanel time1, JPanel time2, JPanel meuPainel,
                                        JPanel custoT1, JPanel custoT2){
        ladoT1.add(time1, BorderLayout.NORTH);
        ladoT1.add(custoT1, BorderLayout.SOUTH);
        ladoT2.add(time2, BorderLayout.NORTH);
        ladoT2.add(custoT2, BorderLayout.SOUTH);
        meuPainel.add(ladoT1);
        meuPainel.add(ladoT2);
    }

    public static void ConfigurarAcoesSaveLoadClear(JComboBox<String>[] dropdownsPersoT1,
                                               JSpinner[] spinnersEidolonT1,JComboBox<String>[] dropdownsPersoT2,
                                               JSpinner[] spinnersEidolonT2,JComboBox<String>[] dropdownsConeT1,
                                               JSpinner[] spinnersConeT1, JComboBox<String>[] dropdownsConeT2,
                                               JSpinner[] spinnersConeT2, JButton botaoSalvarT1, JButton botaoCarregarT1,
                                               JButton botaoSalvarT2, JButton botaoCarregarT2, JButton botaoLimparT1,
                                                    JButton botaoLimparT2){
        botaoSalvarT1.addActionListener(e -> {
            JFileChooser explorador = new JFileChooser();
            int resultado = explorador.showSaveDialog(null);
            if (resultado == JFileChooser.APPROVE_OPTION) {
                String textoFinal = Utils.StringTimeSalvo(dropdownsPersoT1, spinnersEidolonT1, dropdownsConeT1, spinnersConeT1);
                String caminhoArquivo = explorador.getSelectedFile().getAbsolutePath();
                try {
                    java.io.FileWriter escritor = new java.io.FileWriter(caminhoArquivo);
                    escritor.write(textoFinal);
                    escritor.close();
                    JOptionPane.showMessageDialog(null, "Time 1 salvo com sucesso!");
                } catch (Exception erro) {
                    JOptionPane.showMessageDialog(null, "Erro ao salvar: " + erro.getMessage());
                }
            }
        });

        botaoSalvarT2.addActionListener(e -> {
            JFileChooser explorador = new JFileChooser();
            int resultado = explorador.showSaveDialog(null);
            if (resultado == JFileChooser.APPROVE_OPTION) {
                String textoFinal = Utils.StringTimeSalvo(dropdownsPersoT2, spinnersEidolonT2, dropdownsConeT2, spinnersConeT2);
                String caminhoArquivo = explorador.getSelectedFile().getAbsolutePath();
                try {
                    java.io.FileWriter escritor = new java.io.FileWriter(caminhoArquivo);
                    escritor.write(textoFinal);
                    escritor.close();
                    JOptionPane.showMessageDialog(null, "Time 2 salvo com sucesso!");
                } catch (Exception erro) {
                    JOptionPane.showMessageDialog(null, "Erro ao salvar: " + erro.getMessage());
                }
            }
        });

        botaoCarregarT1.addActionListener(e -> {
            JFileChooser explorador = new JFileChooser();
            if (explorador.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                try {
                    String caminhoArquivo = explorador.getSelectedFile().getAbsolutePath();
                    String[] linhasLidas = Utils.lerLinhasDoArquivo(caminhoArquivo);
                    if (linhasLidas.length > 0) {
                        String textoLido = linhasLidas[0];
                        Utils.carregarStringTime(textoLido, dropdownsPersoT1, spinnersEidolonT1, dropdownsConeT1, spinnersConeT1);
                        JOptionPane.showMessageDialog(null, "Time carregado com sucesso!");
                    } else {
                        JOptionPane.showMessageDialog(null, "O arquivo selecionado está vazio.");
                    }
                } catch (Exception erro) {
                    JOptionPane.showMessageDialog(null, "Erro ao carregar: " + erro.getMessage());
                }
            }
        });

        botaoCarregarT2.addActionListener(e -> {
            JFileChooser explorador = new JFileChooser();
            if (explorador.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                try {
                    String caminhoArquivo = explorador.getSelectedFile().getAbsolutePath();
                    String[] linhasLidas = Utils.lerLinhasDoArquivo(caminhoArquivo);
                    if (linhasLidas.length > 0) {
                        String textoLido = linhasLidas[0];
                        Utils.carregarStringTime(textoLido, dropdownsPersoT2, spinnersEidolonT2, dropdownsConeT2, spinnersConeT2);
                        JOptionPane.showMessageDialog(null, "Time carregado com sucesso!");
                    } else {
                        JOptionPane.showMessageDialog(null, "O arquivo selecionado está vazio.");
                    }
                } catch (Exception erro) {
                    JOptionPane.showMessageDialog(null, "Erro ao carregar: " + erro.getMessage());
                }
            }
        });

        botaoLimparT1.addActionListener(e -> {
            for (int i = 0; i < 4; i++){
                dropdownsPersoT1[i].setSelectedItem("");
                spinnersEidolonT1[i].setValue(0);
                dropdownsConeT1[i].setSelectedItem("");
                spinnersConeT1[i].setValue(1);
            }
        });

        botaoLimparT2.addActionListener(e -> {
            for (int i = 0; i < 4; i++){
                dropdownsPersoT2[i].setSelectedItem("");
                spinnersEidolonT2[i].setValue(0);
                dropdownsConeT2[i].setSelectedItem("");
                spinnersConeT2[i].setValue(1);
            }
        });
    }

    public static void custoComposicoes(List<Personagem> listaPersonagens, JComboBox<String>[] dropdownsPersoT1,
                                        JSpinner[] spinnersEidolonT1, JComboBox<String>[] dropdownsConeT1, JSpinner[] spinnersConeT1,
                                        JLabel labelCustoT1, JComboBox<String>[] dropdownsPersoT2, JSpinner[] spinnersEidolonT2,
                                        JComboBox<String>[] dropdownsConeT2, JSpinner[] spinnersConeT2, JLabel labelCustoT2,
                                        JLabel labelCustoTotal, JLabel[] labelsImagemT1, JLabel[] labelsImagemT2,
                                        JSpinner[] spinnerCustoTotalAdicional) {
        spinnerCustoTotalAdicional[0].addChangeListener(e -> {
            labelCustoTotal.setText("Custo Total: " + CalculadoraCustos.valorCustoTotal(listaPersonagens, dropdownsPersoT1, spinnersEidolonT1,
                    dropdownsPersoT2, spinnersEidolonT2,dropdownsConeT1, spinnersConeT1, dropdownsConeT2, spinnersConeT2, labelCustoTotal,
                    spinnerCustoTotalAdicional));
        });
        for (int i = 0; i < 4; i++) {
            double soma = 0.0;
            int finalI = i;
            dropdownsPersoT1[i].addActionListener(e -> {
                double total = CalculadoraCustos.calcularCusto(dropdownsPersoT1, spinnersEidolonT1, listaPersonagens);
                total += CalculadoraCustos.calcularExtraCones(dropdownsPersoT1, dropdownsConeT1, spinnersConeT1);
                labelCustoT1.setText("Custo: " + total);
                labelCustoTotal.setText("Custo Total: " + CalculadoraCustos.valorCustoTotal(listaPersonagens, dropdownsPersoT1, spinnersEidolonT1,
                        dropdownsPersoT2, spinnersEidolonT2,dropdownsConeT1, spinnersConeT1, dropdownsConeT2, spinnersConeT2, labelCustoTotal,
                        spinnerCustoTotalAdicional));
                String nomeSelecionado = (String) dropdownsPersoT1[finalI].getSelectedItem();
                atualizarRetrato(nomeSelecionado, labelsImagemT1[finalI]);
            });

            spinnersEidolonT1[i].addChangeListener(e -> {
                double total = CalculadoraCustos.calcularCusto(dropdownsPersoT1, spinnersEidolonT1, listaPersonagens);
                total += CalculadoraCustos.calcularExtraCones(dropdownsPersoT1, dropdownsConeT1, spinnersConeT1);
                labelCustoT1.setText("Custo: " + total);
                labelCustoTotal.setText("Custo Total: " + CalculadoraCustos.valorCustoTotal(listaPersonagens, dropdownsPersoT1, spinnersEidolonT1,
                        dropdownsPersoT2, spinnersEidolonT2,dropdownsConeT1, spinnersConeT1, dropdownsConeT2, spinnersConeT2, labelCustoTotal,
                        spinnerCustoTotalAdicional));
            });

            dropdownsConeT1[i].addActionListener(e -> {
                double total = CalculadoraCustos.calcularCusto(dropdownsPersoT1, spinnersEidolonT1, listaPersonagens);
                total += CalculadoraCustos.calcularExtraCones(dropdownsPersoT1, dropdownsConeT1, spinnersConeT1);
                labelCustoT1.setText("Custo: " + total);
                labelCustoTotal.setText("Custo Total: " + CalculadoraCustos.valorCustoTotal(listaPersonagens, dropdownsPersoT1, spinnersEidolonT1,
                        dropdownsPersoT2, spinnersEidolonT2,dropdownsConeT1, spinnersConeT1, dropdownsConeT2, spinnersConeT2, labelCustoTotal,
                        spinnerCustoTotalAdicional));
            });

            spinnersConeT1[i].addChangeListener(e -> {
                double total = CalculadoraCustos.calcularCusto(dropdownsPersoT1, spinnersEidolonT1, listaPersonagens);
                total += CalculadoraCustos.calcularExtraCones(dropdownsPersoT1, dropdownsConeT1, spinnersConeT1);
                labelCustoT1.setText("Custo: " + total);
                labelCustoTotal.setText("Custo Total: " + CalculadoraCustos.valorCustoTotal(listaPersonagens, dropdownsPersoT1, spinnersEidolonT1,
                        dropdownsPersoT2, spinnersEidolonT2,dropdownsConeT1, spinnersConeT1, dropdownsConeT2, spinnersConeT2, labelCustoTotal,
                        spinnerCustoTotalAdicional));
            });
            dropdownsPersoT2[i].addActionListener(e -> {
                double total = CalculadoraCustos.calcularCusto(dropdownsPersoT2, spinnersEidolonT2, listaPersonagens);
                total += CalculadoraCustos.calcularExtraCones(dropdownsPersoT2, dropdownsConeT2, spinnersConeT2);
                labelCustoT2.setText("Custo: " + total);
                labelCustoTotal.setText("Custo Total: " + CalculadoraCustos.valorCustoTotal(listaPersonagens, dropdownsPersoT1, spinnersEidolonT1,
                        dropdownsPersoT2, spinnersEidolonT2,dropdownsConeT1, spinnersConeT1, dropdownsConeT2, spinnersConeT2, labelCustoTotal,
                        spinnerCustoTotalAdicional));
                String nomeSelecionado = (String) dropdownsPersoT2[finalI].getSelectedItem();
                atualizarRetrato(nomeSelecionado, labelsImagemT2[finalI]);
            });

            spinnersEidolonT2[i].addChangeListener(e -> {
                double total = CalculadoraCustos.calcularCusto(dropdownsPersoT2, spinnersEidolonT2, listaPersonagens);
                total += CalculadoraCustos.calcularExtraCones(dropdownsPersoT2, dropdownsConeT2, spinnersConeT2);
                labelCustoT2.setText("Custo: " + total);
                labelCustoTotal.setText("Custo Total: " + CalculadoraCustos.valorCustoTotal(listaPersonagens, dropdownsPersoT1, spinnersEidolonT1,
                        dropdownsPersoT2, spinnersEidolonT2,dropdownsConeT1, spinnersConeT1, dropdownsConeT2, spinnersConeT2, labelCustoTotal,
                        spinnerCustoTotalAdicional));
            });

            dropdownsConeT2[i].addActionListener(e -> {
                double total = CalculadoraCustos.calcularCusto(dropdownsPersoT2, spinnersEidolonT2, listaPersonagens);
                total += CalculadoraCustos.calcularExtraCones(dropdownsPersoT2, dropdownsConeT2, spinnersConeT2);
                labelCustoT2.setText("Custo: " + total);
                labelCustoTotal.setText("Custo Total: " + CalculadoraCustos.valorCustoTotal(listaPersonagens, dropdownsPersoT1, spinnersEidolonT1,
                        dropdownsPersoT2, spinnersEidolonT2,dropdownsConeT1, spinnersConeT1, dropdownsConeT2, spinnersConeT2, labelCustoTotal,
                        spinnerCustoTotalAdicional));
            });

            spinnersConeT2[i].addChangeListener(e -> {
                double total = CalculadoraCustos.calcularCusto(dropdownsPersoT2, spinnersEidolonT2, listaPersonagens);
                total += CalculadoraCustos.calcularExtraCones(dropdownsPersoT2, dropdownsConeT2, spinnersConeT2);
                labelCustoT2.setText("Custo: " + total);
                labelCustoTotal.setText("Custo Total: " + CalculadoraCustos.valorCustoTotal(listaPersonagens, dropdownsPersoT1, spinnersEidolonT1,
                        dropdownsPersoT2, spinnersEidolonT2,dropdownsConeT1, spinnersConeT1, dropdownsConeT2, spinnersConeT2, labelCustoTotal,
                        spinnerCustoTotalAdicional));
            });
        }
    }

    public static void configurarBuscaDropdown(JComboBox<String> dropdownPerso, String[] listaPersonagens) {
        dropdownPerso.setEditable(true);
        JTextField textoEditor = (JTextField) dropdownPerso.getEditor().getEditorComponent();

        textoEditor.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent e) {
                if (e.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
                    if (dropdownPerso.getItemCount() > 0) {
                        dropdownPerso.setSelectedIndex(0);
                        dropdownPerso.setPopupVisible(false);
                    }
                    return;
                }

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