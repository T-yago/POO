package Code;

public class Sapatilha_Nova extends Sapatilha implements Novos {

    public Sapatilha_Nova() {
        super();
        this.setPrecoFinal(0);
    }

    public Sapatilha_Nova(int Id_proprietario, String descricao, String marca, double preco, String transportadora, int tamanho, boolean atacadores, String cor, int ano_Lancamento, int desconto) {
        super(Id_proprietario, descricao, marca, preco, transportadora, tamanho, atacadores, cor, ano_Lancamento, desconto);
        this.setPrecoFinal(this.calculaPreco(desconto));
    }

    public Sapatilha_Nova(Sapatilha_Nova sapatilha_Nova) {
        super(sapatilha_Nova);
        this.setPrecoFinal(getPrecoBase());
    }

    public double calculaPreco(double desconto) {
        if (this.getTamanho()>45) return this.getPrecoBase() * (1 - desconto)/100;
        return this.getPrecoBase();
    }

    public void recalculaPreco () {
        this.setPrecoFinal(this.calculaPreco(0));
    }

    public Sapatilha_Nova clone() {
        return new Sapatilha_Nova(this);
    }
}
