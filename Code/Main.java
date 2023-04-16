package Code;

import java.io.File;
import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        if (!args[1].substring(args[1].length()-4).equals(".obj")) {
            System.out.println("Pretende converter um fichero .txt para .obj?\n(Sim -> s)\n(Não->n)");
            char convertBool = sc.nextLine().charAt(0);
            if (convertBool=='s') {
                Parser.convertTextToBinary(args[0], null);

                sc.close();
                return;
            }
        }

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
