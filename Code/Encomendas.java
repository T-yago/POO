package Code;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Encomendas {
    private int id_Counter = 0;
    private Map<String, Encomenda> encomendas;

    public Encomendas() {
        this.encomendas = new HashMap<>();
    }

    public Encomendas(Collection<Artigo> artigos, int id_Comprador, int estado_Encomenda, LocalDate date, Transportadoras transportadoras) {
        this.encomendas = new HashMap<>();

        // Dividir as encomendas por transportadora

        Map<String, Collection<Artigo>> map_Transportadoras = new HashMap<>();

        for (Artigo a: artigos) {

            Collection<Artigo> artigos_Transportadora;
            if ((artigos_Transportadora = map_Transportadoras.get(a.getTransportadora()))!=null) {
                artigos_Transportadora.add(a);
            }
            else {
                Collection<Artigo> artigos_Nova_Transportadora = new HashSet<>();
                artigos_Nova_Transportadora.add(a);
                map_Transportadoras.put(a.getTransportadora(), artigos_Nova_Transportadora);
            }
        }

        // Criar as encomendas

        map_Transportadoras.forEach((String key, Collection<Artigo> a) -> {String id = String.format("%06d", id_Comprador) + String.format("%06d", id_Counter++);
                                                                           this.encomendas.put(id, new Encomenda(a, estado_Encomenda, date, transportadoras));
                                                                           });
    }

    public Encomendas(Encomendas encomendas) {
        this.encomendas = new HashMap<>();

        for (Map.Entry<String, Encomenda> e: encomendas.encomendas.entrySet()) {
            this.encomendas.put(e.getKey(), e.getValue().clone());
        }
    }

    public Encomenda getEncomenda(String id_Encomenda, String estado_Artigo) {
        return this.encomendas.get(id_Encomenda);
    }

    public Map<String, Encomenda> getEncomendas() {
        Map<String, Encomenda> encomendas = new HashMap<>();

        for (Map.Entry<String, Encomenda> e: this.encomendas.entrySet()) {
            encomendas.put(e.getKey(), e.getValue().clone());
        }

        return encomendas;
    }
}
