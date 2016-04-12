package zumbi.Atacar;

public interface Atacavel {
    
    int atacar(boolean chefe, int vidaC, int strengthC);
    int[] golpe(int strengthZ, int vidaH, int fatorPerda);
    int golpe(int strengthZ, int vidaH);
    boolean miss();
}
