package Code;

public class T_Shirt_Usada extends T_Shirt{
    
    public T_Shirt_Usada() {
        super();
    }

    public T_Shirt_Usada(String proprietario, String descricao, String marca, double preco, int tamanho, int padrao) {
        super(proprietario, descricao, marca, preco, tamanho, padrao);
        this.setPrecoFinal(this.calculaPreco(preco, 0, 0));
    }

    public T_Shirt_Usada(T_Shirt_Usada t_Shirt_Usada) {
        super(t_Shirt_Usada);
        this.setPrecoFinal(this.calculaPreco(t_Shirt_Usada.getPrecoBase(), 0, 0));
    }

    public double calculaPreco(double preco_Base, int b, int c) {
        if (this.getPadrao() != 0) {
            return preco_Base * 0.50;
        }
        return preco_Base;
    }
}
