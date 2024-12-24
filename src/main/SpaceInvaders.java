package main;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

public class SpaceInvaders extends JPanel {
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

    Block vaisseau;

    SpaceInvaders() {
        setPreferredSize(new Dimension(boardWidth, boardHeight));
        setBackground(Color.black);

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
    }
}
