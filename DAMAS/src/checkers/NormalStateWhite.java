package checkers;

import java.io.Serializable;

/** Implements the state pattern for the metods findValidMoves and 
 * findValidJumps in class Checker.
 */
public class NormalStateWhite implements CheckerState,Serializable {   
   
   // Attaches valid moves to the validMoves list. Returns true if a valid jump
   // exist.
   public boolean findValidMoves(final CheckerPosition c, final Board board, 
                              MoveList validMoves) {   
      if (! findValidJumps(c, board, validMoves)) {
         // If no valid jump exist then look for valid moves.
         if (GameSearch.validWhiteMove(c.getPosicion(), c.getPosicion().upLeftMove(), 
             board))
            validMoves.add(new Movimiento_normal(c, c.getPosicion().upLeftMove()));
            
         if (GameSearch.validWhiteMove(c.getPosicion(), c.getPosicion().upRightMove(), 
             board))
            validMoves.add(new Movimiento_normal(c, c.getPosicion().upRightMove()));
         return false;
     }
     else
      return true; 
      
   }   
   
   
   public boolean findValidJumps(CheckerPosition c, Board board, MoveList validJumps) {
      boolean found = false;
      if (GameSearch.validWhiteJump(c.getPosicion(), c.getPosicion().upLeftJump(), 
          board)) {
         validJumps.add(new MoverSaltar(c, c.getPosicion().upLeftJump()));
         found = true;
      }
      
      if (GameSearch.validWhiteJump(c.getPosicion(), c.getPosicion().upRightJump(), 
          board)) {
         validJumps.add(new MoverSaltar(c, c.getPosicion().upRightJump()));
         found = true;
      }
      return found;
   }
}

