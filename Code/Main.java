package Code;

import java.io.File;
import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Utilizadores utilizadores = new Utilizadores();

        if (args.length>0) {
            Parser.parse(args[0], utilizadores);
        }
        
        char stopOperations = 'c';

        while (stopOperations!='q') {
            System.out.println("\nQue operação pretende executar:\n\nInserir um utilizador (1)\nImprimir um utilizador (2)\nImprimir todos os utilizadores (3)");
            int operacao = sc.nextInt();
            sc.nextLine();

            if (operacao==1) {
                System.out.println("\nInsira um utilizador no formato \"Email,Nome,Morada,Numero Fiscal\"\nQualquer outro formato não será aceite");

                String line = sc.nextLine().trim();
                String[] tokens = line.split("\\s*,\\s*");
                if (tokens.length>=4) {
                    Utilizador utilizador = new Utilizador(tokens[0], tokens[1], tokens[2], Integer.parseInt(tokens[3]));
                    utilizadores.addUtilizador(utilizador);
                }
            }
            else if (operacao==2) {
                System.out.println("\nInsira o nome do utilizador:");
                String nome = sc.nextLine().trim();

                Utilizador utilizador = utilizadores.getUtilizador(nome);
                if (utilizador!=null) {
                    System.out.println(utilizador.toString());
                }
                else {
                    System.out.println("Não existe o utilizador " + nome + ".");
                }
            }
            else if (operacao==3) {
                System.out.printf(utilizadores.toString());
            }

            System.out.println("\nPretende realizar mais alguma operação:\nNão (Pressione 'q')\nSim (Pressione qualquer outra tecla)");
            stopOperations = sc.nextLine().charAt(0);
        }

        sc.close();
    }
}
