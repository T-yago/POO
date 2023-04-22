package Code;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Encomendas {
    private int id_Counter = 0;
    private Map<String, Collection<Encomenda>> encomendas;

    public Encomendas() {
        this.encomendas = new HashMap<>();
    }

    public Encomendas(Collection<Artigo> artigos, int estado_Encomenda, LocalDate date, Transportadoras transportadoras) {
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

        String id;
        if (artigos.size()>0) {
            for (Artigo a: artigos) {
                id = String.format("%06d", a.getCodigo().substring(0, 6)) + String.format("%06d", id_Counter++);
                break;
            }
        }
        else {
            id = String.format("%06d", 999999) + String.format("%06d", id_Counter++);
        }

        map_Transportadoras.forEach((String key, Collection<Artigo> a) -> {if (this.encomendas.containsKey(id)) {this.encomendas.get(key).add(new Encomenda(a, estado_Encomenda, date, transportadoras)}
                                                                           else {this.encomendas.put(id, new Encomenda(a, estado_Encomenda, date, transportadoras));}});
        id_Counter++;
    }

    public Encomendas(Encomendas encomendas) {
        this.encomendas = new HashMap<>();

        for 
    }
}
