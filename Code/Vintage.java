package Code;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.HashSet;
import java.util.Scanner;

public class Vintage implements Serializable {
    private Utilizadores utilizadores;
    private Artigos artigos;
    private Encomendas encomendas;
    private Transportadoras transportadoras;

    public Vintage() {
        this.utilizadores = new Utilizadores();
        this.artigos = new Artigos();
        this.encomendas = new Encomendas();
        this.transportadoras = new Transportadoras();
    }

    public Vintage(Utilizadores utilizadores, Artigos artigos, Encomendas encomedas, Transportadoras transportadoras) {
        this.utilizadores = utilizadores.clone();
        this.artigos = artigos.clone();
        this.encomendas = encomedas.clone();
        this.transportadoras = transportadoras.clone();
    }

    public Vintage(Vintage vintage) {
        this.utilizadores = vintage.utilizadores.clone();
        this.artigos = vintage.artigos.clone();
        this.encomendas = vintage.encomendas.clone();
        this.transportadoras = vintage.transportadoras.clone();
    }

    public void setUtilizadores (Utilizadores utilizadores) {
        this.utilizadores = utilizadores.clone();
    }

    public void setArtigos (Artigos artigos) {
        this.artigos = artigos.clone();
    }

    public void setEncomendas (Encomendas encomendas) {
        this.encomendas = encomendas.clone();
    }

    public void setTransportadoras (Transportadoras transportadoras) {
        this.transportadoras = transportadoras.clone();
    }

    public Utilizadores getUtilizadores() {
        return this.utilizadores.clone();
    }
    
    public Artigos getArtigos() {
        return this.artigos.clone();
    }

    public Encomendas getEncomendas() {
        return this.encomendas.clone();
    }

    public Transportadoras getTransportadoras() {
        return this.transportadoras.clone();
    }

    public String toString() {
        return this.utilizadores.toString() + this.artigos.toString() + this.encomendas.toString() + this.transportadoras.toString();
    }

    public Vintage clone() {
        return new Vintage(this);
    }

    Scanner sc = new Scanner(System.in);

    public void registaUtilizador(){
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

    public void imprimeUtilizador(){
        System.out.println("\nInsira o id do utilizador:");
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

    public void eliminaUtilizador(){
        System.out.println("\nInsira o nome do utilizador:");
                int id = sc.nextInt();
                sc.nextLine();
                utilizadores.removeUtilizador(id);
    }

    public void imprimeAllUtilizadores(){
        System.out.printf(utilizadores.toString());
    }

    public void inserirArtigo(){
        System.out.println("\nQue tipo de artigo pretende adicionar?\n1 - Sapatilha Nova\n2 - Sapatilha Usada\n3 - Sapatilha Premium\n4 - T-Shirt Nova\n5 - T-Shirt Usada\n6 - Mala Nova\n7 - Mala Usada\n8 - Mala Premium");

                int tipo_Artigo = sc.nextInt();
                sc.nextLine();

                if (tipo_Artigo==1) {
                    System.out.println("Insira o artigo no formato \"Id Proprietáiro, Descrição, Marca, Preço, Transportadora, Tamanho, Atacadores, Cor, Ano Lançamento, Desconto\"\nQualquer outro formato não será aceite");

                    String line = sc.nextLine().trim();
                    String[] tokens = line.split("\\s*,\\s*");
                    if (tokens.length==10) {
                        Artigo artigo = new Sapatilha_Nova(Integer.parseInt(tokens[0]), tokens[1], tokens[2], Double.parseDouble(tokens[3]), tokens[4], Integer.parseInt(tokens[5]), Boolean.parseBoolean(tokens[6]), tokens[7], Integer.parseInt(tokens[8]), Integer.parseInt(tokens[9]));
                        artigos.addArtigo(artigo, utilizadores);
                    }
                    else {
                        System.out.println("Os argumentos inseridos são inferiores/superiores aos necessários para criar uma Sapatilha Nova.");
                    }
                }
                else if (tipo_Artigo==2) {
                    System.out.println("Insira o artigo no formato \"Id Proprietáiro, Descrição, Marca, Preço, Transportadora, Tamanho, Atacadores, Cor, Ano Lançamento, Estado, Número de Donos, Desconto, \"\nQualquer outro formato não será aceite");

                    String line = sc.nextLine().trim();
                    String[] tokens = line.split("\\s*,\\s*");
                    if (tokens.length==12) {
                        Artigo artigo = new Sapatilha_Usada(Integer.parseInt(tokens[0]), tokens[1], tokens[2], Double.parseDouble(tokens[3]), tokens[4], Integer.parseInt(tokens[5]), Boolean.parseBoolean(tokens[6]), tokens[7], Integer.parseInt(tokens[8]), tokens[9], Byte.parseByte(tokens[10]), Integer.parseInt(tokens[11]));
                        artigos.addArtigo(artigo, utilizadores);
                    }
                    else {
                        System.out.println("Os argumentos inseridos são inferiores/superiores aos necessários para criar uma Sapatilha Usada.");
                    }
                }
                else if (tipo_Artigo==3) {
                    System.out.println("Insira o artigo no formato \"Id Proprietáiro, Descrição, Marca, Preço, Transportadora, Tamanho, Atacadores, Cor, Ano Lançamento, Desconto\"\nQualquer outro formato não será aceite");

                    String line = sc.nextLine().trim();
                    String[] tokens = line.split("\\s*,\\s*");
                    if (tokens.length==10) {
                        Transportadora transp = transportadoras.getTransportadora(tokens[4]);
                            if (transp instanceof Transportadora_Premium) {
                                Artigo artigo = new Sapatilha_Premium(Integer.parseInt(tokens[0]), tokens[1], tokens[2], Double.parseDouble(tokens[3]), (Transportadora_Premium) transp, Integer.parseInt(tokens[5]), Boolean.parseBoolean(tokens[6]), tokens[7], Integer.parseInt(tokens[8]), Integer.parseInt(tokens[9]));
                                artigos.addArtigo(artigo, utilizadores);
                            }
                            else {
                                System.out.println("Não é possível enviar uma artigo premium através de uma transportadora que não seja premium.");
                            }
                    }
                    else {
                        System.out.println("Os argumentos inseridos são inferiores/superiores aos necessários para criar uma Sapatilha Nova.");
                    }
                }
                else if (tipo_Artigo==4) {
                    System.out.println("Insira o artigo no formato \"Id Proprietáiro, Descrição, Marca, Preço, Transportadora, Tamanho, Padrão\"\nQualquer outro formato não será aceite");

                    String line = sc.nextLine().trim();
                    String[] tokens = line.split("\\s*,\\s*");
                    if (tokens.length==7) {
                        Artigo artigo = new T_Shirt_Nova(Integer.parseInt(tokens[0]), tokens[1], tokens[2], Double.parseDouble(tokens[3]), tokens[4], Integer.parseInt(tokens[5]), Integer.parseInt(tokens[6]));
                        artigos.addArtigo(artigo, utilizadores);
                    }
                    else {
                        System.out.println("Os argumentos inseridos são inferiores/superiores aos necessários para criar uma Sapatilha Premium.");
                    }
                }
                else if (tipo_Artigo==5) {
                    System.out.println("Insira o artigo no formato \"Id Proprietáiro, Descrição, Marca, Preço, Transportadora, Tamanho, Padrão, Número de Donos, Estado\"\nQualquer outro formato não será aceite");

                    String line = sc.nextLine().trim();
                    String[] tokens = line.split("\\s*,\\s*");
                    if (tokens.length==9) {
                        Artigo artigo = new T_Shirt_Usada(Integer.parseInt(tokens[0]), tokens[1], tokens[2], Double.parseDouble(tokens[3]), tokens[4], Integer.parseInt(tokens[5]), Integer.parseInt(tokens[6]), Byte.parseByte(tokens[7]), tokens[8]);
                        artigos.addArtigo(artigo, utilizadores);
                    }
                    else {
                        System.out.println("Os argumentos inseridos são inferiores/superiores aos necessários para criar uma Sapatilha Premium.");
                    }
                }
                else if (tipo_Artigo==6) {
                    System.out.println("Insira o artigo no formato \"Id Proprietáiro, Descrição, Marca, Preço, Transportadora, Dimensão, Material, Ano de Lançamento\"\nQualquer outro formato não será aceite");

                    String line = sc.nextLine().trim();
                    String[] tokens = line.split("\\s*,\\s*");
                    if (tokens.length==8) {
                        Artigo artigo = new Mala_Nova(Integer.parseInt(tokens[0]), tokens[1], tokens[2], Double.parseDouble(tokens[3]), tokens[4], Double.parseDouble(tokens[5]), tokens[6], Integer.parseInt(tokens[7]));
                        artigos.addArtigo(artigo, utilizadores);
                    }
                    else {
                        System.out.println("Os argumentos inseridos são inferiores/superiores aos necessários para criar uma Sapatilha Premium.");
                    }
                }
                else if (tipo_Artigo==7) {
                    System.out.println("Insira o artigo no formato \"Id Proprietáiro, Descrição, Marca, Preço, Transportadora, Dimensão, Material, Ano de Lançamento, Estado, Número de Donos\"\nQualquer outro formato não será aceite");

                    String line = sc.nextLine().trim();
                    String[] tokens = line.split("\\s*,\\s*");
                    if (tokens.length==10) {
                        Artigo artigo = new Mala_Usada(Integer.parseInt(tokens[0]), tokens[1], tokens[2], Double.parseDouble(tokens[3]), tokens[4], Double.parseDouble(tokens[5]), tokens[6], Integer.parseInt(tokens[7]), tokens[8], Byte.parseByte(tokens[9]));
                        artigos.addArtigo(artigo, utilizadores);
                    }
                    else {
                        System.out.println("Os argumentos inseridos são inferiores/superiores aos necessários para criar uma Sapatilha Premium.");
                    }
                }
                else if (tipo_Artigo==8) {
                    System.out.println("Insira o artigo no formato \"Id Proprietáiro, Descrição, Marca, Preço, Transportadora, Dimensão, Material, Ano de Lançamento\"\nQualquer outro formato não será aceite");

                    String line = sc.nextLine().trim();
                    String[] tokens = line.split("\\s*,\\s*");
                    if (tokens.length==8) {
                        Transportadora transp = transportadoras.getTransportadora(tokens[4]);
                        if (transp instanceof Transportadora_Premium) {
                            Artigo artigo = new Mala_Premium(Integer.parseInt(tokens[0]), tokens[1], tokens[2], Double.parseDouble(tokens[3]), (Transportadora_Premium) transp, Double.parseDouble(tokens[5]), tokens[6], Integer.parseInt(tokens[7]));
                            artigos.addArtigo(artigo, utilizadores);
                        }
                    }
                    else {
                        System.out.println("Os argumentos inseridos são inferiores/superiores aos necessários para criar uma Sapatilha Premium.");
                    }
                }
                else {
                    System.out.println("A opção que selecionou não é válida.");
                }
    }

    public void removerArtigo(){
        System.out.println("Insira o código do artigo que pretende remover.");
            String codigo = sc.nextLine().trim();
            artigos.removeArtigo(codigo);
    }

    public void imprimirArtigo(){
        System.out.println("Insira o código do artigo:");
            String codigo = sc.nextLine().trim();
            System.out.println(artigos.getArtigo(codigo).toString());
    }

    public void imprimirAllArtigos(){
        System.out.println(artigos.toString());
    }

    public void inserirTransportadora(){
        System.out.println("Qual o tipo de transportadora? \n1 - Normal \n2 - Premium");
                int tipo_transportadora = sc.nextInt();
                sc.nextLine();

                if (tipo_transportadora==1) {

                System.out.println("Insira a transportadora no formato: \"Nome, Preço base encomenda pequena, Preço base encomenda média, Preço base encomenda grande, imposto, margem de lucro, nº de dias que demora a preparacao da encomenda, nº de dias que demora a enviar a encomenda, nº dias máximo que a transportadora aceita para devoluções \"\nQualquer outro formato não será aceite");
                String line = sc.nextLine().trim();
                String[] tokens = line.split("\\s*,\\s*");
                if (tokens.length == 8) {
                    Transportadora transportadora = new Transportadora(tokens[0],
                    Double.parseDouble(tokens[1]),
                    Double.parseDouble(tokens[2]),
                    Double.parseDouble(tokens[3]),
                    Double.parseDouble(tokens[4]),
                    Double.parseDouble(tokens[5]),
                    Integer.parseInt(tokens[6]),
                    Integer.parseInt(tokens[7]),
                    Integer.parseInt(tokens[8]));
                    transportadoras.adicionaTransportadora(transportadora);
                }
                }
                else if (tipo_transportadora == 2) {
                
                    System.out.println("Insira a transportadora no formato: \"Nome, Preço base encomenda pequena, Preço base encomenda média, Preço base encomenda grande, imposto, margem de lucro,nº de dias que demora a preparacao da encomenda, nº de dias que demora a enviar a encomenda, nº dias máximo que a transportadora aceita para devoluções \"\nQualquer outro formato não será aceite");
                    String line = sc.nextLine().trim();
                    String[] tokens = line.split("\\s*,\\s*");
                    if (tokens.length == 8) {
                        Transportadora transportadora = new Transportadora_Premium(tokens[0],
                        Double.parseDouble(tokens[1]),
                        Double.parseDouble(tokens[2]),
                        Double.parseDouble(tokens[3]),
                        Double.parseDouble(tokens[4]),
                        Double.parseDouble(tokens[5]),
                        Integer.parseInt(tokens[6]),
                        Integer.parseInt(tokens[7]),
                        Integer.parseInt(tokens[8]));
                        transportadoras.adicionaTransportadora(transportadora);
                    }
                }
    }

    public void removerTransportadora(){
        System.out.println("Insira o nome da transportadora a remover:");
            String nome = sc.nextLine();
            transportadoras.removeTransportadora(nome);
    }

    public void imprimirTransportadora(){
        System.out.println("Insira o nome da transportadora a imprimir:");
            String nome = sc.nextLine();
            System.out.println(transportadoras.getTransportadora(nome));
    }

    public void imprimirAllTransportadoras(){
        System.out.println(transportadoras.toString());
    }

    public void inserirEncomenda(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                System.out.println("Insira a encomenda no formato: \"Id do Comprador, Estado da Encomenda, Data (dd/MM/yyyy), Id do Artigo 1, Id do Artigo 2, ...\"\nQualquer outro formato não será aceite");

                String line = sc.nextLine().trim();
                String[] tokens = line.split("\\s*,\\s*");
                if (tokens.length>=5) {

                    Collection<Artigo> conjunto_Artigos = new HashSet<>();
                    for (int i = 4;i<tokens.length;i++) {
                        Artigo artigo;
                        if ((artigo = artigos.getArtigo(tokens[i]))!=null) {
                            conjunto_Artigos.add(artigo);
                        }
                    }

                    String defaultVAlue = "DefaultValue";
                    Encomenda encomenda = new Encomenda(conjunto_Artigos, defaultVAlue, Integer.parseInt(tokens[1]), LocalDate.parse(tokens[2], formatter), transportadoras);
                    encomendas.addEncomenda(encomenda, Integer.parseInt(tokens[0]), transportadoras, utilizadores);
                }
                else {
                    System.out.println("Os argumentos inseridos são inferiores aos necessários para criar uma encomenda.");
                }
    }

    public void inserirArtigoEncomenda(){
        Collection<Artigo> conjunto_Artigos = new HashSet<>();
                char inserir_Artigos = 's';
                System.out.println("Insira o id da encomenda:");
                String id_Encomenda = sc.nextLine().trim();

                while (inserir_Artigos=='s') {
                    System.out.println("Insira o id do artigo que pretende adicionar:");

                    String id_Artigo = sc.nextLine().trim();
                    Artigo artigo;
                    if ((artigo = artigos.getArtigo(id_Artigo))!=null) {
                        conjunto_Artigos.add(artigo);
                    }

                    System.out.println("Pretende inserir mais algum artigo a esta encomeda?\n(Sim->s)\n(Não->n)");
                    inserir_Artigos = sc.nextLine().charAt(0);
                }
                if (conjunto_Artigos!=null) {
                    encomendas.add_Artigos_To_Encomenda(id_Encomenda, conjunto_Artigos, transportadoras);
                }
    }

    public void removerArtigoEncomenda(){
        System.out.println("Insira o id da encomenda:");
        String id_Encomenda = sc.nextLine().trim();
        System.out.println("Insira o id do artigo:");
        String id_Artigo = sc.nextLine().trim();

        encomendas.removeArtigo(id_Encomenda, id_Artigo, transportadoras);
    }

    public void removerEncomenda(){
        System.out.println("Insira o id da encomenda:");
            String id_Encomenda = sc.nextLine().trim();
                
            encomendas.removeEncomenda(id_Encomenda, transportadoras);
    }

    public void finalizarEncomenda(){
        System.out.println("Insira o id da encomenda:");
        String id_Encomenda = sc.nextLine().trim();
        System.out.println("Insira a data na qual finalizou a compra no formato \"dd/MM/yyyy\":");
        String data = sc.nextLine().trim();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        encomendas.finalizarEncomenda(id_Encomenda, LocalDate.parse(data, formatter), transportadoras, utilizadores);
    }

    public void imprimirEncomenda(){
        System.out.println("Insira o id da encomenda:");
            String id_Encomenda = sc.nextLine().trim();
            Collection<Encomenda> conjunto_Encomenda;
            if ((conjunto_Encomenda = encomendas.getEncomenda(id_Encomenda, transportadoras))!=null) {
                System.out.println();
                for (Encomenda e: conjunto_Encomenda) {
                        System.out.println(e.toString());
                }
            }
            else {
                    System.out.println("Essa encomenda não existe.");
            }
    }

    public void imprimirAllEncomendas(){
        System.out.println("\n" + encomendas.toString());
    }

    private static LocalDate currentDate = LocalDate.now();

    public static LocalDate getCurrentDate() {
        return currentDate;
    }

    public void saltarTempo(){
        System.out.println("1 - Saltar x número de dias \n2 - Saltar para uma data específica");
                int opcao = sc.nextInt();
                sc.nextLine();
                if (opcao == 1) {
                    System.out.println("Insira o número de dias que quer saltar:");
                    int dias = sc.nextInt();
                    sc.nextLine();
                    Vintage.currentDate = Vintage.currentDate.plusDays(dias);
                }
                else if (opcao == 2) {
                    System.out.println("Insira a data para a qual quer saltar (dia/mes/ano):");
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    LocalDate data = LocalDate.parse(sc.nextLine(), formatter);
                    if (data.isBefore (Vintage.currentDate)){ System.out.println("Não é possível regressar no tempo!");}
                    else {
                    Vintage.currentDate = data;
                    }
                }
                encomendas.updateEncomendas(currentDate,transportadoras);
    }   
}

