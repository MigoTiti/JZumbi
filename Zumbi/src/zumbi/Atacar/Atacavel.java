/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zumbi.Atacar;

public interface Atacavel {
    
    int atacar(boolean chefe, int vidaC, int strengthC);
    int golpe(int strengthZ, int vidaH, int vidaZ);
    int golpe(int strengthZ, int vidaH);
    boolean miss();
}
