package Code;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Encomendas {
    private int id_Counter = 1;
    private Map<LocalDate, Set<Encomenda>> encomendas_Entregues;
    private Map<String, Encomenda> encomendas;

    public Encomendas() {
        this.encomendas_Entregues = new HashMap<>();
        this.encomendas = new HashMap<>();
    }

    public Encomendas(Map<Integer, Encomenda> encomendas, Transportadoras transportadoras, Utilizadores utilizadores) {
        this.encomendas_Entregues = new HashMap<>();
        this.encomendas = new HashMap<>();

        for (Map.Entry<Integer, Encomenda> e: encomendas.entrySet()) {
            addEncomenda(e.getValue(), e.getKey(), transportadoras, utilizadores);
        }
    }

    public Encomendas(Encomendas encomendas) {
        this.encomendas_Entregues = new HashMap<>();

        for (Map.Entry<LocalDate, Set<Encomenda>> e: encomendas.encomendas_Entregues.entrySet()) {
            Set<Encomenda> conjunto_Encomendas = new HashSet<>();

            for (Encomenda encomenda: e.getValue()) {
                conjunto_Encomendas.add(encomenda.clone());
            }
            this.encomendas_Entregues.put(e.getKey(), conjunto_Encomendas);
        }

        this.encomendas = new HashMap<>();

        for (Map.Entry<String, Encomenda> e: encomendas.encomendas.entrySet()) {
            this.encomendas.put(e.getKey(), e.getValue().clone());
        }
    }

    public Collection<Encomenda> getEncomenda(String id_Encomenda, Transportadoras transportadoras) {
        if (existeEncomenda(id_Encomenda, transportadoras)) {
            Collection<Encomenda> conjunto_Encomendas = new HashSet<>();

            Encomenda encomenda;
            for (String t: transportadoras.getTransportadoras().keySet()) {
                if ((encomenda = this.encomendas.get(t + id_Encomenda))!=null) {
                    conjunto_Encomendas.add(encomenda);
                }
            }
            return conjunto_Encomendas;
        }
        return null;
    }

    public Map<String, Encomenda> getEncomendas() {
        Map<String, Encomenda> encomendas = new HashMap<>();

        for (Map.Entry<String, Encomenda> e: this.encomendas.entrySet()) {
            encomendas.put(e.getKey(), e.getValue().clone());
        }

        return encomendas;
    }

    public void setNumero_Encomendas(int numero_Encomendas) {
        this.id_Counter = numero_Encomendas;
    }

    public void addEncomenda(Encomenda encomenda, int id_Comprador, Transportadoras transportadoras, Utilizadores utilizadores) {
        if (utilizadores.existeUtilizador(id_Comprador)) {
            Collection<Artigo> artigos = encomenda.getArtigos();

            // Dividir os artigos por transportadora

            Map<String, Collection<Artigo>> map_Transportadoras = new HashMap<>();

            for (Artigo a: artigos) {

                if (item_Disponivel(a)) {
                    Collection<Artigo> artigos_Transportadora;
                    if ((artigos_Transportadora = map_Transportadoras.get(a.getTransportadora()))!=null && !artigos_Transportadora.contains(a)) {
                        artigos_Transportadora.add(a);
                    }
                    else {
                        Collection<Artigo> artigos_Nova_Transportadora = new HashSet<>();
                        artigos_Nova_Transportadora.add(a);
                        map_Transportadoras.put(a.getTransportadora(), artigos_Nova_Transportadora);
                    }
                }
            }

            // Criar as encomendas

            map_Transportadoras.forEach((String key, Collection<Artigo> a) -> {
                                                                                String id = key + String.format("%06d", id_Comprador) + String.format("%06d", id_Counter);
                                                                                this.encomendas.put(id, new Encomenda(a, id, encomenda.getEstadoEncomenda(), encomenda.getData(), transportadoras));
                                                                            });
            id_Counter++;
        }
    }

    public void add_Artigos_To_Encomenda(String id_Encomenda, Collection<Artigo> artigos, Transportadoras transportadoras) {

        if (existeEncomenda(id_Encomenda, transportadoras) && encomendaPendente(id_Encomenda, transportadoras)) {

            // Dividir os artigos por transportadora

            Map<String, Collection<Artigo>> map_Transportadoras = new HashMap<>();

            for (Artigo a: artigos) {

                if (item_Disponivel(a)) {
                    Collection<Artigo> artigos_Transportadora;
                    if ((artigos_Transportadora = map_Transportadoras.get(a.getTransportadora()))!=null && !artigos_Transportadora.contains(a)) {
                        artigos_Transportadora.add(a);
                    }
                    else {
                        Collection<Artigo> artigos_Nova_Transportadora = new HashSet<>();
                        artigos_Nova_Transportadora.add(a);
                        map_Transportadoras.put(a.getTransportadora(), artigos_Nova_Transportadora);
                    }
                }
            }

            // Adicionar os artigos

            for (Map.Entry<String, Collection<Artigo>> e: map_Transportadoras.entrySet()) {
                if (existeEncomenda(id_Encomenda, transportadoras)) {

                    Encomenda encomenda;
                    if ((encomenda = this.encomendas.get(e.getKey() + id_Encomenda))!=null) {
                        if (encomenda.getEstadoEncomenda()==0) {
                            encomenda.addArtigos(e.getValue(), transportadoras);
                        }
                        else {
                            System.out.println("Já não é possível adicionar artigos a esta encomenda, pois já não se encontra no estado pendente.");
                        }
                    }
                    else {
                        Encomenda nova_Encomenda = new Encomenda(e.getValue(), e.getKey() + id_Encomenda, 0, null, transportadoras);
                        this.encomendas.put(e.getKey() + id_Encomenda, nova_Encomenda);
                    }
                }
                else {
                    System.out.println("Não existe nenhuma encomenda com esse id.");
                    break;
                }
            }
        }
    }

    public void removeEncomenda(String id_Encomenda, Transportadoras transportadoras) {

        String codigo_Encomenda;
        if (existeEncomenda(id_Encomenda, transportadoras) && encomendaPendente(id_Encomenda, transportadoras)) {
            for (String transportadora: transportadoras.getTransportadoras().keySet()) {
                codigo_Encomenda = transportadora + id_Encomenda;

                this.encomendas.remove(codigo_Encomenda);
            }
        }
        else {
            System.out.println("A encomenda não existe ou já passou o prazo para puder removê-la.");
        }
    }

    public void removeArtigo(String id_Encomenda, String id_Artigo, Transportadoras transportadoras) {

        String codigo_Encomenda;
        if (existeEncomenda(id_Encomenda, transportadoras) && encomendaPendente(id_Encomenda, transportadoras)) {
            for (String transportadora: transportadoras.getTransportadoras().keySet()) {
                codigo_Encomenda = transportadora + id_Encomenda;

                Encomenda encomenda;
                if ((encomenda = this.encomendas.get(codigo_Encomenda))!=null) {
                    encomenda.removeArtigo(id_Artigo, transportadoras);
                    break;
                }
            }
        }
    }

    public void finalizarEncomenda(String id_Encomenda, LocalDate data_Finalizacao, Transportadoras transportadoras) {
        if (existeEncomenda(id_Encomenda, transportadoras) && encomendaPendente(id_Encomenda, transportadoras)) {

            Collection<Encomenda> encomendas;
            if ((encomendas = this.getEncomenda(id_Encomenda, transportadoras))!=null) {
                for (Encomenda e: encomendas) {
                    e.setEstadoEncomenda(1);
                    e.setData(data_Finalizacao);
                    System.out.println("Encomenda finalizada" );
                }
            }
        }
    }

    public void expedirEncomenda(String id_Encomenda, LocalDate data_Finalizacao, Transportadoras transportadoras) {
        if (existeEncomenda(id_Encomenda, transportadoras) && encomendaFinalizada(id_Encomenda, transportadoras)) {

            Collection<Encomenda> encomendas;
            if ((encomendas = this.getEncomenda(id_Encomenda, transportadoras))!=null) {
                for (Encomenda e: encomendas) {
                    e.setEstadoEncomenda(2);
                    e.setData(data_Finalizacao);
                }
            }
        }
    }

    public void devolerEncomenda(String id_Transportadora, LocalDate tempo) {

    }

    public boolean item_Disponivel(Artigo artigo) {
        for (Encomenda e: this.encomendas.values()) {
            
            if (e.tem_Item(artigo)) {
                return false;
            }
        }
        return true;
    }

    public boolean existeEncomenda(String id_Encomenda, Transportadoras transportadoras) {

        String codigo_Encomenda;
        for (String transportadora: transportadoras.getTransportadoras().keySet()) {
            codigo_Encomenda = transportadora + id_Encomenda;

            if (this.encomendas.containsKey(codigo_Encomenda)) {
                return true;
            }
        }
        return false;
    }

    public boolean encomendaPendente(String id_Encomenda, Transportadoras transportadoras) {

        String codigo_Encomenda;
        Encomenda encomenda;
        for (String transportadora: transportadoras.getTransportadoras().keySet()) {
            codigo_Encomenda = transportadora + id_Encomenda;

            if ((encomenda = this.encomendas.get(codigo_Encomenda))!=null) {
                return encomenda.getEstadoEncomenda() == 0;
            }
        }
        return false;
    }

    public boolean encomendaFinalizada(String id_Encomenda, Transportadoras transportadoras) {

        String codigo_Encomenda;
        Encomenda encomenda;
        for (String transportadora: transportadoras.getTransportadoras().keySet()) {
            codigo_Encomenda = transportadora + id_Encomenda;

            if ((encomenda = this.encomendas.get(codigo_Encomenda))!=null) {
                return encomenda.getEstadoEncomenda() == 1;
            }
        }
        return false;
    }

    void updateEncomendas (LocalDate data, Transportadoras transportadoras) {
        for (Map.Entry<String,Encomenda> encomendas: this.encomendas.entrySet()) {
            Encomenda encomenda = encomendas.getValue();
            String codigo = encomendas.getKey();
            String nome_transportadora = codigo.substring(0, codigo.length()-12);
            Transportadora transportadora = transportadoras.getTransportadora(nome_transportadora);
            if (encomenda.getEstadoEncomenda() == 1 &&  Main.getCurrentDate().minusDays(transportadora.getDiasPreparacaoEncomenda()).isAfter(encomenda.getData())) {
                encomenda.setEstadoEncomenda(2);
                encomenda.setData(encomenda.getData().plusDays(transportadora.getDiasPreparacaoEncomenda()));
            }
            if (encomenda.getEstadoEncomenda() ==2 && Main.getCurrentDate().minusDays(transportadoras.getTransportadora(nome_transportadora).getDiasEnvio()).isAfter(encomenda.getData())) {
                encomenda.setEstadoEncomenda(3);
                encomenda.setData(encomenda.getData().plusDays(transportadora.getDiasEnvio()));
            }
        }
    }

    public String toString() {
        String encomendas = "";

        for (Encomenda e: this.encomendas.values()) {
            encomendas += e.toString() + "\n\n";
        }

        return encomendas;
    }

    public Encomendas clone() {
        return new Encomendas(this);
    }

}
