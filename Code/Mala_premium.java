package Code;

import java.time.*;

public class Mala_premium extends Mala {
    
    public Mala_premium () {
        super ();
        this.setPrecoFinal(0);
    }

    public Mala_premium (String proprietario, String descricao, String marca, double preco, double dimensao, String material, int ano_Lancamento) {
        super (proprietario,descricao,marca,preco,dimensao,material,ano_Lancamento);
        this.setPrecoFinal(this.calculaPreco(0));
    }

    public Mala_premium (Mala_premium mala_premium) {
        super (mala_premium);
        this.setPrecoFinal(mala_premium.getPrecoFinal());
    }


    public String toString () {
        return super.toString(); // desta forma não há diferença entre malas novas e premium -> Imprimir getclass().getname()
    }

    public Mala_premium clone() {
        return new Mala_premium(this);
    }

    public double calculaPreco(double desconto) { 
        return this.getPrecoBase() + (1/this.getDimensao()) * (this.getPrecoBase() / (double)this.getAnoLancamento() - LocalDate.now().getYear());
    }

}