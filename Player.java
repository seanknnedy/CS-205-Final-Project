import java.io.*;
import java.lang.*;
import java.util.*;

public class Player {

    Random rand = new Random();
    public ArrayList<Piece> playerPieces;
    public ArrayList<Piece> blotPieces;
    private boolean win;
    private boolean blotHit;

    // coordinates of player's home quad & team
    private int homeXb;
    private int homeXt;
    private int homeY;
    private String team;


    /** Constructors **/
    public Player(int b, int t, int y, String team) {
        // initialize player pieces arraylist
        playerPieces = new ArrayList<Piece>();
        makePieces(team);
        // initialize win, blotHit, and Home x & y
        win = false;
        blotHit = false;
        homeXb = b;
        homeXt = t;
        homeY = y;
    }

    public Player(ArrayList<Piece> p, int b, int t, int y) {
        playerPieces = p;
        win = false;
        blotHit = false;
        homeXb = b;
        homeXt = t;
        homeY = y;
    }

    /** Setters **/

    // Set playerPieces to input ArrayList
    public void setPieces(ArrayList<Piece> p) {
        playerPieces = p;
    }

    // Set win = true
    public void setWin() {
        win = true;
    }

    // Set blotHit status
    public void setBlotHit() {
        if(blotHit) {
            blotHit = false;
        } else {
            blotHit = true;
        }

    }

    // Methods //

    // Determine if a player has won
    public boolean hasWon() {
        if(allHome()) {
            setWin();
        }
        return win;
    }

    /** TODO determineBlot() will need altering once more players are added, as it only looks at player pieces. Perhaps it
     should be a function within a Rules class or something that then flags pieces as blots so if another player lands on them, the
     Rules know instantly that it's a blotHit? */

    // Determine if there's a blotted piece (one piece on the spike) --> Fixed this to use getBlot() and it doesnt work?
    public void determineBlot() {
        blotPieces = new ArrayList<Piece>();
        for(int p=0; p < playerPieces.size(); p++) {
            if (playerPieces.get(p).getBlot()) {
                blotPieces.add(playerPieces.get(p));
            }
        }
    }

    // Determine if all player pieces are in the home quad
    public boolean allHome() {
        for(int p=0; p < playerPieces.size(); p++) {
            if(playerPieces.get(p).getY() != homeY) {
                return false;
            } else if(playerPieces.get(p).getX() <= homeXb || playerPieces.get(p).getX() >= homeXt) {
                return false;
            }
        }
        return true;
    }

    // Determine if a single piece is in the home quad
    public boolean isHome(Piece h) {
        if(h.getY() == homeY && h.getX() >= homeXb && h.getX() <= homeXt) {
            return true;
        }
        return false;
    }

    // Establish player pieces, though I don't think all pieces start at 0,0?
    public void makePieces(String t) {

        //creating pieces --> Thanks Sean for the code
        Piece p1 = new Piece(t, 0, 0);
        Piece p2 = new Piece(t, 0, 0);
        Piece p3 = new Piece(t, 0, 0);
        Piece p4 = new Piece(t, 0, 0);
        Piece p5 = new Piece(t, 0, 0);
        Piece p6 = new Piece(t, 0, 0);
        Piece p7 = new Piece(t, 0, 0);
        Piece p8 = new Piece(t, 0, 0);
        Piece p9 = new Piece(t, 0, 0);
        Piece p10 = new Piece(t, 0, 0);
        Piece p11 = new Piece(t, 0, 0);
        Piece p12 = new Piece(t, 0, 0);
        Piece p13 = new Piece(t, 0, 0);
        Piece p14 = new Piece(t, 0, 0);
        Piece p15 = new Piece(t, 0, 0);

        //adding pieces to playerPieces arraylist
        playerPieces.add(p1);
        playerPieces.add(p2);
        playerPieces.add(p3);
        playerPieces.add(p4);
        playerPieces.add(p5);
        playerPieces.add(p6);
        playerPieces.add(p7);
        playerPieces.add(p8);
        playerPieces.add(p9);
        playerPieces.add(p10);
        playerPieces.add(p11);
        playerPieces.add(p12);
        playerPieces.add(p13);
        playerPieces.add(p14);
        playerPieces.add(p15);
    }

    /** PAST THIS POINT: Computer stuff --> TODO: figure out how to put this into another file or, since it works
     here, leave it here and not call these methods on a real player. The program refused to find Computer when it
     was added as a seperate class (and file) extending Player */

    // sends compute to playDouble() or playSingle() depending on their roll
    public void playComp(ArrayList<Integer> roll, ArrayList<Spike> board) {
        if(roll.get(0) == roll.get(1)) {
            playDouble(roll, board);
        } else {
            playSingle(roll, board);
        }
    }

    // handles when the computer rolls the same number on both dice
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

    // handles when the computer rolls different numbers on the dice
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

    // grabs a new piece
    public Piece randPiece(ArrayList<Piece> c) {
        return c.get(rand.nextInt(c.size()));
    }

    // determines if the piece can take a turn, places piece & returns true if yes, returns false otherwise
    public boolean takeTurn(Piece p, int r, ArrayList<Spike> b) {
        int pX = p.getX();
        int toMove = pX + r;

        // TODO: not sure how to handle bearing off
        if(toMove >= 24) {
            return false;

        } else {
            Spike spike = b.get(toMove);
            ArrayList<Piece> temp = b.get(toMove).getSpike();

            // if spike has no pieces on it, add piece
            if(temp.size() == 0) {
                spike.addToSpike(p);
                return true;

                // if spike has 1 piece on it
            } else if (temp.size() == 1) {

                // add to spike if it is a computer's piece
                if(temp.get(0).isEqual(p, team)) {
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
                if(temp.get(0).isEqual(p, team)) {
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