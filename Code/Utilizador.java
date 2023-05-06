package Code;
import java.util.*;
import java.io.Serializable;




public class Utilizador implements Serializable {
    private static int numero_Utilizadores = 0;
    private int id;
    private String email;
    private String nome;
    private String morada;
    private int numero_Fiscal;
    public List<Fatura> faturas;
    

    public Utilizador() {
        this.id = numero_Utilizadores++;
        this.email = null;
        this.nome = null;
        this.morada = null;
        this.numero_Fiscal = -1;
        this.faturas = new ArrayList<>();
    }

    public Utilizador(String email, String nome, String morada, int numero_Fiscal) {
        this.id = ++numero_Utilizadores;
        this.email = email;
        this.nome = nome;
        this.morada = morada;
        this.numero_Fiscal = numero_Fiscal;
        this.faturas = new ArrayList<>();
    }

    public Utilizador(Utilizador utilizador) {
        this.id = utilizador.id;
        this.email = utilizador.email;
        this.nome = utilizador.nome;
        this.morada = utilizador.morada;
        this.numero_Fiscal = utilizador.numero_Fiscal;
        this.faturas = new ArrayList<>();
        for (Fatura fatura : utilizador.faturas) {
            this.faturas.add(fatura);
        }
    }

    public int getId() {
        return this.id;
    }

    public String getEmail() {
        return this.email;
    }

    public String getNome() {
        return this.nome;
    }

    public String getMorada() {
        return this.morada;
    }

    public int getNumeroFiscal() {
        return this.numero_Fiscal;
    }

    public List<Fatura> getFaturas() {
        return new ArrayList<>(faturas); 
    }

    public void setFaturas(List<Fatura> faturas) {
        this.faturas = new ArrayList<>();
        for (Fatura fatura : faturas) {
            this.faturas.add(fatura.clone()); 
        }
    }

    public void addFatura (Fatura fatura) {
        this.faturas.add (fatura);
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }

    public void setNumeroFiscal(int numeroFiscal) {
        this.numero_Fiscal = numeroFiscal;
    }

    public void setNumero_Utilizadores(int numero_Utilizadores) {
        Utilizador.numero_Utilizadores = numero_Utilizadores;
    }

    public String toString() {
        return "Id: " + this.id + "\nNome: " + this.nome + "\nMorada: " + this.morada + "\nNumero Fiscal: " + this.numero_Fiscal + "\nEmail: " + this.email + "\nFaturas: " + this.faturas.toString();
    }

    public Utilizador clone() {
        return new Utilizador(this);
    }
}
