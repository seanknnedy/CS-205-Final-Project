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
    public Player(int b, int t, int y, String tm) {
        // initialize player pieces arraylist
        playerPieces = new ArrayList<Piece>();
        // initialize win, blotHit, and Home x & y
        win = false;
        blotHit = false;
        homeXb = b;
        homeXt = t;
        homeY = y;
        team = tm;
    }

    public Player(ArrayList<Piece> p, int b, int t, int y) {
        playerPieces = p;
        win = false;
        blotHit = false;
        homeXb = b;
        homeXt = t;
        homeY = y;
    }

    /** Setters / Getters **/

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

    public String getTeam() {
        return team;
    }

    // Methods //

    // Determine if a player has won
    public boolean hasWon() {
        if(allHome()) {
            win = true;
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
/*
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

 */
}