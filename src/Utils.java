import java.io.BufferedReader;
import java.io.FileReader;
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
                if (!linha.trim().isEmpty()) {
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
                if (!linha.trim().isEmpty()) {
                    String[] partes = linha.trim().split(",");
                    String nome = partes[0];
                    String tier = partes[1];
                    // custoBase e custoEidolon serão definidos futuramente com base na tier
                    if (Objects.equals(tier, "Error")) {
                        lista.add(new Personagem(nome, 1.5, 2, tier));
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
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler arquivo: " + e.getMessage());
        }
        return lista;
    }
}
