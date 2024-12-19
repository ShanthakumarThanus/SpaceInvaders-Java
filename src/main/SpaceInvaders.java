package main;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

public class SpaceInvaders extends JPanel {
    //specs fenêtre
    int tileSize = 32;
    int rows = 16;
    int columns = 16;
    int boardWidth = tileSize * columns;
    int boardHeight = tileSize * rows;

    Image ImgVaisseau;
    Image ImgAlien;
    Image ImgAlienCyan;
    Image ImgAlienMagenta;
    Image ImgAlienJaune;
    ArrayList<Image> ListeImgAlien;

    SpaceInvaders() {
        setPreferredSize(new Dimension(boardWidth, boardHeight));
        setBackground(Color.black);
    }
}
