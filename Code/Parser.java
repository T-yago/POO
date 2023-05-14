package Code;

import java.io.BufferedInputStream;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.io.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Parser {

    public static void parseText(String file_Utilizadores, String file_Artigos, String file_Transportadoras, String file_Encomendas,Vintage vintage) {
        
        Utilizadores utilizadores = vintage.getUtilizadores();
        Artigos artigos = vintage.getArtigos();
        Transportadoras transportadoras = vintage.getTransportadoras();
        Encomendas encomendas = vintage.getEncomendas();
        
        System.out.println();
        File file_U = new File(file_Utilizadores);
        File file_A = new File(file_Artigos);
        File file_T = new File (file_Transportadoras);
        File file_E = new File(file_Encomendas);

        try {
            Scanner sc = new Scanner(file_U);
            
            while (sc.hasNextLine()) {
                String line = sc.nextLine().trim();

                String[] tokens = line.split("\\s*,\\s*");
                if (tokens.length==4) {
                    Utilizador utilizador = new Utilizador(tokens[0], tokens[1], tokens[2], Integer.parseInt(tokens[3]));
                    utilizadores.addUtilizador(utilizador);
                }
            }

            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try{
            Scanner sc = new Scanner(file_T);
            while (sc.hasNextLine()) {
                String line = sc.nextLine().trim();

                    String[] tokens = line.split("\\s*,\\s*");
                    if (tokens.length == 10) {
                        if (tokens[0].equals("Transportadora")) {
                        Transportadora transportadora = new Transportadora(tokens[1],
                                                                            Double.parseDouble(tokens[2]),
                                                                            Double.parseDouble(tokens[3]),
                                                                            Double.parseDouble(tokens[4]),
                                                                            Double.parseDouble(tokens[5]),
                                                                            Double.parseDouble(tokens[6]),
                                                                            Integer.parseInt(tokens[7]),
                                                                            Integer.parseInt(tokens[8]),
                                                                            Integer.parseInt(tokens[9]));
                        transportadoras.adicionaTransportadora(transportadora);
                        }
                        else if (tokens[0].equals("Transportadora Premium")) {
                            Transportadora_Premium transportadora_Premium = new Transportadora_Premium(tokens[1],
                                                                            Double.parseDouble(tokens[2]),
                                                                            Double.parseDouble(tokens[3]),
                                                                            Double.parseDouble(tokens[4]),
                                                                            Double.parseDouble(tokens[5]),
                                                                            Double.parseDouble(tokens[6]),
                                                                            Integer.parseInt(tokens[7]),
                                                                            Integer.parseInt(tokens[8]),
                                                                            Integer.parseInt(tokens[9]));
                        transportadoras.adicionaTransportadora(transportadora_Premium);
                        
                        }
                    }
                }

            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            Scanner sc = new Scanner(file_A);
            
            while (sc.hasNextLine()) {
                String line = sc.nextLine().trim();

                String[] tokens = line.split("\\s*,\\s*");
                if (tokens[0].equals("Sapatilha Nova")) {
                    Artigo artigo = new Sapatilha_Nova(Integer.parseInt(tokens[1]), tokens[2], tokens[3], Double.parseDouble(tokens[4]), tokens[5], Integer.parseInt(tokens[6]), Boolean.parseBoolean(tokens[7]), tokens[8], Integer.parseInt(tokens[9]), Integer.parseInt(tokens[10]));
                    artigos.addArtigo(artigo, utilizadores);
                }
                else if (tokens[0].equals("Sapatilha Usada")) {
                    Artigo artigo = new Sapatilha_Usada(Integer.parseInt(tokens[1]), tokens[2], tokens[3], Double.parseDouble(tokens[4]), tokens[5], Integer.parseInt(tokens[6]), Boolean.parseBoolean(tokens[7]), tokens[8], Integer.parseInt(tokens[9]), tokens[10], Byte.parseByte(tokens[11]), Integer.parseInt(tokens[12]));
                    artigos.addArtigo(artigo, utilizadores);
                }
                else if (tokens[0].equals("Sapatilha Premium")) {
                    Transportadora trans = transportadoras.getTransportadora(tokens[5]);
                    Artigo artigo = new Sapatilha_Premium(Integer.parseInt(tokens[1]), tokens[2], tokens[3], Double.parseDouble(tokens[4]), (Transportadora_Premium) trans, Integer.parseInt(tokens[6]), Boolean.parseBoolean(tokens[7]), tokens[8], Integer.parseInt(tokens[9]), Integer.parseInt(tokens[10]));
                    artigos.addArtigo(artigo, utilizadores);
                }
                else if (tokens[0].equals("T-Shirt Nova")) {
                    Artigo artigo = new T_Shirt_Nova(Integer.parseInt(tokens[1]), tokens[2], tokens[3], Double.parseDouble(tokens[4]), tokens[5], Integer.parseInt(tokens[6]), Integer.parseInt(tokens[7]));
                    artigos.addArtigo(artigo, utilizadores);
                }
                else if (tokens[0].equals("T-Shirt Usada")) {
                    Artigo artigo = new T_Shirt_Usada(Integer.parseInt(tokens[1]), tokens[2], tokens[3], Double.parseDouble(tokens[4]), tokens[5], Integer.parseInt(tokens[6]), Integer.parseInt(tokens[7]), Byte.parseByte(tokens[8]), tokens[9]);
                    artigos.addArtigo(artigo, utilizadores);
                }
                else if (tokens[0].equals("Mala Nova")) {
                    Artigo artigo = new Mala_Nova(Integer.parseInt(tokens[1]), tokens[2], tokens[3], Double.parseDouble(tokens[4]), tokens[5], Double.parseDouble(tokens[6]), tokens[7], Integer.parseInt(tokens[8]));
                    artigos.addArtigo(artigo, utilizadores);
                }
                else if (tokens[0].equals("Mala Usada")) {
                    Artigo artigo = new Mala_Usada(Integer.parseInt(tokens[1]), tokens[2], tokens[3], Double.parseDouble(tokens[4]), tokens[5], Double.parseDouble(tokens[6]), tokens[7], Integer.parseInt(tokens[8]), tokens[9], Byte.parseByte(tokens[10]));
                    artigos.addArtigo(artigo, utilizadores);
                }
                else if (tokens[0].equals("Mala Premium")) {
                    Transportadora trans = transportadoras.getTransportadora(tokens[5]);
                    Artigo artigo = new Mala_Premium(Integer.parseInt(tokens[1]), tokens[2], tokens[3], Double.parseDouble(tokens[4]), (Transportadora_Premium)trans, Double.parseDouble(tokens[6]), tokens[7], Integer.parseInt(tokens[8]));
                    artigos.addArtigo(artigo, utilizadores);
                }
            }

            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        try {
            Scanner sc = new Scanner(file_E);
            
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            while (sc.hasNextLine()) {
                String line = sc.nextLine().trim();

                String[] tokens = line.split("\\s*,\\s*");
                if (tokens.length>=4) {

                    Collection<Artigo> artigos_Encomenda = new HashSet<>();
                    for (int i = 3;i<tokens.length;i++) {
                        artigos_Encomenda.add(artigos.getArtigo(tokens[i]));
                    }

                    String defaultVAlue = "DefaultValue";
                    Encomenda encomenda = new Encomenda(artigos_Encomenda, defaultVAlue, Integer.parseInt(tokens[0]), LocalDate.parse(tokens[2], formatter), transportadoras);
                    encomendas.addEncomenda(encomenda, Integer.parseInt(tokens[1]), transportadoras, utilizadores, Integer.parseInt(tokens[0]));
                }
            }

            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // Atualizar a lista de artigos e faturas

        encomendas.updateEncomendasFinalizadas(artigos);
        encomendas.updateFaturas(utilizadores);
        encomendas.updateEncomendas(Menu.getCurrentDate(),transportadoras);
        artigos.updateArtigos(Menu.getCurrentDate());

        // Adicionar à Vintage

        vintage.setArtigos(artigos);
        vintage.setEncomendas(encomendas);
        vintage.setTransportadoras(transportadoras);
        vintage.setUtilizadores(utilizadores);
    }

    public static void parseBinary(String file_Utilizadores, Vintage vintage) {

        FileInputStream fileIn = null;
        BufferedInputStream bufferedIn = null;
        ObjectInputStream in = null;

        Utilizadores utilizadores = vintage.getUtilizadores();
        Artigos artigos = vintage.getArtigos();
        Transportadoras transportadoras = vintage.getTransportadoras();
        Encomendas encomendas = vintage.getEncomendas();

        try {
            fileIn = new FileInputStream(file_Utilizadores);
            bufferedIn = new BufferedInputStream(fileIn);
            in = new ObjectInputStream(bufferedIn);

            while (bufferedIn.available() > 0) {
                Object obj = in.readObject();
                if (obj instanceof Utilizador) {
                    Utilizador utilizador = (Utilizador) obj;
                    utilizadores.addUtilizador(utilizador);
                }
                else if (obj instanceof Sapatilha_Premium) {
                    Sapatilha_Premium artigo = (Sapatilha_Premium) obj;
                    artigos.addArtigo(artigo, utilizadores);
                }
                else if (obj instanceof Sapatilha_Nova) {
                    Sapatilha_Nova artigo = (Sapatilha_Nova) obj;
                    artigos.addArtigo(artigo, utilizadores);
                }
                else if (obj instanceof Sapatilha_Usada) {
                    Sapatilha_Usada artigo = (Sapatilha_Usada) obj;
                    artigos.addArtigo(artigo, utilizadores);
                }
                else if (obj instanceof Mala_Nova) {
                    Mala_Nova artigo = (Mala_Nova) obj;
                    artigos.addArtigo(artigo, utilizadores);
                }
                else if (obj instanceof Mala_Usada) {
                    Mala_Usada artigo = (Mala_Usada) obj;
                    artigos.addArtigo(artigo, utilizadores);
                }
                else if (obj instanceof Mala_Premium) {
                    Mala_Premium artigo = (Mala_Premium) obj;
                    artigos.addArtigo(artigo, utilizadores);
                }
                else if (obj instanceof T_Shirt_Nova) {
                    T_Shirt_Nova artigo = (T_Shirt_Nova) obj;
                    artigos.addArtigo(artigo, utilizadores);
                }
                else if (obj instanceof T_Shirt_Usada) {
                    T_Shirt_Usada artigo = (T_Shirt_Usada) obj;
                    artigos.addArtigo(artigo, utilizadores);
                }
                else if (obj instanceof Transportadora) {
                    Transportadora transportadora = (Transportadora) obj;
                    transportadoras.adicionaTransportadora(transportadora);
                }
                else if (obj instanceof Transportadora_Premium) { // é preciso?
                    Transportadora_Premium transportadora_premium = (Transportadora_Premium) obj;
                    transportadoras.adicionaTransportadora(transportadora_premium);
                }
                else if (obj instanceof Encomenda) {
                    Encomenda encomenda = (Encomenda) obj;
                    encomendas.addEncomenda(encomenda, encomenda.getIdComprador(), transportadoras, utilizadores, encomenda.getEstadoEncomenda());
                }
                else {
                    System.out.println("Objeto não conhecido: " + obj.getClass());
                }

                int id_Max_Utilizadores;
                if ((id_Max_Utilizadores = utilizadores.getUtilizadores().size())>0) {
                    utilizadores.getUtilizador(id_Max_Utilizadores).setNumero_Utilizadores(id_Max_Utilizadores);
                }

                int id_Max_Artigos = 0;
                String codigo_artigo = "";
                for (Map<String, Artigo> e: artigos.getArtigos().values()) {
                    for (String s: e.keySet()) {
                        id_Max_Artigos++;
                        codigo_artigo = s;
                    }
                }
                if (id_Max_Artigos>0) {
                    artigos.getArtigo(codigo_artigo).setNumero_Artigos(id_Max_Artigos);
                }

                Set<String> ids_encomendas = new HashSet<>();

                String id_Encomenda;
                for (String e: encomendas.getEncomendas().keySet()) {
                    id_Encomenda = e.substring(e.length()-12);
                    ids_encomendas.add(id_Encomenda);
                }
                encomendas.setNumero_Encomendas(ids_encomendas.size());
            }


        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        // Atualizar a lista de artigos e faturas

        encomendas.updateEncomendasFinalizadas(artigos);
        encomendas.updateFaturas(utilizadores);
        encomendas.updateEncomendas(Menu.getCurrentDate(),transportadoras);
        artigos.updateArtigos(Menu.getCurrentDate());

        // Adicionar à Vintage

        vintage.setArtigos(artigos);
        vintage.setEncomendas(encomendas);
        vintage.setTransportadoras(transportadoras);
        vintage.setUtilizadores(utilizadores);
    }

    public static void storeBinary(Vintage vintage) {
        File file = null;
        ObjectOutputStream out = null;

        Utilizadores utilizadores = vintage.getUtilizadores();
        Artigos artigos = vintage.getArtigos();
        Transportadoras transportadoras = vintage.getTransportadoras();
        Encomendas encomendas = vintage.getEncomendas();

        try {
            file = new File("Code/Dados/Older_Save/input.obj");

            if (file.exists()) {
                file.delete();
            }

            // Passa o input do Last_Save para o Older_Save

            Path sourcePath = Paths.get("Code/Dados/Last_Save/input.obj");
            Path targetPath = Paths.get("Code/Dados/Older_Save/input.obj");

            try {
                Files.move(sourcePath, targetPath);
            } catch (NoSuchFileException e) {
                System.out.println("The file does not exist: " + e.getMessage());
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }

            // Cria o novo ficheiro no Last_Save

            out = new ObjectOutputStream(new FileOutputStream("Code/Dados/Last_Save/input.obj", true));

            if (utilizadores!=null) {
                for (Utilizador utilizador: utilizadores.getUtilizadores().values()) {
                    out.writeObject(utilizador);
                }
            }
            if (artigos!=null) {
                for (Map<String, Artigo> artigos_utilizador: artigos.getArtigos().values()) {
                    for (Artigo artigo: artigos_utilizador.values()) {
                        if (artigo instanceof Sapatilha_Nova) {
                            Sapatilha_Nova a = (Sapatilha_Nova) artigo;
                            out.writeObject(a);
                        }
                        else if (artigo instanceof Sapatilha_Usada) {
                            Sapatilha_Usada a = (Sapatilha_Usada) artigo;
                            out.writeObject(a);
                        }
                        else if (artigo instanceof Sapatilha_Premium) {
                            Sapatilha_Premium a = (Sapatilha_Premium) artigo;
                            out.writeObject(a);
                        }
                        else if (artigo instanceof Mala_Nova) {
                            Mala_Nova a = (Mala_Nova) artigo;
                            out.writeObject(a);
                        }
                        else if (artigo instanceof Mala_Usada) {
                            Mala_Usada a = (Mala_Usada) artigo;
                            out.writeObject(a);
                        }
                        else if (artigo instanceof Mala_Premium) {
                            Mala_Premium a = (Mala_Premium) artigo;
                            out.writeObject(a);
                        }
                        else if (artigo instanceof T_Shirt_Nova) {
                            T_Shirt_Nova a = (T_Shirt_Nova) artigo;
                            out.writeObject(a);
                        }
                        else if (artigo instanceof T_Shirt_Usada) {
                            T_Shirt_Usada a = (T_Shirt_Usada) artigo;
                            out.writeObject(a);
                        }
                    }
                }
            }
            if (transportadoras!=null) {
                for (Transportadora transportadora: transportadoras.getTransportadoras().values())
                {
                    out.writeObject(transportadora);
                }
            }
            if (encomendas!=null) {
                for (Encomenda encomenda: encomendas.getEncomendas().values()) {
                    out.writeObject(encomenda);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
