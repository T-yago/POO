package Code;

import java.io.Serializable;

public class Vintage implements Serializable {
    private Utilizadores utilizadores;
    private Artigos artigos;
    private Encomendas encomendas;
    private Transportadoras transportadoras;
    private double taxaGarantia;

    public Vintage() {
        this.utilizadores = new Utilizadores();
        this.artigos = new Artigos();
        this.encomendas = new Encomendas();
        this.transportadoras = new Transportadoras();
        this.taxaGarantia = 0;
    }

    public Vintage(Utilizadores utilizadores, Artigos artigos, Encomendas encomedas, Transportadoras transportadoras, double taxaGarantia) {
        this.utilizadores = utilizadores.clone();
        this.artigos = artigos.clone();
        this.encomendas = encomedas.clone();
        this.transportadoras = transportadoras.clone();
        this.taxaGarantia = taxaGarantia;
    }

    public Vintage(Vintage vintage) {
        this.utilizadores = vintage.utilizadores.clone();
        this.artigos = vintage.artigos.clone();
        this.encomendas = vintage.encomendas.clone();
        this.transportadoras = vintage.transportadoras.clone();
        this.taxaGarantia = vintage.taxaGarantia;
    }

    public void setTaxaGarantia (double taxa) {
        this.taxaGarantia = taxa;
    }

    public double getTaxaGarantia () {
        return this.taxaGarantia;
    }

    public void setUtilizadores (Utilizadores utilizadores) {
        this.utilizadores = utilizadores.clone();
    }

    public void setArtigos (Artigos artigos) {
        this.artigos = artigos.clone();
    }

    public void setEncomendas (Encomendas encomendas) {
        this.encomendas = encomendas.clone();
    }

    public void setTransportadoras (Transportadoras transportadoras) {
        this.transportadoras = transportadoras.clone();
    }

    public Utilizadores getUtilizadores() {
        return this.utilizadores.clone();
    }
    
    public Artigos getArtigos() {
        return this.artigos.clone();
    }

    public Encomendas getEncomendas() {
        return this.encomendas.clone();
    }

    public Transportadoras getTransportadoras() {
        return this.transportadoras.clone();
    }

    public String toString() {
        return this.utilizadores.toString() + this.artigos.toString() + this.encomendas.toString() + this.transportadoras.toString();
    }

    public double total_ganho (Encomendas encomendas, Utilizadores utilizadores){
        double preco = 0;
        for (Encomenda encomenda : encomendas.getEncomendas().values()) {
            preco += encomenda.getTaxaSatisfacao();
        }
        for (Utilizador user : utilizadores.getUtilizadores().values()) {
            for (Fatura fatura : user.faturas) {
                if (fatura instanceof Fatura_Comprador) {
                    Fatura_Comprador faturaComprador = (Fatura_Comprador) fatura;
                    preco += faturaComprador.getPrecoTotal() * taxaGarantia * 0.1;
                }
            }
        }
        return preco;
    }

    public Vintage clone() {
        return new Vintage(this);
    }

}
