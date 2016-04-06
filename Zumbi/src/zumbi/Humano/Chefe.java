package zumbi.Humano;

public class Chefe extends Humano {
    
    public Chefe(){
        super(5000,300);
    }
    
    public Chefe(final Chefe c1){
        super(c1.vida,c1.strength);
        this.armado = c1.armado;
    }
            
    public void decrementarStrength(){
        this.strength -= 10;
    }
    
    public int getVida(){
        return this.vida;
    }
    
    public int getStrength(){
        return this.strength;
    }
    
    public boolean isArmado(){
        return this.armado;
    }
    
    @Override
    public void incrementarVida(int valor) {
        this.vida += valor;
    }
    
    private boolean armado;

    
}
