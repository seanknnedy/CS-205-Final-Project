import java.lang.*;


public class PlayerComputerTesting {

    /*
    * Did not test Player constructor #2
    * Did not test setPieces due to unclear intentions
    * Did not test setWin due to unclear reasoning (hasWon does the same thing)
    */

    public static void PlayerTesting() {
        //create new board and print
        Board newBoard = new Board();
        Board.printBoard(newBoard);


        // Testing Player Constructor 1 (uses make Pieces)
        Player first = new Player(7, 12, 2, "Red");

        // Testing setBlotHit

        // Testing hasWon

        // Testing allHome

        // Testing isHome

        // Testing makePieces


        // add two pieces to the same spike
        Piece pc = first.playerPieces.get(4);
        newBoard.getBoard().get(4).addToSpike(pc);

        Piece pc2 = first.playerPieces.get(7);
        newBoard.getBoard().get(4).addToSpike(pc2);

        // add one piece to a spike
        Piece blot = first.playerPieces.get(6);
        newBoard.getBoard().get(22).addToSpike(blot);

        // print player board
        System.out.println("\nPlayer Board");
        Board.printBoard(newBoard);

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
        newBoard.getBoard().get(4).removeFromSpike(pc2);
        newBoard.getBoard().get(21).addToSpike(pc2);
        first.determineBlot();
        System.out.println("Spikes with Blots take 2: " + first.blotPieces);

        // print player board again
        System.out.println("Player Board");
        Board.printBoard(newBoard);
    }

    public static void ComputerTesting() {

        /** TESTING COMPUTER **/
        /*

        System.out.println("Testing Computer");
        System.out.println("---------------------------------");
        Board compBoard = new Board();
        Player computer = new Player(1, 6, 2, "Black");

        // testing Double
        ArrayList<Integer> roll1 = new ArrayList<Integer>(2);
        roll1.add(4);
        roll1.add(4);
        computer.playComp(roll1, compBoard.board);

        System.out.println("Computer Board Doubles");
        Board.printBoard(newBoard);
        System.out.println("------------------------------------------------------------------------------------------------------");

        // testing Single
        ArrayList<Integer> roll2 = new ArrayList<Integer>(2);
        roll2.add(2);
        roll2.add(3);
        computer.playComp(roll2, compBoard.board);
        System.out.println("Computer Board Single");
        Board.printBoard(newBoard);
        System.out.println("------------------------------------------------------------------------------------------------------");

        // testing multiple rounds of Computer
        for (int j = 0; j < 6; j++) {
            System.out.println("\n");
            computer.playComp(compBoard.roll(), compBoard.board);
            System.out.println("Computer Board, Round " + j);
            Board.printBoard(newBoard);
            System.out.println("------------------------------------------------------------------------------------------------------");
        }
        */

    }
    public static void main(String [] args) {
        PlayerTesting();
    }
}
