package Code;

import java.time.LocalDate;

public class Sapatilha_Premium extends Sapatilha {
    public Sapatilha_Premium () {
        super();
        this.setPrecoFinal(this.calculaPreco(this.getDesconto())); 
    }

    public Sapatilha_Premium(String proprietario, String descricao, String marca, double preco, int tamanho, boolean atacadores, String cor, int ano_Lancamento, int desconto) {
        super(proprietario, descricao, marca, preco, tamanho, atacadores, cor, ano_Lancamento, desconto);
        this.setPrecoFinal(this.calculaPreco(this.getDesconto())); 
    }

    public Sapatilha_Premium (Sapatilha_Premium sapatilha_premium) {
        super (sapatilha_premium);
        this.setPrecoFinal(sapatilha_premium.getPrecoFinal()); 
    }

    public Sapatilha_Premium clone () {
        return new Sapatilha_Premium();
    }

    public double calculaPreco(double desconto) {
        double preco_base = this.getPrecoBase();
        return  preco_base + (preco_base + (this.getAnoLancamento()-LocalDate.now().getYear())*0.1);
    }

    public String toString () {
        return super.toString();
    }
}