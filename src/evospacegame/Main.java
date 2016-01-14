/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evospacegame;

/**
 *
 * @author Caner-Hekim
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       Game g1 =  new Game();

       g1.setVisible(true);
               SampleGame s1 = (SampleGame) g1.getComponent(5);

       while(true){
           s1.moveBall();
           s1.repaint();
           
        }
    }
    
}
