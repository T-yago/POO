package Code;

public class Mala_Premium extends Mala {
    
    public Mala_Premium () {
        super ();
        this.setPrecoFinal(0);
    }

    public Mala_Premium (int Id_proprietario, String descricao, String marca, double preco, Transportadora_Premium transportadora, double dimensao, String material, int ano_Lancamento) {
            super (Id_proprietario, descricao, marca, preco, transportadora.getNome(), dimensao, material, ano_Lancamento);
            this.setPrecoFinal(this.calculaPreco(0));
    }

    public Mala_Premium (Mala_Premium mala_premium) {
        super (mala_premium);
        this.setPrecoFinal(mala_premium.getPrecoFinal());
    }

    public Mala_Premium clone() {
        return new Mala_Premium(this);
    }

    public double calculaPreco(double desconto) {
        double preco_Final = this.getPrecoBase() - this.getDimensao() + (Menu.getCurrentDate().getYear() - this.getAnoLancamento()) * 10;
        return Math.round(preco_Final * 100.0) / 100.0;
    }

    public void recalculaPreco () {
        this.setPrecoFinal(this.calculaPreco(0));
    }

}
