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
        if(roll.get(0).equals(roll.get(1))) {
            playDouble(roll, board, computer, player);
        } else {
            playSingle(roll, board, computer, player);
        }
    }

    // Handles when the computer rolls the same number on both dice
    public void playDouble(ArrayList<Integer> dieRoll, Board board, Computer computer, Player player) {
        // declaring everything
        System.out.println("In playDouble with roll of " + dieRoll);
        ArrayList<Piece> tempPieces = playerPieces;
        int rollsAll = dieRoll.get(0) * 4;
        int rollsThree = dieRoll.get(0) * 3;
        int rollsTwo = dieRoll.get(0) * 2;
        int rollsOne = dieRoll.get(0);
        int diceUsed = 0;

        while(diceUsed != 4) {

            // if there are no more pieces, exit turn
            if(tempPieces.size() == 0) {
                System.out.println("No playable pieces");
                break;
            }

            // get piece to play
            Piece playingPiece = randPiece(tempPieces);

            // test to see if piece can be moved using all dice
            boolean playableAll = takeTurn(playingPiece, rollsAll, board, computer, player);
            if(playableAll) {
                System.out.println("Moved Piece " + playingPiece + " using 4/4 dice " + rollsAll + " spots.");
            }

            // test to see if piece can be moved using 3 dice
            else if(diceUsed <= 1) {
                boolean playableThree = takeTurn(playingPiece, rollsThree, board, computer, player);
                if(playableThree) {
                    tempPieces.remove(playingPiece);
                    diceUsed += 3;
                    System.out.println("Moved Piece " + playingPiece + " using 3/4 dice " + rollsThree + " spots.");
                }
            }

            // test to see if piece can be moved using 2 dice
            else if(diceUsed <= 2) {
                boolean playableTwo = takeTurn(playingPiece, rollsTwo, board, computer, player);
                if(playableTwo) {
                    tempPieces.remove(playingPiece);
                    diceUsed += 2;
                    System.out.println("Moved Piece " + playingPiece + " using 2/4 dice " + rollsTwo + " spots.");
                }
            }

            // test to see if piece can be moved using 1 die
            else if(diceUsed <= 3) {
                boolean playableOne = takeTurn(playingPiece, rollsOne, board, computer, player);
                if(playableOne) {
                    tempPieces.remove(playingPiece);
                    diceUsed++;
                    System.out.println("Moved Piece " + playingPiece + " using 1/4 dice " + rollsOne + " spots.");
                }
            }

            // if the piece made it this far, it cannot be used)
            else {
                tempPieces.remove(playingPiece);
                System.out.println("Piece " + playingPiece + " is unusable");
            }
        }
    }

    // Handles when the computer rolls different numbers on the dice
    public void playSingle(ArrayList<Integer> s, Board b, Computer computer, Player player) {

        System.out.println("In playSingle with roll of " + s);
        ArrayList<Piece> tempPieces = playerPieces;
        int rollUsed = 3;
        int diceUsed = 0;
        int bothDie = s.get(0) + s.get(1);
        int firstDie = s.get(0);
        int secondDie = s.get(1);

        while(diceUsed != 2) {

            // if there are no more pieces, exit turn
            if(tempPieces.size() == 0) {
                System.out.println("No playable pieces");
                break;
            }

            // get piece to play
            Piece playingPiece = randPiece(tempPieces);

            // test to see if one piece can be moved using both dice
            boolean playableT = takeTurn(playingPiece, bothDie, b, computer, player);
            if(playableT) {
                System.out.println("Moved Piece " + playingPiece + " " + bothDie + " spots using both die." );
                break;
            }

            // test to see if the piece can be moved using first die
            boolean playable0 = takeTurn(playingPiece, firstDie, b, computer, player);
            if(rollUsed == 3 || rollUsed != 0) {
                if(playable0) {
                    tempPieces.remove(playingPiece);
                    rollUsed = 0;
                    diceUsed++;
                    System.out.println("Moved Piece " + playingPiece + " " +  firstDie + " spots using the first die.");
                }
            }

            // test to see if the piece can be moved using second die
            boolean playable1 = takeTurn(playingPiece,s.get(1),b, computer, player);
            if(!playable0 && (rollUsed == 3 || rollUsed != 1)) {
                if(playable1) {
                    tempPieces.remove(playingPiece);
                    rollUsed = 1;
                    diceUsed++;
                    System.out.println("Moved Piece " + playingPiece + " " + secondDie +" spots using the second die.");
                }
            }

            // unusable piece
            if(!playableT && !playable0 && !playable1) {
                tempPieces.remove(playingPiece);
                System.out.println("Piece " + playingPiece + " is unusable");
            }
        }
    }

    // Choose a random piece
    public Piece randPiece(ArrayList<Piece> c) {
        return c.get(rand.nextInt(c.size()));
    }

    // Determines if the piece can take a turn, returns true if move is valid and places piece, returns false otherwise
    public boolean takeTurn(Piece p, int displacement, Board b, Computer computer, Player player) {
        //return super.makeMove(b, computer, player, p.getID(), p.getX(), p.getX() + displacement);
        return true;
    }
}
