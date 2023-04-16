package Code;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Artigos {
    private Map<Integer, Set<Artigo>> artigos;

    public Artigos() {
        this.artigos = new HashMap<>();
    }

    public Artigos(Map<Integer, Set<Artigo>> artigos) {
        this.artigos = new HashMap<>();

        for (Map.Entry<Integer, Set<Artigo>> e: artigos.entrySet()) {
            Set<Artigo> s = new HashSet<>();

            for (Artigo a: e.getValue()) {
                s.add(a.clone());
            }

            this.artigos.put(e.getKey(), s);
        }
    }

    public Artigos(Artigos artigos) {
        this.artigos = new HashMap<>();

        for (Map.Entry<Integer, Set<Artigo>> e: artigos.artigos.entrySet()) {
            Set<Artigo> s = new HashSet<>();

            for (Artigo a: e.getValue()) {
                s.add(a.clone());
            }

            this.artigos.put(e.getKey(), s);
        }
    }

    public Artigo getArtigo(int id_Vendedor, String id_Artigo) {
        for (Artigo a: this.artigos.get(id_Vendedor)) {
            if (a.getCodigo().equals(id_Artigo)) {
                return a.clone();
            }
        }
        return null;
    }

    public Artigo getArtigo(String id_Artigo) {
        int id_Vendedor = Integer.parseInt(id_Artigo.substring(0, id_Artigo.length()-6));

        if (this.artigos.containsKey(id_Vendedor)) {
            for (Artigo a: this.artigos.get(id_Vendedor)) {
                if (a.getCodigo().equals(id_Artigo)) {
                    return a.clone();
                }
            }
        }
        return null;
    }

    public Map<Integer, Set<Artigo>> getArtigos() {
        Map<Integer, Set<Artigo>> artigos = new HashMap<>();

        for (Map.Entry<Integer, Set<Artigo>> e: this.artigos.entrySet()) {
            Set<Artigo> s = new HashSet<>();

            for (Artigo a: e.getValue()) {
                s.add(a.clone());
            }

            this.artigos.put(e.getKey(), s);
        }

        return artigos;
    }

    public void addArtigo(Artigo artigo) {
        int id_Vendedor = Integer.parseInt(artigo.getCodigo().substring(0, artigo.getCodigo().length()-6));

        if (this.artigos.containsKey(id_Vendedor)) {
            this.artigos.get(id_Vendedor).add(artigo);
        }
        else {
            System.out.println("O utilizador com id " + id_Vendedor + " não existe.");
        }
    }
}
