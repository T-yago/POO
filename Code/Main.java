package Code;

import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Utilizadores utilizadores = new Utilizadores();
        Artigos artigos = new Artigos();

        // Criar os caminhos para os ficheiros

        String ficheiro_Utilizadores;
        String ficheiro_Artigos;

        if (args.length>0) {
            if (args[0].equals("-txt")) {
                ficheiro_Utilizadores = args[1] + "input_Utilizadores.txt";
                ficheiro_Artigos = args[1] + "input_Artigos.txt";

                Parser.parseText(ficheiro_Utilizadores, ficheiro_Artigos, utilizadores, artigos);
            }
            else if (args[0].equals("-obj")) {
                ficheiro_Utilizadores = args[1] + "input.obj";

                Parser.parseBinary(ficheiro_Utilizadores, utilizadores, artigos, null);
            }
        }
        
        char stopOperations = 'c';

        while (stopOperations!='q') {
            System.out.println("\nQue operação pretende executar:\n\nInserir um utilizador (1)\nImprimir um utilizador (2)\nImprimir todos os utilizadores (3)\nAdicionar um artigo (4)\nRemover um artigo (5)\nImprimir um artigo (6)\nImprimir todos os artigos (7)");
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
                System.out.println("\nQue tipo de artigo pretende adicionar?\nSapatilha Nova (1)\nSapatilha Usada (2)\nSapatilha Premium (3)\nT-Shirt Nova (4)\nT-Shirt Usada (5)\nMala Nova (6)\nMala Usada (7)\nMala Premium (8)");

                int tipo_Artigo = sc.nextInt();
                sc.nextLine();

                if (tipo_Artigo==1) {
                    System.out.println("Insira o artigo no formato \"Id Proprietáiro, Descrição, Marca, Preço, Tamanho, Atacadores, Cor, Ano Lançamento, Desconto\"\nQualquer outro formato não será aceite");

                    String line = sc.nextLine().trim();
                    String[] tokens = line.split("\\s*,\\s*");
                    if (tokens.length==9) {
                        Artigo artigo = new Sapatilha_Nova(Integer.parseInt(tokens[0]), tokens[1], tokens[2], Double.parseDouble(tokens[3]), Integer.parseInt(tokens[4]), Boolean.parseBoolean(tokens[5]), tokens[6], Integer.parseInt(tokens[7]), Integer.parseInt(tokens[8]));
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
                        Artigo artigo = new Sapatilha_Usada(Integer.parseInt(tokens[0]), tokens[1], tokens[2], Double.parseDouble(tokens[3]), Integer.parseInt(tokens[4]), Boolean.parseBoolean(tokens[5]), tokens[6], Integer.parseInt(tokens[7]), tokens[8], Byte.parseByte(tokens[9]), Integer.parseInt(tokens[10]));
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
                        Artigo artigo = new Sapatilha_Premium(Integer.parseInt(tokens[0]), tokens[1], tokens[2], Double.parseDouble(tokens[3]), Integer.parseInt(tokens[4]), Boolean.parseBoolean(tokens[5]), tokens[6], Integer.parseInt(tokens[7]), Integer.parseInt(tokens[8]));
                        artigos.addArtigo(artigo, utilizadores);
                    }
                    else {
                        System.out.println("Os argumentos inseridos são inferiores/superiores aos necessários para criar uma Sapatilha Nova.");
                    }
                }
                else if (tipo_Artigo==4) {
                    System.out.println("Insira o artigo no formato \"Id Proprietáiro, Descrição, Marca, Preço, Tamanho, Padrão\"\nQualquer outro formato não será aceite");

                    String line = sc.nextLine().trim();
                    String[] tokens = line.split("\\s*,\\s*");
                    if (tokens.length==6) {
                        Artigo artigo = new T_Shirt_Nova(Integer.parseInt(tokens[0]), tokens[1], tokens[2], Double.parseDouble(tokens[3]), Integer.parseInt(tokens[4]), Integer.parseInt(tokens[5]));
                        artigos.addArtigo(artigo, utilizadores);
                    }
                    else {
                        System.out.println("Os argumentos inseridos são inferiores/superiores aos necessários para criar uma Sapatilha Premium.");
                    }
                }
                else if (tipo_Artigo==5) {
                    System.out.println("Insira o artigo no formato \"Id Proprietáiro, Descrição, Marca, Preço, Tamanho, Padrão, Número de Donos, Estado\"\nQualquer outro formato não será aceite");

                    String line = sc.nextLine().trim();
                    String[] tokens = line.split("\\s*,\\s*");
                    if (tokens.length==8) {
                        Artigo artigo = new T_Shirt_Usada(Integer.parseInt(tokens[0]), tokens[1], tokens[2], Double.parseDouble(tokens[3]), Integer.parseInt(tokens[4]), Integer.parseInt(tokens[5]), Byte.parseByte(tokens[6]), tokens[7]);
                        artigos.addArtigo(artigo, utilizadores);
                    }
                    else {
                        System.out.println("Os argumentos inseridos são inferiores/superiores aos necessários para criar uma Sapatilha Premium.");
                    }
                }
                else if (tipo_Artigo==6) {
                    System.out.println("Insira o artigo no formato \"Id Proprietáiro, Descrição, Marca, Preço, Dimensão, Material, Ano de Lançamento\"\nQualquer outro formato não será aceite");

                    String line = sc.nextLine().trim();
                    String[] tokens = line.split("\\s*,\\s*");
                    if (tokens.length==7) {
                        Artigo artigo = new Mala_Nova(Integer.parseInt(tokens[0]), tokens[1], tokens[2], Double.parseDouble(tokens[3]), Double.parseDouble(tokens[4]), tokens[5], Integer.parseInt(tokens[6]));
                        artigos.addArtigo(artigo, utilizadores);
                    }
                    else {
                        System.out.println("Os argumentos inseridos são inferiores/superiores aos necessários para criar uma Sapatilha Premium.");
                    }
                }
                else if (tipo_Artigo==7) {
                    System.out.println("Insira o artigo no formato \"Id Proprietáiro, Descrição, Marca, Preço, Dimensão, Material, Ano de Lançamento, Estado, Número de Donos\"\nQualquer outro formato não será aceite");

                    String line = sc.nextLine().trim();
                    String[] tokens = line.split("\\s*,\\s*");
                    if (tokens.length==9) {
                        Artigo artigo = new Mala_Usada(Integer.parseInt(tokens[0]), tokens[1], tokens[2], Double.parseDouble(tokens[3]), Double.parseDouble(tokens[4]), tokens[5], Integer.parseInt(tokens[6]), tokens[7], Byte.parseByte(tokens[8]));
                        artigos.addArtigo(artigo, utilizadores);
                    }
                    else {
                        System.out.println("Os argumentos inseridos são inferiores/superiores aos necessários para criar uma Sapatilha Premium.");
                    }
                }
                else if (tipo_Artigo==8) {
                    System.out.println("Insira o artigo no formato \"Id Proprietáiro, Descrição, Marca, Preço, Dimensão, Material, Ano de Lançamento\"\nQualquer outro formato não será aceite");

                    String line = sc.nextLine().trim();
                    String[] tokens = line.split("\\s*,\\s*");
                    if (tokens.length==7) {
                        Artigo artigo = new Mala_Premium(Integer.parseInt(tokens[0]), tokens[1], tokens[2], Double.parseDouble(tokens[3]), Double.parseDouble(tokens[4]), tokens[5], Integer.parseInt(tokens[6]));
                        artigos.addArtigo(artigo, utilizadores);
                    }
                    else {
                        System.out.println("Os argumentos inseridos são inferiores/superiores aos necessários para criar uma Sapatilha Premium.");
                    }
                }
                else {
                    System.out.println("A opção que selecionou não é válida.");
                }
            }
            else if (operacao==5) {
                System.out.println("Insira o código do artigo que pretende remover.");
                String codigo = sc.nextLine().trim();
                artigos.removeArtigo(codigo);
            }
            else if (operacao==6) {
                System.out.println("Insira o código do artigo:");
                String codigo = sc.nextLine().trim();
                System.out.println(artigos.getArtigo(codigo).toString());
            }
            else if (operacao==7) {
                System.out.println(artigos.toString());
            }

            System.out.println("\nPretende realizar mais alguma operação:\nNão (Pressione 'q')\nSim (Pressione qualquer outra tecla)");
            stopOperations = sc.nextLine().charAt(0);
        }

        System.out.println("Pretende guardar as novas alterações?\n(Sim->s)\n(Não->n)");
        char guardar = sc.nextLine().charAt(0);

        if (guardar=='s') {
            Parser.storeBinary(utilizadores, true);
            Parser.storeBinary(artigos, false);
        }

        sc.close();
    }
}
