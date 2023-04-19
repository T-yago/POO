package Code;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.*;
import java.util.Map;
import java.util.Scanner;

public class Parser {

    public static void parseText(String file_Utilizadores, Utilizadores utilizadores) {
        File file = new File(file_Utilizadores);

        try {
            Scanner sc = new Scanner(file);
            
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
    }

    public static void parseBinary(String file_Utilizadores, Object categoria) {
        FileInputStream fileIn = null;
        BufferedInputStream bufferedIn = null;
        ObjectInputStream in = null;

        try {
            fileIn = new FileInputStream(file_Utilizadores);
            bufferedIn = new BufferedInputStream(fileIn);
            in = new ObjectInputStream(bufferedIn);

            if (categoria instanceof Utilizadores) {
                Utilizadores u = (Utilizadores) categoria;

                while (bufferedIn.available() > 0) {
                    Object obj = in.readObject();
                    if (obj instanceof Utilizador) {
                        Utilizador utilizador = (Utilizador) obj;
                        u.addUtilizador(utilizador);
                    }
                    else {
                        System.out.println("Objeto não conhecido: " + obj.getClass());
                    }

                    int id_Max;
                    if ((id_Max = u.getUtilizadores().size())>0) {
                        u.getUtilizador(id_Max).setNumero_Utilizadores(id_Max);
                    }
                }
            }
            else if (categoria instanceof Artigos) {
            
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
