package Code;
import Code.Sapatilha;

public class Sapatilha_premium extends Sapatilha {
    public Sapatilha_premium () {
        super();
        this.setPrecoFinal(this.calculaPreco(this.getDesconto())); 
    }

    public Sapatilha_premium(String proprietario, String descricao, String marca, double preco, int tamanho, boolean atacadores, String cor, int ano_Lancamento, int desconto) {
        super(proprietario, descricao, marca, preco, tamanho, atacadores, cor, ano_Lancamento, desconto);
        this.setPrecoFinal(this.calculaPreco(this.getDesconto())); 
    }

    public Sapatilha_premium (Sapatilha_premium sapatilha_premium) {
        super (sapatilha_premium);
        this.setPrecoFinal(sapatilha_premium.getPrecoFinal()); 
    }

    public Sapatilha_premium clone () {
        return new Sapatilha_premium();
    }

    public double calculaPreco(double desconto) {
        double preco_base = this.getPrecoBase();
        return  preco_base + (preco_base + this.getAnoLancamento()*0.1);
    }

    public String toString () {
        return super.toString();
    }
}