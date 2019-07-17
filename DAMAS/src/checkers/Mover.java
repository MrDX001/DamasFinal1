package checkers;

import java.io.Serializable;

public abstract class Mover implements Serializable{   
   protected Coordenada destination;
   protected CheckerPosition checker;
   protected Mover next = null;
      
   public abstract boolean isJump();
   public abstract String toString();
   public abstract Mover copy(Board newBoard);
   public abstract Mover copy();
   
   
   public CheckerPosition getChecker() {
      return checker;
   }
   
   
   public Coordenada getDestination() {
      return destination;
   }


   // For the MoveList class.
   public void setNext(Mover next) {
      this.next = next;   
   }
   
   
   // For the MoveList class.
   public Mover getNext() {
      return next;
   }
}