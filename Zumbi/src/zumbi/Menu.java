package zumbi;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import zumbi.Humano.ZumbiCharger;
import zumbi.Humano.ZumbiHunter;
import zumbi.mapa.Mapa;

public class Menu extends JFrame {

    public Menu(Mapa m1) {
        this.m1 = m1;
        initComponents();
        this.setVisible(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        andar = new javax.swing.JButton();
        exibirStatus = new javax.swing.JButton();
        exibirDia = new javax.swing.JButton();
        exibirSobreviventes = new javax.swing.JButton();
        sair = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        andar.setText("Andar");
        andar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                andarActionPerformed(evt);
            }
        });

        exibirStatus.setText("Exibir status");
        exibirStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exibirStatusActionPerformed(evt);
            }
        });

        exibirDia.setText("Exibir dia");
        exibirDia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exibirDiaActionPerformed(evt);
            }
        });

        exibirSobreviventes.setText("Exibir número de sobreviventes");
        exibirSobreviventes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exibirSobreviventesActionPerformed(evt);
            }
        });

        sair.setText("Sair");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(sair, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(exibirSobreviventes)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(exibirDia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(exibirStatus, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(andar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(andar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(exibirStatus)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(exibirDia)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(exibirSobreviventes)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sair)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void andarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_andarActionPerformed
        m1.setVisible(true);
        dispose();
    }//GEN-LAST:event_andarActionPerformed

    private void exibirStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exibirStatusActionPerformed
        if(m1.getJogador() instanceof ZumbiHunter){
            JOptionPane.showMessageDialog(rootPane, new ZumbiHunter((ZumbiHunter) m1.getJogador()).toString()); 
        }else{
            JOptionPane.showMessageDialog(rootPane, new ZumbiCharger((ZumbiCharger) m1.getJogador()).toString());
        }
    }//GEN-LAST:event_exibirStatusActionPerformed

    private void exibirDiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exibirDiaActionPerformed
        m1.exibirDia();
    }//GEN-LAST:event_exibirDiaActionPerformed

    private void exibirSobreviventesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exibirSobreviventesActionPerformed
        Mapa.exibirHumanos();
    }//GEN-LAST:event_exibirSobreviventesActionPerformed
   
    private Mapa m1;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton andar;
    private javax.swing.JButton exibirDia;
    private javax.swing.JButton exibirSobreviventes;
    private javax.swing.JButton exibirStatus;
    private javax.swing.JButton sair;
    // End of variables declaration//GEN-END:variables
}
