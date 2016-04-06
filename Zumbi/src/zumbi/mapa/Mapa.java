package zumbi.mapa;

import java.io.IOException;
import java.util.Random;
import javax.swing.JOptionPane;
import zumbi.Clear;
import zumbi.Data;
import zumbi.Humano.Chefe;
import zumbi.Humano.ZumbiCharger;
import zumbi.Humano.Zumbi;
import zumbi.Humano.ZumbiHunter;

public class Mapa {

    public Mapa(Zumbi z1) {
        this.dataAtual = new Data(16, 02, 2016);
        this.chefeFinal = new Chefe();
        this.jogador = z1;
        this.mapa = new char[30][30];
    }

    public static void exibirHumanos() {
        Clear.clear();
        JOptionPane.showMessageDialog(null, humanosVivos + "estão vivos.");
    }

    public void exibirDia() {
        dataAtual.imprimir();
    }

    public void avancarDia() {
        dataAtual.incrementarDia();
    }

    public void iniciarMapa() {
        int i, j, c;
        Random gerador = new Random();
        for (i = 0; i <= 9; i++) {
            for (j = 0; j <= 29; j++) {
                mapa[i][j] = '0';
            }
        }
        for (i = 10; i <= 19; i++) {
            for (j = 0; j <= 29; j++) {
                mapa[i][j] = '1';
            }
        }
        for (i = 20; i <= 29; i++) {
            for (j = 0; j <= 29; j++) {
                mapa[i][j] = '0';
            }
        }
        mapa[15][0] = 'Z';
        for (c = 0; c <= (HUMANOS - 1); c++) {
            i = gerador.nextInt(30);
            j = gerador.nextInt(30);
            if (((i == 15) && (j == 0)) || (mapa[i][j] == '0')) {
                c--;
            } else {
                mapa[i][j] = 'H';
            }
        }
        do {
            i = gerador.nextInt(30);
            j = gerador.nextInt(30);
            if (((i == 15) && (j == 0)) || (mapa[i][j] == '0') || (mapa[i][j] == 'H')) {
                c = 1;
            } else {
                mapa[i][j] = 'C';
                c = 0;
            }
        } while (c > 0);
        do {
            i = gerador.nextInt(30);
            j = gerador.nextInt(30);
            if (((i == 15) && (j == 0)) || (mapa[i][j] == '0') || (mapa[i][j] == 'H') || (mapa[i][j] == 'C')) {
                c = 1;
            } else {
                mapa[i][j] = 'A';
                c = 0;
            }
        } while (c > 0);
    }

    private void iniciarMapa2() {
        int i, j, c;
        Random gerador = new Random();
        for (i = 0; i <= 29; i++) {
            for (j = 0; j <= 29; j++) {
                mapa[i][j] = '0';
            }
        }
        for (i = 10; i <= 19; i++) {
            for (j = 0; j <= 13; j++) {
                mapa[i][j] = '1';
            }
        }
        for (i = 5; i <= 24; i++) {
            for (j = 14; j <= 25; j++) {
                mapa[i][j] = '1';
            }
        }
        mapa[15][0] = 'Z';
        for (c = 0; c <= (HUMANOS - 1); c++) {
            i = gerador.nextInt(30);
            j = gerador.nextInt(30);
            if (((i == 15) && (j == 0)) || (mapa[i][j] == '0')) {
                c--;
            } else {
                mapa[i][j] = 'H';
            }
        }
        humanosVivos = HUMANOS;
    }

    private void iniciarMapa3() {
        int i, j, c;
        Random gerador = new Random();
        for (i = 0; i <= 29; i++) {
            for (j = 0; j <= 29; j++) {
                mapa[i][j] = '1';
            }
        }
        for (i = 19; i <= 29; i++) {
            for (j = 0; j <= 9; j++) {
                mapa[i][j] = '0';
            }
        }
        for (i = 3; i <= 26; i++) {
            for (j = 13; j <= 18; j++) {
                mapa[i][j] = '0';
            }
        }
        for (i = 24; i <= 29; i++) {
            for (j = 24; j <= 29; j++) {
                mapa[i][j] = '0';
            }
        }
        for (i = 0; i <= 9; i++) {
            for (j = 0; j <= 9; j++) {
                mapa[i][j] = '0';
            }
        }
        for (i = 0; i <= 9; i++) {
            mapa[i][21] = '0';
        }
        for (j = 23; j <= 29; j++) {
            mapa[9][j] = '0';
        }
        for (i = 19; i <= 23; i++) {
            mapa[i][29] = '0';
        }
        mapa[15][0] = 'Z';
        mapa[15][25] = 'B';
        for (c = 0; c <= (HUMANOS - 1); c++) {
            i = gerador.nextInt(30);
            j = gerador.nextInt(30);
            if (((i == 15) && (j == 0)) || (mapa[i][j] == '0') || ((i == 15) && (j == 25))) {
                c--;
            } else {
                mapa[i][j] = 'H';
            }
        }
        humanosVivos = HUMANOS;
    }

    public void andarMapa(char d, Integer c) {
        int cfases = 1;

        procurarMapa();

        do {
            Clear.clear();
            verificarMapa(d, c);
            exibirMapa();
            if ((c == HUMANOS) && (cfases == 1)) {
                avancarDia();
                cfases++;
                iniciarMapa2();
                if (jogador instanceof ZumbiHunter) {
                    ZumbiHunter aux = new ZumbiHunter((ZumbiHunter) jogador);
                    aux.incrementarVida(500);
                    this.jogador = new ZumbiHunter(aux);
                    Clear.clear();
                    JOptionPane.showMessageDialog(null, "Hunter possui vantagem a noite, +500 de vida.");
                }
                procurarMapa();
                try {
                    d = (char) System.in.read();
                } catch (IOException ex) {
                }
            } else if ((c == (HUMANOS * 2)) && (cfases == 2)) {
                avancarDia();
                cfases++;
                iniciarMapa3();
                procurarMapa();
                try {
                    d = (char) System.in.read();
                } catch (IOException ex) {}
            } else {
                try {
                    d = (char) System.in.read();
                } catch (IOException ex) {}
            }
        } while (d != '0');
    }

    public void exibirMapa() {
        for (int i = 0; i <= 29; i++) {
            for (int j = 0; j <= 29; j++) {
                if (mapa[i][j] == 'Z') {
                    //c.setColor(JavaHowTo.FOREGROUND_RED, JavaHowTo.BACKGROUND_BLACK);
                    System.out.println(" " + mapa[i][j]);
                } else if (mapa[i][j] == 'H' || mapa[i][j] == 'B') {
                    //c.setColor(JavaHowTo.FOREGROUND_WHITE, JavaHowTo.BACKGROUND_BLACK);
                    System.out.println(" " + mapa[i][j]);
                } else if (mapa[i][j] == '0') {
                    //c.setColor(JavaHowTo.FOREGROUND_GREEN, JavaHowTo.BACKGROUND_BLACK);
                    System.out.println(" " + mapa[i][j]);
                } else if (mapa[i][j] == 'A') {
                    //c.setColor(JavaHowTo.FOREGROUND_BLUE, JavaHowTo.BACKGROUND_BLACK);
                    System.out.println(" " + mapa[i][j]);
                } else if (mapa[i][j] == 'C') {
                   // c.setColor(JavaHowTo.FOREGROUND_BLUE, JavaHowTo.BACKGROUND_BLACK);
                    System.out.println(" " + mapa[i][j]);
                } else {
                    //c.setColor(JavaHowTo.FOREGROUND_BLACK, JavaHowTo.BACKGROUND_BLACK);
                    System.out.println(" " + mapa[i][j]);
                }
                //c.setColor(JavaHowTo.FOREGROUND_WHITE, JavaHowTo.BACKGROUND_BLACK);
            }
            System.out.println("\n");
        }
    }

    private void procurarMapa() {
        int i, j;
        for (i = 0; i <= 29; i++) {
            for (j = 0; j <= 29; j++) {
                if (mapa[i][j] == 'Z') {
                    xAtual = i;
                    yAtual = j;
                    break;
                }
            }
        }
    }

    private void andarAux(int xDirecao, int yDirecao, int x, int y) {
        boolean chefe = false, hunter = false;
        int v;

        if (jogador instanceof ZumbiHunter) {
            hunter = true;
        }

        if (mapa[x + xDirecao][y + yDirecao] == '1') {
            mapa[x + xDirecao][y + yDirecao] = 'Z';
            mapa[x][y] = '1';
            xAtual = x + xDirecao;
            yAtual = y + yDirecao;
        } else if (mapa[x + 1][y + yDirecao] == 'H') {
            if (hunter) {
                ZumbiHunter aux = new ZumbiHunter((ZumbiHunter) jogador);
                v = aux.atacarHumano(chefe, chefeFinal.getVida(), chefeFinal.getStrength());
            } else {
                ZumbiCharger aux = new ZumbiCharger((ZumbiCharger) jogador);
                v = aux.atacarHumano(chefe, chefeFinal.getVida(), chefeFinal.getStrength());
            }
            if (v == 1) {
                humanosVivos--;
                mapa[x + xDirecao][y + yDirecao] = 'Z';
                chefeFinal.incrementarVida(100);
                chefeFinal.decrementarStrength();
            } else if (v == 0) {
                JOptionPane.showMessageDialog(null, "Voce perdeu. Aperte qualquer botao para sair: ");
                System.exit(0);
            }
        } else if ((mapa[x + xDirecao][y + yDirecao] == 'A') || (mapa[x + 1][y + yDirecao] == 'C')) {
            jogador.pegarItem(mapa[x + 1][y + yDirecao]);
            mapa[x + xDirecao][y + yDirecao] = '1';
        } else if (mapa[x + xDirecao][y + yDirecao] == 'B') {
            chefe = true;

            if (hunter) {
                ZumbiHunter aux = new ZumbiHunter((ZumbiHunter) jogador);
                v = aux.atacarHumano(chefe, chefeFinal.getVida(), chefeFinal.getStrength());
            } else {
                ZumbiCharger aux = new ZumbiCharger((ZumbiCharger) jogador);
                v = aux.atacarHumano(chefe, chefeFinal.getVida(), chefeFinal.getStrength());
            }

            if (v == 1) {
                mapa[x + xDirecao][y + yDirecao] = 'Z';
            } else if (v == 0) {
                JOptionPane.showMessageDialog(null, "Voce perdeu. Aperte qualquer botao para sair: ");
                System.exit(0);
            }
        }
    }

    private void verificarMapa(char d, Integer c1) {

        switch (d) {
            case '1':
                andarAux(1, -1, xAtual, yAtual);
                break;
            case '2':
            case 's':
            case 'S':
                andarAux(1, 0, xAtual, yAtual);
                break;
            case '3':
                andarAux(1, 1, xAtual, yAtual);
                break;
            case '4':
            case 'a':
            case 'A':
                andarAux(0, -1, xAtual, yAtual);
                break;
            case '6':
            case 'd':
            case 'D':
                andarAux(0, 1, xAtual, yAtual);
                break;
            case '7':
                andarAux(-1, -1, xAtual, yAtual);
                break;
            case '8':
            case 'w':
            case 'W':
                andarAux(-1, 0, xAtual, yAtual);
                break;
            case '9':
                andarAux(-1, 1, xAtual, yAtual);
                break;
            default:
                JOptionPane.showMessageDialog(null, "Posição inválida.");
                break;
        }
    }

    private int xAtual, yAtual;
    Data dataAtual;
    Chefe chefeFinal;
    Zumbi jogador;
    private char mapa[][];
    public static final int HUMANOS = 8;
    static int humanosVivos = HUMANOS;
}
