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


    /**************************************************** Constructors ************************************************/
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


    /**************************************************** Getters/Setters *********************************************/
    // Set win = true
    public void setWin() {
        win = true;
    }

    // Set blotHit status
    public void setBlotHit() {
        blotHit = !blotHit;
    }

    // Returns the team of the player
    public String getTeam() {
        return team;
    }

    // Returns the piece with the given id
    public Piece getPiece(int id) {
        return playerPieces.get(id - 1);
    }


    /**************************************************** Methods *****************************************************/
    // Determine if a player has won
    public boolean hasWon() {
        return bearOffPieces.size() == 15;
    }

    // Determine if all player pieces are in the home quad
    public boolean allHome() {
        for (Piece playerPiece : playerPieces) {
            if (!isHome(playerPiece)) {
                return false;
            }
        }
        return true;
    }

    // Determine if a single piece is in the home quad
    public boolean isHome(Piece h) {
        return h.getY() == homeY && h.getX() >= homeXb && h.getX() <= homeXt;
    }

    // Bear off the specified piece
    public void bearOffAPiece(Piece p) {
        bearOffPieces.add(p);
    }

    // Print the Pieces that have been beared
    public void printBearOffs() {
        for (Piece bearOffPiece : bearOffPieces) {
            System.out.print(bearOffPiece + " ");
        }
    }

    // Add a blot to the list of blotted pieces
    public void addBlot(int pieceID) {
        blotPieces.add(getPiece(pieceID));
    }

    // Print the list blots
    public void printBlots() {
        for (Piece blotPiece : blotPieces) {
            System.out.print(blotPiece + " ");
        }
    }
}