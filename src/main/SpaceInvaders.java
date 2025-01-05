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

    //aliens
    ArrayList<Block> listeAliens;
    int largeurAlien = tileSize*2;
    int hauteurAlien = tileSize;
    int alienX = tileSize;
    int alienY = tileSize;

    int alienRows = 2; // nb de ligne d'aliens
    int alienColumns = 3; // nb de colonne d'aliens
    int alienCount = 0; // score
    int alienVelocityX = 1; //vitesse de déplacement de l'alien

    //balles
    ArrayList<Block> listeBalle;
    int largeurBalle = tileSize/8;
    int hauteurBalle = tileSize/2;
    int balleVelocityY = -10; //vitesse de déplacement d'une balle

    Timer gameLoop; // boucle du jeu pour update chaque mouvement

    //balles pour tirer sur les aliens


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
        listeAliens = new ArrayList<Block>();
        listeBalle = new ArrayList<Block>();

        //timer du jeu
        gameLoop = new Timer(1000/60, this); // appel de la classe en elle même, 60 fois par secondes
        creationAliens();
        gameLoop.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        // vaisseau
        g.drawImage(vaisseau.img, vaisseau.x, vaisseau.y, vaisseau.width, vaisseau.height, null);

        // aliens
        for (int i = 0; i < listeAliens.size(); i++) {
            Block alien = listeAliens.get(i);
            if (alien.alive) {
                g.drawImage(alien.img, alien.x, alien.y, alien.width, alien.height, null);
            }
        }

        //balle
        g.setColor(Color.CYAN);
        for (int i = 0; i < listeBalle.size(); i++) {
            Block balle = listeBalle.get(i);
            if (!balle.used) {
                //g.drawRect(balle.x, balle.y, balle.width, balle.height);
                g.fillRect(balle.x, balle.y, balle.width, balle.height);
            }
        }
    }

    public void move() {
        //aliens
        for (int i = 0; i < listeAliens.size(); i++) {
            Block alien = listeAliens.get(i);
            if (alien.alive) {
                alien.x += alienVelocityX;

                //si l'alien touche les bords
                if (alien.x + largeurAlien >= boardWidth || alien.x <= 0) {
                    alienVelocityX *= -1;
                    alien.x += alienVelocityX * 2;

                    //changement de ligne vers le bas
                    for (int j = 0; j < listeAliens.size(); j++) {
                        listeAliens.get(j).y += hauteurAlien;
                    }
                }
            }
        }

        //balles
        for (int i = 0; i < listeBalle.size(); i++) {
            Block balle = listeBalle.get(i);
            balle.y += balleVelocityY;
        }
    }

    public void creationAliens() {
        Random random = new Random(); // sélection d'alien de couleur aléatoire
        for (int r = 0; r < alienRows; r++) {
            for (int c = 0; c < alienColumns; c++) {
                int randomImgIndex = random.nextInt(listeImgAlien.size());
                Block alien = new Block(
                        alienX + c*largeurAlien,
                        alienY + r*hauteurAlien,
                        largeurAlien,
                        hauteurAlien,
                        listeImgAlien.get(randomImgIndex)
                );
                listeAliens.add(alien);
            }
        }
        alienCount = listeAliens.size();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT && vaisseau.x - vaisseauVelocityX >= 0) {
            vaisseau.x -= vaisseauVelocityX; // bouge à gauche de 1 bloc
        }
        else if (e.getKeyCode() == KeyEvent.VK_RIGHT && vaisseau.x + largeurVaisseau + vaisseauVelocityX <= boardWidth) {
            vaisseau.x += vaisseauVelocityX; // bouge à droite de 1 bloc
        }
        else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            Block balle = new Block(vaisseau.x + largeurVaisseau*15/32, vaisseau.y, largeurBalle, hauteurBalle, null);
            listeBalle.add(balle);
        }
    }
}
