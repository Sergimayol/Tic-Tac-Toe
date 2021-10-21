/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.lang.Math.random;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Sergi
 */
public class TicTacToe extends JFrame implements ActionListener {

    //DECLARACIÓN OBJETO Container PARA REPRESENTAR EL PANEL DE
    //CONTENIDOS DEL OBJETO JFrame
    private Container panelContenidos;
    private JButton casillas[];
    private JPanel panelCasillas;
    private JPanel panelTexto;
    private JLabel texto;
    private final Color colorCasillas;
    private final int MAXDIMENSION = 800;
    private boolean Jugador1;
    private int empate;

    public static void main(String[] args) {
        new TicTacToe();

    }

    //Constructor
    public TicTacToe() {
        colorCasillas = Colores.ALICE_BLUE;
        casillas = new JButton[9];
        panelCasillas = new JPanel();
        panelCasillas.setLayout(new GridLayout(3, 3));
        panelCasillas.setBackground(colorCasillas);
        panelTexto = new JPanel();
        panelTexto.setLayout(new BorderLayout());
        panelTexto.setBounds(0, 0, MAXDIMENSION, 100);
        texto = new JLabel();
        configVentana();
        configCasillas();
        configTextoInfo();
        incioJuego();
    }

    private void configCasillas() {
        for (int i = 0; i < casillas.length; i++) {
            casillas[i] = new JButton();
            casillas[i].setBackground(colorCasillas);
            casillas[i].setFont(new Font("Ink Free", Font.BOLD, 120));
            casillas[i].setFocusable(false);
            casillas[i].addActionListener(this);
            panelCasillas.add(casillas[i]);
        }
    }

    private void configTextoInfo() {
        //Configuracion texto
        texto.setBackground(Colores.ALICE_BLUE);
        texto.setForeground(Colores.VIRIDIAN);
        texto.setFont(new Font("Ink Free", Font.BOLD, 75));
        texto.setHorizontalAlignment(JLabel.CENTER);
        texto.setText("Tic Tac Toe");
        texto.setOpaque(true);
        //Adicion del texto al JPanel
        panelTexto.add(texto);
    }

    //Creación de la interfaz
    private void configVentana() {
        //Añadimos un titulo a la ventana
        setTitle("TIC TAC TOE");
        //Alamacena en la variable nuestro sistema nativo de ventanas
        Toolkit pantalla = Toolkit.getDefaultToolkit();
        //Tamañano de la pantalla del usuario
        Dimension tampant = pantalla.getScreenSize();
        //Obtener el alto de resolución de pantalla
        int altpant = tampant.height;
        //Obtener el ancho de resolución de pantalla
        int anchopant = tampant.width;
        //Localización(x,y) + tamaño(ancho,alto). De esta manera siempre
        //la ventana estará situada en el centro
        setBounds(anchopant / 4, altpant / 6, MAXDIMENSION, MAXDIMENSION);
        //Activar el cierre interactivo del contenedor JFrame ventana para finalizar
        //ejecución
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Asignación al objeto Container panelContenidos DEL PANEL JContentPane 
        //del JFrame 
        panelContenidos = getContentPane();
        //asignación administrador de Layout BordeLayout al panel de contenidos
        //del JFrame
        panelContenidos.setLayout(new BorderLayout());
        //Adicion panel casillas al panel contenidos
        panelContenidos.add(panelTexto, BorderLayout.NORTH);
        panelContenidos.add(panelCasillas, BorderLayout.CENTER);
        //Activar visualización contenedor JFrame ventana
        setVisible(true);
    }

    //Metodo encargado de la gestion de la interface
    private void incioJuego() {
        int inicio;
        Random random;
        try {
            random = new Random();
            Thread.sleep(2000);
            texto.setText("Preparando...");
            inicio = random.nextInt(100);
            Thread.sleep(1000);
            if (inicio <= 50) {
                Jugador1 = false;
                texto.setText("Turno de: O");
            } else {
                Jugador1 = true;
                texto.setText("Turno de: X");
            }
        } catch (InterruptedException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.toString());
        }
    }

    private void finPartida(String s) {
        empate = 0;
        Object[] option = {"Reiniciar", "Salir"};
        try {
            int n = JOptionPane.showOptionDialog(this, "Game Over\n" + s, "Game Over", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, option, option[0]);
            if (n == 0) {
                this.dispose();
                new TicTacToe();
            } else {
                this.dispose();
            }
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.toString());
        }
    }

    private void jugadorGanaPartida(String s, int g1, int g2, int g3) {
        casillas[g1].setBackground(Colores.DARK_RED);
        casillas[g2].setBackground(Colores.DARK_RED);
        casillas[g3].setBackground(Colores.DARK_RED);
        for (JButton casilla : casillas) {
            casilla.setEnabled(false);
        }
        texto.setText(s);
        finPartida(s);
    }

    private void comprobarVictoria() {
        String x = "Jugador X gana";
        String o = "Jugador O gana";
        String empateJugs = "Empate";
        if (("X".equals(casillas[0].getText())) && ("X".equals(casillas[1].getText())) && ("X".equals(casillas[2].getText()))) {
            jugadorGanaPartida(x, 0, 1, 2);
        } else if (("X".equals(casillas[0].getText())) && ("X".equals(casillas[4].getText())) && ("X".equals(casillas[8].getText()))) {
            jugadorGanaPartida(x, 0, 4, 8);
        } else if (("X".equals(casillas[0].getText())) && ("X".equals(casillas[3].getText())) && ("X".equals(casillas[6].getText()))) {
            jugadorGanaPartida(x, 0, 3, 6);
        } else if (("X".equals(casillas[1].getText())) && ("X".equals(casillas[4].getText())) && ("X".equals(casillas[7].getText()))) {
            jugadorGanaPartida(x, 1, 4, 7);
        } else if (("X".equals(casillas[2].getText())) && ("X".equals(casillas[4].getText())) && ("X".equals(casillas[6].getText()))) {
            jugadorGanaPartida(x, 2, 4, 6);
        } else if (("X".equals(casillas[2].getText())) && ("X".equals(casillas[5].getText())) && ("X".equals(casillas[8].getText()))) {
            jugadorGanaPartida(x, 2, 5, 8);
        } else if (("X".equals(casillas[3].getText())) && ("X".equals(casillas[4].getText())) && ("X".equals(casillas[5].getText()))) {
            jugadorGanaPartida(x, 3, 4, 5);
        } else if (("X".equals(casillas[6].getText())) && ("X".equals(casillas[7].getText())) && ("X".equals(casillas[8].getText()))) {
            jugadorGanaPartida(x, 6, 7, 8);
        } else if (("O".equals(casillas[0].getText())) && ("O".equals(casillas[1].getText())) && ("O".equals(casillas[2].getText()))) {
            jugadorGanaPartida(o, 0, 1, 2);
        } else if (("O".equals(casillas[0].getText())) && ("O".equals(casillas[3].getText())) && ("O".equals(casillas[6].getText()))) {
            jugadorGanaPartida(o, 0, 3, 6);
        } else if (("O".equals(casillas[0].getText())) && ("O".equals(casillas[4].getText())) && ("O".equals(casillas[8].getText()))) {
            jugadorGanaPartida(o, 0, 4, 8);
        } else if (("O".equals(casillas[1].getText())) && ("O".equals(casillas[4].getText())) && ("O".equals(casillas[7].getText()))) {
            jugadorGanaPartida(o, 1, 4, 7);
        } else if (("O".equals(casillas[2].getText())) && ("O".equals(casillas[4].getText())) && ("O".equals(casillas[6].getText()))) {
            jugadorGanaPartida(o, 2, 4, 6);
        } else if (("O".equals(casillas[2].getText())) && ("O".equals(casillas[5].getText())) && ("O".equals(casillas[8].getText()))) {
            jugadorGanaPartida(o, 2, 5, 8);
        } else if (("O".equals(casillas[3].getText())) && ("O".equals(casillas[4].getText())) && ("O".equals(casillas[5].getText()))) {
            jugadorGanaPartida(o, 3, 4, 5);
        } else if (("O".equals(casillas[6].getText())) && ("O".equals(casillas[7].getText())) && ("O".equals(casillas[8].getText()))) {
            jugadorGanaPartida(o, 6, 7, 8);
        } else if (empate == 9) {
            texto.setText(empateJugs);
            finPartida(empateJugs);
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        for (JButton casilla : casillas) {
            if (ae.getSource() == casilla) {
                if (Jugador1) {
                    if ("".equals(casilla.getText())) {
                        casilla.setForeground(Colores.DARK_MEGENTA);
                        casilla.setText("X");
                        Jugador1 = false;
                        texto.setText("Turno de: O");
                        empate++;
                        comprobarVictoria();
                    }
                } else {
                    if ("".equals(casilla.getText())) {
                        casilla.setForeground(Colores.STEEL_BLUE);
                        casilla.setText("O");
                        Jugador1 = true;
                        texto.setText("Turno de: X");
                        empate++;
                        comprobarVictoria();
                    }
                }
            }
        }
    }
}
