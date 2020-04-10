import java.io.*;
import java.lang.*;
import java.util.*;

public class Player {

    Random rand = new Random();
    public ArrayList<Piece> playerPieces;
    public ArrayList<Piece> blotPieces;
    private ArrayList<Piece> bearOffPieces;
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
        blotPieces = new ArrayList<Piece>();
        bearOffPieces = new ArrayList<Piece>();
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
        blotHit = !blotHit;
    }

    public String getTeam() {
        return team;
    }

    public Piece getPiece(int id) {
        return playerPieces.get(id - 1);
    }

    // Methods //

    // Determine if a player has won
    public boolean hasWon() {
        if(allHome()) {
            win = true;
        }
        return win;
    }

    // Determine if all player pieces are in the home quad
    public boolean allHome() {
        for(int p = 0; p < playerPieces.size(); p++) {
            if(!isHome(playerPieces.get(p))) {
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

    public void bearOffAPiece(Piece p) {
        bearOffPieces.add(p);
    }

    public void addBlot(int pieceID) {
        blotPieces.add(getPiece(pieceID));
    }

    public void printBlots() {
        for(int i = 0; i < blotPieces.size(); i++) {
            System.out.print(blotPieces.get(i) + " ");
        }
    }

    public void printBearOffs() {
        for(int i = 0; i < bearOffPieces.size(); i++) {
            System.out.print(bearOffPieces.get(i) + " ");
        }
    }
}