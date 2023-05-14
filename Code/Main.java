package Code;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.HashSet;
import java.util.Scanner;

public class Main {
    
    private static LocalDate currentDate = LocalDate.now();

    public static LocalDate getCurrentDate() {
        return currentDate;
    }

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


        NewMenu utilizadores = new NewMenu(new String[]{
            "Inserir um utilizador",
            "Imprimir um utilizador",
            "Eliminar um utilizador",
            "Imprimir todos os utilizadores"
        });
        utilizadores.setHandler(1, (NewMenu.Handler)()->vintage.registaUtilizador());
        utilizadores.setHandler(2, (NewMenu.Handler)()->vintage.imprimeUtilizador());
        utilizadores.setHandler(3, (NewMenu.Handler)()->vintage.eliminaUtilizador());
        utilizadores.setHandler(4, (NewMenu.Handler)()->vintage.imprimeAllUtilizadores());


        NewMenu artigos = new NewMenu(new String[]{
            "Adicionar um artigo", 
            "Remover um artigo" ,
            "Imprimir um artigo",
            "Imprimir todos os artigos"
        });
        artigos.setHandler(1, (NewMenu.Handler)()->vintage.inserirArtigo());
        artigos.setHandler(2, (NewMenu.Handler)()->vintage.removerArtigo());
        artigos.setHandler(3, (NewMenu.Handler)()->vintage.imprimirArtigo());
        artigos.setHandler(4, (NewMenu.Handler)()->vintage.imprimirAllArtigos());

        NewMenu encomendas = new NewMenu(new String[]{
            "Adicionar encomendas", 
            "Adicionar artigos a uma encomenda",
            "Remover um artigo de uma encomenda",
            "Remover uma encomenda", 
            "Finalizar uma encomenda",
            "Imprimir uma encomenda",
            "Imprimir todas as encomendas"
        });
        encomendas.setHandler(1, (NewMenu.Handler)()->vintage.inserirEncomenda());
        encomendas.setHandler(2, (NewMenu.Handler)()->vintage.inserirArtigoEncomenda());
        encomendas.setHandler(3, (NewMenu.Handler)()->vintage.removerArtigoEncomenda());
        encomendas.setHandler(4, (NewMenu.Handler)()->vintage.removerEncomenda());
        encomendas.setHandler(5, (NewMenu.Handler)()->vintage.finalizarEncomenda());
        encomendas.setHandler(6, (NewMenu.Handler)()->vintage.imprimirEncomenda());
        encomendas.setHandler(7, (NewMenu.Handler)()->vintage.imprimirAllEncomendas());

        
        NewMenu transportadoras = new NewMenu(new String[]{
            "Inserir uma transportadora",
            "Remover uma transportadora",
            "Imprimir uma transportadora",
            "Imprimir todas as transportadoras" 
        });
        transportadoras.setHandler(1, (NewMenu.Handler)()->vintage.inserirTransportadora());
        transportadoras.setHandler(2, (NewMenu.Handler)()->vintage.removerTransportadora());
        transportadoras.setHandler(3, (NewMenu.Handler)()->vintage.imprimirTransportadora());
        transportadoras.setHandler(4, (NewMenu.Handler)()->vintage.imprimirAllTransportadoras());

        NewMenu menu = new NewMenu(new String[]{
            "Utilizadores",
            "Artigos",
            "Encomendas", 
            "Transportadoras",
            "Saltar no tempo",
            "Data Atual: " + Main.currentDate
        });
        menu.setHandler(1, (NewMenu.Handler)()->utilizadores.run());
        menu.setHandler(2, (NewMenu.Handler)()->artigos.run());
        menu.setHandler(3, (NewMenu.Handler)()->encomendas.run());
        menu.setHandler(4, (NewMenu.Handler)()->transportadoras.run());
        menu.setHandler(5, (NewMenu.Handler)()->vintage.saltarTempo());
        
    


       



        

       

    

       menu.run();  
    }
}
