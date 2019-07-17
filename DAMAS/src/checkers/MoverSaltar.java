package checkers;

public class MoverSaltar extends Mover {
 
   public MoverSaltar(CheckerPosition checker, Coordenada destination) {
      this.checker = checker;
      this.destination = destination;
   }
  
  
   public boolean isJump() {
      return true;
   }
   
   
   public Mover copy() {
      return new MoverSaltar(checker.copy(), destination);
   }
   
   
   // Return a copy of this move from the newBoard.   
   public Mover copy(Board newBoard) {
      return new MoverSaltar(newBoard.getChecker(checker.getPosicion()), 
                          destination);
   }
      
  
   // Returns the coordinate of the captured checker of this move.
   public Coordenada capturedCoordinate() {
      if (checker.getPosicion().row() - destination.row() == 2) { // Up.
         if (checker.getPosicion().column() - destination.column() == 2) //Up,left.
            return checker.getPosicion().upLeftMove();      
         else
            return checker.getPosicion().upRightMove();
      }
      else { // Down.
         if (checker.getPosicion().column() - destination.column() == 2)//Down,left.
            return checker.getPosicion().downLeftMove();      
         else
            return checker.getPosicion().downRightMove();     
      }
   }
  
   
   public String toString() {
      String s = "";
      if (checker.getColor() == CheckerPosition.BLACK) s = "Black-J:"; else s = "White-J:";
      s = s + "(" + checker.getPosicion() + "-" + destination + ")";
      return s; 
   }
}