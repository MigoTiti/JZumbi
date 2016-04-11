package zumbi.mapa;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import zumbi.Data;
import zumbi.Humano.Chefe;
import zumbi.Humano.ZumbiCharger;
import zumbi.Humano.Zumbi;
import zumbi.Humano.ZumbiHunter;
import zumbi.JZumbi;
import zumbi.Menu;

public class Mapa extends JFrame implements KeyListener{

    public Mapa(Zumbi z1) {
        this.dataAtual = new Data(16, 02, 2016);
        this.chefeFinal = new Chefe();
        this.jogador = z1;
        this.mapa = new char[30][30];
        this.cFases = 1;
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        iniciarFrame();
    }

    private void iniciarFrame(){
        
        this.setTitle("Zumbi");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBackground(Color.GRAY);
        this.setResizable(false);
        this.voltar = new JButton();
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(600, 675));
        setMinimumSize(new java.awt.Dimension(600, 675));
        setPreferredSize(new java.awt.Dimension(600, 675));

        voltar.setText("Voltar");
        
        voltar.addActionListener((java.awt.event.ActionEvent evt) -> {
            new Menu(Mapa.this);
            dispose();
        });
        this.setLocationRelativeTo(null);
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(voltar)
                .addContainerGap(517, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(576, Short.MAX_VALUE)
                .addComponent(voltar)
                .addContainerGap())
        );
        
        pack();
    }
    
    public void exibirMapa(){
        this.setVisible(true);
        repaint();
    }
    
    public static void exibirHumanos() {
        JOptionPane.showMessageDialog(null, humanosVivos + " humanos estão vivos.");
    }

    public void exibirDia() {
        dataAtual.imprimir();
    }

    public void avancarDia() {
        dataAtual.incrementarDia();
    }
    
    public char[][] getMapa(){
        return this.mapa;
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
        procurarMapa();
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
        procurarMapa();
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
        procurarMapa();
    }

    public void andarMapa(char d) {

        verificarMapa(d);
        repaint();
        
        int c = JZumbi.getC();
        if ((c == HUMANOS) && (cFases == 1)) {
            avancarDia();
            cFases++;
            iniciarMapa2();
            if (jogador instanceof ZumbiHunter) {
                ZumbiHunter aux = new ZumbiHunter((ZumbiHunter) jogador);
                aux.incrementarVida(500);
                this.jogador = new ZumbiHunter(aux);
                JOptionPane.showMessageDialog(null, "Hunter possui vantagem a noite, +500 de vida.");
            }
        } else if ((JZumbi.getC() == (HUMANOS * 2)) && (cFases == 2)) {
            avancarDia();
            cFases++;
            iniciarMapa3();
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
        } else if (mapa[x + xDirecao][y + yDirecao] == 'H') {
            if (hunter) {
                ZumbiHunter aux = new ZumbiHunter((ZumbiHunter) jogador);
                v = aux.atacar(chefe, chefeFinal.getVida(), chefeFinal.getStrength());
            } else {
                ZumbiCharger aux = new ZumbiCharger((ZumbiCharger) jogador);
                v = aux.atacar(chefe, chefeFinal.getVida(), chefeFinal.getStrength());
            }
            if (v == 1) {
                humanosVivos--;
                JZumbi.setC();
                mapa[x + xDirecao][y + yDirecao] = 'Z';
                chefeFinal.incrementarVida(100);
                chefeFinal.decrementarStrength();
            } else if (v == 0) {
                JOptionPane.showMessageDialog(null, "Voce perdeu.");
                System.exit(0);
            }
        } else if ((mapa[x + xDirecao][y + yDirecao] == 'A') || (mapa[x + xDirecao][y + yDirecao] == 'C')) {
            jogador.pegarItem(mapa[x + xDirecao][y + yDirecao]);
            mapa[x + xDirecao][y + yDirecao] = '1';
        } else if (mapa[x + xDirecao][y + yDirecao] == 'B') {
            chefe = true;

            if (hunter) {
                ZumbiHunter aux = new ZumbiHunter((ZumbiHunter) jogador);
                v = aux.atacar(chefe, chefeFinal.getVida(), chefeFinal.getStrength());
            } else {
                ZumbiCharger aux = new ZumbiCharger((ZumbiCharger) jogador);
                v = aux.atacar(chefe, chefeFinal.getVida(), chefeFinal.getStrength());
            }

            if (v == 1) {
                mapa[x + xDirecao][y + yDirecao] = 'Z';
            } else if (v == 0) {
                JOptionPane.showMessageDialog(null, "Voce perdeu. Aperte qualquer botao para sair: ");
                System.exit(0);
            }
        }
    }

    private void verificarMapa(char d) {

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
        }
    }
    
    public Zumbi getJogador(){
        return this.jogador;
    }
    
    @Override
    public void paint(Graphics g){
        super.paintComponents(g);
        boolean sucesso = false;
        int varY = 20;
        int varX = 0;
        g.setColor(Color.BLACK);
        g.fillRect(0, 20, 600, 600);
        g.setFont(new Font("Arial",Font.BOLD,20));
        for(int i=0;i<30;i++){
            for(int j=0;j<30;j++){
                if (mapa[i][j] == 'Z') {
                    g.setColor(Color.RED);
                    g.fillRect(varX+1, varY, 20, 20);
                    sucesso = true;
                } else if (mapa[i][j] == 'H' || mapa[i][j] == 'B') {
                    g.setColor(Color.WHITE);
                    g.fillRect(varX+1, varY, 20, 20);
                    sucesso = true;
                } else if (mapa[i][j] == '0') {
                    g.setColor(Color.GREEN);
                    g.fillRect(varX+1, varY, 20, 20);
                    sucesso = true;
                } else if (mapa[i][j] == 'A' || mapa[i][j] == 'C') {
                    g.setColor(Color.YELLOW);
                    g.fillRect(varX+1, varY, 20, 20);
                    sucesso = true;
                }
                if(sucesso)
                    varX+=20;
            }
            if(sucesso)
                varY+=20;
            varX = 0;
        }
    }

    private JButton voltar;
    private int xAtual, yAtual, cFases;
    private Data dataAtual;
    private Chefe chefeFinal;
    private Zumbi jogador;
    private char mapa[][];
    public static final int HUMANOS = 8;
    private static int humanosVivos = HUMANOS;

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {
        andarMapa(e.getKeyChar());
    }
}
