package Code;
public abstract class Artigo {
    private String descricao;
    private String marca;
    private static int codigo_counter = 0;
    private String codigo;
    private double preco_Base;
    private double preco_Final;

    public Artigo() {
        this.descricao = "";
        this.marca = "";
        this.codigo = "" + String.format("%06", ++codigo_counter);
        this.preco_Base = 0;
    }

    public Artigo(String proprietario, String descricao, String marca, double preco) {
        this.descricao = descricao;
        this.marca = marca;
        this.codigo = proprietario + String.format("%06", ++codigo_counter);
        this.preco_Base = preco;
    }

    public Artigo(Artigo artigo) {
        this.descricao = artigo.descricao;
        this.marca = artigo.marca;
        this.codigo = artigo.codigo.substring(0, artigo.codigo.length()-6) + String.format("%06", ++codigo_counter);
        this.preco_Base = artigo.preco_Base;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public String getMarca() {
        return this.marca;
    }
    
    public String getCodigo() {
        return this.codigo;
    }

    public double getPrecoBase() {
        return this.preco_Base;
    }

    public double getPrecoFinal() {
        return this.preco_Final;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setMarca(String marca) {
        this.descricao = marca;
    }

    public void setPrecoBase(double preco_Base) {
        this.preco_Base = preco_Base;
    }

    public void setPrecoFinal(double preco_Final) {
        this.preco_Final = preco_Final;
    }

    public abstract double calculaPreco(double a, int b, int c);
}
