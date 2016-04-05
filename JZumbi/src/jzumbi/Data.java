package jzumbi;

import javax.swing.JOptionPane;

public class Data {
    
    public Data(int dia, int mes, int ano){
        this.mes = mes;
        this.ano = ano;
        this.dia = checarDia(dia);
    }
    
    private int checarDia(int dia){
    
        if ((dia > 0) && (dia <= DIAS_POR_MES[this.mes]))
            return dia;

        if ((this.mes == 2) && (dia == 29) && ((ano % 400 == 0) || ((this.ano % 4 == 0) && (this.ano % 100 != 0))))
            return dia;

        JOptionPane.showMessageDialog(null, "Dia invalido. \n");
        return 1;
    }
    
    public void incrementarDia(){
        int fev;

        if ((this.ano % 400 == 0) || ((this.ano % 4 == 0) && (this.ano % 100 != 0)))
            fev = 29;
        else
            fev = 28;

        if (dia < DIAS_POR_MES[this.mes])
            this.dia++;
        else if (dia == DIAS_POR_MES[this.mes]){
            this.dia = 1;
            if (mes == 12){
                this.mes = 1;
                this.ano++;
            }else
            this.mes++;
        }
    }
    
    public void imprimir(){
        JOptionPane.showMessageDialog(null, dia + "/" + mes + "/" + ano);
    }

    public static final int[] DIAS_POR_MES = {0,31,28,31,30,31,30,31,31,30,31,30,31};
    private int mes;
    private int dia;
    private int ano;
}
