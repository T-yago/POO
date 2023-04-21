package Code;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.*;
import java.util.Map;
import java.util.Scanner;

import javax.sound.midi.Track;

import Code.Transportadora;
import Code.Transportadoras;

public class Parser {

    public static void parseText(String file_Utilizadores, String file_Artigos, String file_Transportadoras, Utilizadores utilizadores, Artigos artigos, Transportadoras transportadoras) {
        File file_U = new File(file_Utilizadores);
        File file_A = new File(file_Artigos);
        File file_T = new File (file_Transportadoras);

        try {
            Scanner sc = new Scanner(file_U);
            
            while (sc.hasNextLine()) {
                String line = sc.nextLine().trim();

                String[] tokens = line.split("\\s*,\\s*");
                if (tokens.length>=4) {
                    Utilizador utilizador = new Utilizador(tokens[0], tokens[1], tokens[2], Integer.parseInt(tokens[3]));
                    utilizadores.addUtilizador(utilizador);
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
                    Artigo artigo = new Sapatilha_Nova(Integer.parseInt(tokens[1]), tokens[2], tokens[3], Double.parseDouble(tokens[4]), Integer.parseInt(tokens[5]), Boolean.parseBoolean(tokens[6]), tokens[7], Integer.parseInt(tokens[8]), Integer.parseInt(tokens[9]));
                    artigos.addArtigo(artigo, utilizadores);
                }
                else if (tokens[0].equals("Sapatilha Usada")) {
                    Artigo artigo = new Sapatilha_Usada(Integer.parseInt(tokens[1]), tokens[2], tokens[3], Double.parseDouble(tokens[4]), Integer.parseInt(tokens[5]), Boolean.parseBoolean(tokens[6]), tokens[7], Integer.parseInt(tokens[8]), tokens[9], Byte.parseByte(tokens[10]), Integer.parseInt(tokens[11]));
                    artigos.addArtigo(artigo, utilizadores);
                }
                else if (tokens[0].equals("Sapatilha Premium")) {
                    Artigo artigo = new Sapatilha_Premium(Integer.parseInt(tokens[1]), tokens[2], tokens[3], Double.parseDouble(tokens[4]), Integer.parseInt(tokens[5]), Boolean.parseBoolean(tokens[6]), tokens[7], Integer.parseInt(tokens[8]), Integer.parseInt(tokens[9]));
                    artigos.addArtigo(artigo, utilizadores);
                }
                else if (tokens[0].equals("T-Shirt Nova")) {
                    Artigo artigo = new T_Shirt_Nova(Integer.parseInt(tokens[1]), tokens[2], tokens[3], Double.parseDouble(tokens[4]), Integer.parseInt(tokens[5]), Integer.parseInt(tokens[6]));
                    artigos.addArtigo(artigo, utilizadores);
                }
                else if (tokens[0].equals("T-Shirt Usada")) {
                    Artigo artigo = new T_Shirt_Usada(Integer.parseInt(tokens[1]), tokens[2], tokens[3], Double.parseDouble(tokens[4]), Integer.parseInt(tokens[5]), Integer.parseInt(tokens[6]), Byte.parseByte(tokens[7]), tokens[8]);
                    artigos.addArtigo(artigo, utilizadores);
                }
                else if (tokens[0].equals("Mala Nova")) {
                    Artigo artigo = new Mala_Nova(Integer.parseInt(tokens[1]), tokens[2], tokens[3], Double.parseDouble(tokens[4]), Double.parseDouble(tokens[5]), tokens[6], Integer.parseInt(tokens[7]));
                    artigos.addArtigo(artigo, utilizadores);
                }
                else if (tokens[0].equals("Mala Usada")) {
                    Artigo artigo = new Mala_Usada(Integer.parseInt(tokens[1]), tokens[2], tokens[3], Double.parseDouble(tokens[4]), Double.parseDouble(tokens[5]), tokens[6], Integer.parseInt(tokens[7]), tokens[8], Byte.parseByte(tokens[9]));
                    artigos.addArtigo(artigo, utilizadores);
                }
                else if (tokens[0].equals("Mala Premium")) {
                    Artigo artigo = new Mala_Premium(Integer.parseInt(tokens[1]), tokens[2], tokens[3], Double.parseDouble(tokens[4]), Double.parseDouble(tokens[5]), tokens[6], Integer.parseInt(tokens[7]));
                    artigos.addArtigo(artigo, utilizadores);
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
                    if (tokens.length == 6) {
                        Transportadora transportadora = new Transportadora(tokens[0],
                                                                            Double.parseDouble(tokens[1]),
                                                                            Double.parseDouble(tokens[2]),
                                                                            Double.parseDouble(tokens[3]),
                                                                            tokens[4].equals("S"),
                                                                            Double.parseDouble(tokens[5]));
                        transportadoras.adicionaTransportadora(transportadora);
                    }
                }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void parseBinary(String file_Utilizadores, Utilizadores utilizadores, Artigos artigos, Encomendas encomendas, Transportadoras transportadoras) {
        FileInputStream fileIn = null;
        BufferedInputStream bufferedIn = null;
        ObjectInputStream in = null;

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
                else if (obj instanceof Artigo) {
                    Artigo artigo = (Artigo) obj;
                    artigos.addArtigo(artigo, utilizadores);
                }
                else if (obj instanceof Transportadora) {
                    Transportadora transportadora = (Transportadora) obj;
                    transportadoras.adicionaTransportadora(transportadora);
                }
                else {
                    System.out.println("Objeto não conhecido: " + obj.getClass());
                }

                int id_Max;
                if ((id_Max = utilizadores.getUtilizadores().size())>0) {
                    utilizadores.getUtilizador(id_Max).setNumero_Utilizadores(id_Max);
                }
            }


        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void storeBinary(Object categoria, String file_Output, boolean reset_Ficheiro){
        File file;
        ObjectOutputStream out = null;

        try {
            if (file_Output == null) {
                file = new File("Code/input_Utilizadores.obj");

                if (file.exists() && reset_Ficheiro) {
                    file.delete();
                }

                out = new ObjectOutputStream(new FileOutputStream("Code/input_Utilizadores.obj"));
            }
            else {
                file = new File(file_Output);

                if (file.exists() && reset_Ficheiro) {
                    file.delete();
                }

                out = new ObjectOutputStream(new FileOutputStream(file_Output));
            }

            if (categoria instanceof Utilizadores) {
                Utilizadores u = (Utilizadores) categoria;
                
                for (Utilizador utilizador: u.getUtilizadores().values()) {
                    out.writeObject(utilizador);
                }
            }
            else if (categoria instanceof Artigos) {
                Artigos u = (Artigos) categoria;
                
                for (Map<String, Artigo> artigos_utilizador: u.getArtigos().values()) {
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

            else if (categoria instanceof Transportadoras) {
                Transportadoras transportadoras = (Transportadoras) categoria;
                out.writeObject(transportadoras);
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
