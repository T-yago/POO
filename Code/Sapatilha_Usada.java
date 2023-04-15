package Code;

public class Sapatilha_Usada extends Sapatilha{
    private static int EXCELENTE = 5;               // Excelente
    private static int MUITO_BOM = 4;               // Muito Bom
    private static int BOM = 3;                     // Bom
    private static int SATISFATORIO = 2;            // Satisfatório
    private static int MAU = 1;                     // Mau
    private int estado;
    private int numero_Donos;

    public Sapatilha_Usada() {
        super();
        this.estado = -1;
        this.numero_Donos = -1;
        this.setPrecoFinal(this.calculaPreco(0, 1, -1));
    }

    public Sapatilha_Usada(String proprietario, String descricao, String marca, double preco, int tamanho, boolean atacadores, String cor, int ano_Lancamento, String estado, int numero_Donos) {
        super(proprietario, descricao, marca, preco, tamanho, atacadores, cor, ano_Lancamento);
        if (estado.equals("Novo")) this.estado = 1;
        else if (estado.equals("Muito_Bom")) this.estado = 2;
        else if (estado.equals("Bom")) this.estado = 3;
        else if (estado.equals("Satisfatorio")) this.estado = 4;
        else if (estado.equals("Mau")) this.estado = 5;
        this.numero_Donos = numero_Donos;
        this.setPrecoFinal(this.calculaPreco(preco, numero_Donos, this.estado));
    }

    public Sapatilha_Usada(Sapatilha_Usada sapatilha_Usada) {
        super(sapatilha_Usada);
        this.estado = sapatilha_Usada.estado;
        this.numero_Donos = sapatilha_Usada.numero_Donos;
        this.setPrecoFinal(sapatilha_Usada.getPrecoFinal());
    }

    public double calculaPreco(double preco_Base, int numero_Donos, int estado) {
        double preco_Final = preco_Base;

        if (estado==4) preco_Final = preco_Final/numero_Donos * 0.85;
        else if (estado==3) preco_Final = preco_Final/numero_Donos * 0.60;
        else if (estado==2) preco_Final = preco_Final/numero_Donos * 0.45;
        else if (estado==1) preco_Final = preco_Final/numero_Donos * 0.25;

        return preco_Final;
    }
}
