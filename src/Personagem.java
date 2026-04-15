public class Personagem {
    String nome;
    double custoBase;
    double custoEidolon;
    String tierPersonagem;

    public Personagem(String nome, double custoBase, double custoEidolon, String tierPersonagem) {
        this.nome = nome;
        this.custoBase = custoBase;
        this.custoEidolon = custoEidolon;
        this.tierPersonagem = tierPersonagem;
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

    public String getTierPersonagem() {
        return tierPersonagem;
    }

    public void setTierPersonagem(String tierPersonagem) {
        this.tierPersonagem = tierPersonagem;
    }
}
