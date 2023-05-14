package Code;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Utilizadores implements Serializable {
    Map<Integer, Utilizador> utilizadores;
    
    public Utilizadores() {
        this.utilizadores = new HashMap<>();
    }

    public Utilizadores(Map<Integer, Utilizador> utilizadores) {
        this.utilizadores = new HashMap<>();

        for (Map.Entry<Integer, Utilizador> e: utilizadores.entrySet()) {
            this.utilizadores.put(e.getKey(), e.getValue().clone());
        }
    }

    public Utilizadores(Utilizadores utilizadores) {
        this.utilizadores = new HashMap<>();

        for (Map.Entry<Integer, Utilizador> e: utilizadores.utilizadores.entrySet()) {
            this.utilizadores.put(e.getKey(), e.getValue().clone());
        }
    }

    public Utilizador getUtilizador(int id) {
        return this.utilizadores.get(id);
    }

    public Map<Integer, Utilizador> getUtilizadores() {
        Map<Integer, Utilizador> utilizadores = new HashMap<>();

        for (Map.Entry<Integer, Utilizador> e: this.utilizadores.entrySet()) {
            utilizadores.put(e.getKey(), e.getValue().clone());
        }

        return utilizadores;
    }

    public void addUtilizador(Utilizador utilizador) {
        this.utilizadores.put(utilizador.getId(), utilizador.clone());
    }

    public void removeUtilizador(int id) {
        this.utilizadores.remove(id);
    }

    public boolean existeUtilizador(int id_Utilizador) {
        if (this.utilizadores.containsKey(id_Utilizador)) {
            return true;
        }
        return false;
    }

    public Fatura getFatura (int id_fatura) {
        for (Utilizador utilizador : utilizadores.values()) {
            for (Fatura fatura : utilizador.faturas) {
                if (fatura.getCodigo() == id_fatura) {
                    return fatura;
                }
            }
        }
        System.out.println("\nFatura não existe.");
        return null;
    }

    public void removeFaturas (String id_Encomenda) {

        for (Utilizador utilizador : utilizadores.values()) {
            for (Fatura fatura : utilizador.getFaturas()) {
                System.out.println(fatura.getIdEncomenda());
                if (fatura.getIdEncomenda().equals(id_Encomenda) ) {
                    utilizador.faturas.remove(fatura);
                    System.out.println("removeu3");

                }
            }
        }
    }

    public Utilizador maisFaturou() {
        double max = 0;
        Utilizador maisFaturou = null; 
        for (Utilizador user : this.utilizadores.values()) {
            double total_faturado = 0;
            for (Fatura fatura : user.getFaturas()) {
                if (fatura instanceof Fatura_Vendedor) {
                    Fatura_Vendedor faturaVendedor = (Fatura_Vendedor) fatura;
                    List<Artigo> list_artigos = faturaVendedor.getArtigos();
                    for (Artigo artigo : list_artigos) {
                        total_faturado += artigo.getPrecoFinal();
                    }
                    if (total_faturado > max) {
                        max = total_faturado;
                        maisFaturou = user;
                    }
                }
            }
        }
        return maisFaturou;
    }
    


    public Utilizador maisFaturou (LocalDate inicio, LocalDate fim) {
        double max = 0;
        Utilizador maisFaturou = null;
        for (Utilizador user : this.utilizadores.values()) {
            double total_faturado = 0;
            for (Fatura fatura : user.faturas) {
                if (fatura instanceof Fatura_Vendedor && fatura.getData().isAfter(inicio) && fatura.getData().isBefore(fim)) {
                    Fatura_Vendedor faturaVendedor = (Fatura_Vendedor) fatura;
                    List<Artigo> list_artigos = faturaVendedor.getArtigos();
                    for (Artigo artigo : list_artigos) {
                        total_faturado += artigo.getPrecoFinal();
                    }
                    if (total_faturado > max) {
                        max = total_faturado;
                        maisFaturou = user;
                    }
                }
            }
        }
        return maisFaturou;
    }

    public List<Utilizador> topVendedores (LocalDate inicio, LocalDate fim) {
        Map<Utilizador, Double> faturacoesPorUtilizador = new HashMap<>();
    
        //Calcula o total faturado por cada vendedor
        for (Utilizador user : this.utilizadores.values()) {
            double total_faturado = 0;
            for (Fatura fatura : user.faturas) {
                if (fatura instanceof Fatura_Vendedor && fatura.getData().isAfter(inicio) && fatura.getData().isBefore(fim)) {
                    Fatura_Vendedor faturaVendedor = (Fatura_Vendedor) fatura;
                    List<Artigo> list_artigos = faturaVendedor.getArtigos();
                    for (Artigo artigo : list_artigos) {
                        total_faturado += artigo.getPrecoFinal();
                    }
                }
            }
            faturacoesPorUtilizador.put(user, total_faturado);
        }
        
        //Converte o hashmap numa lista e ordena
        List<Map.Entry<Utilizador, Double>> sortedEntries = new ArrayList<>(faturacoesPorUtilizador.entrySet());
        sortedEntries.sort((Map.Entry.comparingByValue()));
    
        // Muda para uma lista só com os utilizadores
        List<Utilizador> result = new ArrayList<>();
        for (int i = 0; i < this.utilizadores.size() && i < sortedEntries.size(); i++) {
            result.add(sortedEntries.get(i).getKey());
        }
    
        return result;
    }

    public List<Utilizador> topCompradores (LocalDate inicio, LocalDate fim) {
        Map<Utilizador, Double> faturacoesPorUtilizador = new HashMap<>();
    
        //Calcula o total faturado por cada vendedor
        for (Utilizador user : this.utilizadores.values()) {
            double total_faturado = 0;
            for (Fatura fatura : user.faturas) {
                if (fatura instanceof Fatura_Comprador && fatura.getData().isAfter(inicio) && fatura.getData().isBefore(fim)) {
                    Fatura_Comprador fatura_Comprador = (Fatura_Comprador) fatura;
                    Map<Integer,List<Artigo>> vendasPorVendedor = fatura_Comprador.getVendasPorVendedor();
                    for (List<Artigo> vendas : vendasPorVendedor.values()) {
                        for (Artigo artigo : vendas) {
                            total_faturado += artigo.getPrecoFinal();
                        }
                    }
                }
            }
            faturacoesPorUtilizador.put(user, total_faturado);
        }
        
        //Converte o hashmap numa lista e ordena
        List<Map.Entry<Utilizador, Double>> sortedEntries = new ArrayList<>(faturacoesPorUtilizador.entrySet());
        sortedEntries.sort((Map.Entry.comparingByValue()));
    
        // Muda para uma lista só com os utilizadores
        List<Utilizador> result = new ArrayList<>();
        for (int i = 0; i < this.utilizadores.size() && i < sortedEntries.size(); i++) {
            result.add(sortedEntries.get(i).getKey());
        }
    
        return result;
    }
    
/*
    public List<Encomenda> encomendasEmitidasVendedor (Encomendas encomendas, Transportadoras transportadoras) {
        List<Encomendas> encomedasEmitidas = new ArrayList<>();
        for (Utilizador user : this.utilizadores.values()) {
            for (Fatura fatura : user.faturas) {
                if (fatura instanceof Fatura_Vendedor) {
                    String idEncomenda = fatura.getIdEncomenda();
                    Encomendas encomendas = encomendas.getEncomenda (idEncomenda,transportadoras);
                    encomedasEmitidas.add(encomenda);
                }
            }
        }
        return encomedasEmitidas;
    }
     */
    public List<Encomenda> encomendasEmitidasVendedor (Encomendas encomendas, Transportadoras transportadoras) {
        return this.utilizadores.values().stream() 
            .flatMap(user -> user.faturas.stream()) 
            .filter(fatura -> fatura instanceof Fatura_Vendedor)
            .map(Fatura::getIdEncomenda) 
            .flatMap(id -> encomendas.getEncomenda(id, transportadoras).stream()) 
            .collect(Collectors.toList()); 
    }


    public String toString() {
        String s = "";

        for (Utilizador u: this.utilizadores.values()) {
            s += u.toString() + "\n\n";
        }

        return s;
    }

    public Utilizadores clone() {
        return new Utilizadores(this);
    }
}
