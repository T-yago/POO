package Code;
import java.time.*;

public class Mala_Usada extends Mala implements Usados {
    private static int EXCELENTE = 5;               // Excelente
    private static int MUITO_BOM = 4;               // Muito Bom
    private static int BOM = 3;                     // Bom
    private static int SATISFATORIO = 2;            // Satisfatório
    private static int MAU = 1;                     // Mau
    private byte estado; // 1 a 10
    private byte numero_Donos;

    public Mala_Usada () {
        this.estado = -1;
        this.numero_Donos = -1;
        this.setPrecoFinal(0);
    }

    public Mala_Usada (int Id_proprietario, String descricao, String marca, double preco, String transportadora, double dimensao, String material, int ano_Lancamento, String estado, byte numero_donos) {
        super(Id_proprietario, descricao, marca, preco, transportadora, dimensao, material, ano_Lancamento);
        if (estado.equals("Novo")) this.estado = 1;
        else if (estado.equals("Muito_Bom")) this.estado = 2;
        else if (estado.equals("Bom")) this.estado = 3;
        else if (estado.equals("Satisfatorio")) this.estado = 4;
        else if (estado.equals("Mau")) this.estado = 5;
        this.numero_Donos = numero_donos;
        this.setPrecoFinal(this.calculaPreco(0));
    }

    public Mala_Usada (Mala_Usada mala_usada) {
        super(mala_usada);
        this.estado = mala_usada.estado;
        this.numero_Donos = mala_usada.numero_Donos;
        this.setPrecoFinal(mala_usada.getPrecoFinal());
    }


    //falta ter em conta o material???? 
    public double calculaPreco(double desconto) { 
        return this.getPrecoBase() - (1/this.getDimensao()) * (this.getPrecoBase() / (LocalDate.now().getYear() - (double)this.getAnoLancamento()));
    }

    public Mala_Usada clone () {
        return new Mala_Usada(this);
    }

    public String toString () {
        return super.toString() + "\nEstado: " + this.estado
                                + "\nNúmero de donos: " + this.numero_Donos;
    }

}
