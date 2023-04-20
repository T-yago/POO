package Code;

public class Sapatilha_Nova extends Sapatilha{

    public Sapatilha_Nova() {
        super();
        this.setPrecoFinal(0);
    }

    public Sapatilha_Nova(int Id_proprietario, String descricao, String marca, double preco, int tamanho, boolean atacadores, String cor, int ano_Lancamento, int desconto) {
        super(Id_proprietario, descricao, marca, preco, tamanho, atacadores, cor, ano_Lancamento, desconto);
        this.setPrecoFinal(this.calculaPreco(desconto));
    }

    public Sapatilha_Nova(Sapatilha_Nova sapatilha_Nova) {
        super(sapatilha_Nova);
        this.setPrecoFinal(getPrecoBase());
    }

    public double calculaPreco(double desconto) {
        if (this.getTamanho()>45) return this.getPrecoBase() * desconto/100;
        return this.getPrecoBase();
    }

    // public String toString() {
    //     String result = "";
    //     result = result + "Artigo: " + this.getClass().getName() 
    //               + "Tamanho: " + this.getTamanho()
    //               + "Atacores: " + (this.getAtacadores() == true ? "Sim" : "Não")
    //               + "Cor: " + this.getCor()
    //               + "Ano lançamento: " + this.getAnoLancamento()
    //               + "Desconto: " + this.getDesconto();
    //     return result;
    // }

    public Sapatilha_Nova clone() {
        return new Sapatilha_Nova(this);
    }
}
