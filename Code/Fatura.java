package Code;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public abstract class Fatura {
    private int codigo;
    private static int counter = 1;
    private LocalDate data;
    private Utilizador comprador;



    public Fatura() {
        this.codigo = counter++;
        this.data = LocalDate.now();
        this.comprador = null;
    }

    public Fatura(Utilizador comprador) {
        this.codigo = counter;
        counter++;
        this.comprador = comprador;
        this.data = LocalDate.now();
    }

    public Fatura(Fatura fatura) {
        this.codigo = counter;
        counter++;     // tinhamos decidido isto?
        this.comprador = fatura.comprador;
        this.data = fatura.data;

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

    @Override
    public String toString() {
        return ("\nFatura: " + codigo + "\nData:" + data + "\nComprador:" + comprador);
    }

    public abstract Fatura clone();

    public abstract double getPrecoTotal();

}
