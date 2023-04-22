package Code;

import java.io.Serializable;

public class Transportadora_Premium extends Transportadora implements Serializable {

    public Transportadora_Premium () {
        super();
    }

    public Transportadora_Premium (String nome, double preco_base_pequena, double preco_base_media, double preco_base_grande, double imposto) {
        super(nome,preco_base_pequena,preco_base_media,preco_base_grande,imposto);
    }

    public Transportadora_Premium (Transportadora_Premium transportadora_Premium) {
        super (transportadora_Premium);
    }

    public double calculaPreco (int numero_itens, double margemlucro) {
        double preco_super = super.calculaPreco(numero_itens, margemlucro);
        return preco_super * 1.5;    
    }

    public Transportadora_Premium clone() {
        return new Transportadora_Premium();
    }

}