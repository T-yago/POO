package Code;

public abstract class T_Shirt extends Artigo{
    private static final int S = 0;
    private static final int M = 1;
    private static final int L = 2;
    private static final int XL = 3;
    private int tamanho;
    private static final int LISO = 0;
    private static final int RISCAS = 1;
    private static final int PALMEIRAS = 2;
    private int padrao;

    public T_Shirt() {
        super();
        this.tamanho = -1;
        this.padrao = -1;
    }

    public T_Shirt(String proprietario, String descricao, String marca, double preco, int tamanho, int padrao) {
        super(proprietario, descricao, marca, preco);
        this.tamanho = tamanho;
        this.padrao = padrao;
    }

    public T_Shirt(T_Shirt t_shirt) {
        super(t_shirt);
        this.tamanho = t_shirt.tamanho;
        this.padrao = t_shirt.padrao;
    }

    public int getTamanho() {
        return this.tamanho;
    }

    public int getPadrao() {
        return this.padrao;
    }

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }

    public void setPadrao(int padrao) {
        this.padrao = padrao;
    }
}
