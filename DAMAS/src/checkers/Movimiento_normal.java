package checkers;

public class Movimiento_normal extends Mover {
   
   public Movimiento_normal(CheckerPosition checker, Coordenada destination) {
      this.checker = checker;
      this.destination = destination;
   }
   
   
   public boolean isJump() {
      return false;
   }
   
   
   public Mover copy() {
      return new Movimiento_normal(checker.copy(), destination);
   }

   
   // Return a copy of this move from the newBoard.   
   public Mover copy(Board newBoard) {
      return new Movimiento_normal(newBoard.getChecker(checker.getPosicion()), 
                            destination);
   }
   
   
   public String toString() {
      String s = "Move: ";
      if (checker.getColor() == CheckerPosition.BLACK) s = "Black:"; else s = "White:";
      s = s + "(" + checker.getPosicion() + "-" + destination + ")";
      return s; 
   }
}