package Code;

public class Sapatilha_Nova extends Sapatilha{

    public Sapatilha_Nova() {
        super();
    }

    public Sapatilha_Nova(String proprietario, String descricao, String marca, double preco, int tamanho, boolean atacadores, String cor, int ano_Lancamento) {
        super(proprietario, descricao, marca, preco, tamanho, atacadores, cor, ano_Lancamento);
        this.setPrecoFinal(this.calculaPreco(preco, tamanho, 0));
    }

    public Sapatilha_Nova(Sapatilha_Nova sapatilha_Nova) {
        super(sapatilha_Nova);
    }

    public double calculaPreco(double preco_Base, int tamanho, int b) {
        if (tamanho>45) return preco_Base * 0.90;
        return preco_Base;
    }
}
