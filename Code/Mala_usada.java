package Code;
import java.time.*;

public class Mala_usada extends Mala{
    private static int EXCELENTE = 5;               // Excelente
    private static int MUITO_BOM = 4;               // Muito Bom
    private static int BOM = 3;                     // Bom
    private static int SATISFATORIO = 2;            // Satisfatório
    private static int MAU = 1;                     // Mau
    private byte estado; // 1 a 10
    private byte numero_Donos;

    public Mala_usada () {
        this.estado = -1;
        this.numero_Donos = -1;
        this.setPrecoFinal(0);
    }

    public Mala_usada (String proprietario, String descricao, String marca, double preco, double dimensao, String material, int ano_Lancamento, byte estado, byte numero_donos) {
        super(proprietario,descricao,marca,preco,dimensao,material,ano_Lancamento);
        this.estado = estado;
        this.numero_Donos = numero_donos;
        this.setPrecoFinal(this.calculaPreco(0));
    }

    public Mala_usada (Mala_usada mala_usada) {
        super(mala_usada);
        this.estado = mala_usada.estado;
        this.numero_Donos = mala_usada.numero_Donos;
        this.setPrecoFinal(mala_usada.getPrecoFinal());
    }


    //falta ter em conta o material???? e deixar de ter redundancia em b e c
    public double calculaPreco(double desconto) { 
        return this.getPrecoBase() - (1/this.getDimensao()) * (this.getPrecoBase() / (LocalDate.now().getYear() - (double)this.getAnoLancamento()));
    }

    public Mala_usada clone () {
        return new Mala_usada(this);
    }

    public String toString () {
        return super.toString() + "\nEstado: " + this.estado
                                + "\nNúmero de donos: " + this.numero_Donos;
    }

}
