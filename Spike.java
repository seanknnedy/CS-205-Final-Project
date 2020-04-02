import java.io.*;
import java.lang.*;
import java.util.*;

public class Spike {

   //each spike is a list of pieces with x and y coord
   private int xCoord;
   private int yCoord;
   private ArrayList<Piece> spike;

   //constructor
   public Spike(int x, int y) {
      setX(x);
      setY(y);
      spike = new ArrayList<Piece>();
   }

   //set x coord
   public void setX(int x) {
      this.xCoord = x;
   }

   //set y coord
   public void setY(int y) {
      this.yCoord = y;
   }

   //add piece to spike
   public void addToSpike(Piece p) {
      if (spike.size() >= 5) {
         System.out.println("test: max number of pieces (5) on this spike");
      } else {
         if(spike.size() == 0) {
            p.setBlot(true);
         }
         spike.add(p);
         p.setX(xCoord);
         p.setY(yCoord);

      }
   }

   //remove piece from spike
   public void removeFromSpike(Piece p) {
      spike.remove(p);
      p.setX(0);
      p.setY(0);
      p.switchBlot();
   }

   //get spike
   public ArrayList<Piece> getSpike() {
      return spike;
   }

   //overridden toString method
   @Override
   public String toString() {
      return spike.toString();
   }

}