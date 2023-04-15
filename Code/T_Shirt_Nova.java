package Code;

public class T_Shirt_Nova extends T_Shirt{

    public T_Shirt_Nova() {
        super();
    }

    public T_Shirt_Nova(String proprietario, String descricao, String marca, double preco, int tamanho, int padrao) {
        super(proprietario, descricao, marca, preco, tamanho, padrao);
        this.setPrecoFinal(preco);
    }

    public T_Shirt_Nova(T_Shirt_Nova t_Shirt_Usada) {
        super(t_Shirt_Usada);
        this.setPrecoFinal(this.getPrecoBase());
    }

    public double calculaPreco(double preco, int b, int c) {
        return preco;
    }
}
