package Code;

import java.io.File;
import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Utilizadores utilizadores = new Utilizadores();

        if (args.length>0) {
            if (args[0].equals("-U.txt")) {
                Parser.parseText(args[1], utilizadores);
            }
            else if (args[0].equals("-U.obj")) {
                Parser.parseBinary(args[1], utilizadores);
            }
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
                int id = sc.nextInt();
                sc.nextLine();

                Utilizador utilizador = utilizadores.getUtilizador(id);
                if (utilizador!=null) {
                    System.out.println(utilizador.toString());
                }
                else {
                    System.out.println("Não existe o utilizador " + id + ".");
                }
            }
            else if (operacao==3) {
                System.out.printf(utilizadores.toString());
            }

            System.out.println("\nPretende realizar mais alguma operação:\nNão (Pressione 'q')\nSim (Pressione qualquer outra tecla)");
            stopOperations = sc.nextLine().charAt(0);
        }

        System.out.println("Pretende guardar as novas alterações?\n(Sim->s)\n(Não->n)");
        char guardar = sc.nextLine().charAt(0);

        if (guardar=='s') {
            System.out.println("Quer atualizar o ficheiro daonde carregou os dados?\n(Sim->s)\n(Não->n)");
            guardar = sc.nextLine().charAt(0);

            if ((guardar=='s')) {
                Parser.storeBinary(utilizadores, args[1], true);
            }
            else {
                System.out.println("Digite o nome do ficheiro onde pretende guardar");
                String nome = sc.nextLine().trim();
                Parser.storeBinary(utilizadores, nome, true);
            }

        }

        sc.close();
    }
}
