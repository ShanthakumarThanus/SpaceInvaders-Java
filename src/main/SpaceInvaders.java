package main;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

public class SpaceInvaders extends JPanel implements ActionListener, KeyListener {

    //classe pour la position du vaisseau / alien / balles
    class Block {
        int x;
        int y;
        int width;
        int height;
        Image img;
        boolean alive = true; //utilisé pour les aliens
        boolean used = false; //utilisé pour les balles

        Block(int x, int y, int width, int height, Image img) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
            this.img = img;
        }
    }

    //specs fenêtre
    int tileSize = 32;
    int rows = 16;
    int columns = 16;
    int boardWidth = tileSize * columns;
    int boardHeight = tileSize * rows;

    Image imgVaisseau;
    Image imgAlien;
    Image imgAlienCyan;
    Image imgAlienMagenta;
    Image imgAlienJaune;
    ArrayList<Image> listeImgAlien;

    //vaisseau
    int largeurVaisseau = tileSize*2; //64px
    int hauteurVaisseau = tileSize; //32px
    int vaisseauX = tileSize*columns/2 - tileSize;
    int vaisseauY = boardHeight - tileSize*2;
    int vaisseauVelocityX = tileSize; // déplacement position axe x du vaisseau
    Block vaisseau;

    Timer gameLoop; // boucle du jeu pour update chaque mouvement

    SpaceInvaders() {
        setPreferredSize(new Dimension(boardWidth, boardHeight));
        setBackground(Color.black);
        setFocusable(true);
        addKeyListener(this);

        //chargement des images
        imgVaisseau = new ImageIcon(getClass().getResource("./ship.png")).getImage();
        imgAlien = new ImageIcon(getClass().getResource("./alien.png")).getImage();
        imgAlienCyan = new ImageIcon(getClass().getResource("./alien-cyan.png")).getImage();
        imgAlienMagenta = new ImageIcon(getClass().getResource("./alien-magenta.png")).getImage();
        imgAlienJaune = new ImageIcon(getClass().getResource("./alien-yellow.png")).getImage();

        listeImgAlien = new ArrayList<Image>();
        listeImgAlien.add(imgAlien);
        listeImgAlien.add(imgAlienCyan);
        listeImgAlien.add(imgAlienMagenta);
        listeImgAlien.add(imgAlienJaune);

        vaisseau = new Block(vaisseauX, vaisseauY, largeurVaisseau, hauteurVaisseau, imgVaisseau);

        //timer du jeu
        gameLoop = new Timer(1000/60, this); // appel de la classe en elle même, 60 fois par secondes
        gameLoop.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        g.drawImage(vaisseau.img, vaisseau.x, vaisseau.y, vaisseau.width, vaisseau.height, null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            vaisseau.x -= vaisseauVelocityX; // bouge à gauche de 1 bloc
        }
        else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            vaisseau.x += vaisseauVelocityX; // bouge à droite de 1 bloc
        }
    }
}
