package Code;

public class T_Shirt_Usada extends T_Shirt{
    private static int EXCELENTE = 5;               // Excelente
    private static int MUITO_BOM = 4;               // Muito Bom
    private static int BOM = 3;                     // Bom
    private static int SATISFATORIO = 2;            // Satisfatório
    private static int MAU = 1;                     // Mau
    private byte estado; // 1 a 10
    private byte numero_Donos;


    public byte get_estado () {
        return this.estado;
    }

    public byte numero_Donos () {
        return this.numero_Donos;
    }

    public T_Shirt_Usada() {
        super();
        this.estado = -1;
        this.numero_Donos = -1;
        this.setPrecoFinal(0);
    }

    public T_Shirt_Usada(int Id_proprietario, String descricao, String marca, double preco, int tamanho, int padrao, byte numero_Donos, byte estado ) {
        super(Id_proprietario, descricao, marca, preco, tamanho, padrao);
        this.estado = estado;
        this.numero_Donos = numero_Donos;
        this.setPrecoFinal(this.calculaPreco(0));
    }

    public T_Shirt_Usada(T_Shirt_Usada t_Shirt_Usada) {
        super(t_Shirt_Usada);
        this.estado = t_Shirt_Usada.estado;
        this.numero_Donos = t_Shirt_Usada.numero_Donos;
        this.setPrecoFinal(t_Shirt_Usada.getPrecoFinal());
    }

    public double calculaPreco(double desconto) {
        if (this.getPadrao() != 0) {
            return this.getPrecoBase() * 0.50;
        }
        return this.getPrecoBase();
    }

    public T_Shirt_Usada clone() {
        return new T_Shirt_Usada(this);
    }

    public String toString() {
        return super.toString() + "\nEstado: " + this.estado
                                + "\nNúmero_Donos: " + this.numero_Donos;
    }
}
