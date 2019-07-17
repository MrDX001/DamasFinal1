package checkers;

import java.io.Serializable;

public class Board implements Serializable{
   private CheckerPosition[] board = new CheckerPosition[32];
   private MoveList history = new MoveList();  // History of moves.
   protected Board next = null;     
 
     
   public Board copy() {
      Board newBoard = new Board();
      Coordenada temp = null;
      newBoard.setHistory(history.copy());
      for (int i = 1; i < 33; i++) {
         temp = new Coordenada(i);
         if (getChecker(temp) != null)
            newBoard.setChecker(getChecker(temp).copy(), temp);
      }
      return newBoard;
      //for (int y = 0; y < 8; y++) {
         //for (int x = (y + 1) % 2; x < 8; x = x + 2)
   }

             
   public CheckerPosition getChecker(Coordenada c) {
      return board[c.get() - 1];
   }
   
   
   public void setChecker(CheckerPosition checker, Coordenada c) {
      board[c.get() - 1] = checker;
      checker.setPosicion(c);
   }
   
   
   public void removeChecker(CheckerPosition checker) {
      board[checker.getPosicion().get() - 1] = null;
   }
   
   
   public boolean vacantCoordinate(Coordenada c) {
      return (getChecker(c) == null);
   }

   
   public int evaluate() {
      int score = 0;
      Coordenada c = null;
      for (int i = 1; i < 33; i++) {
         c = new Coordenada(i);
         if (getChecker(c) != null)
            score = score + getChecker(c).getValue();
      }
      return score;
   }
  

   public void setHistory(MoveList history) {
      this.history = history;
   }
   
   
   public MoveList getHistory() {
      return history;
   }


   public void addMoveToHistory(Mover move) {
      history.add(move);
   }
   
   
   public void initialize() {
      initializeTop();
      initializeMiddle();
      initializeBottom();
   }     
  
  
   private void initializeTop() {
      for (int i = 1; i < 13; i++)
         board[i - 1] = new Ficha_negra(new Coordenada(i));   
   }     
   
   
   private void initializeMiddle() {
      for (int i = 13; i < 21; i++)
         board[i - 1] = null; 
   }   
  
  
   private void initializeBottom() {
      for (int i = 21; i < 33; i++)
         board[i - 1] = new WhiteChecker(new Coordenada(i));   
   }
    
   
   // For the BoardList class.
   public void setNext(Board next) { 
      this.next = next;   
   }
   
   
   // For the BoardList class.
   public Board getNext() {
      return next;
   }
  
   
   public String toString() {
      String stringBoard[][] = new String[8][8];
      Coordenada temp = null;
      for (int i = 1;i < 33; i++) {
         temp = new Coordenada(i);
         if (getChecker(temp) != null)
            stringBoard[temp.column()][temp.row()] = getChecker(temp).toString();
      }
      String s = " +---+---+---+---+---+---+---+---+\n ";
      for (int y = 0; y < 8; y++) {
         for (int x = 0; x < 8; x++) {
            if (stringBoard[x][y] != null)
               s = s + "| " + stringBoard[x][y] + " ";
            else
               s = s + "|   ";
         }
         s = s + "| (" + ((y*4)+1) + "-" + ((y*4)+4) + ")";
         s = s + "\n +---+---+---+---+---+---+---+---+\n ";       
      }
      return s;
   }
}