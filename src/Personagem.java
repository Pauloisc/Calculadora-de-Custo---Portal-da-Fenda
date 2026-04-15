public class Personagem {
    String nome;
    double custoBase;
    double custoEidolon;

    public Personagem(String nome, double custoBase, double custoEidolon) {
        this.nome = nome;
        this.custoBase = custoBase;
        this.custoEidolon = custoEidolon;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getCustoBase() {
        return custoBase;
    }

    public void setCustoBase(double custoBase) {
        this.custoBase = custoBase;
    }

    public double getCustoEidolon() {
        return custoEidolon;
    }

    public void setCustoEidolon(double custoEidolon) {
        this.custoEidolon = custoEidolon;
    }
}
