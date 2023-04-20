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

    public T_Shirt(int Id_proprietario, String descricao, String marca, double preco, int tamanho, int padrao) {
        super(Id_proprietario, descricao, marca, preco);
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


    @Override
public String toString() {
    return super.toString() 
            + "\nTamanho: " + tamanhoToString(tamanho)
            + "\nPadrão: " + padraoToString(padrao)
            + "\nPreço base: " + getPrecoBase()
            + "\nPreço final: " + getPrecoFinal();
}

private String tamanhoToString(int tamanho) {
    switch (tamanho) {
        case 0:
            return "S";
        case 1:
            return "M";
        case 2:
            return "L";
        case 3:
            return "XL";
        default:
            return "Unknown";
    }
}

private String padraoToString(int padrao) {
    switch (padrao) {
        case 0:
            return "Liso";
        case 1:
            return "Riscas";
        case 2:
            return "Palmeiras";
        default:
            return "Unknown";
    }
}


    public abstract Artigo clone();
}

