import java.io.*;
import java.lang.*;
import java.util.*;

public class Player {

    public ArrayList<Piece> playerPieces;
    public ArrayList<Integer> blotPieces;
    private boolean win;
    private boolean blotHit;
    private int homeX;
    private int homeY;

    public Player(int x, int y) {
        playerPieces = new ArrayList<Piece>();
        makePieces();
        blotPieces = new ArrayList<Integer>();
        win = false;
        blotHit = false;
        homeX = x;
        homeY = y;
    }

    public Player(ArrayList<Piece> p, int x, int y) {
        playerPieces = p;
        blotPieces = new ArrayList<Integer>();
        win = false;
        blotHit = false;
        homeX = x;
        homeY = y;
    }

    // Setters //
    public void setPieces(ArrayList<Piece> p) {
        playerPieces = p;
    }

    public void makePieces() {

        //creating red pieces --> Thanks Sean for the code
        Piece red1 = new Piece("red", 0, 0);
        Piece red2 = new Piece("red", 0, 0);
        Piece red3 = new Piece("red", 0, 0);
        Piece red4 = new Piece("red", 0, 0);
        Piece red5 = new Piece("red", 0, 0);
        Piece red6 = new Piece("red", 0, 0);
        Piece red7 = new Piece("red", 0, 0);
        Piece red8 = new Piece("red", 0, 0);
        Piece red9 = new Piece("red", 0, 0);
        Piece red10 = new Piece("red", 0, 0);
        Piece red11 = new Piece("red", 0, 0);
        Piece red12 = new Piece("red", 0, 0);
        Piece red13 = new Piece("red", 0, 0);
        Piece red14 = new Piece("red", 0, 0);
        Piece red15 = new Piece("red", 0, 0);

        //adding pieces to player 1 arraylist
        playerPieces.add(red1);
        playerPieces.add(red2);
        playerPieces.add(red3);
        playerPieces.add(red4);
        playerPieces.add(red5);
        playerPieces.add(red6);
        playerPieces.add(red7);
        playerPieces.add(red8);
        playerPieces.add(red9);
        playerPieces.add(red10);
        playerPieces.add(red11);
        playerPieces.add(red12);
        playerPieces.add(red13);
        playerPieces.add(red14);
        playerPieces.add(red15);
    }

    public void setWin() {
        win = true;
    }

    public void setBlotHit() {
        blotHit = true;
    }

    public void undoBlotHit() {
        blotHit = false;
    }

    // Methods //

    public boolean hasWon() {
        if(allHome()) {
            setWin();
        }
        return win;
    }


    // Determine if there's a blotted piece (one piece on the spike)
    public void determineBlot() {
        int[] values = new int[24];
        for(int v=0; v < values.length; v++) {
            values[v] = 0;
        }

        for(int p=0; p < playerPieces.size(); p++) {
            int temp = playerPieces.get(p).getX();
            if(playerPieces.get(p).getY() == 2) {
                temp += 12;
            }
            values[temp] = values[temp] + 1;
        }

        blotPieces.clear();
        for(int c=0; c < values.length; c++) {
            if(values[c] == 1) {
                blotPieces.add(c);
            }
        }
    }

    public boolean allHome() {
        for(int p=0; p < playerPieces.size(); p++) {
            if(playerPieces.get(p).getY() != homeY) {
                return false;
            } else if(playerPieces.get(p).getX() >= homeX) {
                return false;
            }
        }
        return true;
    }

    public boolean isHome(Piece h) {
        if(h.getY() == homeY && h.getX() >= homeX) {
            return true;
        }
        return false;
    }
}
