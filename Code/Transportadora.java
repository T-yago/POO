package Code;

import java.io.Serializable;

public class Transportadora implements Serializable {
    private String nome;
    private double preco_base_pequena;
    private double preco_base_media;
    private double preco_base_grande;
    private double margemlucro;

    private double imposto;

    public Transportadora () {
        this.nome = null;
        this.preco_base_pequena = 0;
        this.preco_base_media = 0;
        this.preco_base_grande = 0;
        this.imposto = 0;
        this.margemlucro = 1;
    }

    public Transportadora(String nome, double preco_base_pequena, double preco_base_media, double preco_base_grande, double imposto, double margemlucro) {
        this.nome = nome;
        this.preco_base_pequena = preco_base_pequena;
        this.preco_base_media = preco_base_media;
        this.preco_base_grande = preco_base_grande;
        this.imposto = imposto;
        this.margemlucro = margemlucro;
    }

    public Transportadora(Transportadora transportadora) {
        this.nome = transportadora.nome;
        this.preco_base_pequena = transportadora.preco_base_pequena;
        this.preco_base_media = transportadora.preco_base_media;
        this.preco_base_grande = transportadora.preco_base_grande;
        this.imposto = transportadora.imposto;
        this.margemlucro = transportadora.margemlucro;
    }

    public String getNome () {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getMargemLucro () {
        return this.margemlucro;
    }

    public void setMargemLucro(double margemlucro) {
        this.margemlucro = margemlucro;
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

    public double calculaPreco (int numero_itens) {
        double valor_base;
        if (numero_itens == 1) {
            valor_base = preco_base_pequena;
        } else if (numero_itens >= 2 && numero_itens <= 5) {
            valor_base = preco_base_media;
        } else {
            valor_base = preco_base_grande;
        }

        double preco_expedicao = valor_base * margemlucro * (1 + this.imposto) * 0.9;
        return preco_expedicao;
    }
 

    public Transportadora clone () {
        return new Transportadora(this);
    }

    public String toString() {
        return "Transportadora: " + this.nome +  "\npreco_base_pequena=" + preco_base_pequena + "\npreco_base_media=" + preco_base_media
                + "\npreco_base_grande=" + preco_base_grande + "\nimposto=" + imposto + "\n\n";
    }
}