package Code;

public class T_Shirt_Nova extends T_Shirt implements Novos {

    public T_Shirt_Nova() {
        super();
        this.setPrecoFinal(0);
    }

    public T_Shirt_Nova(int Id_proprietario, String descricao, String marca, double preco, String transportadora, int tamanho, int padrao) {
        super(Id_proprietario, descricao, marca, preco, transportadora, tamanho, padrao);
        this.setPrecoFinal(this.calculaPreco(0));
    }

    public T_Shirt_Nova(T_Shirt_Nova t_Shirt_Usada) {
        super(t_Shirt_Usada);
        this.setPrecoFinal(this.getPrecoFinal());
    }

    public double calculaPreco(double desconto) {
        return this.getPrecoBase();
    }

    public void recalculaPreco () {
        this.setPrecoFinal(this.calculaPreco(0));
    }

    public T_Shirt_Nova clone() {
        return new T_Shirt_Nova(this);
    }
}
