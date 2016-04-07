package zumbi;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.*;
import zumbi.mapa.Mapa;


public class GUI extends JFrame{

    public GUI(Mapa m1){
        this.setTitle("Zumbi");
        this.setSize(500,500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.mapa = m1;
    }
    
    @Override
    public void paint(Graphics g){
        
        g.setColor(Color.BLACK);
        g.drawRect(40, 40, 30, 30);
        g.setColor(Color.RED);
        g.drawOval(80, 80, 30, 30);
        g.fillArc(140, 140, 30, 30, 180, 10);
        g.drawLine(100, 300, 350, 400); 
        g.setFont(new Font("Arial",Font.BOLD,34));
        g.drawString("Paint this!", 150, 100);
    }
    
    private Mapa mapa;
}
