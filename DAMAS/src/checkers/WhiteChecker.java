package checkers;

import javax.swing.ImageIcon;
import java.net.URL;

/**
 * Represent a white piece, which means that the piece moves upwards.
 */
public class WhiteChecker extends CheckerPosition {

    public WhiteChecker(Coordenada c) {
        checkerState = new NormalStateWhite();
        posicion = c;
        value = WHITE_VALUE_NORMAL;
        stringRep = "O";
    }

    public int getColor() {
        return WHITE;
    }

    public ImageIcon getIcon() {
        return null;
    }

    public void makeKing() {
        checkerState = new KingState();
        value = WHITE_VALUE_KING;
        stringRep = "W";
    }

    public boolean kingRow() {
        return ((posicion.get() >= 1) && (posicion.get() <= 4));
    }

    public boolean isKing() {
        return (value == WHITE_VALUE_KING);
    }

    public CheckerPosition copy() {
        CheckerPosition newChecker = new WhiteChecker(posicion);
        if (value == WHITE_VALUE_KING) {
            newChecker.makeKing();
        }
        return newChecker;
    }
}
