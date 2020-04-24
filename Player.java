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
        blotPieces = new ArrayList<Piece>();
        bearOffPieces = new ArrayList<Piece>();
    }


    /**************************************************** Getters/Setters *********************************************/
    // Set win = true
    public void setWin() {
        win = true;
    }

    // Get blotHit status
    public boolean getBlotHit() {
        return this.blotHit;
    }

    // Returns the team of the player
    public String getTeam() {
        return this.team;
    }

    // Returns the piece with the given id
    public Piece getPiece(int id) {
        return this.playerPieces.get(id - 1);
    }

    public ArrayList<Piece> getBlotPieces() {
        return blotPieces;
    }


    /**************************************************** Methods *****************************************************/
    // Set blotHit status
    public void switchBlotHit(Piece p) {

        if(p.getBlot()) {
            blotPieces.remove(p);
        } else {
            blotPieces.add(p);
        }

        p.switchBlot();

        if (this.getBlotPieces().size() == 0) {
            blotHit = !blotHit;
        }
    }

    // Determine if a player has won
    public boolean hasWon() {
        return bearOffPieces.size() == 15;
    }

    // Determine if all player pieces are in the home quad
    public boolean allHome() {
        for (Piece playerPiece : this.playerPieces) {
            if (!isHome(playerPiece)) {
                return false;
            }
        }
        return true;
    }

    // Determine if a single piece is in the home quad
    public boolean isHome(Piece h) {
        boolean flag = false;
        if (h.getY() == homeY && h.getX() >= homeXb && h.getX() <= homeXt) {
            flag = true;
        } else if (h.getBoardLocation() == 25 || h.getBoardLocation() == 26) {
            flag = true;
        }
        return flag;
    }

    // Bear off the specified piece
    public void bearOffAPiece(int pieceID) {
        Piece p = getPiece(pieceID);
        this.bearOffPieces.add(p);
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
    // TODO: Once we are using clicks, change parameters and uncomment... int pieceID = Piece piece
    public boolean makeMove(Board b, Player p, Player opponent, Piece piece, int roll, boolean move) {
        boolean valid;
        //TODO: uncomment the comment below and comment out the line below that
        int pieceID = piece.getID();
        //Piece piece = p.getPiece(pieceID);
        int fromSpike = piece.getBoardLocation();
        int targetSpike = 0;

        // calculate the target spike
        if (p.getTeam().equals("BLK")) {
            targetSpike = fromSpike - roll;
            if (targetSpike < 1) {
                targetSpike = 25;
            }
            if (piece.getBlot()) {
                targetSpike = 25 - roll;
            }
        }
        if (p.getTeam().equals("RED")) {
            targetSpike = fromSpike + roll;
            if (targetSpike > 24) {
                targetSpike = 26;
            }
            if (piece.getBlot()) {
                targetSpike = roll;
            }
        }

        valid = validateMove(b, p, opponent, pieceID, fromSpike, targetSpike, roll);
        if (move) {
            if (valid) {
                if (piece.getBlot()) {
                    p.switchBlotHit(p.getPiece(pieceID));
                }
                b.getSpike(fromSpike).removeFromSpike(p.getPiece(pieceID));
                b.getSpike(targetSpike).addToSpike(p.getPiece(pieceID));
                System.out.println("Move complete\n");
            } else {
                System.out.println("Move Incomplete... Try Again!\n");
            }
        }

        return valid;
    }

    // Validate the the selected move
    public boolean validateMove(Board b, Player p, Player opponent, int pieceID, int fromSpike, int targetSpike, int roll) {
        System.out.println("\nValidating Move...");
        Spike current = b.getSpike(targetSpike);
        boolean bearingOff = false;
        if (targetSpike == 25 || targetSpike == 26) {
            bearingOff = true;
        }


        // Case 1: player has a blot hit, must choose to play blot
        boolean blotHit = p.getBlotHit();
        boolean blotChoosen = false;
        if (p.getBlotPieces().size() != 0) {
            for (Piece blotPiece : p.blotPieces) {
                if (blotPiece.getID() == pieceID) {
                    blotChoosen = true;
                    break;
                }
            }
            if (!blotChoosen) {
                System.out.println("Invalid Move - blot must be played");
                return false;
            }
        }

        // Case 2: player is trying to bear off a piece
        if (bearingOff) {
            // if all of the player pieces are not home, move is invalid
            if (!p.allHome()) {
                System.out.println("Invalid Move - all pieces must be home before bearing off");
                return false;
            }

            // calculate furthest piece and distance from bearing off
            int furthest = 0;
            int distance = 0;
            if (p.getTeam().equals("BLK")) {
                distance = fromSpike;
                for (Piece piece1 : p.playerPieces) {
                    if (piece1.getBoardLocation() > furthest) {
                        furthest = piece1.getBoardLocation();
                    }
                }
            }
            if (p.getTeam().equals("RED")) {
                distance = 25 - fromSpike;
                for (Piece piece1 : p.playerPieces) {
                    if ((25 - piece1.getBoardLocation()) > furthest) {
                        furthest = 25 - piece1.getBoardLocation();
                    }
                }
            }

            // if the roll is higher then the distance and there is a further piece, move is invalid
            if (roll > distance && furthest > distance) {
                System.out.println("Invalid Move - must choose furthest piece");
                return false;
            }

            p.bearOffAPiece(pieceID);
            return true;

        } else {
            // Case 3: other team is on this spike with more than one piece
            if (( !current.getCurrentTeam().equals(p.getPiece(pieceID).getColor()) ) & current.getPiecesOnSpike().size() > 1) {
                System.out.println("Invalid Move - other team is occupying spike already");
                return false;
            }

            // Case 4: blot occrus, other team is on this spike with one piece, move is valid
            else if (!current.getCurrentTeam().equals(p.getPiece(pieceID).getColor()) & current.getPiecesOnSpike().size() == 1) {
                System.out.println("Valid Move - blot!");
                // add to blotHit to individual piece and opponent
                opponent.switchBlotHit(b.getSpike(targetSpike).getPiecesOnSpike().get(0));
                // add that piece to blots
                b.getSpike(27).addToSpike(b.getSpike(targetSpike).getPiecesOnSpike().get(0));
                // remove piece from board
                b.getSpike(targetSpike).removeFromSpike(b.getSpike(targetSpike).getPiecesOnSpike().get(0));
                // return to complete move
                return true;
            }

            // Case 5: you already have 5 pieces on this spike
            else if (current.getCurrentTeam().equals(p.getPiece(pieceID).getColor()) & current.getPiecesOnSpike().size() == 5) {
                System.out.println("Invalid Move - maximum pieces on spike");
                return false;
            }

            // Case 6: assume valid move
            else {
                System.out.println("Valid Move - Not in any cases");
                return true;
            }
        }
    }
}