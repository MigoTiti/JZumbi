package zumbi.Humano;

import java.util.Random;
import javax.swing.JOptionPane;
import zumbi.Clear;

public class ZumbiHunter extends Zumbi{
    
    public ZumbiHunter(){
        super("Sem nome",2000,200);
        this.velocidade = 2;
    }
    
    public ZumbiHunter(final String nome, int vida, int strength){
        super(nome,vida,strength);
        this.velocidade = 2;
    }
    
    public ZumbiHunter(final ZumbiHunter z1){
        super(z1.nome,z1.vida,z1.strength);
        this.velocidade = z1.velocidade;
    }
    
    @Override
    public int atacarHumano(boolean chefe, int vidaC, int strengthC){
        int vidaH = 4000, vidaZ = this.vida, strengthH = 200, strengthZ = this.strength, maisMenos, atk, total;
        String opt;
        String nomeH;
        Random gerador = new Random();
        
        if(chefe){
            vidaH = vidaC;
            strengthH = strengthC;
            nomeH = "Chefe";
        }else
            nomeH = "Humano";
        
        do{
            Clear.clear();
            System.out.println("HP " + nomeH + ": " + vidaH + "    "
            + "HP " + nome + ": " + vidaZ);
            opt = JOptionPane.showInputDialog("\n1- Ataque normal;\n2- Mordida (custa pontos de vida);\n3- Fugir;");
            switch(opt){
                case "1":
                    maisMenos = gerador.nextInt(2);
                    atk = gerador.nextInt(201);
                    if(maisMenos==1){
                        total = strengthZ + atk;
                        vidaH = vidaH - total;
                    }else{
                        total = strengthZ - atk;
                        vidaH = vidaH - total;
                    }
                    Clear.clear();
                    JOptionPane.showMessageDialog(null,nome + "usou Ataque normal, causando" + total + " de dano.");
                    break;
                case "2":
                    vidaZ = vidaZ - 200;
                    if(vidaZ < 0)
                        vidaZ = 0;
                    maisMenos = gerador.nextInt(2);
                    atk = gerador.nextInt(201);
                    if(maisMenos==1){
                        total = strengthZ + atk + 100;
                        vidaH = vidaH - total;
                    }else{
                        total = strengthZ - atk;
                        vidaH = vidaH - total;
                    }
                    Clear.clear();
                    JOptionPane.showMessageDialog(null,nome + "usou Mordida, causando" + total + " de dano e recebendo 200 de dano colateral.");
                    break;
                case "3":
                    Clear.clear();
                    return 2;
            }
            maisMenos = gerador.nextInt(2);
            atk = gerador.nextInt(101);
            if (maisMenos == 1){
                total = strengthH + atk;
                vidaZ -= total;
                if (vidaZ < 0)
                    vidaZ=0;
            }else{
                total = strengthH - atk;
                vidaZ -= total;
                if (vidaZ < 0)
                    vidaZ = 0;
            }
                    JOptionPane.showMessageDialog(null, nomeH + "usou Ataque normal, causando" + total + " de dano.");
        }while((vidaZ > 0) && (vidaH > 0));
        if(vidaZ == 0){
            Clear.clear();
            return 0;
        }else{
            Clear.clear();
            return 1;
        }
    }
    
    @Override
    public String toString(){
        String saida = "";
        saida += this.nome + "\nPontos de vida: "+
                this.vida + "\nForca: " + this.strength;
        
        for (String item : this.itens) {
            saida += "\nPossui" + item + ";";
        }
        
        saida += "\nVelocidade: " + this.velocidade;
        return saida;
    }
    
    protected int velocidade;
}
