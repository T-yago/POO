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

    public static void parseBinary(String file_Utilizadores, Object utilizadores) {
        FileInputStream fileIn = null;
        BufferedInputStream bufferedIn = null;
        ObjectInputStream in = null;

        try {
            fileIn = new FileInputStream("Code/input_Utilizadores.obj");
            bufferedIn = new BufferedInputStream(fileIn);
            in = new ObjectInputStream(bufferedIn);

            if (utilizadores instanceof Utilizadores) {
                Utilizadores u = (Utilizadores) utilizadores;

                while (bufferedIn.available() > 0) {
                    Object obj = in.readObject();
                    if (obj instanceof Utilizador) {
                        Utilizador utilizador = (Utilizador) obj;
                        u.addUtilizador(utilizador);
                    } else {
                        System.out.println("Objeto não conhecido: " + obj.getClass());
                    }
                }

                int id_Max = u.getUtilizadores().size();
                u.getUtilizador(id_Max).setNumero_Utilizadores(id_Max);
            }
            else if (utilizadores instanceof Encomendas) {

            }




        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void convertTextToBinary(String file_Utilizadores, String file_Output) {
        File file = new File(file_Utilizadores);
        File file2;
        ObjectOutputStream out = null;

        try {
            Scanner sc = new Scanner(file);
            if (file_Output == null) {
                file2 = new File("Code/input_Utilizadores.obj");

                if (file2.exists()) {
                    file2.delete();
                }

                out = new ObjectOutputStream(new FileOutputStream("Code/input_Utilizadores.obj"));
            }
            else {
                file2 = new File(file_Output);

                if (file2.exists()) {
                    file2.delete();
                }

                out = new ObjectOutputStream(new FileOutputStream(file_Output));
            }

            while (sc.hasNextLine()) {
                String line = sc.nextLine().trim();

                String[] tokens = line.split("\\s*,\\s*");
                if (tokens.length>=4) {
                    out.writeObject(new Utilizador(tokens[0], tokens[1], tokens[2], Integer.parseInt(tokens[3])));
                }
            }

            sc.close();
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

    public static void storeBinary(Utilizadores utilizadores, String file_Output){
        File file;
        ObjectOutputStream out = null;

        try {
            if (file_Output == null) {
                file = new File("Code/input_Utilizadores.obj");

                if (file.exists()) {
                    file.delete();
                }

                out = new ObjectOutputStream(new FileOutputStream("Code/input_Utilizadores.obj"));
            }
            else {
                file = new File(file_Output);

                if (file.exists()) {
                    file.delete();
                }

                out = new ObjectOutputStream(new FileOutputStream(file_Output));
            }

            for (Utilizador u: utilizadores.getUtilizadores().values()) {
                out.writeObject(u);
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
