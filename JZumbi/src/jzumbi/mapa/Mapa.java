package jzumbi.mapa;

import java.io.IOException;
import java.util.Random;
import javax.swing.JOptionPane;
import jzumbi.Clear;
import jzumbi.Data;
import jzumbi.Humano.Chefe;
import jzumbi.Humano.ZumbiCharger;
import jzumbi.Humano.ZumbiHunter;

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
	Integer x = null, y = null; 
        int cfases=1;
        procurarMapa(x,y);
	    
        do{
            Clear.clear();
            verificarMapa(d,x,y,&x,&y,c);
            exibirMapa();
            if ((c==HUMANOS) && (cfases==1)){
                avancarDia();
                cfases++;
                iniciarMapa2();
                if(jogador instanceof ZumbiHunter){
                    ZumbiHunter aux = new ZumbiHunter((ZumbiHunter)jogador);
                    aux.incrementarVida(500);
                    this.jogador = new ZumbiHunter(aux);
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
    
    private void procurarMapa(Integer l, Integer c){
	int i, j;
	for (i=0;i<=29;i++){
            for (j=0;j<=29;j++){
                if(mapa[i][j]=='Z'){
                    l=i;
                    c=j;
                    break;
                }
            }
	}
    }
    
    private void verificarMapa(char d, Integer x, Integer y, Integer l, Integer c, Integer c1){
	int v;
	boolean chefe=false; 
        
	switch(d){
		case '1':
                    if(mapa[x+1][y-1]=='1'){
                        mapa[x+1][y-1]='Z';
                        mapa[x][y]='1';
                        l=x+1;
                        c=y-1;
                    }else if(mapa[x+1][y-1]=='H'){
                        v=jogador.atacarHumano(chefe,chefeFinal.getVida(),chefeFinal.getStrength());
                        if (v==1){
                            c1=c1+1;
                            humanosVivos--;
                            mapa[x+1][y-1]='Z';
                            chefeFinal.incrementarVida(100);
                            chefeFinal.decrementarStrength();
                        }else if (v==0){
                            JOptionPane.showMessageDialog(null, "Voce perdeu. Aperte qualquer botao para sair: ");
                            cin.get();
                            exit(0);
                        }
                    }else if((mapa[x+1][y-1]=='A') || (mapa[x+1][y-1]=='C')){
                        jogador.pegarItem(mapa[x+1][y-1]);
                        mapa[x+1][y-1]='1';
                    }else if (mapa[x+1][y-1]=='B'){
                        chefe=true;
                        v=jogador.atacarHumano(chefe,chefeFinal.getVida(),chefeFinal.getStrength());
                        if (v==1)
                            mapa[x+1][y-1]='Z';
                        else if (v==0){
                            JOptionPane.showMessageDialog(null, "Voce perdeu. Aperte qualquer botao para sair: ");
                            cin.get();
                            exit(0);
                        }								
                    }
		    break;
		case '2':
		case 's':
		case 'S':
			if(mapa[x+1][y]=='1'){
			    mapa[x+1][y]='Z';
			    mapa[x][y]='1';
			    l=x+1;
			}else if(mapa[x+1][y]=='H'){
				v=Jogador.atacarHumano(chefe,chefeFinal.getVida(),chefeFinal.getStrength());
				if (v==1){
				c1=c1+1;
                humanosVivos--;
                chefeFinal.incrementarVida(100);
                chefeFinal.decrementarStrength();
			    mapa[x+1][y]='Z';
			    }else if (v==0){
			    JOptionPane.showMessageDialog(null, "Voce perdeu. Aperte qualquer botao para sair: ");
				cin.get();
			    exit(0);
                }
		    }else if((mapa[x+1][y]=='A') || (mapa[x+1][y]=='C')){
				Jogador->pegarItem(mapa[x+1][y]);
				mapa[x+1][y]='1';
			}else if (mapa[x+1][y]=='B'){
				chefe=true;
				v=Jogador->atacarHumano(chefe,chefeFinal.getVida(),chefeFinal.getStrength());
				if (v==1)
			    mapa[x+1][y-1]='Z';
				else if (v==0){
			    JOptionPane.showMessageDialog(null, "Voce perdeu. Aperte qualquer botao para sair: ");
				cin.get();
			    exit(0);
                }								
			}
		    break;
		case '3':
			if(mapa[x+1][y+1]=='1'){
			    mapa[x+1][y+1]='Z';
			    mapa[x][y]='1';
			    *l=x+1;
			    *c=y+1;
			}else if(mapa[x+1][y+1]=='H'){
				v=Jogador->atacarHumano(chefe,chefeFinal.getVida(),chefeFinal.getStrength());
				if (v==1){
				*c1=*c1+1;
                humanosVivos--;
                chefeFinal.incrementarVida(100);
                chefeFinal.decrementarStrength();
			    mapa[x+1][y+1]='Z';
			    }else if (v==0){
			    JOptionPane.showMessageDialog(null, "Voce perdeu. Aperte qualquer botao para sair: ");
				cin.get();
			    exit(0);
                }
		    }else if((mapa[x+1][y+1]=='A') || (mapa[x+1][y+1]=='C')){
				Jogador->pegarItem(mapa[x+1][y+1]);
				mapa[x+1][y+1]='1';
			}else if (mapa[x+1][y+1]=='B'){
				chefe=true;
				v=Jogador->atacarHumano(chefe,chefeFinal.getVida(),chefeFinal.getStrength());
				if (v==1)
			    mapa[x+1][y-1]='Z';
				else if (v==0){
			    JOptionPane.showMessageDialog(null, "Voce perdeu. Aperte qualquer botao para sair: ");
				cin.get();
			    exit(0);
                }								
			}
		    break;
		case '4':
		case 'a':
		case 'A':
			if(mapa[x][y-1]=='1'){
			    mapa[x][y-1]='Z';
			    mapa[x][y]='1';
			    *c=y-1;
			}else if(mapa[x][y-1]=='H'){
				v=Jogador->atacarHumano(chefe,chefeFinal.getVida(),chefeFinal.getStrength());
				if (v==1){
				*c1=*c1+1;
                humanosVivos--;
                chefeFinal.incrementarVida(100);
                chefeFinal.decrementarStrength();
			    mapa[x][y-1]='Z';
			    }else if (v==0){
			    cout << "Voce perdeu. Aperte qualquer botao para sair: ";
				cin.get();
			    exit(0);
                }
		    }else if((mapa[x][y-1]=='A') || (mapa[x][y-1]=='C')){
				Jogador->pegarItem(mapa[x][y-1]);
				mapa[x][y-1]='1';
			}else if (mapa[x][y-1]=='B'){
				chefe=true;
				v=Jogador->atacarHumano(chefe,chefeFinal.getVida(),chefeFinal.getStrength());
				if (v==1)
			    mapa[x+1][y-1]='Z';
				else if (v==0){
			    cout << "Voce perdeu. Aperte qualquer botao para sair: ";
				cin.get();
			    exit(0);
                }								
			}
		    break;
		case '6':
		case 'd':
		case 'D':
			if(mapa[x][y+1]=='1'){
			    mapa[x][y+1]='Z';
			    mapa[x][y]='1';
			    *c=y+1;
			}else if(mapa[x][y+1]=='H'){
				v=Jogador->atacarHumano(chefe,chefeFinal.getVida(),chefeFinal.getStrength());
				if (v==1){
				*c1=*c1+1;
                humanosVivos--;
                chefeFinal.incrementarVida(100);
                chefeFinal.decrementarStrength();
			    mapa[x][y+1]='Z';
			    }else if (v==0){
			    cout << "Voce perdeu. Aperte qualquer botao para sair: ";
				cin.get();
			    exit(0);
                }
		    }else if((mapa[x][y+1]=='A') || (mapa[x][y+1]=='C')){
				Jogador->pegarItem(mapa[x][y+1]);
				mapa[x][y+1]='1';
			}else if (mapa[x][y+1]=='B'){
				chefe=true;
				v=Jogador->atacarHumano(chefe,chefeFinal.getVida(),chefeFinal.getStrength());
				if (v==1)
			    mapa[x+1][y-1]='Z';
				else if (v==0){
			    cout << "Voce perdeu. Aperte qualquer botao para sair: ";
				cin.get();
			    exit(0);
                }								
			}
		    break;
		case '7':
			if(mapa[x-1][y-1]=='1'){
			    mapa[x-1][y-1]='Z';
			    mapa[x][y]='1';
			    *l=x-1;
			    *c=y-1;
			}else if(mapa[x-1][y-1]=='H'){
				v=Jogador->atacarHumano(chefe,chefeFinal.getVida(),chefeFinal.getStrength());
				if (v==1){
				*c1=*c1+1;
                humanosVivos--;
                chefeFinal.incrementarVida(100);
                chefeFinal.decrementarStrength();
			    mapa[x-1][y-1]='Z';
			    }else if (v==0){
			    cout << "Voce perdeu. Aperte qualquer botao para sair: ";
				cin.get();
			    exit(0);
                }
		    }else if((mapa[x-1][y-1]=='A') || (mapa[x-1][y-1]=='C')){
				Jogador->pegarItem(mapa[x-1][y-1]);
				mapa[x-1][y-1]='1';
			}else if (mapa[x-1][y-1]=='B'){
				chefe=true;
				v=Jogador->atacarHumano(chefe,chefeFinal.getVida(),chefeFinal.getStrength());
				if (v==1)
			    mapa[x+1][y-1]='Z';
				else if (v==0){
			    cout << "Voce perdeu. Aperte qualquer botao para sair: ";
				cin.get();
			    exit(0);
                }								
			}
		    break;
		case '8':
		case 'w':
		case 'W':
			if(mapa[x-1][y]=='1'){
			    mapa[x-1][y]='Z';
			    mapa[x][y]='1';
			    *l=x-1;
			}else if(mapa[x-1][y]=='H'){
				v=Jogador->atacarHumano(chefe,chefeFinal.getVida(),chefeFinal.getStrength());
				if (v==1){
				*c1=*c1+1;
                humanosVivos--;
                chefeFinal.incrementarVida(100);
                chefeFinal.decrementarStrength();
			    mapa[x-1][y]='Z';
			    }else if (v==0){
			    cout << "Voce perdeu. Aperte qualquer botao para sair: ";
				cin.get();
			    exit(0);
                }
		    }else if((mapa[x-1][y]=='A') || (mapa[x-1][y]=='C')){
				Jogador->pegarItem(mapa[x-1][y]);
                mapa[x-1][y]='1';
			}else if (mapa[x-1][y]=='B'){
				chefe=true;
				v=Jogador->atacarHumano(chefe,chefeFinal.getVida(),chefeFinal.getStrength());
				if (v==1)
			    mapa[x+1][y-1]='Z';
				else if (v==0){
			    cout << "Voce perdeu. Aperte qualquer botao para sair: ";
				cin.get();
			    exit(0);
                }								
			}
		    break;
		case '9':
			if(mapa[x-1][y+1]=='1'){
			    mapa[x-1][y+1]='Z';
			    mapa[x][y]='1';
			    *l=x-1;
			    *c=x+1;
			}else if(mapa[x-1][y+1]=='H'){
				v=Jogador->atacarHumano(chefe,chefeFinal.getVida(),chefeFinal.getStrength());
				if (v==1){
				*c1=*c1+1;
                humanosVivos--;
                chefeFinal.incrementarVida(100);
                chefeFinal.decrementarStrength();
			    mapa[x-1][y+1]='Z';
			    }else if (v==0){
			    cout << "Voce perdeu. Aperte qualquer botao para sair: ";
				cin.get();
			    exit(0);
			    }
		    }else if((mapa[x-1][y+1]=='A') || (mapa[x-1][y+1]=='C')){
				Jogador->pegarItem(mapa[x-1][y+1]);
				mapa[x-1][y+1]='1';
			}else if (mapa[x-1][y+1]=='B'){
				chefe=true;
				v=Jogador->atacarHumano(chefe,chefeFinal.getVida(),chefeFinal.getStrength());
				if (v==1)
			    mapa[x+1][y-1]='Z';
				else if (v==0){
			    cout << "Voce perdeu. Aperte qualquer botao para sair: ";
				cin.get();
			    exit(0);
                }								
			}
		    break;
		default:
			cout << "Posicao invalida.\n";
			break;
	}
    }
    
    Data dataAtual;
    Chefe chefeFinal;
    Object jogador;
    private char mapa[][];
    public static final int HUMANOS = 8;
    static int humanosVivos = HUMANOS;
}
