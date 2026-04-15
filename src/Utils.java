import java.io.*;
import java.util.*;

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
}