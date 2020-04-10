import java.util.ArrayList;

public class Computer extends Player{


    /**************************************************** Constructor *************************************************/
    public Computer(int b, int t, int y, String tm) {
        // call Player constructor
        super(b, t, y, tm);
    }


    /**************************************************** Methods *****************************************************/
    // Sends compute to playDouble() or playSingle() depending on their roll
    public void playComp(ArrayList<Integer> roll, ArrayList<Spike> board) {
        if(roll.get(0).equals(roll.get(1))) {
            playDouble(roll, board);
        } else {
            playSingle(roll, board);
        }
    }

    // Handles when the computer rolls the same number on both dice
    public void playDouble(ArrayList<Integer> d, ArrayList<Spike> b) {
        // declaring everything
        System.out.println("In playDouble with roll of " + d);
        ArrayList<Piece> tempPieces = playerPieces;
        final int doubleTurns = 4;
        int rollsAll = d.get(0) * 4;
        int rollsThree = d.get(0) * 3;
        int rollsHalf = d.get(0) * 2;
        int count = 0;

        while(count < doubleTurns) {

            // if there are no more pieces, exit turn
            if(tempPieces.size() == 0) {
                System.out.println("No playable pieces");
                break;
            }

            // get piece to play
            Piece play = randPiece(tempPieces);

            // test to see if piece can be moved using all dice
            boolean playableA = takeTurn(play,rollsAll,b);
            if(playableA) {
                System.out.println("Moved Piece " + play + " using 4/4 dice");
                break;
            }

            // test to see if piece can be moved using 3 dice
            boolean playableTh = takeTurn(play,rollsThree,b);
            if(((4 - count) > 3) || count == 0) {
                if(playableTh) {
                    tempPieces.remove(play);
                    count += 3;
                    System.out.println("Moved Piece " + play + " using 3/4 dice");
                }
            }

            // test to see if piece can be moved using 2 dice
            boolean playableH = takeTurn(play,rollsHalf,b);
            if((((4 - count) > 2) || count == 0) && !playableTh) {
                if(playableH) {
                    tempPieces.remove(play);
                    count += 2;
                    System.out.println("Moved Piece " + play + " using 2/4 dice");
                }
            }

            // test to see if piece can be moved using 1 die
            boolean playable0 = takeTurn(play,d.get(0),b);
            if((((4 - count) > 1) || count == 0) && !playableTh && !playableH) {
                if(playable0) {
                    tempPieces.remove(play);
                    count++;
                    System.out.println("Moved Piece " + play + " using 1/4 dice");
                }
            }

            if(!playableA && !playableTh && !playableH && !playable0) {
                tempPieces.remove(play);
                System.out.println("Piece " + play + " is unusable");
            }
        }
    }

    // Handles when the computer rolls different numbers on the dice
    public void playSingle(ArrayList<Integer> s, ArrayList<Spike> b) {

        System.out.println("In playSingle with roll of " + s);
        ArrayList<Piece> tempPieces = playerPieces;
        final int singleTurns = 2;
        int rollUsed = 3;
        int count = 0;
        int rollsTogether = s.get(0) + s.get(1);

        while(count < singleTurns) {

            // if there are no more pieces, exit turn
            if(tempPieces.size() == 0) {
                System.out.println("No playable pieces");
                break;
            }

            // get piece to play
            Piece play = randPiece(tempPieces);

            // test to see if one piece can be moved using both dice
            boolean playableT = takeTurn(play,rollsTogether,b);
            if(playableT) {
                System.out.println("Moved Piece " + play + " using both dice");
                break;
            }

            // otherwise test using first die
            boolean playable0 = takeTurn(play,s.get(0),b);
            if(rollUsed == 3 || rollUsed != 0) {
                if(playable0) {
                    tempPieces.remove(play);
                    rollUsed = 0;
                    count++;
                    System.out.println("Moved Piece " + play + " using first die");
                }
            }

            // otherwise test using second die
            boolean playable1 = takeTurn(play,s.get(1),b);
            if(!playable0 && (rollUsed == 3 || rollUsed != 1)) {
                if(playable1) {
                    tempPieces.remove(play);
                    rollUsed = 1;
                    count++;
                    System.out.println("Moved Piece " + play + " using second die");
                }
            }

            // unusable piece
            if(!playableT && !playable0 && !playable1) {
                tempPieces.remove(play);
                System.out.println("Piece " + play + " is unusable");
            }
        }
    }

    // Choose a random piece
    public Piece randPiece(ArrayList<Piece> c) {
        return c.get(rand.nextInt(c.size()));
    }

    // Determines if the piece can take a turn, places piece & returns true if yes, returns false otherwise
    public boolean takeTurn(Piece p, int r, ArrayList<Spike> b) {
        int pX = p.getX();
        int toMove = pX + r;

        // TODO: not sure how to handle bearing off
        if(toMove >= 24) {
            return false;

        } else {
            Spike spike = b.get(toMove);
            ArrayList<Piece> temp = b.get(toMove).getPiecesOnSpike();

            // if spike has no pieces on it, add piece
            if(temp.size() == 0) {
                spike.addToSpike(p);
                return true;

                // if spike has 1 piece on it
            } else if (temp.size() == 1) {

                // add to spike if it is a computer's piece
                if(temp.get(0).isEqual(p, super.getTeam())) {
                    spike.addToSpike(p);
                    return true;

                    // add to spike if other player's piece, remove other player's piece from spike & mark as blotHit
                } else {
                    spike.addToSpike(p);
                    spike.removeFromSpike(temp.get(0));
                    temp.get(0).switchBlot(); // not sure if switchBlot() marks pieces as a plot hit?
                    return true;
                }

                // if the spike has more than 1 piece on it
            } else {

                // if the pieces are the Computers, can add to spike
                if(temp.get(0).isEqual(p, super.getTeam())) {
                    spike.addToSpike(p);
                    return true;

                    // otherwise, returns false
                } else {
                    return false;
                }
            }
        }
    }
}
