package Code;

import java.io.Serializable;

public class Vintage implements Serializable {
    private Utilizadores utilizadores;
    private Artigos artigos;
    private Encomendas encomendas;
    private Transportadoras transportadoras;

    public Vintage() {
        this.utilizadores = new Utilizadores();
        this.artigos = new Artigos();
        this.encomendas = new Encomendas();
        this.transportadoras = new Transportadoras();
    }

    public Vintage(Utilizadores utilizadores, Artigos artigos, Encomendas encomedas, Transportadoras transportadoras) {
        this.utilizadores = utilizadores.clone();
        this.artigos = artigos.clone();
        this.encomendas = encomedas.clone();
        this.transportadoras = transportadoras.clone();
    }

    public Vintage(Vintage vintage) {
        this.utilizadores = vintage.utilizadores.clone();
        this.artigos = vintage.artigos.clone();
        this.encomendas = vintage.encomendas.clone();
        this.transportadoras = vintage.transportadoras.clone();
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

    public Vintage clone() {
        return new Vintage(this);
    }

}
