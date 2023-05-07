package Code;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import Code.Artigo;
import Code.Utilizador;

import java.util.HashMap;


public class Fatura_Comprador extends Fatura {
    private Map<Integer, List<Artigo>> vendasPorVendedor;

    public Fatura_Comprador() {
        super();
        this.vendasPorVendedor = new HashMap<>();
    }

    public Fatura_Comprador(Utilizador comprador, Map<Integer, List<Artigo>> vendasPorVendedor, String id_Encomenda) {
        super(comprador.clone(),id_Encomenda);
        this.vendasPorVendedor = new HashMap<>();
        for (Integer id_vendedor : vendasPorVendedor.keySet()) {
            List<Artigo> artigos = vendasPorVendedor.get(id_vendedor);
            this.vendasPorVendedor.put(id_vendedor, new ArrayList<>(artigos));
        }
    }

    public Fatura_Comprador(Fatura_Comprador fatura_Comprador) {
        super (fatura_Comprador);
        for (Integer id_vendedor : fatura_Comprador.vendasPorVendedor.keySet()) {
            List<Artigo> artigos = vendasPorVendedor.get(id_vendedor);
            this.vendasPorVendedor.put(id_vendedor, new ArrayList<>(artigos));
        }
    }
    
    public Map<Integer, List<Artigo>> getVendasPorVendedor() {
        Map<Integer, List<Artigo>> vendasPorVendedorClone = new HashMap<>();
        for (Integer id_vendedor : this.vendasPorVendedor.keySet()) {
            List<Artigo> artigos = this.vendasPorVendedor.get(id_vendedor);
            vendasPorVendedorClone.put(id_vendedor, new ArrayList<>(artigos));
        }
        return vendasPorVendedorClone;
    }

    public void setVendasPorVendedor(Map<Integer, List<Artigo>> vendasPorVendedor) {
        this.vendasPorVendedor = new HashMap<>();
        for (Integer vendedor : vendasPorVendedor.keySet()) {
            List<Artigo> artigos = vendasPorVendedor.get(vendedor);
            this.vendasPorVendedor.put(vendedor, new ArrayList<>(artigos));
        }
    }

    public String toString () {
        String r = super.toString();
        for (Map.Entry<Integer, List<Artigo>> entry : this.vendasPorVendedor.entrySet()) {
            r = r + "\nVendedor: " + entry.getKey();
            for (Artigo artigo : entry.getValue()) {
                r = r + "- " + artigo.getCodigo() + ": " + artigo.getDescricao() + " - " + artigo.getPrecoFinal();
            }
        }
        return r;
    }

    public double getPrecoTotal() {
        double total = 0;
        for (List<Artigo> list : this.vendasPorVendedor.values()) {
            for (Artigo artigo : list) {
                total += artigo.getPrecoFinal();
            }
        }
        return total;
    }

    public Fatura_Comprador clone () {
        return new Fatura_Comprador(this);
    }
}
