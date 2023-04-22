package Code;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

public class Encomenda implements Serializable {
    private String codigo;
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
        this.codigo = "";
        this.artigos = new ArrayList<>();
        this.tamanho = PEQUENA;
        this.preco = 0;
        this.estado_Encomenda = PENDENTE;
        this.data = LocalDate.now();
    }

    public Encomenda(Collection<Artigo> artigos, String codigo, int estado_Encomenda, LocalDate date, Transportadoras transportadoras) {
        this.codigo = codigo;
        this.artigos = new ArrayList<>();

        for (Artigo a: artigos) {
            this.artigos.add(a.clone());
        }

        if (artigos.size()==1) this.tamanho = PEQUENA;
        else if (artigos.size()>5) this.tamanho = GRANDE;
        else this.tamanho = MEDIA;

        this.preco = preco(artigos, transportadoras);
        this.estado_Encomenda = estado_Encomenda;
        this.data = date;
    }

    public Encomenda(Encomenda encomenda) {
        this.codigo = encomenda.codigo;
        this.artigos = new ArrayList<>();

        for (Artigo a: encomenda.artigos) {
            this.artigos.add(a.clone());
        }

        this.tamanho = encomenda.tamanho;
        this.preco = encomenda.preco;
        this.estado_Encomenda = encomenda.estado_Encomenda;
        this.data = encomenda.data;
    }

    private double preco(Collection<Artigo> artigos, Transportadoras transportadoras) {
        double preco = 0;

        for (Artigo a: artigos) {
            preco += a.getPrecoFinal();

            if (a instanceof Novos) {
                preco += 0.5;
            }
            else if (a instanceof Usados) {
                preco += 0.25;
            }

            preco += transportadoras.getTransportadora(a.getTransportadora()).calculaPreco(artigos.size(), preco);
        }

        return preco;
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

    public int getIdComprador() {
        return Integer.parseInt(this.codigo.substring(this.codigo.length()-12, this.codigo.length()-6));
    }

    public void setEstadoEncomenda(int estado_Encomenda) {
        this.estado_Encomenda = estado_Encomenda;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public void addArtigo(Artigo artigo, Transportadoras transportadoras) {
        this.artigos.add(artigo.clone());

        this.preco = preco(this.artigos, transportadoras);
    }

    public void addArtigos(Collection<Artigo> artigos, Transportadoras transportadoras) {
        for (Artigo a: artigos) {
            this.artigos.add(a.clone());
        }

        this.preco = preco(this.artigos, transportadoras);
    }

    public void removeArtigo(String id_Artigo, Transportadoras transportadoras) {
        for (Artigo a: this.artigos) {
            if (a.getCodigo().equals(id_Artigo)) {
                this.artigos.remove(a);

                this.preco = preco(this.artigos, transportadoras);
            }
        }
    }

    public boolean tem_Item (Artigo artigo) {
        for (Artigo a: this.artigos) {
            if (a.equals(artigo)) {
                return true;
            }
        }
        return false;
    }

    public Collection<Artigo> getArtigos() {
        Collection<Artigo> artigos = new HashSet<>();

        for (Artigo a: this.artigos) {
            artigos.add(a);
        }

        return artigos;
    }

    public Encomenda clone() {
        return new Encomenda(this);
    }

    public String toString() {
        String encomenda =  "Código: " + this.codigo +
                            "\nTamanho: " + this.tamanho +
                            "\nPreço: " + this.preco +
                            "\nEstado Encomenda: " + this.estado_Encomenda +
                            "\nData: " + this.data +
                            "\nArtigos: \n";

        for (Artigo a: this.artigos) {
            encomenda += a.toString();
        }

        return encomenda;
    }

}
