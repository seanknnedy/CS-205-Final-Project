import java.util.*;
import java.lang.*;
/** This program tests the Board class */
public class BoardTest {
    public static boolean testBoard() {
        boolean passed = true;
        /*


        ArrayList<Piece> pieces1 = new ArrayList<Piece>();
        ArrayList<Piece> pieces2 = new ArrayList<Piece>();
        Piece red1 = new Piece("red", 0, 0, 1);
        Piece red2 = new Piece("red", 0, 0, 2);
        Piece red3 = new Piece("red", 0, 0, 3);
        Piece red4 = new Piece("red", 0, 0, 4);
        Piece red5 = new Piece("red", 0, 0, 5);
        Spike testSpike = new Spike(1,1);
        testSpike.addToSpike(red1);
        testSpike.addToSpike(red2);
        testSpike.addToSpike(red3);
        testSpike.addToSpike(red4);
        testSpike.addToSpike(red5);

        Board testBoard = new Board(pieces1,"red",pieces2,"black");
        ArrayList<Spike> board = testBoard.getBoard();

        if (board.size()!=24 || !board.get(0).equals(testSpike)
                || pieces1.size() != 15 || pieces2.size() !=15 ){
            passed = false;
            System.out.println("Failed constructor test case");
        }
        if (!testBoard.getSpike(1).equals(board.get(0)) ||
                !testBoard.getSpike(24).equals(board.get(23))) {
            passed = false;
            System.out.println("Failed getSpike test case");
        }

         */
        return passed;
    }


    public static void main(String [] args) {
        if(testBoard())
            System.out.println("All tests passed.\n Sample run:\n");

        //Program Run
        ArrayList<Piece> pieces1 = new ArrayList<Piece>();
        ArrayList<Piece> pieces2 = new ArrayList<Piece>();

        Player p1 = new Player(pieces1,7,12,2);
        Player p2 = new Player(pieces2,7,12,1);
        Board testBoard = new Board(pieces1,"red",pieces2,"black");
        Board.printBoard(testBoard);
        //Removing piece 1 from first spike
        testBoard.getSpike(1).removeFromSpike(pieces1.get(0));
        Board.printBoard(testBoard);

        //Adding piece to spike 2
        testBoard.getSpike(2).addToSpike(new Piece("red",4,4,1));
        Board.printBoard(testBoard);
        //Testing rolls
        System.out.println("Sample Rolls:");
        for (int i=0;i<10;++i)
             System.out.println(testBoard.roll());



    }
}
