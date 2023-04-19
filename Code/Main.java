package Code;

import java.io.File;
import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Utilizadores utilizadores = new Utilizadores();
        Artigos artigos = new Artigos();

        // Criar os caminhos para os ficheiros

        String ficheiro_Utilizadores;

        if (args.length>0) {
            if (args[0].equals("-U.txt")) {
                ficheiro_Utilizadores = args[1] + "input_Utilizadores.txt";

                Parser.parseText(ficheiro_Utilizadores, utilizadores);
            }
            else if (args[0].equals("-U.obj")) {
                ficheiro_Utilizadores = args[1] + "input_Utilizadores.obj";

                Parser.parseBinary(ficheiro_Utilizadores, utilizadores);
            }
        }
        
        char stopOperations = 'c';

        while (stopOperations!='q') {
            System.out.println("\nQue operação pretende executar:\n\nInserir um utilizador (1)\nImprimir um utilizador (2)\nImprimir todos os utilizadores (3)\n Adicionar um artigo (4)");
            int operacao = sc.nextInt();
            sc.nextLine();

            if (operacao==1) {
                System.out.println("\nInsira um utilizador no formato \"Email,Nome,Morada,Numero Fiscal\"\nQualquer outro formato não será aceite");

                String line = sc.nextLine().trim();
                String[] tokens = line.split("\\s*,\\s*");
                if (tokens.length==4) {
                    Utilizador utilizador = new Utilizador(tokens[0], tokens[1], tokens[2], Integer.parseInt(tokens[3]));
                    utilizadores.addUtilizador(utilizador);
                }
                else {
                    System.out.println("Os argumentos inseridos são inferiores/superiores aos necessários para criar um Utilizador.");
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
            else if (operacao==4) {
                System.out.println("\nQue tipo de artigo pretende adicionar?\nSapatilha Nova (1)\nSapatilha Usada (2)\nSapatilha Premium (3)\n T-Shirt Nova (4)\n T-Shirt Usada (5)\n Mala Nova (6)\n Mala Usada (7)\nMala Premium (8)");

                int tipo_Artigo = sc.nextInt();

                if (tipo_Artigo==1) {
                    System.out.println("Insira o artigo no formato \"Id Proprietáiro, Descrição, Marca, Preço, Tamanho, Atacadores, Cor, Ano Lançamento, Desconto\"\nQualquer outro formato não será aceite");

                    String line = sc.nextLine().trim();
                    String[] tokens = line.split("\\s*,\\s*");
                    if (tokens.length==9) {
                        Artigo artigo = new Sapatilha_Nova(tokens[0], tokens[1], tokens[2], Double.parseDouble(tokens[3]), Integer.parseInt(tokens[4]), Boolean.parseBoolean(tokens[5]), tokens[6], Integer.parseInt(tokens[7]), Integer.parseInt(tokens[8]));
                        artigos.addArtigo(artigo, utilizadores);
                    }
                    else {
                        System.out.println("Os argumentos inseridos são inferiores/superiores aos necessários para criar uma Sapatilha Nova.");
                    }
                }
                else if (tipo_Artigo==2) {
                    System.out.println("Insira o artigo no formato \"Id Proprietáiro, Descrição, Marca, Preço, Tamanho, Atacadores, Cor, Ano Lançamento, Estado, Número de Donos, Desconto, \"\nQualquer outro formato não será aceite");

                    String line = sc.nextLine().trim();
                    String[] tokens = line.split("\\s*,\\s*");
                    if (tokens.length==11) {
                        Artigo artigo = new Sapatilha_Usada(tokens[0], tokens[1], tokens[2], Double.parseDouble(tokens[3]), Integer.parseInt(tokens[4]), Boolean.parseBoolean(tokens[5]), tokens[6], Integer.parseInt(tokens[7]), tokens[8], Byte.parseByte(tokens[9]), Integer.parseInt(tokens[10]));
                        artigos.addArtigo(artigo, utilizadores);
                    }
                    else {
                        System.out.println("Os argumentos inseridos são inferiores/superiores aos necessários para criar uma Sapatilha Usada.");
                    }
                }
                else if (tipo_Artigo==3) {
                    System.out.println("Insira o artigo no formato \"Id Proprietáiro, Descrição, Marca, Preço, Tamanho, Atacadores, Cor, Ano Lançamento, Desconto\"\nQualquer outro formato não será aceite");

                    String line = sc.nextLine().trim();
                    String[] tokens = line.split("\\s*,\\s*");
                    if (tokens.length==9) {
                        Artigo artigo = new Sapatilha_Premium(tokens[0], tokens[1], tokens[2], Double.parseDouble(tokens[3]), Integer.parseInt(tokens[4]), Boolean.parseBoolean(tokens[5]), tokens[6], Integer.parseInt(tokens[7]), Integer.parseInt(tokens[8]));
                        artigos.addArtigo(artigo, utilizadores);
                    }
                    else {
                        System.out.println("Os argumentos inseridos são inferiores/superiores aos necessários para criar uma Sapatilha Premium.");
                    }
                }
                else if (tipo_Artigo==4) {

                }
                else if (tipo_Artigo==5) {

                }
                else if (tipo_Artigo==6) {

                }
                else if (tipo_Artigo==7) {

                }
                else if (tipo_Artigo==8) {

                }
                else {
                    System.out.println("A opção que selecionou não é válida.");
                }
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
