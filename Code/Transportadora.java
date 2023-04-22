package Code;

import java.io.Serializable;

public class Transportadora implements Serializable {
    private String nome;
    private double preco_base_pequena;
    private double preco_base_media;
    private double preco_base_grande;
    private boolean is_premium;

    private double imposto;

    public Transportadora () {
        this.nome = null;
        this.preco_base_pequena = 0;
        this.preco_base_media = 0;
        this.preco_base_grande = 0;
        this.imposto = 0;
        this.is_premium = false;
    }

    public Transportadora(String nome, double preco_base_pequena, double preco_base_media, double preco_base_grande,boolean isPremium, double imposto) {
        this.nome = nome;
        this.preco_base_pequena = preco_base_pequena;
        this.preco_base_media = preco_base_media;
        this.preco_base_grande = preco_base_grande;
        this.imposto = imposto;
        this.is_premium = isPremium;
    }

    public Transportadora(Transportadora transportadora) {
        this.nome = transportadora.nome;
        this.preco_base_pequena = transportadora.preco_base_pequena;
        this.preco_base_media = transportadora.preco_base_media;
        this.preco_base_grande = transportadora.preco_base_grande;
        this.imposto = transportadora.imposto;
        this.is_premium = transportadora.is_premium;
    }

    public String getNome () {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPrecoBasePequena() {
        return preco_base_pequena;
    }
    
    public void setPrecoBasePequena(double preco_base_pequena) {
        this.preco_base_pequena = preco_base_pequena;
    }
    
    public double getPrecoBaseMedia() {
        return preco_base_media;
    }
    
    public void setPrecoBaseMedia(double preco_base_media) {
        this.preco_base_media = preco_base_media;
    }
    
    public double getPrecoBaseGrande() {
        return preco_base_grande;
    }
    
    public void setPrecoBaseGrande(double preco_base_grande) {
        this.preco_base_grande = preco_base_grande;
    }
    
    public double getImposto() {
        return imposto;
    }
    
    public void setImposto(double imposto) {
        this.imposto = imposto;
    }

    public double calculaPreco (int numero_itens, double margemlucro) {
        double valor_base;
        if (numero_itens == 1) {
            valor_base = preco_base_pequena;
        } else if (numero_itens >= 2 && numero_itens <= 5) {
            valor_base = preco_base_media;
        } else {
            valor_base = preco_base_grande;
        }

        //if (this.is_premium && encomenda.tem_premium()) valor_base *= 1.5; // premium fee

        double preco_expedicao = valor_base * margemlucro * (1 + this.imposto) * 0.9;
        return preco_expedicao;
    }
 

    public Transportadora clone () {
        return new Transportadora(this);
    }

    public String toString() {
        return "Transportadora [preco_base_pequena=" + preco_base_pequena + ", preco_base_media=" + preco_base_media
                + ", preco_base_grande=" + preco_base_grande + ", imposto=" + imposto + "]";
    }
}