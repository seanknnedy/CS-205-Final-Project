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

    // Get blotHit status
    public boolean getBlotHit() {
        return blotHit;
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
    // Set blotHit status
    public void switchBlotHit(Piece p) {
        blotHit = !blotHit;
        p.switchBlot();
        if(blotHit) {
            blotPieces.add(p);
        } else {
            blotPieces.remove(p);
        }
    }

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
        p.setX(0);
        p.setY(0);
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


    // Handle the move
    public boolean makeMove(Board b, Player p, Player opponent, int pieceID, int fromSpike, int toSpike) {
        boolean successfulMove = false;
        boolean allAtHome = p.allHome();
        boolean valid = validateMove(b, p, opponent, pieceID, toSpike);
        if(!allAtHome) {
            if(valid) {
                b.getSpike(fromSpike).removeFromSpike(p.getPiece(pieceID));
                b.getSpike(toSpike).addToSpike(p.getPiece(pieceID));
                System.out.println("Move complete\n");
                successfulMove = true;
            } else {
                System.out.println("Move Incomplete... Try Again!\n");
            }
        } else {
            //bearingOffMove(b, p, pieceID, fromSpike);
            System.out.println("Bearing off section");
            successfulMove = true;
        }

        return successfulMove;
    }


    // Validate the the selected move
    public boolean validateMove(Board b, Player p, Player opponent, int pieceID, int toSpike) {
        System.out.println("\nValidating Move...");
        Spike current = b.getSpike(toSpike);

        // Case 1: player has a blot hit
        boolean blotHit = p.getBlotHit();
        boolean blotChoosen = false;
        if (blotHit) {
            for(Piece blotPiece : p.blotPieces) {
                if (blotPiece.getID() == pieceID) {
                    blotChoosen = true;
                    p.switchBlotHit(p.getPiece(pieceID));
                    break;
                }
            }
            if(!blotChoosen) {
                System.out.println("Invalid Move - blot must be played");
                return false;
            }
            return true;
        }

        // Case 2: other team is this spike with more than one piece
        else if (!current.getCurrentTeam().equals(p.getPiece(pieceID).getColor()) & current.getPiecesOnSpike().size() > 1) {
            System.out.println("Invalid Move - other team is occupying spike already");
            return false;
        }

        // Case 3: other team is this spike with one piece, move is valid, blot occurs
        else if (!current.getCurrentTeam().equals(p.getPiece(pieceID).getColor()) & current.getPiecesOnSpike().size() == 1) {
            System.out.println("Valid Move - blot!");
            // add to blotHit to individual piece and opponent
            opponent.switchBlotHit(b.getSpike(toSpike).getPiecesOnSpike().get(0));
            // remove piece from board
            b.getSpike(toSpike).removeFromSpike(b.getSpike(toSpike).getPiecesOnSpike().get(0));
            // return to complete move
            return true;
        }

        // Case 4: you already have 5 pieces on this spike
        else if (current.getCurrentTeam().equals(p.getPiece(pieceID).getColor()) & current.getPiecesOnSpike().size() == 5) {
            System.out.println("Invalid Move - maximum pieces on spike");
            return false;
        }

        // Case 5: assume valid move
        else {
            System.out.println("Valid Move - Not in any cases");
            return true;
        }
    }
}