package Code;

public class Transportadora_Premium extends Transportadora {

    public Transportadora_Premium () {
        super();
    }

    public Transportadora_Premium (String nome, double preco_base_pequena, double preco_base_media, double preco_base_grande, double imposto, double margemlucro, int dias_preparacao_encomenda, int dias_envio, int dias_devolucao) {
        super(nome,preco_base_pequena,preco_base_media,preco_base_grande,imposto, margemlucro,dias_preparacao_encomenda,dias_envio,dias_devolucao);
    }

    public Transportadora_Premium (Transportadora_Premium transportadora_Premium) {
        super (transportadora_Premium);
    }

    public double calculaPreco (int numero_itens) {
        double preco_super = super.calculaPreco(numero_itens);
        return preco_super * 1.5;    
    }

    public Transportadora_Premium clone() {
        return new Transportadora_Premium(this);
    }

    public String toString () {
        String r = "[Premium]";
        r+= super.toString();
        return r;
    }

}
