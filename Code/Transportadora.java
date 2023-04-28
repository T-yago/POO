package Code;

import java.io.Serializable;

public class Transportadora implements Serializable {
    private String nome;
    private double preco_base_pequena;
    private double preco_base_media;
    private double preco_base_grande;
    private double margemlucro;
    private int dias_envio;
    private int dias_devolucao;
    private int dias_preparacao_encomenda;

    private double imposto;

    public Transportadora () {
        this.nome = null;
        this.preco_base_pequena = 0;
        this.preco_base_media = 0;
        this.preco_base_grande = 0;
        this.imposto = 0;
        this.margemlucro = 1;
        this.dias_preparacao_encomenda = 0;
        this.dias_envio = 0;
        this.dias_devolucao = 0;
    }

    public Transportadora(String nome, double preco_base_pequena, double preco_base_media, double preco_base_grande, double imposto, double margemlucro, int dias_preparacao_encomenda, int dias_envio, int dias_devolucao) {
        this.nome = nome;
        this.preco_base_pequena = preco_base_pequena;
        this.preco_base_media = preco_base_media;
        this.preco_base_grande = preco_base_grande;
        this.imposto = imposto;
        this.margemlucro = margemlucro;
        this.dias_preparacao_encomenda = dias_preparacao_encomenda;
        this.dias_envio = dias_envio;
        this.dias_devolucao = dias_devolucao;
    }

    public Transportadora(Transportadora transportadora) {
        this.nome = transportadora.nome;
        this.preco_base_pequena = transportadora.preco_base_pequena;
        this.preco_base_media = transportadora.preco_base_media;
        this.preco_base_grande = transportadora.preco_base_grande;
        this.imposto = transportadora.imposto;
        this.margemlucro = transportadora.margemlucro;
        this.dias_preparacao_encomenda = transportadora.dias_preparacao_encomenda;
        this.dias_envio = transportadora.dias_envio;
        this.dias_devolucao = transportadora.dias_devolucao;
    }


    public String getNome () {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getMargemLucro () {
        return this.margemlucro;
    }

    public void setMargemLucro(double margemlucro) {
        this.margemlucro = margemlucro;
    }

    public double getPrecoBasePequena() {
        return preco_base_pequena;
    }
    
    public void setPrecoBasePequena(double preco_base_pequena) {
        this.preco_base_pequena = preco_base_pequena;
    }
    
    public double getPrecoBaseMedia() {
        return preco_base_media;
    }
    
    public void setPrecoBaseMedia(double preco_base_media) {
        this.preco_base_media = preco_base_media;
    }
    
    public double getPrecoBaseGrande() {
        return preco_base_grande;
    }
    
    public void setPrecoBaseGrande(double preco_base_grande) {
        this.preco_base_grande = preco_base_grande;
    }
    
    public double getImposto() {
        return imposto;
    }
    
    public void setImposto(double imposto) {
        this.imposto = imposto;
    }

    public int getDiasEnvio() {
        return dias_envio;
    }
    
    public void setDiasEnvio(int dias_envio) {
        this.dias_envio = dias_envio;
    }
    
    public int getDiasDevolucao() {
        return dias_devolucao;
    }
    
    public void setDiasDevolucao(int dias_devolucao) {
        this.dias_devolucao = dias_devolucao;
    }
    
    public int getDiasPreparacaoEncomenda() {
        return dias_preparacao_encomenda;
    }
    
    public void setDiasPreparacaoEncomenda(int dias_preparacao_encomenda) {
        this.dias_preparacao_encomenda = dias_preparacao_encomenda;
    }
    

    public double calculaPreco (int numero_itens) {
        double valor_base;
        if (numero_itens == 1) {
            valor_base = preco_base_pequena;
        } else if (numero_itens >= 2 && numero_itens <= 5) {
            valor_base = preco_base_media;
        } else {
            valor_base = preco_base_grande;
        }

        double preco_expedicao = valor_base * (1+margemlucro) * (1 + this.imposto) * 0.9;
        return preco_expedicao;
    }
 

    public Transportadora clone () {
        return new Transportadora(this);
    }

    public String toString() {
        return "Transportadora{" +
                "\n  nome='" + nome + '\'' +
                "\n  preco_base_pequena=" + preco_base_pequena +
                "\n  preco_base_media=" + preco_base_media +
                "\n  preco_base_grande=" + preco_base_grande +
                "\n  margemlucro=" + margemlucro +
                "\n  dias_envio=" + dias_envio +
                "\n  dias_devolucao=" + dias_devolucao +
                "\n  dias_preparacao_encomenda=" + dias_preparacao_encomenda +
                "\n  imposto=" + imposto +
                "\n}";
    }
    
    
}