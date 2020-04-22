import java.util.ArrayList;


public class Computer extends Player{


    /**************************************************** Constructor *************************************************/
    public Computer(int b, int t, int y, String tm) {
        // call Player constructor
        super(b, t, y, tm);
    }


    /**************************************************** Methods *****************************************************/
    // Sends compute to playDouble() or playSingle() depending on their roll
    public void playComp(ArrayList<Integer> roll, Board board, Computer computer, Player player) {
        System.out.println(roll.toString());
        ArrayList<Piece> tempPieces = computer.playerPieces;
        System.out.println("Player Pieces: " + computer.playerPieces);
        if(roll.get(0).equals(roll.get(1))) {
            playDouble(roll, board, computer, player, tempPieces);
        } else {
            playSingle(roll, board, computer, player, tempPieces);
        }
    }

    // Handles when the computer rolls the same number on both dice
    public void playDouble(ArrayList<Integer> dieRoll, Board board, Computer computer, Player player, ArrayList<Piece> tempPieces) {
        // declaring everything
        System.out.println("In playDouble with roll of " + dieRoll);

        int rollsAll = dieRoll.get(0) * 4;
        int rollsThree = dieRoll.get(0) * 3;
        int rollsTwo = dieRoll.get(0) * 2;
        int rollsOne = dieRoll.get(0);
        int diceUsed = 0;
        boolean currentPieceUsed = false;

        while(diceUsed != 4) {

            System.out.println("Temp: " + tempPieces.toString() + "\nturns used: " + diceUsed);

            // if there are no more pieces, exit turn
            if(tempPieces.size() == 0) {
                System.out.println("No playable pieces");
                break;
            }

            // get piece to play
            Piece playingPiece = randPiece(tempPieces);
            currentPieceUsed = false;

            // test to see if piece can be moved using all dice
            boolean playableAll = takeTurn(playingPiece, rollsAll, board, computer, player);
            if(playableAll) {
                System.out.println("Moved Piece " + playingPiece + " using 4/4 dice " + rollsAll + " spots.");
                currentPieceUsed = true;
            }

            // test to see if piece can be moved using 3 dice
            else if(diceUsed <= 1) {
                boolean playableThree = takeTurn(playingPiece, rollsThree, board, computer, player);
                if(playableThree) {
                    tempPieces.remove(playingPiece);
                    diceUsed += 3;
                    currentPieceUsed = true;
                    System.out.println("Moved Piece " + playingPiece + " using 3/4 dice " + rollsThree + " spots.");
                }
            }

            // test to see if piece can be moved using 2 dice
            else if(diceUsed <= 2) {
                boolean playableTwo = takeTurn(playingPiece, rollsTwo, board, computer, player);
                if(playableTwo) {
                    tempPieces.remove(playingPiece);
                    diceUsed += 2;
                    currentPieceUsed = true;
                    System.out.println("Moved Piece " + playingPiece + " using 2/4 dice " + rollsTwo + " spots.");
                }
            }

            // test to see if piece can be moved using 1 die
            else if(diceUsed <= 3) {
                boolean playableOne = takeTurn(playingPiece, rollsOne, board, computer, player);
                if(playableOne) {
                    tempPieces.remove(playingPiece);
                    diceUsed++;
                    currentPieceUsed = true;
                    System.out.println("Moved Piece " + playingPiece + " using 1/4 dice " + rollsOne + " spots.");
                }
            }

            // if the piece made it this far, it cannot be used)
            if (!currentPieceUsed) {
                tempPieces.remove(playingPiece);
                System.out.println("Piece " + playingPiece + " is unusable");
            }
        }
    }

    // Handles when the computer rolls different numbers on the dice
    public void playSingle(ArrayList<Integer> s, Board b, Computer computer, Player player, ArrayList<Piece> tempPieces) {

        System.out.println("In playSingle with roll of " + s);
        //ArrayList<Piece> tempPieces = playerPieces;
        int diceUsed = 0;
        int bothDie = s.get(0) + s.get(1);
        int firstDie = s.get(0);
        int secondDie = s.get(1);
        boolean currentPieceUsed = false;

        while(diceUsed != 2) {

            // if there are no more pieces, exit turn
            if(tempPieces.size() == 0) {
                System.out.println("No playable pieces");
                break;
            }

            // get piece to play
            Piece playingPiece = randPiece(tempPieces);
            currentPieceUsed = false;

            System.out.println("Temp: " + tempPieces.toString() + "\nturns used: " + diceUsed);
            System.out.println("Attempting to move: " + playingPiece);

            // test to see if one piece can be moved using both dice
            boolean playableT = takeTurn(playingPiece, bothDie, b, computer, player);
            if(playableT) {
                System.out.println("Moved Piece " + playingPiece + " " + bothDie + " spots using both die." );
                //diceUsed = 2;
                //currentPieceUsed = true;
                break;
            }

            // test to see if the piece can be moved using first die
            if(diceUsed <= 1) {
                boolean playable0 = takeTurn(playingPiece, firstDie, b, computer, player);
                if(playable0) {
                    tempPieces.remove(playingPiece);
                    diceUsed++;
                    currentPieceUsed = true;
                    System.out.println("Moved Piece " + playingPiece + " " +  firstDie + " spots using the first die.");
                }
            }

            // test to see if the piece can be moved using second die
            if(diceUsed <= 1) {
                boolean playable1 = takeTurn(playingPiece, s.get(1), b, computer, player);
                if(playable1) {
                    tempPieces.remove(playingPiece);
                    diceUsed++;
                    currentPieceUsed = true;
                    System.out.println("Moved Piece " + playingPiece + " " + secondDie +" spots using the second die.");
                }
            }

            // if the piece made it this far, it cannot be used)
            if (!currentPieceUsed) {
                tempPieces.remove(playingPiece);
                System.out.println("Piece " + playingPiece + " is unusable");
            }
        }
    }

    // Choose a random piece
    public Piece randPiece(ArrayList<Piece> c) {
        return c.get(rand.nextInt(c.size()-1));
    }

    // Determines if the piece can take a turn, returns true if move is valid and places piece, returns false otherwise
    public boolean takeTurn(Piece p, int roll, Board b, Computer computer, Player player) {
        return super.makeMove(b, computer, player, p, roll);

    }
}
