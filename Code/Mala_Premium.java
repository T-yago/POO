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
        return this.getPrecoBase() + (1/this.getDimensao()) * (this.getPrecoBase() / (double) (Menu.getCurrentDate().getYear() - this.getAnoLancamento()));
    }

}
