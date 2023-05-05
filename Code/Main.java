package Code;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.HashSet;
import java.util.Scanner;

public class Main {
    

    public static void main(String[] args) {

        Vintage vintage = new Vintage();
        // Criar os caminhos para os ficheiros

        String ficheiro_Utilizadores;
        String ficheiro_Artigos;
        String ficheiro_Transportadoras;
        String ficheiro_Encomendas;

        if (args.length>0) {
            if (args[0].equals("-txt")) {
                ficheiro_Utilizadores = args[1] + "input_Utilizadores.txt";
                ficheiro_Artigos = args[1] + "input_Artigos.txt";
                ficheiro_Transportadoras = args[1] + "input_Transportadoras.txt";
                ficheiro_Encomendas = args[1] + "input_Encomendas.txt";

                 Parser.parseText(ficheiro_Utilizadores, ficheiro_Artigos, ficheiro_Transportadoras, ficheiro_Encomendas, vintage);
            }
            else if (args[0].equals("-obj")) {
                ficheiro_Utilizadores = args[1] + "input.obj";

                Parser.parseBinary(ficheiro_Utilizadores, vintage);
            }
        }

        Menu.run(vintage);
        
    }
}
