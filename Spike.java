import java.lang.*;
import java.util.*;


public class Spike {

   //each spike is a list of pieces with x and y coord
   private int xCoord;
   private int yCoord;
   private ArrayList<Piece> spike;

   //bookkeeping - keeps track of what is on each spike
   private String currentTeam;


   /**************************************************** Constructor **************************************************/
   public Spike(int x, int y) {
      setX(x);
      setY(y);
      spike = new ArrayList<Piece>();
      currentTeam = "NONE";
   }


   /**************************************************** Getters/Setters **********************************************/
   // Set x coordinate
   public void setX(int x) {
      this.xCoord = x;
   }

   // Set y coordinate
   public void setY(int y) {
      this.yCoord = y;
   }

   // Set current team
   public void setCurrentTeam(Piece p) {
      this.currentTeam = p.getColor();
   }

   // Returns current team
   public String getCurrentTeam() {
      return currentTeam;
   }

   // Returns pieces on spike
   public ArrayList<Piece> getPiecesOnSpike() {
      return spike;
   }


   /**************************************************** Methods ******************************************************/
   // Add piece to spike
   public void addToSpike(Piece p) {
      spike.add(p);
      currentTeam = p.getColor();
      p.setX(xCoord);
      p.setY(yCoord);
   }

   // Remove piece from spike
   public void removeFromSpike(Piece p) {
      spike.remove(p);
      p.setX(0);
      p.setY(0);
      // update current team if the spike is now empty
      if(spike.size() == 0) {
         currentTeam = "NONE";
      }
   }

   // Overridden toString method
   @Override
   public String toString() {
      return spike.toString();
   }
}