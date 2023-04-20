package Code;

public abstract class Mala extends Artigo{
    private double dimensao;
    private String material;
    private int ano_Lancamento;

    public Mala() {
        super();
        this.dimensao = 0;
        this.material = "";
        this.ano_Lancamento = 0;
    }

    public Mala(int Id_proprietario, String descricao, String marca, double preco, double dimensao, String material, int ano_Lancamento) {
        super(Id_proprietario, descricao, marca, preco);
        this.dimensao = dimensao;
        this.material = material;
        this.ano_Lancamento = ano_Lancamento;
    }

    public Mala(Mala mala) {
        super(mala);
        this.dimensao = mala.dimensao;
        this.material = mala.material;
        this.ano_Lancamento = mala.ano_Lancamento;
    }

    public double getDimensao() {
        return this.dimensao;
    }

    public String getMaterial() {
        return this.material;
    }

    public int getAnoLancamento() {
        return this.ano_Lancamento;
    }

    public void setDimensao(int dimensao) {
        this.dimensao = dimensao;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public void setAnoLancamento(int ano_Lancamento) {
        this.ano_Lancamento = ano_Lancamento;
    }

    public String toString() {
        return super.toString() + "\nDimensão: " + dimensao + " cm" +
            "\nMaterial: " + material +
            "\nAno de Lançamento: " + ano_Lancamento;
    }

    public abstract Artigo clone();
}
