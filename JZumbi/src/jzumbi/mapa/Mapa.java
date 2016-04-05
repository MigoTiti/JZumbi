package jzumbi.mapa;

import java.io.IOException;
import java.util.Random;
import javax.swing.JOptionPane;
import jzumbi.Clear;
import jzumbi.Data;
import jzumbi.Humano.Chefe;

public class Mapa {
    
    public Mapa(Object z1){
        this.dataAtual = new Data(16,02,2016);
        this.chefeFinal = new Chefe();
        this.jogador = z1;
        this.mapa = new char[30][30];
    }
    
    public static void exibirHumanos(){
        Clear.clear();
        JOptionPane.showMessageDialog(null, humanosVivos + "estão vivos.");
    }
    
    public void exibirDia(){
        dataAtual.imprimir();
    }
    
    public void avancarDia(){
        dataAtual.incrementarDia();
    }
    
    public void iniciarMapa(){
	int i, j, c;
        Random gerador = new Random();
	for (i=0;i<=9;i++){
		for (j=0;j<=29;j++){
			mapa[i][j]='0';
		}
	}
	for (i=10;i<=19;i++){
		for (j=0;j<=29;j++){
			mapa[i][j]='1';
		}
	}
	for (i=20;i<=29;i++){
		for (j=0;j<=29;j++){
			mapa[i][j]='0';
		}
	}
	mapa[15][0]='Z';
	for (c=0;c<=(HUMANOS-1);c++){
		i=gerador.nextInt(30);
		j=gerador.nextInt(30);
		if (((i==15) && (j==0)) || (mapa[i][j]=='0')){
			c--;
		}else{
			mapa[i][j]='H';
		}
	}
	do{
		i=gerador.nextInt(30);
		j=gerador.nextInt(30);
		if (((i==15) && (j==0)) || (mapa[i][j]=='0') || (mapa[i][j]=='H')){
			c=1;
		}else{
			mapa[i][j]='C';
			c=0;
		}
	}while(c>0);
	do{
		i=gerador.nextInt(30);
		j=gerador.nextInt(30);
		if (((i==15) && (j==0)) || (mapa[i][j]=='0') || (mapa[i][j]=='H') || (mapa[i][j] == 'C')){
			c=1;
		}else{
			mapa[i][j]='A';
			c=0;
		}
	}while(c>0);
}
    
    private void iniciarMapa2(){
	int i, j, c;
        Random gerador = new Random();
	for (i=0;i<=29;i++){
            for (j=0;j<=29;j++){
                mapa[i][j]='0';
            }
	}
	for (i=10;i<=19;i++){
            for (j=0;j<=13;j++){
                mapa[i][j]='1';
            }
	}
	for (i=5;i<=24;i++){
		for (j=14;j<=25;j++){
			mapa[i][j]='1';
		}
	}
	mapa[15][0]='Z';
	for (c=0;c<=(HUMANOS-1);c++){
            i=gerador.nextInt(30);
            j=gerador.nextInt(30);
            if (((i==15) && (j==0)) || (mapa[i][j]=='0')){
                    c--;
            }else{
                    mapa[i][j]='H';
            }
	}
        humanosVivos = HUMANOS;
    }
    
    private void iniciarMapa3(){
	int i, j, c;
        Random gerador = new Random();
	for (i=0;i<=29;i++){
		for (j=0;j<=29;j++){
			mapa[i][j]='1';
		}
	}
	for (i=19;i<=29;i++){
		for (j=0;j<=9;j++){
			mapa[i][j]='0';
		}
	}
	for (i=3;i<=26;i++){
		for (j=13;j<=18;j++){
			mapa[i][j]='0';
		}
	}
	for (i=24;i<=29;i++){
		for (j=24;j<=29;j++){
			mapa[i][j]='0';
		}
	}
	for (i=0;i<=9;i++){
		for (j=0;j<=9;j++){
			mapa[i][j]='0';
		}
	}
	for (i=0;i<=9;i++){
        mapa[i][21]='0';
	}
	for (j=23;j<=29;j++){
		mapa[9][j]='0';
	}
	for (i=19;i<=23;i++){
		mapa[i][29]='0';
	}
	mapa[15][0]='Z';
	mapa[15][25]='B';
	for (c=0;c<=(HUMANOS-1);c++){
		i=gerador.nextInt(30);
		j=gerador.nextInt(30);
		if (((i==15) && (j==0)) || (mapa[i][j]=='0') || ((i==15) && (j==25))){
			c--;
		}else{
			mapa[i][j]='H';
		}
	}
    humanosVivos = HUMANOS;
}
    
    public void andarMapa(char d, Integer c){
	Integer x, y; 
        int cfases=1;
        procurarMapa(x,y);
        ZumbiHunter* zAux = dynamic_cast<ZumbiHunter*>(Jogador);
	    
        do{
            Clear.clear();
            verificarMapa(d,x,y,&x,&y,c);
            exibirMapa();
            if ((c==HUMANOS) && (cfases==1)){
                avancarDia();
                cfases++;
                iniciarMapa2();
                if(zAux!=0){
                    this.jogador.incrementarVida(500);
                    Clear.clear();
                    JOptionPane.showMessageDialog(null, "Hunter possui vantagem a noite, +500 de vida.");
                }
                procurarMapa(&x,&y);	
                try {
                    d = (char)System.in.read();
                } catch (IOException ex) {}
            }else if ((c==(HUMANOS*2)) && (cfases==2)){
                avancarDia();
                cfases++;
                iniciarMapa3();
                procurarMapa(&x,&y);				
                try {
                    d = (char)System.in.read();
                } catch (IOException ex) {}
            }else
                try {
                    d = (char)System.in.read();
                } catch (IOException ex) {}
        }while(d!='0');
    }
    
    Data dataAtual;
    Chefe chefeFinal;
    Object jogador;
    private char mapa[][];
    public static final int HUMANOS = 8;
    static int humanosVivos = HUMANOS;
}
