package jzumbi;

import javax.swing.JOptionPane;
import jzumbi.Humano.ZumbiCharger;
import jzumbi.Humano.ZumbiHunter;
import jzumbi.mapa.Mapa;

public class JZumbi {

    public static void main(String[] args) {
        String nome = JOptionPane.showInputDialog("Digite o nome do zumbi: ");
        Object jogador = new Object();
        
        String escolhaClasse = JOptionPane.showInputDialog("Escolha a classe do zumbi: \n1-Hunter; \n2-Charger.");
        
        if(escolhaClasse.equals("1"))
            jogador = new ZumbiHunter(nome,4000,200);
        else if(escolhaClasse.equals("2"))
            jogador = new ZumbiCharger(nome,6000,300);
        
        Mapa m1 = new Mapa(jogador);
        
        m1.iniciarMapa();
        
        Integer c = 0;
        String op;
        do{
            op = menu();
            escolha(op,c,jogador,m1);
        }while(!"666".equals(op));
    }
    
    private static void escolha(String op, Integer c, Object z1, Mapa m1){
        
    }
    
    private static String menu(){
        Clear.clear();
        String op;
        op = JOptionPane.showInputDialog("Escolha uma opcao: \n\n1- Ver mapa;\n2- Andar;\n3- Exibir status;\n4- Exibir dia;\n5-Exibir o numero de sobreviventes;\n6-Sair.\n\n");
        return op;
    }
    
}
