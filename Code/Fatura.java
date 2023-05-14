package Code;

import java.io.Serializable;
import java.time.LocalDate;


public abstract class Fatura implements Serializable {
    private int codigo;
    String id_Encomenda; // para devolucoes
    private static int counter = 1;
    private LocalDate data;
    private Utilizador comprador;



    public Fatura() {
        this.codigo = counter++;
        this.data = LocalDate.now();
        this.comprador = null;
        this.id_Encomenda = null;
    }

    public Fatura(Utilizador comprador, String id_Encomenda) {
        this.codigo = counter;
        counter++;
        this.comprador = comprador;
        this.data = LocalDate.now();
        this.id_Encomenda = id_Encomenda;
    }

    public Fatura(Fatura fatura) {
        this.codigo = counter;
        counter++;     // tinhamos decidido isto?
        this.comprador = fatura.comprador;
        this.data = fatura.data;
        this.id_Encomenda = fatura.id_Encomenda;

    }

    public int getCodigo() {
        return codigo;
    }

    public String getIdEncomenda () {
        return this.id_Encomenda;
    }

    public LocalDate getData() {
        return data;
    }

    public Utilizador getComprador() {
        return comprador;
    }

    @Override
    public String toString() {
        return ("\nFatura: " + codigo + "\nData:" + data + "\nComprador:" + comprador);
    }

    public abstract Fatura clone();

    public abstract double getPrecoTotal();

}
