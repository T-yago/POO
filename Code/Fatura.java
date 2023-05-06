package Code;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import Code.Artigo;

public class Fatura {
    private int codigo;
    private static int counter = 1;
    private LocalDate data;
    private Utilizador comprador;
    private Utilizador vendedor;
    private List<Artigo> artigos;
    private double precoTotal;

    public Fatura() {
        this.codigo = counter++;
        this.data = LocalDate.now();
        this.comprador = null;
        this.vendedor = null;
        this.artigos = new ArrayList<>();
        this.precoTotal = 0.0;
    }

    public Fatura(Utilizador comprador, Utilizador vendedor, List<Artigo> artigos) {
        this.codigo = counter;
        counter++;
        this.comprador = comprador;
        this.vendedor = vendedor;
        this.data = LocalDate.now();
        for (Artigo artigo : artigos) {
            this.artigos.add(artigo);
        }
        this.precoTotal = 0;
    }

    public Fatura(Fatura fatura) {
        this.codigo = counter;
        counter++;     // tinhamos decidido isto?
        this.comprador = fatura.comprador;
        this.vendedor = fatura.vendedor;
        this.data = fatura.data;
        for (Artigo artigo : fatura.artigos) {
            this.artigos.add(artigo);
        }
        this.precoTotal = fatura.precoTotal;

    }

    public int getCodigo() {
        return codigo;
    }

    public LocalDate getData() {
        return data;
    }

    public Utilizador getComprador() {
        return comprador;
    }

    public Utilizador getVendedor() {
        return vendedor;
    }

    public void adicionarArtigo(Artigo artigo) {
        artigos.add(artigo);
        precoTotal += artigo.getPrecoFinal();
    }

    public double getPrecoTotal() {
        return precoTotal;
    }

    public void imprimirFatura() {
        System.out.println("Fatura " + codigo);
        System.out.println("Data: " + data);
        System.out.println("Comprador: " + comprador.getNome());
        System.out.println("Vendedor: " + vendedor.getNome());
        System.out.println("Artigos:");

        for (Artigo artigo : artigos) {
            System.out.println("- " + artigo.getDescricao() + " - " + artigo.getPrecoFinal());
        }

        System.out.println("Preço total: " + precoTotal);
    }

    public Fatura clone () {
        return new Fatura(this);
    }
}
