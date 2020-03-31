import java.io.*;
import java.lang.*;
import java.util.*;

public class Board {

   //initializing board object and piece arrays for each player (not sure if latter part is needed yet)
   private static ArrayList<Spike> board; // = new Piece[24][5];
   private ArrayList<Piece> player1;
   private ArrayList<Piece> player2;

   //board constructor
   public Board() {

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
      Piece red1 = new Piece("red", 0, 0);
      Piece red2 = new Piece("red", 0, 0);
      Piece red3 = new Piece("red", 0, 0);
      Piece red4 = new Piece("red", 0, 0);
      Piece red5 = new Piece("red", 0, 0);
      Piece red6 = new Piece("red", 0, 0);
      Piece red7 = new Piece("red", 0, 0);
      Piece red8 = new Piece("red", 0, 0);
      Piece red9 = new Piece("red", 0, 0);
      Piece red10 = new Piece("red", 0, 0);
      Piece red11 = new Piece("red", 0, 0);
      Piece red12 = new Piece("red", 0, 0);
      Piece red13 = new Piece("red", 0, 0);
      Piece red14 = new Piece("red", 0, 0);
      Piece red15 = new Piece("red", 0, 0);

      //initializing player1 list of pieces
      player1 = new ArrayList<Piece>();

      //adding pieces to player 1 arraylist
      player1.add(red1);
      player1.add(red2);
      player1.add(red3);
      player1.add(red4);
      player1.add(red5);
      player1.add(red6);
      player1.add(red7);
      player1.add(red8);
      player1.add(red9);
      player1.add(red10);
      player1.add(red11);
      player1.add(red12);
      player1.add(red13);
      player1.add(red14);
      player1.add(red15);

      //creating black pieces
      Piece black1 = new Piece("black", 0, 0);
      Piece black2 = new Piece("black", 0, 0);
      Piece black3 = new Piece("black", 0, 0);
      Piece black4 = new Piece("black", 0, 0);
      Piece black5 = new Piece("black", 0, 0);
      Piece black6 = new Piece("black", 0, 0);
      Piece black7 = new Piece("black", 0, 0);
      Piece black8 = new Piece("black", 0, 0);
      Piece black9 = new Piece("black", 0, 0);
      Piece black10 = new Piece("black", 0, 0);
      Piece black11 = new Piece("black", 0, 0);
      Piece black12 = new Piece("black", 0, 0);
      Piece black13 = new Piece("black", 0, 0);
      Piece black14 = new Piece("black", 0, 0);
      Piece black15 = new Piece("black", 0, 0);

      //initializing list of pieces for player 2
      player2 = new ArrayList<Piece>();

      //adding pieces to player 2 list
      player2.add(black1);
      player2.add(black2);
      player2.add(black3);
      player2.add(black4);
      player2.add(black5);
      player2.add(black6);
      player2.add(black7);
      player2.add(black8);
      player2.add(black9);
      player2.add(black10);
      player2.add(black11);
      player2.add(black12);
      player2.add(black13);
      player2.add(black14);
      player2.add(black15);
   
   }
   
   //roll die: returns an array of two integers between 1 and 6 (inclusive)
   public int[] roll() {
      int[] roll = new int[2];
      Random rand = new Random();
      roll[0] = rand.nextInt(6) + 1;
      roll[1] = rand.nextInt(6) + 1;
      return roll;
   }

   //getBoard
   public ArrayList<Spike> getBoard() {
      return board;
   }

   //print board
   public static void printBoard(ArrayList<Spike> b) {
      //for each spike
      for (int i = 0; i < b.size(); i++) {
         //print spike
         System.out.print(b.get(i).toString());
         System.out.print("   ");
         //this is just for visualization, splits spikes into two layers in I/O
         if (i == 11) {
            System.out.println();
         }
      }
      System.out.println();
   }

   //simple testing
   public static void main(String [] args) {
      //create new board and print
      Board newBoard = new Board();
      printBoard(newBoard.board);

      //create new piece, print x and y coords
      Piece p = new Piece("red", 0, 0);
      System.out.println(p.getX());
      System.out.println(p.getY());

      //add to spike 5, print x and y coords
      board.get(5).addToSpike(p);
      System.out.println(p.getX());
      System.out.println(p.getY());

      //print board to show piece is on spike (havent implemented toString for Piece, but this shows that it's on the spike)
      printBoard(board);

      //remove piece from spike 5, print x and y coords
      board.get(5).removeFromSpike(p);
      System.out.println(p.getX());
      System.out.println(p.getY());

      //print board again to show piece is removed
      printBoard(board);

      /** TESTING PLAYER **/
      Player first = new Player(7, 2);

      // add two pieces to the same spike
      Piece pc = first.playerPieces.get(4);
      board.get(4).addToSpike(pc);

      Piece pc2 = first.playerPieces.get(7);
      board.get(4).addToSpike(pc2);

      // add one piece to a spike
      Piece blot = first.playerPieces.get(6);
      board.get(22).addToSpike(blot);

      // print player board
      System.out.println("\nPlayer Board");
      printBoard(board);

      // test if a piece isHome()
      System.out.println("Testing isHome()");
      System.out.println("Piece on Spike 22: " + first.isHome(blot));
      System.out.println("Piece on Spike 4: " + first.isHome(pc));

      // test if pieces are allHome()
      System.out.println("Testing allHome() --> " + first.allHome());

      // test to determineBlot() on board --> prints Spike's array number
      first.determineBlot();
      System.out.println("Spikes with Blots: " + first.blotPieces);

      // add piece to blot, determineBlot() again
      board.get(4).removeFromSpike(pc2);
      board.get(21).addToSpike(pc2);
      first.determineBlot();
      System.out.println("Spikes with Blots take 2: " + first.blotPieces);

      // print player board again
      System.out.println("Player Board");
      printBoard(board);
   }
}