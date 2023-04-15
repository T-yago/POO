package Code;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

public class Encomenda {
    private Collection<Artigo> artigos;
    private static final int PEQUENA = 0;
    private static final int MEDIA = 1;
    private static final int GRANDE = 2;
    private int tamanho;
    private double preco;
    private static final int PENDENTE = 0;
    private static final int FINALIZADA = 1;
    private static final int EXPEDIDA = 2;
    private int estado_Encomenda;
    private LocalDate data;

    public Encomenda() {
        this.artigos = new ArrayList<>();
        this.tamanho = PEQUENA;
        // this.preco = 
        this.estado_Encomenda = PENDENTE;
        this.data = LocalDate.now();
    }

    public Encomenda(Collection<Artigo> artigos, int estado_Encomenda, LocalDate date) {
        this.artigos = new ArrayList<>();

        /*
        Fazer a funcao que clona os artigos

        for (Artigos a: artigos) {
            this.artigos.add(a.clone());
        }

        */

        if (artigos.size()==1) this.tamanho = PEQUENA;
        else if (artigos.size()>5) this.tamanho = GRANDE;
        else this.tamanho = MEDIA;

        // this.preco = 
        this.estado_Encomenda = estado_Encomenda;
        this.data = date;
    }

    public Encomenda(Encomenda encomenda) {
        this.artigos = new ArrayList<>();

        /*
        Fazer a funcao que clona os artigos

        for (Artigos a: encomenda.artigos) {
            this.artigos.add(a.clone());
        }

        */

        this.tamanho = encomenda.tamanho;
        this.preco = encomenda.preco;
        this.estado_Encomenda = encomenda.estado_Encomenda;
        this.data = encomenda.data;
    }

    public int getTamanho() {
        return this.tamanho;
    }

    public double getPreco() {
        return this.preco;
    }

    public int getEstadoEncomenda() {
        return this.estado_Encomenda;
    }

    public LocalDate getData() {
        return this.data;
    }

    public void setEstadoEncomenda(int estado_Encomenda) {
        this.estado_Encomenda = estado_Encomenda;
    }

    public void addArtigo(Artigo artigo) {
        this.artigos.add(artigo.clone());
    }

    public void removeArtigo(String id) {
        this.artigos.remove(id);
    }
}
