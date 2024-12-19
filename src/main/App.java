package main;
import javax.swing.*;
public class App {
    public static void main(String[] args) {
        // Variable pour la fenêtre
        int tileSize = 32;
        int rows = 16;
        int columns = 16;
        int boardWidth = tileSize * columns; // = 512px
        int boardHeight = tileSize * rows; // = 512px

        JFrame frame = new JFrame("Space Invaders");
        frame.setVisible(true);
        frame.setSize(boardWidth, boardHeight);
        frame.setLocationRelativeTo(null); // Ouverture de la fenêtre au centre de l'écran
        frame.setResizable(false); // Taille statique
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        SpaceInvaders spaceInvaders = new SpaceInvaders(); //Instanciation d'un JPanel custom
        frame.add(spaceInvaders);
        frame.pack();
        frame.setVisible(true);
    }
}