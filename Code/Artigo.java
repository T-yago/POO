package Code;

import java.io.Serializable;

public abstract class Artigo implements Serializable {
    private String descricao;
    private String marca;
    private static int codigo_counter = 1;
    private String codigo;
    private double preco_Base;
    private double preco_Final;
    private String transportadora;

    public Artigo() {
        this.descricao = "";
        this.marca = "";
        this.codigo = "" + String.format("%06", codigo_counter++);
        this.preco_Base = 0;
        this.transportadora = "";
    }

    public Artigo(int Id_proprietario, String descricao, String marca, double preco, String transportadora) {
        
        this.descricao = descricao;
        this.marca = marca;
        this.codigo = String.format("%06d", Id_proprietario) + String.format("%06d", codigo_counter++);
        this.preco_Base = preco;
        this.transportadora = transportadora;
    }

    public Artigo(Artigo artigo) {
        this.descricao = artigo.descricao;
        this.marca = artigo.marca;
        this.codigo = artigo.codigo;
        this.preco_Base = artigo.preco_Base;
        this.preco_Final = artigo.preco_Final;
        this.transportadora = artigo.transportadora;
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

    public String getTransportadora() {
        return this.transportadora;
    }

    public int getIdVendedor() {
        return Integer.parseInt(this.codigo.substring(this.codigo.length()-12, this.codigo.length()-6));
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

    public void setTransportadora(String transportadora) {
        this.transportadora = transportadora;
    }

    public void setNumero_Artigos(int numero_Artigos) {
        Artigo.codigo_counter = numero_Artigos;
    }

    @Override
    public String toString() {
        return "Código: " + getCodigo() +
                "\nDescrição: " + getDescricao() +
                "\nMarca: " + getMarca() +
                "\nPreço base: " + getPrecoBase() +
                "\nPreço final: " + getPrecoFinal() +
                "\nTransportadora: " + getTransportadora();
    }


    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if ((o == null) || !(o instanceof Artigo)) {
            return false;
        }

        Artigo a = (Artigo) o;
        return (a.codigo == this.codigo);
    }

    public abstract Artigo clone();
    public abstract double calculaPreco(double desconto);
}
