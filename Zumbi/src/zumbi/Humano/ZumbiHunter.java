package zumbi.Humano;

import java.util.Random;
import javax.swing.JOptionPane;
import zumbi.Atacar.Atacavel;

public class ZumbiHunter extends Zumbi {
    
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
    public int atacar(boolean chefe, int vidaC, int strengthC){
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
            boolean sucesso = false;
            do{
                opt = JOptionPane.showInputDialog("HP " + nomeH + ": " + vidaH + "    "
                + "HP " + nome + ": " + vidaZ+"\n1- Ataque normal;\n2- Pulo (custa vida);\n3- Fugir;");
                if(opt == null)
                    System.exit(0);
                switch(opt){
                    case "1":
                        if(!miss())
                            vidaH = golpe(strengthZ,vidaH);
                        else
                            JOptionPane.showMessageDialog(null,"Não vai ter golpe.");
                        sucesso = true;
                        break;
                    case "2":
                        if(!miss()){
                            int [] resultado = golpe(strengthZ,vidaH,vidaZ);
                            vidaH = resultado[0];
                            vidaZ = resultado[1];
                        }else
                            JOptionPane.showMessageDialog(null,"Não vai ter golpe.");
                        sucesso = true;
                        break;
                    case "3":
                        return 2;
                    default:
                        JOptionPane.showMessageDialog(null,"Escolha uma opção válida.");
                        break;
                }
            }while(!sucesso);
            if(!miss()){
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
                JOptionPane.showMessageDialog(null, nomeH + " usou Ataque normal, causando " + total + " de dano.");
            }else
                JOptionPane.showMessageDialog(null,"Não vai ter golpe também.");
        }while((vidaZ > 0) && (vidaH > 0));
        if(vidaZ == 0)
            return 0;
        else
            return 1;
        
    }
    
    @Override
    public int[] golpe(int strengthZ, int vidaH, int fatorPerda){
        int maisMenos, atk, total;
        Random gerador = new Random();
            fatorPerda -= 200;
            if(fatorPerda < 0)
                fatorPerda = 0;
            maisMenos = gerador.nextInt(2);
            atk = gerador.nextInt(201);
            if(maisMenos==1){
                total = strengthZ + atk + 100;
                vidaH = vidaH - total;
            }else{
                total = strengthZ - atk;
                vidaH = vidaH - total;
            }
            JOptionPane.showMessageDialog(null,nome + " usou Pulo, causando " + total + " de dano e recebendo 200 de dano colateral.");
            int[] resultado = new int[]{vidaH,fatorPerda};
        return resultado;
    }
    
    @Override
    public boolean miss(){
        int fator = new Random().nextInt(101);
        return fator >= 90;
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
