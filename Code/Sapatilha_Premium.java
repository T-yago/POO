package Code;

public class Sapatilha_Premium extends Sapatilha {
    
    public Sapatilha_Premium () {
        super();
        this.setPrecoFinal(this.calculaPreco(this.getDesconto())); 
    }

    public Sapatilha_Premium(int Id_proprietario, String descricao, String marca, double preco, Transportadora_Premium transportadora, int tamanho, boolean atacadores, String cor, int ano_Lancamento, int desconto) {
        super(Id_proprietario, descricao, marca, preco, transportadora.getNome(), tamanho, atacadores, cor, ano_Lancamento, desconto);
        this.setPrecoFinal(this.calculaPreco(this.getDesconto())); 
    }
    
    public Sapatilha_Premium (Sapatilha_Premium sapatilha_premium) {
        super (sapatilha_premium);
        this.setPrecoFinal(sapatilha_premium.getPrecoFinal()); 
    }

    public Sapatilha_Premium clone () {
        return new Sapatilha_Premium(this);
    }

    public void recalculaPreco () {
        this.setPrecoFinal(this.calculaPreco(0));
    }

    public double calculaPreco(double desconto) {
        double preco_base = this.getPrecoBase();
        return  preco_base + ((this.getAnoLancamento() - Menu.getCurrentDate().getYear())*0.2);
    }
}
