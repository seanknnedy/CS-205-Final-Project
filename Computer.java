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
        // clone the computer player pieces to a temp array
        ArrayList<Piece> tempPieces = (ArrayList<Piece>) computer.playerPieces.clone();

        // add all the possible moves to the array
        ArrayList<Integer> possibleMoves = new ArrayList<Integer>();

        int maxMoves;
        boolean turnComplete = false;

        if (roll.get(0) != roll.get(1)) {
            // index 0 = first die, index 1 = second die, index 2 = both die
            possibleMoves.add(roll.get(0));
            possibleMoves.add(roll.get(1));
            possibleMoves.add(roll.get(0) + roll.get(1));
            maxMoves = 2;
        } else {
            // if doubles, 1 = x, 2 = 2x, 3 = 3x, 4 =4x
            possibleMoves.add(roll.get(0));
            possibleMoves.add(roll.get(0) * 2);
            possibleMoves.add(roll.get(0) * 3);
            possibleMoves.add(roll.get(0) * 4);
            maxMoves = 4;
        }

        while(possibleMoves.size() != 0) {

            // get a random piece from computer
            Piece playingPiece = randPiece(tempPieces);

            if (tempPieces.size() == 0 || playingPiece == null) {
                System.out.println("\nNo possible move. Player's Turn!");
                break;
            }

            // for each of the possible moves, check to see if the turn is complete using that roll
            for (int r = 0; r < possibleMoves.size(); r++) {
                turnComplete = takeTurn(playingPiece, possibleMoves.get(r), board, computer, player);
                // if the turn is complete, add the amount of dice for the current move to total dice used and break the statement
                if (turnComplete) {
                    // reset the temp pieces
                    tempPieces = (ArrayList<Piece>) computer.playerPieces.clone();
                    if (maxMoves == 2) {
                        if (r == 0 || r == 1) {
                            if (possibleMoves.size() > 1) {
                                possibleMoves.remove(2);
                            }
                            possibleMoves.remove(r);
                        } else {
                            possibleMoves.clear();
                        }
                    } else {
                        if (possibleMoves.size() == r) {
                            possibleMoves.clear();
                        } else {
                            while (possibleMoves.size() > (r - 1)) {
                                if (possibleMoves.size() != 0) {
                                    possibleMoves.remove(possibleMoves.size() - 1);
                                } else {
                                    break;
                                }
                            }
                        }
                    }
                    break;
                }
            }

            // if the turn is not compelete, the piece cannot be played
            if (!turnComplete) {
                tempPieces.remove(playingPiece);
            }
        }
    }

    // Choose a random piece
    public Piece randPiece(ArrayList<Piece> c) {
        if (c.size() == 0) {
            return null;
        }

        Piece tempPiece = c.get(rand.nextInt(c.size()));

        while (tempPiece.getBoardLocation() == 26) {
            c.remove(tempPiece);
            if (c.size() == 0) {
                return null;
            }
            tempPiece = c.get(rand.nextInt(c.size()));
        }

        return tempPiece;
    }

    // Determines if the piece can take a turn, returns true if move is valid and places piece, returns false otherwise
    public boolean takeTurn(Piece p, int roll, Board b, Computer computer, Player player) {
        return super.makeMove(b, computer, player, p, roll, true);

    }
}
