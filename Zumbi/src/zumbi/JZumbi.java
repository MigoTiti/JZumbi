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
        boolean sucessoEscolha = false;
        
        do {
            String escolhaClasse = JOptionPane.showInputDialog("Escolha a classe do zumbi: \n1-Hunter; \n2-Charger.");

            if (escolhaClasse == null) {
                System.exit(0);
            }
            
            switch (escolhaClasse) {
                case "1":
                    jogador = new ZumbiHunter(nome,4000,200);
                    break;
                case "2":
                    jogador = new ZumbiCharger(nome,6000,300);
                    break;
            }
            
            int numeroClasse = 0;
            try {
                numeroClasse = Integer.parseInt(escolhaClasse);
                sucessoEscolha = true;
            } catch (NumberFormatException e2) {
                JOptionPane.showMessageDialog(null, "Sem letras ou símbolos!");
            }
            
            if(sucessoEscolha&&(numeroClasse!=1&&numeroClasse!=2)){
                JOptionPane.showMessageDialog(null, "Somente 1 e 2 são válidos!");
                sucessoEscolha = false;
            }
                
        } while (!sucessoEscolha);
        
        
        Mapa m1 = new Mapa(jogador);
        
        m1.iniciarMapa();
        
        new Menu(m1);
    }
    
    public static int getC(){
        return JZumbi.c;
    }
    
    public static void setC(){
        JZumbi.c++;
    }
    
    private static int c = 0;
}
