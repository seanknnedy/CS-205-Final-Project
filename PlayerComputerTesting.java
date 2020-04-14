import java.lang.*;
import java.util.ArrayList;


public class PlayerComputerTesting {

    // Handle when all pieces are home and player is bearing off
    public static void bearingOffMove(Board b, Player p, int pieceID, int fromSpike) {
        p.bearOffAPiece(p.getPiece(pieceID));
        b.getSpike(fromSpike).removeFromSpike(p.getPiece(pieceID));
    }

    public static void testingGame() {
        // testing Player/Computer constructor
        Player player1 = new Player(7, 12, 2, "RED");
        Player player2 = new Player(7, 12, 1, "BLK");

        // create new board and print
        Board newBoard = new Board(player1.playerPieces, player1.getTeam(), player2.playerPieces, player2.getTeam());

        // make player move
        player1.makeMove(newBoard, player1, player2,1, 1, 2);
        Board.printBoard(newBoard, player1, player2);

        // test blot (tests setBlotHit)
        player2.makeMove(newBoard, player2, player1, 9, 13, 2);
        Board.printBoard(newBoard, player1, player2);

        // test to see if can play something other than a blot
        player1.makeMove(newBoard, player1, player2, 8, 17, 19);
        Board.printBoard(newBoard, player1, player2);

        // test to see if can play a blot but with an invalid move
        player1.makeMove(newBoard, player1, player2, 1, 24, 7);
        Board.printBoard(newBoard, player1, player2);

        // test to see if can play a blot
        player1.makeMove(newBoard, player1, player2, 1, 24, 11);
        Board.printBoard(newBoard, player1, player2);

        // test to see if can add to a spike with 5 pieces already on it
        player2.makeMove(newBoard, player2, player1, 1, 5, 7);
        Board.printBoard(newBoard, player1, player2);

        // TEST
        //player2.makeMove(newBoard, player2, player1, 9, 2, 24);
        //Board.printBoard(newBoard, player1, player2);

        // testing isHome
        System.out.println("Is Piece RED11 HOME? " + player1.isHome(player1.playerPieces.get(10)));
        System.out.println("Is Piece RED4 HOME? " + player1.isHome(player1.playerPieces.get(3)));

        // testing allHome
        System.out.println("Are all of Player's pieces home? " + player1.allHome());
        player1.makeMove(newBoard, player1, player2,1, 1, 20);
        player1.makeMove(newBoard, player1, player2,2, 1, 20);
        player1.makeMove(newBoard, player1, player2,3, 1, 20);
        player1.makeMove(newBoard, player1, player2,4, 1, 20);
        player1.makeMove(newBoard, player1, player2,5, 1, 20);
        player1.makeMove(newBoard, player1, player2,6, 12, 21);
        player1.makeMove(newBoard, player1, player2,7, 12, 21);
        player1.makeMove(newBoard, player1, player2,8, 17, 21);
        player1.makeMove(newBoard, player1, player2,9, 17, 22);
        player1.makeMove(newBoard, player1, player2,10, 17, 22);
        Board.printBoard(newBoard, player1, player2);
        System.out.println("Are all of Player's pieces home now? " + player1.allHome());

        // testing hasWon
        // TODO: How do we handle bearing off?
    }


    public static void testingComputer() {

        Computer computer1 = new Computer(7, 12, 2, "RED");
        Computer computer2 = new Computer(7, 12, 1, "BLK");

        Board board = new Board(computer1.playerPieces, computer1.getTeam(), computer2.playerPieces, computer2.getTeam());
        Board.printBoard(board, computer1, computer2);

        /*
        // testing Double
        ArrayList<Integer> rollDoubles = new ArrayList<Integer>(2);
        rollDoubles.add(2);
        rollDoubles.add(2);

        computer1.playComp(rollDoubles, board, computer1, computer1);
        Board.printBoard(board, computer1, computer2);

         */

        // testing Single
        ArrayList<Integer> rollSingles = new ArrayList<Integer>(2);
        rollSingles.add(2);
        rollSingles.add(3);
        computer1.playComp(rollSingles, board, computer1, computer2);
        System.out.println("Computer Board Single");
        Board.printBoard(board, computer1, computer2);



        /*
        // testing multiple rounds of Computer
        for (int j = 0; j < 6; j++) {
            System.out.println("\n");
            computer.playComp(board.roll(), board.board);
            System.out.println("Computer Board, Round " + j);
            Board.printBoard();
        }

        ArrayList<Integer> roll = board.roll();

        System.out.println(roll.toString());


         */


    }

    public static void main(String [] args) {
        System.out.println("-------------------------------------- Testing Game -------------------------------------");
        testingGame();
        System.out.println("------------------------------------ Testing Computer -----------------------------------");
        //testingComputer();
    }
}
