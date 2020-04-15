import java.lang.*;
import java.util.ArrayList;


public class PlayerComputerTesting {

    public static void testingGame() {
        // testing Player/Computer constructor
        Player playerRED = new Player(7, 12, 1, "RED");
        Player playerBLK = new Player(7, 12, 2, "BLK");

        // create new board and print
        Board newBoard = new Board(playerRED.playerPieces, playerRED.getTeam(), playerBLK.playerPieces, playerBLK.getTeam());
        Board.printBoard(newBoard);

        // make player move
        System.out.println("SAMPLE PLAYER MOVE");
        playerRED.makeMove(newBoard, playerRED, playerBLK,1, 1);
        Board.printBoard(newBoard);

        // test blot (tests setBlotHit)
        System.out.println("HITTING A BLOT");
        playerBLK.makeMove(newBoard, playerBLK, playerRED, 9, 6);
        Board.printBoard(newBoard);

        // test to see if can play something other than a blot
        System.out.println("NOT PLAYING BLOT");
        playerRED.makeMove(newBoard, playerRED, playerBLK, 8, 1);
        Board.printBoard(newBoard);

        // test to see if can play a blot but with an invalid move
        System.out.println("PLAY BLOT WITH INVALID MOVE");
        playerRED.makeMove(newBoard, playerRED, playerBLK, 1, 8);
        Board.printBoard(newBoard);

        // test to see if can play a blot
        System.out.println("PLAY BLOT");
        playerRED.makeMove(newBoard, playerRED, playerBLK, 1, 5);
        Board.printBoard(newBoard);

        // test to see if can add to a spike with 5 pieces already on it
        System.out.println("INVALID MOVE - MAX 5");
        playerBLK.makeMove(newBoard, playerBLK, playerRED, 8, 2);
        Board.printBoard(newBoard);

        // testing bearing off logic pt 1
        System.out.println("TESTING BEARING OFF LOGIC - NOT ALL PIECES HOME YET");
        playerRED.makeMove(newBoard, playerRED, playerBLK,15, 6);
        Board.printBoard(newBoard);

        // testing isHome
        System.out.println("TESTING IS HOME");
        System.out.println("Is Piece RED11 HOME? " + playerRED.isHome(playerRED.playerPieces.get(10)));
        System.out.println("Is Piece RED4 HOME? " + playerRED.isHome(playerRED.playerPieces.get(3)));

        // testing allHome
        System.out.println("Are all of Player's pieces home? " + playerRED.allHome());
        playerRED.makeMove(newBoard, playerRED, playerBLK,1, 18);
        playerRED.makeMove(newBoard, playerRED, playerBLK,2, 22);
        playerRED.makeMove(newBoard, playerRED, playerBLK,3, 11);
        playerRED.makeMove(newBoard, playerRED, playerBLK,4, 11);
        playerRED.makeMove(newBoard, playerRED, playerBLK,5, 11);
        playerRED.makeMove(newBoard, playerRED, playerBLK,6, 10);
        playerRED.makeMove(newBoard, playerRED, playerBLK,7, 10);
        playerRED.makeMove(newBoard, playerRED, playerBLK,8, 5);
        playerRED.makeMove(newBoard, playerRED, playerBLK,9, 5);
        playerRED.makeMove(newBoard, playerRED, playerBLK,10, 5);
        Board.printBoard(newBoard);
        System.out.println("Are all of Player's pieces home now? " + playerRED.allHome() + "\n\n");

        // testing bearing off logic pt 2
        System.out.println("TESTING BEARING OFF LOGIC - PIECE THAT'S FURTHER MUST BE CHOSEN");
        playerRED.makeMove(newBoard, playerRED, playerBLK,5, 6);
        Board.printBoard(newBoard);

        // testing bearing off and hasWon
        System.out.println("TESTING BEARING OFF PIECES");
        System.out.println("Has the player won? " + playerRED.hasWon());
        playerRED.makeMove(newBoard, playerRED, playerBLK,1, 2);
        playerRED.makeMove(newBoard, playerRED, playerBLK,2, 2);
        playerRED.makeMove(newBoard, playerRED, playerBLK,3, 2);
        playerRED.makeMove(newBoard, playerRED, playerBLK,4, 2);
        playerRED.makeMove(newBoard, playerRED, playerBLK,5, 2);
        playerRED.makeMove(newBoard, playerRED, playerBLK,6, 3);
        playerRED.makeMove(newBoard, playerRED, playerBLK,7, 3);
        playerRED.makeMove(newBoard, playerRED, playerBLK,8, 3);
        playerRED.makeMove(newBoard, playerRED, playerBLK,9, 3);
        playerRED.makeMove(newBoard, playerRED, playerBLK,10, 3);
        playerRED.makeMove(newBoard, playerRED, playerBLK,11, 6);
        playerRED.makeMove(newBoard, playerRED, playerBLK,12, 6);
        playerRED.makeMove(newBoard, playerRED, playerBLK,13, 6);
        playerRED.makeMove(newBoard, playerRED, playerBLK,14, 6);
        playerRED.makeMove(newBoard, playerRED, playerBLK,15, 6);
        Board.printBoard(newBoard);

        System.out.println("Has the player won now? " + playerRED.hasWon());
    }


    public static void testingComputer() {

        Computer computer1 = new Computer(7, 12, 2, "RED");
        Computer computer2 = new Computer(7, 12, 1, "BLK");

        Board board = new Board(computer1.playerPieces, computer1.getTeam(), computer2.playerPieces, computer2.getTeam());
        Board.printBoard(board);

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
        Board.printBoard(board);



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
