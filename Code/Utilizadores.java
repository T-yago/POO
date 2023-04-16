package Code;

import java.util.HashMap;
import java.util.Map;

public class Utilizadores {
    Map<String, Utilizador> utilizadores;
    
    public Utilizadores() {
        this.utilizadores = new HashMap<>();
    }

    public Utilizadores(Map<String, Utilizador> utilizadores) {
        this.utilizadores = new HashMap<>();

        for (Map.Entry<String, Utilizador> e: utilizadores.entrySet()) {
            this.utilizadores.put(e.getKey(), e.getValue().clone());
        }
    }

    public Utilizadores(Utilizadores utilizadores) {
        this.utilizadores = new HashMap<>();

        for (Map.Entry<String, Utilizador> e: utilizadores.utilizadores.entrySet()) {
            this.utilizadores.put(e.getKey(), e.getValue().clone());
        }
    }

    public Utilizador getUtilizador(String nome) {
        return this.utilizadores.get(nome);
    }

    public Map<String, Utilizador> getUtilizadores() {
        Map<String, Utilizador> utilizadores = new HashMap<>();

        for (Map.Entry<String, Utilizador> e: this.utilizadores.entrySet()) {
            utilizadores.put(e.getKey(), e.getValue().clone());
        }

        return utilizadores;
    }

    public void addUtilizador(Utilizador utilizador) {
        this.utilizadores.put(utilizador.getNome(), utilizador.clone());
    }

    public void removeUtilizador(Utilizador utilizador) {
        if (this.utilizadores.containsKey(utilizador.getNome())) {
            this.utilizadores.remove(utilizador.getNome());
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
