package Code;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import Code.Artigo;
import Code.Artigos;
import Code.Utilizador;

public class Fatura_Vendedor extends Fatura {
    private Utilizador vendedor;
    private List<Artigo> artigos;

    public Fatura_Vendedor() {
        super ();
        this.vendedor = new Utilizador();
        this.artigos = new ArrayList<>();
    }

    public Fatura_Vendedor(Utilizador comprador, Utilizador vendedor, List<Artigo> artigos, String id_Encomenda) {
        super (comprador, id_Encomenda);
        this.vendedor = vendedor.clone();
        this.artigos = new ArrayList<>();
        for (Artigo artigo : artigos) {
            this.artigos.add(artigo);
        }
    }

    public Fatura_Vendedor(Fatura_Vendedor fatura_Vendedor) {
        super ();
        this.vendedor = fatura_Vendedor.vendedor.clone();
        this.artigos = new ArrayList<>();
        for (Artigo artigo : fatura_Vendedor.artigos) {
            this.artigos.add(artigo);
        }
    }

    public Utilizador getVendedor() {
        return this.vendedor.clone();
    }

    public void setVendedor(Utilizador vendedor) {
        this.vendedor = vendedor.clone();
    }

    public List<Artigo> getArtigos() {
        List<Artigo> artigosClone = new ArrayList<>();
        for (Artigo artigo : artigos) {
            artigosClone.add(artigo.clone());
        }
        return artigosClone;
    }

    public void setArtigos(List<Artigo> artigos) {
        this.artigos = new ArrayList<>();
        for (Artigo artigo : artigos) {
            this.artigos.add(artigo.clone());
        }
    }

    @Override
    public String toString() {
        String r = super.toString() + "\nVendedor: " + vendedor + "\nArtigos: ";
        for (Artigo artigo : this.artigos) {
            r = r + "- " + artigo.getCodigo() + ": " + artigo.getDescricao() + " - " + artigo.getPrecoFinal();
        }
        return r;
    }
    

    public void adicionarArtigo(Artigo artigo) {
        artigos.add(artigo);
    }

    public double getPrecoTotal () {
        double total = 0;
        for (Artigo artigo : artigos) {
            total += artigo.getPrecoFinal();
        }
        return total;
    }

    public Fatura_Vendedor clone () {
        return new Fatura_Vendedor(this);
    }

}