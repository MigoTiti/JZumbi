package jzumbi.Humano;

public abstract class Humano {
    
    public Humano(int vida, int strength){
        if(vida>0)
            this.vida = vida;
        else
            this.vida = 2000;
        
        if(strength>0)
            this.strength = strength;
        else
            this.strength = 200;
    }
    
    abstract void incrementarVida(int valor);
    
    protected int vida, strength;
}
