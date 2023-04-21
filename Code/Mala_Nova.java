package Code;

import java.io.Serializable;

public class Mala_Nova extends Mala implements Serializable {


    public Mala_Nova() {
        super();
        this.setPrecoFinal(0);
    }

    public Mala_Nova (int Id_proprietario, String descricao, String marca, double preco, double dimensao, String material, int ano_Lancamento) {
        super (Id_proprietario,descricao,marca,preco,dimensao,material,ano_Lancamento);
        this.setPrecoFinal(this.calculaPreco(0));
    }

    public Mala_Nova (Mala_Nova mala_Nova) {
        super (mala_Nova);
        this.setPrecoFinal(mala_Nova.getPrecoFinal());
    }

    public Mala_Nova clone () {
        return new Mala_Nova(this);
    }

    public double calculaPreco(double desconto) { 
        return this.getPrecoBase();
    }

}
