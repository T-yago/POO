package Code;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Artigos implements Serializable {
    private Map<Integer, Map<String,Artigo>> artigos;

    public Artigos() {
        this.artigos = new HashMap<>();
    }

    public Artigos(Map<Integer, Map<String,Artigo>> artigos, Utilizadores utilizadores) {
        this.artigos = new HashMap<>();

        for (Map.Entry<Integer, Map<String,Artigo>> e: artigos.entrySet()) {
            if (utilizadores.existeUtilizador(e.getKey())) {
                Map<String,Artigo> s = new HashMap<>();

                for (Map.Entry<String,Artigo> a: e.getValue().entrySet()) {
                    s.put(a.getKey(), a.getValue().clone());
                }

                this.artigos.put(e.getKey(), s);
            }
        }
    }

    public Artigos(Artigos artigos) {
        this.artigos = new HashMap<>();

        for (Map.Entry<Integer, Map<String,Artigo>> e: artigos.artigos.entrySet()) {
            Map<String,Artigo> s = new HashMap<>();

            for (Map.Entry<String,Artigo> a: e.getValue().entrySet()) {
                s.put(a.getKey(), a.getValue().clone());
            }

            this.artigos.put(e.getKey(), s);
        }
    }

    public Artigo getArtigo(int id_Vendedor, String id_Artigo) {
        return this.artigos.get(id_Vendedor).get(id_Artigo);
    }

    public Artigo getArtigo(String id_Artigo) {
        int id_Vendedor = Integer.parseInt(id_Artigo.substring(0, id_Artigo.length()-6));

        Map<String, Artigo> artigos;
        if ((artigos = this.artigos.get(id_Vendedor))!=null) {
            return artigos.get(id_Artigo);
        }
        return null;
    }

    public Map<Integer, Map<String,Artigo>> getArtigos() {
        Map<Integer, Map<String,Artigo>> artigos = new HashMap<>();

        for (Map.Entry<Integer, Map<String,Artigo>> e: this.artigos.entrySet()) {
            Map<String,Artigo> s = new HashMap<>();

            for (Map.Entry<String,Artigo> a: e.getValue().entrySet()) {
                s.put(a.getKey(), a.getValue().clone());
            }

            artigos.put(e.getKey(), s);
        }

        return artigos;
    }

    public void addArtigo(Artigo artigo, Utilizadores utilizadores) {
        int id_Vendedor = Integer.parseInt(artigo.getCodigo().substring(0, artigo.getCodigo().length()-6));

        Map<String,Artigo> s;
        if (utilizadores.existeUtilizador(id_Vendedor)) {
            if ((s = this.artigos.get(id_Vendedor))!=null) {
                s.putIfAbsent(artigo.getCodigo(), artigo);
            }
            else {
                Map<String, Artigo> artigos = new HashMap<>();
                artigos.put(artigo.getCodigo(), artigo);
                this.artigos.put(id_Vendedor, artigos);
            }
        }
        else {
            System.out.println("Não é possível associar um artigo a um utilizador que não existe.");
        }
    }

    public void removeArtigo(String id_Artigo) {
        int id_Vendedor = Integer.parseInt(id_Artigo.substring(0, id_Artigo.length()-6));

        Map<String,Artigo> s;
        if ((s = this.artigos.get(id_Vendedor))!=null) {
            s.remove(id_Artigo);
        }
    }

    public void updateArtigos(LocalDate data) {
        for (Map<String,Artigo> artigos: this.artigos.values()) {
            for (Artigo a: artigos.values()) {
                if (a instanceof Mala_Nova) {
                    Mala_Nova e = (Mala_Nova) a;
                    e.recalculaPreco();
                }
                else if (a instanceof Mala_Premium) {
                    Mala_Premium e = (Mala_Premium) a;
                    e.recalculaPreco();
                }
                else if (a instanceof Mala_Usada) {
                    Mala_Usada e = (Mala_Usada) a;
                    e.recalculaPreco();
                }
                else if (a instanceof T_Shirt_Nova) {
                    T_Shirt_Nova e = (T_Shirt_Nova) a;
                    e.recalculaPreco();
                }
                else if (a instanceof T_Shirt_Usada) {
                    T_Shirt_Usada e = (T_Shirt_Usada) a;
                    e.recalculaPreco();
                }
                else if (a instanceof Sapatilha_Nova) {
                    Sapatilha_Nova e = (Sapatilha_Nova) a;
                    e.recalculaPreco();
                }
                else if (a instanceof Sapatilha_Premium) {
                    Sapatilha_Premium e = (Sapatilha_Premium) a;
                    e.recalculaPreco();
                }
                else if (a instanceof Sapatilha_Usada) {
                    Sapatilha_Usada e = (Sapatilha_Usada) a;
                    e.recalculaPreco();
                }
            }
        }
    }

    public String toString() {
        String s = "";

        for (Map<String, Artigo> map: this.artigos.values()) {
            for (Artigo a: map.values()) {
                s += a.toString() + "\n\n";
            }
        }

        return s;
    }

    public Artigos clone() {
        return new Artigos(this);
    }
    
}
