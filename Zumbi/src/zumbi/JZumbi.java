package zumbi;

import javax.swing.JOptionPane;
import zumbi.Humano.Zumbi;
import zumbi.Humano.ZumbiCharger;
import zumbi.Humano.ZumbiHunter;
import zumbi.mapa.Mapa;

public class JZumbi {

    public static void main(String[] args) {
       
        String nome = JOptionPane.showInputDialog("Digite o nome do zumbi: ");
        Zumbi jogador = null;
        
        String escolhaClasse = JOptionPane.showInputDialog("Escolha a classe do zumbi: \n1-Hunter; \n2-Charger.");
        
        switch (escolhaClasse) {
            case "1":
                jogador = new ZumbiHunter(nome,4000,200);
                break;
            case "2":
                jogador = new ZumbiCharger(nome,6000,300);
                break;
        }
        
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
        char d;
        Clear.clear();
        
        switch(op){
            case "1":
                Clear.clear();
                m1.exibirMapa();
                int resultado = JOptionPane.showConfirmDialog(null,null, "Deseja retornar ao menu?",JOptionPane.OK_CANCEL_OPTION);
        }
    }
    
    private static String menu(){
        Clear.clear();
        String op;
        op = JOptionPane.showInputDialog("Escolha uma opcao: \n\n1- Ver mapa;\n2- Andar;\n3- Exibir status;\n4- Exibir dia;\n5-Exibir o numero de sobreviventes;\n6-Sair.\n\n");
        return op;
    }
    
}
