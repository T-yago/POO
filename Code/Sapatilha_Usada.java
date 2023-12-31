package Code;

public class Sapatilha_Usada extends Sapatilha implements Usados {
    private static int EXCELENTE = 5;               // Excelente
    private static int MUITO_BOM = 4;               // Muito Bom
    private static int BOM = 3;                     // Bom
    private static int SATISFATORIO = 2;            // Satisfatório
    private static int MAU = 1;                     // Mau
    private byte estado;
    private byte numero_Donos;

    public Sapatilha_Usada() {
        super();
        this.estado = -1;
        this.numero_Donos = -1;
        this.setPrecoFinal(this.calculaPreco(0));
    }

    public Sapatilha_Usada(int Id_proprietario, String descricao, String marca, double preco, String transportadora, int tamanho, boolean atacadores, String cor, int ano_Lancamento, String estado, byte numero_Donos, int desconto) {
        super(Id_proprietario, descricao, marca, preco, transportadora, tamanho, atacadores, cor, ano_Lancamento, desconto);
        if (estado.equals("Novo")) this.estado = 1;
        else if (estado.equals("Muito_Bom")) this.estado = 2;
        else if (estado.equals("Bom")) this.estado = 3;
        else if (estado.equals("Satisfatorio")) this.estado = 4;
        else if (estado.equals("Mau")) this.estado = 5;
        this.numero_Donos = numero_Donos;
        this.setPrecoFinal(this.calculaPreco(desconto));
    }

    public Sapatilha_Usada(Sapatilha_Usada sapatilha_Usada) {
        super(sapatilha_Usada);
        this.estado = sapatilha_Usada.estado;
        this.numero_Donos = sapatilha_Usada.numero_Donos;
        this.setPrecoFinal(sapatilha_Usada.getPrecoFinal());
    }


    public byte get_estado () {
        return this.estado;
    }

    public byte get_numero_Donos () {
        return this.numero_Donos;
    }

    public void setEstado(String estado) {
        if (estado.equals("Novo")) this.estado = 1;
        else if (estado.equals("Muito_Bom")) this.estado = 2;
        else if (estado.equals("Bom")) this.estado = 3;
        else if (estado.equals("Satisfatorio")) this.estado = 4;
        else if (estado.equals("Mau")) this.estado = 5;
    }

    public void setNumeroDonos(byte numero_Donos) {
        this.numero_Donos = numero_Donos;
    }

    public double calculaPreco(double desconto) {
        return (this.getPrecoBase() - this.getPrecoBase() / (double) this.get_numero_Donos() * this.get_estado() / 10.0) * ((100 - desconto)*0.01);
    }

    public void recalculaPreco () {
        this.setPrecoFinal(this.calculaPreco(0));
    }

    public String toString() {
        return super.toString()
                  + "\nEstado: " + this.estado
                  + "\nNúmero de donos: " + this.numero_Donos;
    }

    public Sapatilha_Usada clone () {
        return new Sapatilha_Usada(this);
    }
}
