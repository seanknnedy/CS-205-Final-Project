import java.io.*;
import java.lang.*;
import java.util.*;

public class Piece {

   //color: red or black, each piece has x and y coords
   private String color;
   private int xCoord;
   private int yCoord;
   private boolean blot;
   private int ID;

   //constructor
   public Piece(String c, int x, int y, int id) {
      setColor(c);
      setX(x);
      setY(y);
      blot = false;
      ID = id;
   }

   //setcolor
   public void setColor(String c) {
      this.color = c;
   }

   //set x coord
   public void setX(int x) {
      this.xCoord = x;
   }

   //set y coord
   public void setY(int y) {
      this.yCoord = y;
   }

   //set blot
   public void setBlot(boolean b) {
      this.blot = b;
   }

   //get color
   public String getColor() {
      return color;
   }

   //get ID
   public int getID() {
      return ID;
   }

   //get x coord
   public int getX() {
      return xCoord;
   }

   //get y coord
   public int getY() {
      return yCoord;
   }

   //get blot
   public boolean getBlot() {
      return blot;
   }

   //isEqual: determines if piece is from same player
   public boolean isEqual(Piece p, String expected) {
      if (p.getColor() == expected) {
         return true;
      }
      return false;
   }

   //switch blot
   public void switchBlot() {
      if (this.blot) {
         this.blot = false;
      } else {
         this.blot = true;
      }
   }

   @Override
   public String toString() {
      return getColor() + getID();
   }
}