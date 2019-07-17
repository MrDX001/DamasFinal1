package checkers;

import java.io.Serializable;

/** 
 * A linked list of moves.
 */
public class MoveList implements Serializable{
   private Mover moveList;
   private Mover last;
   private int listSize;
   
   
   public MoveList() {
      listSize = 0;   
      moveList = null;
   }


   public void add(Mover c) {
      if (moveList == null) { // first element.
         moveList = c;
         last = c;
      }
      else {
         last.setNext(c);
         last = c;
      }               
      listSize++;
   } 
   
   
   public void remove(Mover move) {
      if (move == moveList) {  // first element.
         moveList = moveList.getNext();
         listSize--;
      }
      else {
         MoveIterator iterator = getIterator();   
         Mover previous = iterator.next();    // previous = first element.
         Mover current = null;
         while (iterator.hasNext()) {
            current = iterator.next();
            if (move == current) {
               previous.setNext(current.getNext());
               listSize--;
            }
         }
      }
   }
      
   
   public int size() {
      return listSize;
   }   
   
   
   public Mover first() {
      return moveList;
   }
   
   
   public void reset() {
      listSize = 0;
      moveList = null;
   }
   
   
   public Mover get(int index) throws IndexOutOfBoundsException {
      int current = 0;
      Mover move = moveList;
      while (current != index) {
         move = move.getNext();
         if (move == null) throw new IndexOutOfBoundsException(); 
         current++;
      }
      return move;
   }
   
   
   public MoveIterator getIterator() {
      return new MoveIterator(this);
   }
   
   
   public MoveList copy() {
      MoveIterator iterator = getIterator();
      MoveList newList = new MoveList();
      while (iterator.hasNext())
         newList.add(iterator.next().copy());
      return newList;
   }
   
   
   public String toString() {
      MoveIterator iterator = getIterator();
      String s = "Movelist: ";
      while (iterator.hasNext()) {
         s = s + iterator.next().toString();
         if (iterator.hasNext()) s = s + " , ";
      }
      return s;
   }
}