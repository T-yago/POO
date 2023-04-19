package Code;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

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

    public void removeUtilizador(Utilizador utilizador) {
        if (this.utilizadores.containsKey(utilizador.getId())) {
            this.utilizadores.remove(utilizador.getId());
        }
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
