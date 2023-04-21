package Code;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Transportadoras {

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
            else System.out.println(nome + "Removido");
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