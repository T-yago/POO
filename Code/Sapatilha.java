package Code;

public abstract class Sapatilha extends Artigo {
    private int tamanho;
    private boolean atacadores;
    private String cor;
    private int ano_Lancamento;
    private int desconto;

    public Sapatilha() {
        super();
        this.tamanho = -1;
        this.atacadores = false;
        this.cor = "";
        this.ano_Lancamento = 0;
        this.desconto = 0;
    }

    public Sapatilha(int Id_proprietario, String descricao, String marca, double preco, int tamanho, boolean atacadores, String cor, int ano_Lancamento, int desconto) {
        super(Id_proprietario, descricao, marca, preco);
        this.tamanho = tamanho;
        this.atacadores = atacadores;
        this.cor = cor;
        this.ano_Lancamento = ano_Lancamento;
        this.desconto = desconto;
        
    }

    public Sapatilha(Sapatilha sapatilha) {
        super(sapatilha);
        this.tamanho = sapatilha.tamanho;
        this.atacadores = sapatilha.atacadores;
        this.cor = sapatilha.cor;
        this.ano_Lancamento = sapatilha.ano_Lancamento;
        this.desconto = sapatilha.desconto;
    }

    public int getTamanho() {
        return this.tamanho;
    }

    public boolean getAtacadores() {
        return this.atacadores;
    }

    public String getCor() {
        return this.cor;
    }

    public int getAnoLancamento() {
        return this.ano_Lancamento;
    }

    public int getDesconto() {
        return this.desconto;
    }

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }

    public void setAtacadores(boolean atacadores) {
        this.atacadores = atacadores;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public void setAnoLancamento(int ano_Lancamento) {
        this.ano_Lancamento = ano_Lancamento;
    }

    public void setDesconto(int desconto) {
        this.desconto = desconto;
    }

    public  String toString() {
        return super.toString()
        + "\nTamanho: " + this.getTamanho()
        + "\nAtacores: " + (this.getAtacadores() == true ? "Sim" : "Não")
        + "\nCor: " + this.getCor()
        + "\nAno lançamento: " + this.getAnoLancamento()
        + "\nDesconto: " + this.getDesconto();
    }


    public abstract Artigo clone();

}
