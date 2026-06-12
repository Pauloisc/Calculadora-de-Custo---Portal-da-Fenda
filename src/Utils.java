import java.io.BufferedReader;
import java.io.FileReader;
import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Utils {
    public static String[] lerLinhasDoArquivo(String caminho) {
        List<String> linhas = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(caminho))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                linhas.add(linha.trim());
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
                if (!linha.trim().isEmpty()) {
                    String[] partes = linha.trim().split(",");
                    String nome = partes[0];
                    String tier = partes[1];
                    // custoBase e custoEidolon serão definidos futuramente com base na tier
                    if (Objects.equals(tier, "Error")) {
                        lista.add(new Personagem(nome, 2, 1.5, tier));
                    }
                    else if (Objects.equals(tier, "PilaresDoMeta")) {
                        lista.add(new Personagem(nome, 1, 1.5, tier));
                    }
                    else if (Objects.equals(tier, "Brilhantes")) {
                        lista.add(new Personagem(nome, 0.5, 1, tier));
                    }
                    else if (Objects.equals(tier, "Coringas")) {
                        lista.add(new Personagem(nome, 0, 0.5, tier));
                    }
                    else if (Objects.equals(tier, "Neutros")) {
                        lista.add(new Personagem(nome, 0.0, 0, tier));
                    }
                    else if (Objects.equals(tier, "QuebraGalho")) {
                        lista.add(new Personagem(nome, -0.5, 0, tier));
                    }
                    else if (Objects.equals(tier, "FundoDoPoco")) {
                        lista.add(new Personagem(nome, -1, 0, tier));
                    }
                    else if (Objects.equals(tier, "Nada")) {
                        lista.add(new Personagem(nome, -1.5, 0, tier));
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler arquivo: " + e.getMessage());
        }
        return lista;
    }

    public static String StringTimeSalvo(JComboBox<String>[] dropdownsPerso, JSpinner[] spinnersEidolon,
                                         JComboBox<String>[] dropdownsCone, JSpinner[] spinnersCone){
        StringBuilder textoSalvo = new StringBuilder();
        for (int i = 0; i < 4; i++){
            String nome = (String)dropdownsPerso[i].getSelectedItem();
            int eidolon = (Integer) spinnersEidolon[i].getValue();
            String nomeCone = (String)dropdownsCone[i].getSelectedItem();
            int sobreposicaoCone = (Integer)spinnersCone[i].getValue();

            if (!Objects.equals(nome, "") && !Objects.equals(nome, "Nada")) {
                if (Objects.equals(nomeCone, "Nada")){
                    textoSalvo.append(nome + " E" + eidolon + " Nada");
                }
                else{
                    textoSalvo.append(nome + " E" + eidolon + " " + nomeCone + " S" + sobreposicaoCone);
                }
            }else{
                textoSalvo.append("Nada");
            }
            if (i < 3) { textoSalvo.append("; "); }
        }
        return textoSalvo.toString();
    }

    public static void carregarStringTime(String textoLido, JComboBox<String>[] dropdownsPerso, JSpinner[] spinnersEidolon,
                                          JComboBox<String>[] dropdownsCone, JSpinner[] spinnersCone) {
        String[] camposTexto = textoLido.split("; ");
        for (int i = 0; i < 4; i++){
            if (Objects.equals(camposTexto[i], "Nada")){
                dropdownsPerso[i].setSelectedItem("");
                spinnersEidolon[i].setValue(0);
                dropdownsCone[i].setSelectedItem("");
                spinnersCone[i].setValue(1);
            }
            else{
                String[] fatiasPersonagem = camposTexto[i].split(" E");
                String nomePersonagem = fatiasPersonagem[0];
                String restoDaFrase = fatiasPersonagem[1];
                int numeroEidolon = Integer.parseInt(restoDaFrase.substring(0, 1));
                String pedacoDoCone = restoDaFrase.substring(2);
                if (pedacoDoCone.equals("Nada")) {
                    dropdownsCone[i].setSelectedItem("");
                    spinnersCone[i].setValue(1);
                } else {
                    String[] fatiasCone = pedacoDoCone.split(" S");
                    String nomeCone = fatiasCone[0];
                    int numeroSobreposicao = Integer.parseInt(fatiasCone[1]);

                    dropdownsCone[i].setSelectedItem(nomeCone);
                    spinnersCone[i].setValue(numeroSobreposicao);
                }
                dropdownsPerso[i].setSelectedItem(nomePersonagem);
                spinnersEidolon[i].setValue(numeroEidolon);
            }
        }
    }
}
