package Code;
import java.util.HashMap;
import java.util.Map;



public class Utilizador {
    private static int numero_Utilizadores = 0;
    private int id;
    private String email;
    private String nome;
    private String morada;
    private int numero_Fiscal;

    private Map<String, Artigo> comprados;
    private Map<String, Artigo> vendidos;
    private Map<String, Artigo> stock;

    public Utilizador() {
        this.id = ++numero_Utilizadores;
        this.email = null;
        this.nome = null;
        this.morada = null;
        this.numero_Fiscal = -1;
        this.comprados = new HashMap<>();
        this.vendidos = new HashMap<>();
        this.stock = new HashMap<>();
    }

    public Utilizador(String email, String nome, String morada, int numero_Fiscal) {
        this.id = ++numero_Utilizadores;
        this.email = null;
        this.nome = null;
        this.morada = null;
        this.numero_Fiscal = -1;
        this.comprados = new HashMap<>();
        this.vendidos = new HashMap<>();
        this.stock = new HashMap<>();
    }

    public Utilizador(Utilizador utilizador) {
        this.id = utilizador.id;
        this.email = utilizador.email;
        this.nome = utilizador.nome;
        this.morada = utilizador.morada;
        this.numero_Fiscal = utilizador.numero_Fiscal;

        // Fazer as funções que clonam os artigos

        // this.comprados = new HashMap<>();
        // this.vendidos = new HashMap<>();
        // this.stock = new HashMap<>();
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

    public Map<String, Artigo> getComprados() {
        return this.comprados;
    }

    public Map<String, Artigo> getVendidos() {
        return this.vendidos;
    }

    public Map<String, Artigo> getStock() {
        return this.stock;
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

    public void addArtigo_Comprado(Artigo artigo) {
        this.comprados.put(artigo.getCodigo(), artigo);
    }

    public void addArtigo_Vendido(Artigo artigo) {
        this.vendidos.put(artigo.getCodigo(), artigo);
    }

    public void addArtigo_Stock(Artigo artigo) {
        this.stock.put(artigo.getCodigo(), artigo);
    }

    public void removeArtigo_Comprado(String artigo) {
        this.comprados.remove(artigo);
    }

    public void removeArtigo_Vendido(String artigo) {
        this.vendidos.remove(artigo);
    }

    public void removeArtigo_Stock(String artigo) {
        this.stock.remove(artigo);
    }

    public void devolucao(String artigo) {
        Artigo a = this.vendidos.get(artigo);
        this.stock.put(artigo, a);
    }
}
