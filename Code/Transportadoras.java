package Code;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Transportadoras implements Serializable {

    private Map <String,Transportadora> transportadoras;


    public Transportadoras () {
        this.transportadoras = new HashMap<>();
    }

    public Transportadoras (Map <String,Transportadora> transportadoras) {
        this.transportadoras = new HashMap<>();
        for (Map.Entry<String,Transportadora> entry : transportadoras.entrySet()) {
            this.transportadoras.put(entry.getKey(),entry.getValue().clone());
        }
    }

    public Transportadoras (Transportadoras t) {
        this.transportadoras = new HashMap<>();
        for (Map.Entry<String,Transportadora> entry : t.transportadoras.entrySet()) {
            this.transportadoras.put(entry.getKey(),entry.getValue().clone());
        }
    }

    public Map<String,Transportadora> getTransportadoras () {
        return this.transportadoras.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, entry->entry.getValue().clone()));
    }

    public Transportadora getTransportadora (String nome) {
        return this.transportadoras.get(nome).clone();
    }

    public void adicionaTransportadora (Transportadora transportadora){
        this.transportadoras.put(transportadora.getNome(), transportadora.clone());
    }

    public void removeTransportadora (String nome) {
            if (this.transportadoras.remove(nome) == null) System.out.println("Não encontrado");
            else System.out.println(nome + " removido");
    } 

    public Transportadora maiorVolumeFaturação (Encomendas encomendas) {
        List<Transportadora> transportadoras = new ArrayList<>();
        for (Transportadora transportadora : this.transportadoras.values()) {
            transportadoras.add(transportadora);
        }
        Map <String, Double> faturacaoPorTransportadora = new HashMap<>();
        double total_faturado = 0;
        for (Encomenda encomenda : encomendas.getEncomendas().values()) {
            String nome_transportadora = encomenda.getNomeTransportadora();
            if (faturacaoPorTransportadora.containsKey(nome_transportadora)) {
                total_faturado = faturacaoPorTransportadora.get(nome_transportadora);
                total_faturado += encomenda.preco(this);
                faturacaoPorTransportadora.put(nome_transportadora, total_faturado);
            }
            else {
                total_faturado = encomenda.preco(this);
                faturacaoPorTransportadora.put(nome_transportadora, total_faturado);
            }
        }
        return faturacaoPorTransportadora.entrySet().stream()
            .max(Map.Entry.<String, Double>comparingByValue().reversed())
            .map(Map.Entry::getKey)
            .map(key -> this.transportadoras.get(key))
            .orElse(null);
    }

    public Transportadoras clone() {
        return new Transportadoras(this);
    }
    
    public String toString() {
        String result = "";
        for (Map.Entry<String,Transportadora> entry : this.transportadoras.entrySet()) {
            result += entry.getValue().toString() + "\n\n";
        }
        return result;
    }

}