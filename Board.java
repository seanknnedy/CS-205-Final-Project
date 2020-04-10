import java.lang.*;
import java.util.*;


public class Board {

   //initializing board object and piece arrays for each player (not sure if latter part is needed yet)
   private static ArrayList<Spike> board; // = new Piece[24][5];


   /**************************************************** Constructor **************************************************/
   public Board(ArrayList<Piece> p1, String team1, ArrayList<Piece> p2, String team2) {

      //creating each spike on the board (24 in all)
      //first coord represents x: numbered 1 through 12 (12 spikes on each side of board)
      //second coord represents y: numbered 1 and 2 (each side of board)
      Spike spike1 = new Spike(1, 1);
      Spike spike2 = new Spike(2, 1);
      Spike spike3 = new Spike(3, 1);
      Spike spike4 = new Spike(4, 1);
      Spike spike5 = new Spike(5, 1);
      Spike spike6 = new Spike(6, 1);
      Spike spike7 = new Spike(7, 1);
      Spike spike8 = new Spike(8, 1);
      Spike spike9 = new Spike(9, 1);
      Spike spike10 = new Spike(10, 1);
      Spike spike11 = new Spike(11, 1);
      Spike spike12 = new Spike(12, 1);
      Spike spike13 = new Spike(1, 2);
      Spike spike14 = new Spike(2, 2);
      Spike spike15 = new Spike(3, 2);
      Spike spike16 = new Spike(4, 2);
      Spike spike17 = new Spike(5, 2);
      Spike spike18 = new Spike(6, 2);
      Spike spike19 = new Spike(7, 2);
      Spike spike20 = new Spike(8, 2);
      Spike spike21 = new Spike(9, 2);
      Spike spike22 = new Spike(10, 2);
      Spike spike23 = new Spike(11, 2);
      Spike spike24 = new Spike(12, 2);

      //initializing board: array of spikes
      board = new ArrayList<Spike>();

      //adding spikes to board
      board.add(spike1);
      board.add(spike2);
      board.add(spike3);
      board.add(spike4);
      board.add(spike5);
      board.add(spike6);
      board.add(spike7);
      board.add(spike8);
      board.add(spike9);
      board.add(spike10);
      board.add(spike11);
      board.add(spike12);
      board.add(spike13);
      board.add(spike14);
      board.add(spike15);
      board.add(spike16);
      board.add(spike17);
      board.add(spike18);
      board.add(spike19);
      board.add(spike20);
      board.add(spike21);
      board.add(spike22);
      board.add(spike23);
      board.add(spike24);

      //creating red pieces
      Piece red1 = new Piece(team1, 0, 0, 1);
      Piece red2 = new Piece(team1, 0, 0, 2);
      Piece red3 = new Piece(team1, 0, 0, 3);
      Piece red4 = new Piece(team1, 0, 0, 4);
      Piece red5 = new Piece(team1, 0, 0, 5);
      Piece red6 = new Piece(team1, 0, 0, 6);
      Piece red7 = new Piece(team1, 0, 0, 7);
      Piece red8 = new Piece(team1, 0, 0, 8);
      Piece red9 = new Piece(team1, 0, 0, 9);
      Piece red10 = new Piece(team1, 0, 0, 10);
      Piece red11 = new Piece(team1, 0, 0, 11);
      Piece red12 = new Piece(team1, 0, 0, 12);
      Piece red13 = new Piece(team1, 0, 0, 13);
      Piece red14 = new Piece(team1, 0, 0, 14);
      Piece red15 = new Piece(team1, 0, 0, 15);

      //adding red pieces to board
      getSpike(1).addToSpike(red1);
      getSpike(1).addToSpike(red2);
      getSpike(1).addToSpike(red3);
      getSpike(1).addToSpike(red4);
      getSpike(1).addToSpike(red5);
      getSpike(12).addToSpike(red6);
      getSpike(12).addToSpike(red7);
      getSpike(17).addToSpike(red8);
      getSpike(17).addToSpike(red9);
      getSpike(17).addToSpike(red10);
      getSpike(19).addToSpike(red11);
      getSpike(19).addToSpike(red12);
      getSpike(19).addToSpike(red13);
      getSpike(19).addToSpike(red14);
      getSpike(19).addToSpike(red15);

      //adding pieces to player 1 arraylist
      p1.add(red1);
      p1.add(red2);
      p1.add(red3);
      p1.add(red4);
      p1.add(red5);
      p1.add(red6);
      p1.add(red7);
      p1.add(red8);
      p1.add(red9);
      p1.add(red10);
      p1.add(red11);
      p1.add(red12);
      p1.add(red13);
      p1.add(red14);
      p1.add(red15);

      //creating black pieces
      Piece black1 = new Piece(team2, 0, 0, 1);
      Piece black2 = new Piece(team2, 0, 0, 2);
      Piece black3 = new Piece(team2, 0, 0, 3);
      Piece black4 = new Piece(team2, 0, 0, 4);
      Piece black5 = new Piece(team2, 0, 0, 5);
      Piece black6 = new Piece(team2, 0, 0, 6);
      Piece black7 = new Piece(team2, 0, 0, 7);
      Piece black8 = new Piece(team2, 0, 0, 8);
      Piece black9 = new Piece(team2, 0, 0, 9);
      Piece black10 = new Piece(team2, 0, 0, 10);
      Piece black11 = new Piece(team2, 0, 0, 11);
      Piece black12 = new Piece(team2, 0, 0, 12);
      Piece black13 = new Piece(team2, 0, 0, 13);
      Piece black14 = new Piece(team2, 0, 0, 14);
      Piece black15 = new Piece(team2, 0, 0, 15);

      //adding black pieces to board
      getSpike(5).addToSpike(black1);
      getSpike(5).addToSpike(black2);
      getSpike(5).addToSpike(black3);
      getSpike(7).addToSpike(black4);
      getSpike(7).addToSpike(black5);
      getSpike(7).addToSpike(black6);
      getSpike(7).addToSpike(black7);
      getSpike(7).addToSpike(black8);
      getSpike(13).addToSpike(black9);
      getSpike(13).addToSpike(black10);
      getSpike(13).addToSpike(black11);
      getSpike(13).addToSpike(black12);
      getSpike(13).addToSpike(black13);
      getSpike(24).addToSpike(black14);
      getSpike(24).addToSpike(black15);

      //adding pieces to player 2 list
      p2.add(black1);
      p2.add(black2);
      p2.add(black3);
      p2.add(black4);
      p2.add(black5);
      p2.add(black6);
      p2.add(black7);
      p2.add(black8);
      p2.add(black9);
      p2.add(black10);
      p2.add(black11);
      p2.add(black12);
      p2.add(black13);
      p2.add(black14);
      p2.add(black15);

   }


   /**************************************************** Getters/Setters **********************************************/
   // Returns the Board
   public ArrayList<Spike> getBoard() {
      return board;
   }

   // Returns the Spike - use this when adding/deleting pieces to spikes
   public Spike getSpike(int numSpike) {
      return board.get(numSpike - 1);
   }


   /**************************************************** Methods ******************************************************/
   // Returns the die roll in an array of two integers between 1 and 6 (inclusive)
      public ArrayList<Integer> roll() {
         ArrayList<Integer> roll = new ArrayList<Integer>(2);
         Random rand = new Random();
         roll.add((rand.nextInt(6)+1));
         roll.add((rand.nextInt(6)+1));
         return roll;
      }


   // Prints the board
   public static void printBoard(Board board, Player p1, Player p2) {
      System.out.println("Player 2's Pieces Bear Off List: ");

      //for each spike
      ArrayList<Spike> b = board.getBoard();
      for (int i = 0; i < b.size(); i++) {
         //print spike
         System.out.print(b.get(i).toString());
         System.out.print("   ");
         //this is just for visualization, splits spikes into two layers in I/O
         if (i == 11) {
            System.out.println();
         }
      }

      System.out.println("\nPlayer 1's Pieces Bear Off List: ");
      p2.printBearOffs();

      System.out.print("BLOTS: ");
      p1.printBlots();
      p2.printBlots();

      System.out.println("\n");
   }

   public static void main(String [] args) {
      /*
      //create new board and print
      Board newBoard = new Board();
      Board.printBoard(newBoard);

      //create new piece, print x and y coords
      Piece p = new Piece("red", 0, 0);
      System.out.println("X: " + p.getX());
      System.out.println("Y: " + p.getY());

      //add to spike 5, print x and y coords
      newBoard.getBoard().get(5).addToSpike(p);
      System.out.println("X: " + p.getX());
      System.out.println("Y: " + p.getY());

      //print board to show piece is on spike (havent implemented toString for Piece, but this shows that it's on the spike)
      Board.printBoard(newBoard);

      //remove piece from spike 5, print x and y coords
      newBoard.getBoard().get(5).removeFromSpike(p);
      System.out.println("X: " + p.getX());
      System.out.println("Y: " + p.getY());

      //print board again to show piece is removed
      Board.printBoard(newBoard);

       */
   }
}