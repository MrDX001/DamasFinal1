package checkers;

import java.io.Serializable;

/** Implements the state pattern for the metods findValidMoves and 
 * findValidJumps in class Checker.
 */
public class KingState implements CheckerState,Serializable {    
   
   public boolean findValidMoves(final CheckerPosition c, final Board board, 
                              MoveList validMoves) {
      if (! findValidJumps(c, board, validMoves)) {         
      
         if (GameSearch.validKingMove(c.getPosicion(), c.getPosicion().downLeftMove(), 
             board)) 
            validMoves.add(new Movimiento_normal(c, c.getPosicion().downLeftMove()));
           
         if (GameSearch.validKingMove(c.getPosicion(), c.getPosicion().downRightMove(), 
             board))
            validMoves.add(new Movimiento_normal(c, c.getPosicion().downRightMove()));
        
         if (GameSearch.validKingMove(c.getPosicion(), c.getPosicion().upLeftMove(), 
             board))
            validMoves.add(new Movimiento_normal(c, c.getPosicion().upLeftMove()));
            
         if (GameSearch.validKingMove(c.getPosicion(), c.getPosicion().upRightMove(), 
             board))
            validMoves.add(new Movimiento_normal(c, c.getPosicion().upRightMove()));
         return false;
      }
      else
         return true;
   }
   
   
   public boolean findValidJumps(final CheckerPosition c, final Board board, 
                              MoveList validJumps) {
      boolean found = false;                              
      if (GameSearch.validKingJump(c.getPosicion(), c.getPosicion().downLeftJump(), 
          board)) {
         validJumps.add(new MoverSaltar(c, c.getPosicion().downLeftJump()));
         found = true;
      }
      
      if (GameSearch.validKingJump(c.getPosicion(), c.getPosicion().downRightJump(), 
          board)) {
         validJumps.add(new MoverSaltar(c, c.getPosicion().downRightJump()));
         found = true;
      }
    
      if (GameSearch.validKingJump(c.getPosicion(), c.getPosicion().upLeftJump(), 
          board)) {
         validJumps.add(new MoverSaltar(c, c.getPosicion().upLeftJump()));
         found = true;
      }
      
      if (GameSearch.validKingJump(c.getPosicion(), c.getPosicion().upRightJump(), 
          board)) {
         validJumps.add(new MoverSaltar(c, c.getPosicion().upRightJump()));
         found = true;
      }
      return found;
   }
}