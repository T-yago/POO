package Code;
import java.io.Serializable;



public class Utilizador implements Serializable {
    private static int numero_Utilizadores = 0;
    private int id;
    private String email;
    private String nome;
    private String morada;
    private int numero_Fiscal;

    public Utilizador() {
        this.id = numero_Utilizadores++;
        this.email = null;
        this.nome = null;
        this.morada = null;
        this.numero_Fiscal = -1;
    }

    public Utilizador(String email, String nome, String morada, int numero_Fiscal) {
        this.id = ++numero_Utilizadores;
        this.email = email;
        this.nome = nome;
        this.morada = morada;
        this.numero_Fiscal = numero_Fiscal;
    }

    public Utilizador(Utilizador utilizador) {
        this.id = utilizador.id;
        this.email = utilizador.email;
        this.nome = utilizador.nome;
        this.morada = utilizador.morada;
        this.numero_Fiscal = utilizador.numero_Fiscal;
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

    /*

    public Map<String, Artigo> getComprados() {
        return this.comprados;
    }

    public Map<String, Artigo> getVendidos() {
        return this.vendidos;
    }

    public Map<String, Artigo> getStock() {
        return this.stock;
    }

    */

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
        return "Id: " + this.id + "\nNome: " + this.nome + "\nMorada: " + this.morada + "\nNumero Fiscal: " + this.numero_Fiscal + "\nEmail: " + this.email;
    }

    public Utilizador clone() {
        return new Utilizador(this);
    }
}
