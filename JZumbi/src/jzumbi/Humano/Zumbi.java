package jzumbi.Humano;

import java.util.ArrayList;
import jzumbi.Clear;

public abstract class Zumbi extends Humano {

    public Zumbi() {
        super(5000, 300);
        this.nome = "Sem nome";
        this.armadura = false;
        this.capacete = false;
        this.itens = new ArrayList<>();
        this.numeroItens = 0;
    }

    public Zumbi(final Zumbi z1) {
        super(z1.vida, z1.strength);
        this.nome = z1.nome;
        this.armadura = z1.armadura;
        this.capacete = z1.capacete;
        this.itens = z1.itens;
        this.numeroItens = z1.numeroItens;
    }

    public Zumbi(final String name, int vida, int strength) {
        super(vida, strength);
        this.nome = name;
        this.armadura = false;
        this.capacete = false;
        this.itens = new ArrayList<>();
        this.numeroItens = 0;
    }

    protected void setA() {
        this.armadura = true;
        incrementarVida(500);
    }

    protected void setC() {
        this.capacete = true;
        incrementarVida(300);
    }

    public void pegarItem(char item) {
        String itemAtual = "";

        switch (item) {
            case 'C':
                System.out.println("Voce pegou um capacete, +300 de vida.");
                setC();
                itemAtual = "capacete";
                Clear.clear();
                break;
            case 'A':
                System.out.println("Voce pegou uma armadura, +500 de vida.");
                setA();
                itemAtual = "armadura";
                Clear.clear();
                break;
        }
        
        this.numeroItens++;
        this.itens.add(itemAtual);
    }

    @Override
    public void incrementarVida(int valor) {
        this.vida += valor;
    }

    
    @Override
    public boolean equals(Object o) {
        if (o instanceof Zumbi) {
            Zumbi aux = (Zumbi) o;
            if (aux.armadura != this.armadura) {
                return false;
            } else if (aux.capacete != this.capacete) {
                return false;
            } else if (!aux.nome.equals(this.nome)) {
                return false;
            } else if (aux.vida != this.vida) {
                return false;
            } else if (aux.strength != this.strength) {
                return false;
            } else if (aux.numeroItens != this.numeroItens) {
                return false;
            } else {
                for (int i = 0; i < itens.size(); i++) {
                    if (!aux.itens.get(i).equals(this.itens.get(i))) {
                        return false;
                    }
                }
                return true;
            }
        } else {
            return false;
        }
    }
    
    abstract int atacarHumano(boolean chefe, int vidaC, int strengthC);

    protected String nome;
    protected boolean capacete, armadura;
    protected ArrayList<String> itens;
    protected int numeroItens;
}
