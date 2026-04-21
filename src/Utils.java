import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;

public class Utils {
    public static String[] lerLinhasDoArquivo(String caminho) {
        List<String> linhas = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(caminho))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                if (!linha.isBlank()) {
                    linhas.add(linha.trim());
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler arquivo: " + e.getMessage());
        }
        return linhas.toArray(new String[0]);
    }

    public static List<Personagem> lerPersonagens(String caminho) {
        List<Personagem> lista = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(caminho))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                if (!linha.isBlank()) {
                    String[] partes = linha.trim().split(",");
                    String nome = partes[0];
                    String tier = partes[1];
                    // custoBase e custoEidolon serão definidos futuramente com base na tier
                    if (Objects.equals(tier, "PilaresDoMeta")) {
                        lista.add(new Personagem(nome, 1, 0, tier));
                    } else if (Objects.equals(tier, "Brilhantes")) {
                        lista.add(new Personagem(nome, 0.5, 0, tier));
                    } else if (Objects.equals(tier, "Coringas")) {
                        lista.add(new Personagem(nome, 0.5, 0, tier));
                    } else if (Objects.equals(tier, "Neutros")) {
                        lista.add(new Personagem(nome, 0.5, 0, tier));
                    } else {
                        lista.add(new Personagem(nome, 0, 0, tier));
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler arquivo: " + e.getMessage());
        }
        return lista;
    }

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

    // CRIA UM SLOT PARA CADA COLUNA DE CADA TIME, REFERENTE A PERSONAGEM -> EIDOLON
    // -> CONE DE LUZ -> SOBREPOSICAO
    public static void CriarSlots(JComboBox<String>[] dropdownsPersoT1, JComboBox<String>[] dropdownsPersoT2,
            JComboBox<String>[] dropdownsConeT1, JComboBox<String>[] dropdownsConeT2,
            JSpinner[] spinnersEidolonT1, JSpinner[] spinnersEidolonT2, JSpinner[] spinnersConeT1,
            JSpinner[] spinnersConeT2, JPanel time1, JPanel time2) {
        for (int i = 0; i < 4; i++) {
            JPanel slot1 = new JPanel(new GridLayout(4, 1));
            JPanel slot2 = new JPanel(new GridLayout(4, 1));
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

    public static void InfoTimesPainel (JPanel ladoT1, JPanel ladoT2, JPanel time1, JPanel time2, JPanel meuPainel,
                                        JPanel custoT1, JPanel custoT2){
        ladoT1.add(time1, BorderLayout.NORTH);
        ladoT1.add(custoT1, BorderLayout.WEST);
        ladoT2.add(time2, BorderLayout.NORTH);
        ladoT2.add(custoT2, BorderLayout.WEST);
        meuPainel.add(ladoT1);
        meuPainel.add(ladoT2);
    }

    public static double calcularCusto(JComboBox<String>[] dropdownsPerso, JSpinner[] spinnersEidolon,
                                       List<Personagem> listaPersonagens){
        double total = 0.0;
        for (int i = 0; i<4; i++){
            //dropdownsPerso[i]
        }
        return total;
    }

}