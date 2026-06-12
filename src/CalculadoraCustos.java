import javax.swing.*;
import java.awt.*;
import java.util.List;

public class CalculadoraCustos {
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

    public static double calcularExtraCones(JComboBox<String>[] dropdownsPerso, JComboBox<String>[] dropdownsCone, JSpinner[] spinnersCone) {
        double extra = 0;
        for (int j = 0; j < 4; j++) {
            String nomePerso = (String) dropdownsPerso[j].getSelectedItem();
            String nomeCone = (String) dropdownsCone[j].getSelectedItem();
            int sobreposicao = (int) spinnersCone[j].getValue();
            if (nomePerso != null && !nomePerso.equals("") && !nomePerso.equals("Nada")) {
                if (nomeCone.contains("Cone T5")) {
                    if (sobreposicao > 1){extra += 1.0 + (0.25 * sobreposicao-0.25);}
                    else{extra += 1.0;}
                }
                else if (nomeCone.contains("Cone T3")) {
                    extra -= 0.5;
                }
                else if (nomeCone.contains("Nada") || nomeCone.isEmpty()) {
                    extra -= 1;
                }
            }
        }
        return extra;
    }

    public static double valorCustoTotal(List<Personagem> listaPersonagens,JComboBox<String>[] dropdownsPersoT1,
                                         JSpinner[] spinnersEidolonT1,JComboBox<String>[] dropdownsPersoT2,
                                         JSpinner[] spinnersEidolonT2,JComboBox<String>[] dropdownsConeT1,
                                         JSpinner[] spinnersConeT1, JComboBox<String>[] dropdownsConeT2,
                                         JSpinner[] spinnersConeT2, JLabel labelCustoTotal, JSpinner[] spinnerCustoTotalAdicional) {

        double personagensT1 = calcularCusto(dropdownsPersoT1, spinnersEidolonT1, listaPersonagens);
        double personagensT2 = calcularCusto(dropdownsPersoT2, spinnersEidolonT2, listaPersonagens);
        double conesT1 = calcularExtraCones(dropdownsPersoT1, dropdownsConeT1, spinnersConeT1);
        double conesT2 = calcularExtraCones(dropdownsPersoT2, dropdownsConeT2, spinnersConeT2);
        double extra = (Double) spinnerCustoTotalAdicional[0].getValue();
        double custoTimes = personagensT1 + personagensT2 + conesT1 + conesT2 + extra;
        if (custoTimes < 0) {
            labelCustoTotal.setForeground(Color.magenta);
        }
        else if (custoTimes == 0){
            labelCustoTotal.setForeground(Color.BLACK);
        }else if (custoTimes > 0 && custoTimes <= 2) {
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
}
