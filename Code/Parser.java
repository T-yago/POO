package Code;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Parser {

    public static void parse(String file_Utilizadores, Utilizadores utilizadores) {
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
}
