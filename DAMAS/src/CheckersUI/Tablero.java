package CheckersUI;

import checkers.Board;
import checkers.Coordenada;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author hamza
 */
public class Tablero extends JPanel {

    ImageIcon pieza_negra = new ImageIcon(getClass().getResource("/recursos/piece_black.png"));
    ImageIcon pieza_roja = new ImageIcon(getClass().getResource("/recursos/piece_red.png"));
    ImageIcon roja_corona = new ImageIcon(getClass().getResource("/recursos/piece_redc.png"));
    ImageIcon negra_corona = new ImageIcon(getClass().getResource("/recursos/piece_blackc.png"));
    boolean newBoard = true;
    ArrayList<Point> blackPositions = new ArrayList<Point>();
    ArrayList<Point> whitePositions = new ArrayList<Point>();
    ArrayList<Dama> pawns = new ArrayList<Dama>();
    ArrayList<Point> allBoardPoints = new ArrayList<>();
    ArrayList<Integer> possiblemovesindex = new ArrayList<>();
    ArrayList<Integer> bestmovesfromhelp = new ArrayList<>();

    Board boardO = new Board();
    String turn = "your turn";
    String user_move = "";
    String computer_move = "";
    int BaW;
    int radio = 75;
    Rectangle rect = new Rectangle();

    public Tablero() {
        setSize(600, 600);
        initAllpositions();
        BaW = Dialogo.getSeleccion();
        boardO.initialize();
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        if (BaW == 0) {
            g.setColor(new java.awt.Color(255, 255, 255));
            g.fillRect(0, 0, 600, 600);
            g.setColor(new java.awt.Color(0, 0, 0));

            for (int j = 0; j < 8; j++) {
                for (int i = 0; i < 8; i++) {
                    if ((j % 2) != 0) {
                        g.fillRect(2 * radio * i, radio * j, radio, radio);
                    }
                    if ((i % 2 != 0)) {
                        g.fillRect(radio * i, 2 * radio * j, radio, radio);
                    }
                }
            }
        } else if (BaW == 1) {
            g.setColor(new java.awt.Color(203, 154, 83));
            g.fillRect(0, 0, 600, 600);
            g.setColor(new java.awt.Color(55, 21, 3));
            for (int j = 0; j < 8; j++) {
                for (int i = 0; i < 8; i++) {
                    if ((j % 2) != 0) {
                        g.fillRect(2 * radio * i, radio * j, radio, radio);
                    }
                    if ((i % 2 != 0)) {
                        g.fillRect(radio * i, 2 * radio * j, radio, radio);
                    }
                }
            }
        }
        if (newBoard) {
            drawBoard(g);
        }
        drawPawns(g);
    }

    private void initAllpositions() {
        int lignes = 0;
        for (int i = 0; i < 32; i++) {
            Point blackpos1 = new Point(5, 5);
            if (i != 0 && i % 4 == 0) {
                lignes++;
            }
            if (lignes % 2 == 0) {
                blackpos1.x = (i % 4) * 75 * 2 + 5;
                blackpos1.y = lignes * 75 + 5;
            } else {
                blackpos1.x = (i % 4) * 75 * 2 + 5 + 75;
                blackpos1.y = lignes * 75 + 5;
            }

            allBoardPoints.add(blackpos1);
        }
    }

    public void drawPawns(Graphics g) {
        for (Dama p : pawns) {
            p.getImage().paintIcon(this, g, (int) p.point.getX(), (int) p.point.getY());
        }
    }

    public void drawBoard(Graphics g) {
        pawns.clear();

        for (int i = 1; i < 33; i++) {
            Coordenada c = new Coordenada(i);
            int color = 0;

            if (boardO.getChecker(c) != null) {
                color = boardO.getChecker(c).getColor();
            }
            if (color == 2) {

                Dama p = null;
                if (boardO.getChecker(c).isKing()) {
                    p = new Dama(allBoardPoints.get(i - 1), roja_corona);
                } else {
                    p = new Dama(allBoardPoints.get(i - 1), pieza_roja);
                }

                p.posindex = i;
                pawns.add(p);
            }
            if (color == 1) {
                Dama p = null;
                if (boardO.getChecker(c).isKing()) {
                    p = new Dama(allBoardPoints.get(i - 1), negra_corona);
                } else {
                    p = new Dama(allBoardPoints.get(i - 1), pieza_negra);
                }
                p.posindex = i;
                pawns.add(p);
            }

        }
    }

    public Dama getPawnOfPosition(int pos) {

        for (Dama p : pawns) {
            if (p.posindex == pos) {
                return p;
            }
        }
        return null;
    }
}
