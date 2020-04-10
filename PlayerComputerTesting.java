import java.lang.*;


public class PlayerComputerTesting {

    public static void makeMove(Board b, Player p, Player opponent, int pieceID, int fromSpike, int toSpike) {
        boolean valid = validMove(b, p, opponent, pieceID, toSpike);
        if(valid) {
            b.getSpike(fromSpike).removeFromSpike(p.getPiece(pieceID));
            b.getSpike(toSpike).addToSpike(p.getPiece(pieceID));
            System.out.println("Move complete\n");
        } else {
            System.out.println("Move Incomplete... Try Again!\n");
        }
    }

    // Implement all the game rules
    public static boolean validMove(Board b, Player p, Player opponent, int pieceID, int toSpike) {
        System.out.println("\nValidating Move...");
        Spike current = b.getSpike(toSpike);

        // TODO: Case 1: check if a blot is hit! (implement when we do GUI)

        // Case 2: other team is on spike with more than one piece
        if (!current.getCurrentTeam().equals(p.getPiece(pieceID).getColor()) & current.getPiecesOnSpike().size() > 1) {
            System.out.println("Invalid Move - other team is occupying spike already");
            return false;
        }

        // Case 3: other team is on spike with one piece, move is valid, blot occurs
        else if (!current.getCurrentTeam().equals(p.getPiece(pieceID).getColor()) & current.getPiecesOnSpike().size() == 1) {
            System.out.println("Valid Move - blot!");
            // set as a blotPieces
            b.getSpike(toSpike).getPiecesOnSpike().get(0).switchBlot();
            // add to blotHit to player
            opponent.setBlotHit();
            // add to blotPieces
            opponent.addBlot(b.getSpike(toSpike).getPiecesOnSpike().get(0).getID());
            // remove old piece
            b.getSpike(toSpike).removeFromSpike(b.getSpike(toSpike).getPiecesOnSpike().get(0));
            // return to complete move
            return true;
        }

        // Case 4: you already have 5 pieces on the spike
        else if (current.getCurrentTeam().equals(p.getPiece(pieceID).getColor()) & current.getPiecesOnSpike().size() == 5) {
            System.out.println("Invalid Move - maximum pieces on spike");
            return false;
        }

        // Case 5: assume valid move (Add when moving a blot onto board?)
        else {
            System.out.println("Valid Move - Not in any cases");
            return true;
        }
    }


    /*
    * Did not test Player constructor #2
    * Did not test setPieces due to unclear intentions
    * Did not test setWin due to unclear reasoning (hasWon does the same thing)
    */

    public static void main(String [] args)  {

        // testing Player constructor 1
        Player player = new Player(7, 12, 2, "RED");
        Computer computer = new Computer(7, 12, 1, "BLK");

        // create new board and print
        Board newBoard = new Board(player.playerPieces, player.getTeam(), computer.playerPieces, computer.getTeam());

        // make player move
        makeMove(newBoard, player, computer,1, 1, 2);
        Board.printBoard(newBoard, player, computer);

        // test blot (tests setBlotHit)
        makeMove(newBoard, computer, player, 9, 13, 2);
        Board.printBoard(newBoard, player, computer);

        // test to see if can add to a spike with 5 pieces already on it
        makeMove(newBoard, player, computer, 8, 17, 19);
        Board.printBoard(newBoard, player, computer);

        // testing isHome
        System.out.println("Is Piece RED11 HOME? " + player.isHome(player.playerPieces.get(10)));
        System.out.println("Is Piece RED4 HOME? " + player.isHome(player.playerPieces.get(3)));


        // testing allHome
        System.out.println("Are all of Player's pieces home? " + player.allHome());
        makeMove(newBoard, player, computer,1, 1, 20);
        makeMove(newBoard, player, computer,2, 1, 20);
        makeMove(newBoard, player, computer,3, 1, 20);
        makeMove(newBoard, player, computer,4, 1, 20);
        makeMove(newBoard, player, computer,5, 1, 20);
        makeMove(newBoard, player, computer,6, 12, 21);
        makeMove(newBoard, player, computer,7, 12, 21);
        makeMove(newBoard, player, computer,8, 17, 21);
        makeMove(newBoard, player, computer,9, 17, 22);
        makeMove(newBoard, player, computer,10, 17, 22);
        Board.printBoard(newBoard, player, computer);
        System.out.println("Are all of Player's pieces home now? " + player.allHome());


        // testing hasWon
        // TODO: How do we handle bearing off?




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
}
