package CheckersUI;

import checkers.Board;
import checkers.CheckerPosition;
import checkers.Coordenada;
import checkers.Mover;
import checkers.MoveIterator;
import checkers.MoverSaltar;
import checkers.MoveList;
import checkers.Movimiento_normal;
import checkers.GameSearch;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.MenuItem;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JTextField;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

/**
 *
 * @author hamza
 */
public class Ventana extends JFrame implements MouseListener, MouseMotionListener, KeyListener {

    
    private Tablero pan;
    private Menu juego;
    Dialogo DialogJ = new Dialogo(this, true);
    private CheckerPosition multipleJumpsChecker = null;
    ArrayList<Board> boardHistory = new ArrayList<Board>();
    private int clickedHere = 0;
    int FromPawnIndex = 0;
    int ToPawnIndex = 0;
    private static final int FROM = 1;
    private static final int TO = 2;
    private static final int FROM_MULTIPLE = 3;
    private static final int TO_MULTIPLE = 4;
    private static final int COMPUTER_THINKS = 5;
    private static final int NOT_STARTED = 6;
    private int userColor = CheckerPosition.WHITE;
    private int computerColor = CheckerPosition.BLACK;
    private Coordenada from;
    private int thinkDepth = 2;
    private boolean alreadyMoved;
    private boolean moving;
    private int nbrBacks = 0;
    private boolean isForward =false;
    private int nbrBack = 0;
    private int nbrForward = 0;
    private boolean isBack = false;
    String output = "";
    int currentPositionInBoradHistory = 0;
    static int algorithme = 1;
    boolean playMusic = true;

    public Ventana() {
        
        DialogJ.setLocationRelativeTo(null);
        DialogJ.setVisible(true);
        this.dispose();
        initComponents();
        
    }

    
    private void initComponents() {
        
        setTitle("DAMAS");
       
        pan = new Tablero();
        setLayout(null);
        setSize(919, 600);
        juego = new Menu();
        juego.setLocation(600, 0);
         pan.setLocation(0,0);
        setResizable(false);
        setUndecorated(true);
        setLocationRelativeTo(null);
        add(juego);
        add(pan);
        addKeyListener(this);
        addMouseListener(this);
        addMouseMotionListener(this);
        setVisible(true);

    }

    public void createMenu() {
        //Create the menu bar.

        // create Algorithmes menu item
        JMenuItem resetBoard = new JMenuItem("reset board", null);
        resetBoard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Do resetBoard ");
                pan.pawns.clear();
                pan.boardO.initialize();
                boardHistory.clear();
                currentPositionInBoradHistory = 0;
                isBack = false;
                pan.repaint();

            }
        });

    }

    public static void main(String[] args) {
        new Ventana();
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        int test = 0;
        for (int i = 0; i < pan.allBoardPoints.size(); i++) {
            if (e.getX() > (int) pan.allBoardPoints.get(i).getX()
                    && e.getX() < (int) (pan.allBoardPoints.get(i).getX() + 75)
                    && e.getY() - 40 < (int) (pan.allBoardPoints.get(i).getY() + 75)
                    && e.getY() - 40 > (int) (pan.allBoardPoints.get(i).getY())) {
                test = (i + 1);
                break;
            }
        }

        for (int i = 0; i < pan.pawns.size(); i++) {

            if (e.getX() > (int) pan.pawns.get(i).point.getX() && e.getX() < (int) (pan.pawns.get(i).point.getX() + 75)
                    && e.getY() - 27 < (int) (pan.pawns.get(i).point.getY() + 75) && e.getY() - 27 > (int) (pan.pawns.get(i).point.getY())) {
                clickedHere = i;
                break;
            }
        }

        MoveList validMoves;
        validMoves = GameSearch.findAllValidMoves(pan.boardO, userColor);
        pan.possiblemovesindex.clear();
        for (int i = 0; i < validMoves.size(); i++) {
            if ((test - 1) >= 0 && pan.boardO.getChecker(new Coordenada(test)) != null && validMoves.get(i).getChecker().getPosicion() == pan.boardO.getChecker(new Coordenada(test)).getPosicion()) {
                pan.possiblemovesindex.add(validMoves.get(i).getDestination().get());
                pan.repaint();
            }

        }

        if (e.getX() > 690 && e.getX() < 690 + 54 && e.getY() - 27 > 530 && e.getY() - 27 < 530 + 54) {
            pan.pawns.clear();
            pan.boardO.initialize();
            boardHistory.clear();
            currentPositionInBoradHistory = 0;
            isBack = false;
            pan.repaint();
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

        if (alreadyMoved) {
            FromPawnIndex = clickedHere + 1;
            for (int i = 0; i < pan.allBoardPoints.size(); i++) {

                if (e.getX() > (int) pan.allBoardPoints.get(i).getX() && e.getX() < (int) (pan.allBoardPoints.get(i).getX() + 75)
                        && e.getY() - 27 < (int) (pan.allBoardPoints.get(i).getY() + 75) && e.getY() - 27 > (int) (pan.allBoardPoints.get(i).getY())) {
                    ToPawnIndex = i + 1;
                    break;
                }
            }

            if (clickedHere >= 0) {
                moveUser(new Coordenada((pan.pawns.get(clickedHere).posindex)), new Coordenada(ToPawnIndex));
            }
            pan.newBoard = true;
            pan.repaint();
            clickedHere = -48;
            alreadyMoved = false;
            moving = false;
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        
        alreadyMoved = true;
        pan.possiblemovesindex.clear();
        pan.bestmovesfromhelp.clear();

        if (!moving) {
            for (int i = 0; i < pan.pawns.size(); i++) {
                if (e.getX() > (int) pan.pawns.get(i).point.getX()
                        && e.getX() < (int) (pan.pawns.get(i).point.getX() + 75)
                        && e.getY() - 27 < (int) (pan.pawns.get(i).point.getY() + 75)
                        && e.getY() - 27 > (int) (pan.pawns.get(i).point.getY())) {
                    clickedHere = i;
                    break;
                }
            }
        }

        if (clickedHere >= 0) {
            pan.newBoard = false;
            moving = true;
            pan.pawns.get(clickedHere).setP(new Point(e.getX() - 75 / 2, e.getY() - 40 - 75 / 2));
            pan.repaint();

        }
   
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    public void moveUser(Coordenada from, Coordenada to) {
        pan.turn = "your turn";

        Mover move = validateUserMove(from, to);
        if (move == null) {
            System.out.println(" The Move is not Valid ");
            outputText("Invalid move.");
        } else if (move.isJump()) {

            if (isBack) {
                int currentBoard = 0;
                currentBoard = currentPositionInBoradHistory;
                removeBordsAfter(currentPositionInBoradHistory + 1);
                isBack = false;
                currentPositionInBoradHistory = boardHistory.size() + 1;
            } else if (boardHistory.size() == 0) {
                currentPositionInBoradHistory = 0;
                boardHistory.add(pan.boardO);
            }
            pan.boardO = GameSearch.executeUserJump(move, pan.boardO);
            multipleJumpsChecker = pan.boardO.getChecker(move.getDestination());
            if (mandatoryJump(multipleJumpsChecker, pan.boardO)) {
                outputText("A multiple jump must be completed.");
            } else {
                computerMoves();
            }
        } else // Normal move.
        if (GameSearch.existJump(pan.boardO, userColor)) {
            outputText("Invalid move. If you can jump, you must.");
        } else {

            if (isBack) {
                int currentBoard = 0;
                currentBoard = currentPositionInBoradHistory;
                removeBordsAfter(currentPositionInBoradHistory + 1);
                isBack = false;

                currentPositionInBoradHistory = boardHistory.size() + 1;
            } else if (boardHistory.size() == 0) {
                currentPositionInBoradHistory = 0;
                boardHistory.add(pan.boardO);
            }
            pan.boardO = GameSearch.executeMove(move, pan.boardO);
            pan.user_move = move.toString();
            computerMoves();
        }
    }

    public Mover validateUserMove(Coordenada from, Coordenada to) {
        Mover move = null;
        CheckerPosition checker = pan.boardO.getChecker(from);
        if (checker == null) {
        }
        if (checker != null) {
            if (userColor == CheckerPosition.WHITE) {
                if (checker.getColor() == CheckerPosition.WHITE) {
                    if (checker.getValue() == CheckerPosition.WHITE_VALUE_KING) {
                        if (Math.abs(from.row() - to.row()) == 1) {

                            if (GameSearch.validKingMove(from, to, pan.boardO)) {
                                move = new Movimiento_normal(checker, to);
                            }
                        } else if (GameSearch.validKingJump(from, to, pan.boardO)) {

                            move = new MoverSaltar(checker, to);
                        }
                    } else // Normal white checker. 
                    if (from.row() - to.row() == 1) {

                        if (GameSearch.validWhiteMove(from, to, pan.boardO)) {
                            move = new Movimiento_normal(checker, to);
                        }
                    } else if (GameSearch.validWhiteJump(from, to, pan.boardO)) {
                        move = new MoverSaltar(checker, to);
                    }
                }
            } else // User is black.
            if (checker.getColor() == CheckerPosition.BLACK) {
                if (checker.getValue() == CheckerPosition.BLACK_VALUE_KING) {
                    if (Math.abs(from.row() - to.row()) == 1) {
                        if (GameSearch.validKingMove(from, to, pan.boardO)) {
                            move = new Movimiento_normal(checker, to);
                        }
                    } else if (GameSearch.validKingJump(from, to, pan.boardO)) {
                        move = new MoverSaltar(checker, to);
                    }
                } else // Normal black checker.
                if (to.row() - from.row() == 1) {
                    if (GameSearch.validBlackMove(from, to, pan.boardO)) {
                        move = new Movimiento_normal(checker, to);
                    }
                } else if (GameSearch.validBlackJump(from, to, pan.boardO)) {
                    move = new MoverSaltar(checker, to);
                }
            }
        }
        return move;
    }

    private void outputText(String s) {
        output = "\n>>> " + s;
        System.out.println("" + (output));
    }

    // Returns true if checker can make a jump.
    private boolean mandatoryJump(CheckerPosition checker, Board board) {
        MoveList movelist = new MoveList();
        checker.findValidJumps(movelist, board);
        if (movelist.size() != 0) {
            return true;
        } else {
            return false;
        }
    }

    // The computer thinks....
    public void computerMoves() {
        pan.turn = " Computer turn ";
        MoveList validMoves = GameSearch.findAllValidMoves(pan.boardO, computerColor);
        if (validMoves.size() == 0) {
            JOptionPane.showMessageDialog((Component) this, "\nCongratulations!"
                    + "You win\n", "Checkers", JOptionPane.INFORMATION_MESSAGE);
            outputText("You win.");

        } else {

            pan.boardO.getHistory().reset();
            Board comBoard = null;
           
            if (algorithme == 1) {
                comBoard = GameSearch.minimax(pan.boardO, thinkDepth, computerColor);
            }
            Mover move = comBoard.getHistory().first();

            pan.boardO = GameSearch.executeMove(move, pan.boardO);

            if (!isBack && !isForward) {
                boardHistory.add(pan.boardO);
                currentPositionInBoradHistory = boardHistory.size() - 1;
            }
            MoveIterator iterator = pan.boardO.getHistory().getIterator();
            String moves = "";
            while (iterator.hasNext()) {
                moves = moves + iterator.next();
                if (iterator.hasNext()) {
                    moves = moves + " , ";
                }
            }
            pan.computer_move = moves;
            int s = moves.indexOf("(");
            int ss = moves.indexOf(")");
            String[] values = moves.substring(s + 1, ss).split("-");
            outputText("the computer make this move : " + moves);
            validMoves = GameSearch.findAllValidMoves(pan.boardO, userColor);
            if (validMoves.size() == 0) {
                JOptionPane.showMessageDialog((Component) this, "Computer wins!", "Checkers", JOptionPane.INFORMATION_MESSAGE);
                outputText("Sorry. The computer wins.");
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    private void removeBordsAfter(int i) {
        int taille = boardHistory.size();
        for (int k = taille - 1; k >= i; k--) {
            boardHistory.remove(k);
        }
    }


}
