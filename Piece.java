import java.lang.*;


public class Piece {

   //color: red or black, each piece has x and y coords
   private String color;
   private int xCoord;
   private int yCoord;
   private boolean blot;
   private int ID;


   /**************************************************** Constructor **************************************************/
   public Piece(String c, int x, int y, int id) {
      setColor(c);
      setX(x);
      setY(y);
      blot = false;
      ID = id;
   }


   /**************************************************** Getters/Setters **********************************************/
   // Set color
   public void setColor(String c) {
      color = c;
   }

   // Returns color
   public String getColor() {
      return color;
   }

   // Set x coordinate
   public void setX(int x) {
      xCoord = x;
   }

   // Returns x coordinate
   public int getX() {
      return xCoord;
   }

   // Set y coordinate
   public void setY(int y) {
      yCoord = y;
   }

   // Returns y coordinate
   public int getY() {
      return yCoord;
   }

   // Set blot - true if piece is blotted
   public void setBlot(boolean b) {
      blot = b;
   }

   // Returns blot - true if piece is blotted
   public boolean getBlot() {
      return blot;
   }

   // Returns ID
   public int getID() {
      return ID;
   }


   /**************************************************** Methods ******************************************************/
   // Determines if piece is from same player
   public boolean isEqual(Piece p, String expected) {
      return p.getColor().equals(expected);
   }

  // Determines if two pieces are the same
   @Override
   public boolean equals(Object other) {
      if (other == null)
         return false;
      if (getClass() != other.getClass())
         return false;
      if (this == other)
         return true;

      Piece otherPiece = (Piece)other;

      return color.equals(otherPiece.getColor()) && ID == otherPiece.getID();

   }


   // Switches blot - set coordinates to 0 if it is a blot
   public void switchBlot() {
      blot = !blot;
      if(blot) {
         setX(0);
         setY(0);
      }
   }

   // Print the color and ID of the Piece
   @Override
   public String toString() {
      return getColor() + getID();
   }
}