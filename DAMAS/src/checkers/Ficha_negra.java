package checkers;

import javax.swing.ImageIcon; 
import java.net.URL;

public class Ficha_negra extends CheckerPosition {

   public Ficha_negra(Coordenada c) {
      checkerState = new NormalStateBlack();
      posicion = c;
      value = BLACK_VALUE_NORMAL;
      stringRep = "X";
   }
     
   
   public int getColor() {
      return BLACK;
   }
   
   
  
   
   
   public void makeKing() {
      checkerState = new KingState();
      value = BLACK_VALUE_KING;
      stringRep = "B";
   }
   
   
   public boolean isKing() {
      return (value == BLACK_VALUE_KING);
   }
   
     
   public boolean kingRow() {
      return ( (posicion.get() >= 29) && (posicion.get() <= 32) );
   }
   
   
   public CheckerPosition copy() {
      CheckerPosition newChecker = new Ficha_negra(posicion);
      if (value == BLACK_VALUE_KING)
         newChecker.makeKing();
      return newChecker;
   }          

    @Override
    public ImageIcon getIcon() {
        return  null;
    }
}