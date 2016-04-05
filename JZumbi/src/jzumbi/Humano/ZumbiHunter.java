package jzumbi.Humano;

public class ZumbiHunter extends Zumbi{
    public ZumbiHunter(){
        super("Sem nome",2000,200);
        this.velocidade = 2;
    }
    
    public ZumbiHunter(final String nome, int vida, int strength){
        super(nome,vida,strength);
        this.velocidade = 2;
    }
    
    protected int velocidade;
}
