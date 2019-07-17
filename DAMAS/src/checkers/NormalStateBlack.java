package checkers;

import java.io.Serializable;

/** Implements the state pattern for the metods findValidMoves and 
 * findValidJumps in class Checker.
 */
public class NormalStateBlack implements CheckerState ,Serializable{   
   
   // Attaches valid moves to the validMoves list. Returns true if a valid jump
   // exist.
   public boolean findValidMoves(final CheckerPosition c, final Board board, 
                              MoveList validMoves) {   
      if (! findValidJumps(c, board, validMoves)) {
         // If no valid jump exist then look for valid moves.
         if (GameSearch.validBlackMove(c.getPosicion(), c.getPosicion().downLeftMove(), 
             board))
            validMoves.add(new Movimiento_normal(c, c.getPosicion().downLeftMove()));
            
         if (GameSearch.validBlackMove(c.getPosicion(), c.getPosicion().downRightMove(), 
             board))
            validMoves.add(new Movimiento_normal(c, c.getPosicion().downRightMove()));
         return false;
      }
      else
         return true;
   }
   
   // Attaches valid jumps to the validJumps list. Returns true if a valid jump
   // exist.
   public boolean findValidJumps(final CheckerPosition c, final Board board, 
                              MoveList validJumps) {   
      boolean found = false;
      if (GameSearch.validBlackJump(c.getPosicion(), c.getPosicion().downLeftJump(), 
        board)) {
         validJumps.add(new MoverSaltar(c, c.getPosicion().downLeftJump()));
         found = true;
      }
      
      if (GameSearch.validBlackJump(c.getPosicion(), c.getPosicion().downRightJump(), 
          board)) {
         validJumps.add(new MoverSaltar(c, c.getPosicion().downRightJump()));
         found = true;
      }     
      return found;
   }
}