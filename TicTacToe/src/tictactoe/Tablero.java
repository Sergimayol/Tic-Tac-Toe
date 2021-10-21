/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author Sergi
 */
public class Tablero extends JPanel {

    private Casilla[][] tablero;
    private final int FILASYCOLUMNAS = 3;

    public Tablero() {
        tablero = new Casilla[FILASYCOLUMNAS][FILASYCOLUMNAS];
    }

    @Override
    public void paintComponent(Graphics g) {

    }

}
