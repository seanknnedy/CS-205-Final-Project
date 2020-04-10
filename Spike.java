import java.io.*;
import java.lang.*;
import java.util.*;

public class Spike {

   //each spike is a list of pieces with x and y coord
   private int xCoord;
   private int yCoord;
   private ArrayList<Piece> spike;

   //bookkeeping - keeps track of what is on each spike
   private String currentTeam;

   //constructor
   public Spike(int x, int y) {
      setX(x);
      setY(y);
      spike = new ArrayList<Piece>();
      currentTeam = "NONE";
   }

   //set x coord
   public void setX(int x) {
      this.xCoord = x;
   }

   //set y coord
   public void setY(int y) {
      this.yCoord = y;
   }

   //set current team
   public void setCurrentTeam(Piece p) {
      this.currentTeam = p.getColor();
   }

   //get current team
   public String getCurrentTeam() {
      return currentTeam;
   }

   //add piece to spike
   public void addToSpike(Piece p) {
      spike.add(p);
      currentTeam = p.getColor();
      p.setX(xCoord);
      p.setY(yCoord);
   }

   //remove piece from spike
   public void removeFromSpike(Piece p) {
      spike.remove(p);
      p.setX(0);
      p.setY(0);
      // update current team
      if(spike.size() == 0) {
         currentTeam = "NONE";
      }
   }

   //get spike
   public ArrayList<Piece> getPiecesOnSpike() {
      return spike;
   }

   //overridden toString method
   @Override
   public String toString() {
      return spike.toString();
   }

}