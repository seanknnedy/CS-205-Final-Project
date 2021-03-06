//Circle class

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button; 
import javafx.scene.layout.*; 
import javafx.scene.paint.Color; 
import javafx.scene.shape.Circle; 
import javafx.scene.control.*; 
import javafx.stage.Stage;

  
import javafx.scene.Group;

public class PieceFX extends Circle {
   
   private Piece piece;
   private String color;
   private int xCoord;
   private int yCoord;
   private boolean selected;
   private boolean isBlot;
   //private Circle circle = new Circle();

   //Creates FX object representation of Piece
   //accepts piece object, x position (in pixels) on board, and y position (in pixels) on board
   public PieceFX(Piece p, Board b) { //, double xPos, double yPos) {
      this.selected = false;
      this.piece = p;
      this.isBlot = false;
      //these are spike locations
      this.xCoord = p.getX();
      this.yCoord = p.getY();
      //create new circle fx object
      //this.circle.setTranslateY(75.0);
      //this.circle.setRadius(20.0);
      this.setTranslateY(75.0);
      this.setRadius(20.0);
      
      //this.circle.setStyle("-fx-padding: 0 0 0 10;");
      if (p.getColor().equals("RED")) {
         //this.circle.setFill(Color.RED);
         this.setFill(Color.RED);
      } else if (p.getColor().equals("BLK")) {
         //this.circle.setFill(Color.BLACK);
         this.setFill(Color.BLACK);
      }
   }
   
   //retrieves x spike coord
   public int getX() {
      return xCoord;
   }
   
   //retrieves y spike coord
   public int getY() {
      return yCoord;
   }

   //retrieves if FX piece is a blot
   public boolean getBlot() {
      return isBlot;
   }
   
   //set x spike coord
   public void setX(int x) {
      this.xCoord = x;
   }
   
   //set y spike coord
   public void setY(int y) {
      this.yCoord = y;
   }

   //retrieves if FX piece is a blot
   public void setBlot(boolean b) {
      this.isBlot = b;
      if (isBlot) {
         this.xCoord = 0;
         this.yCoord = 0;
      }
   }
   
   //retrieves piece object
   public Piece getPiece() {
      return piece;
   }
   
   //retrieves circle
//    public Circle getCircle() {
//       return circle;
//    }
   
   //sets x location (in pixels) of circle
   public void setXpos(double xPos) {
      this.setCenterX(xPos);
   }
   
   //return selected
   public boolean getSelected() {
      return selected;
   }
   
   //set selected
   public void setSelected(boolean s) {
      this.selected = s;
   }
   
   //sets y location (in pixels) of circle
   public void move(int num) {
      // number of pieces on spike = num
      // yPosition = num * (diameter of circles) + radius
      if (num == 2) {
         this.setTranslateY(num * (25.0) + 25.0);
      }
   }
  
   //call this when a piece is clicked
   //sets color to yellow
//    public void selectPiece() {
//       this.selected = true;
//       this.circle.setFill(Color.YELLOW);
//    }
//    
//    //call this when a piece is unclicked
//    public void unselectPiece() {
//       this.selected = false;
//       if (this.piece.getColor().equals("red")) {
//          this.circle.setFill(Color.RED);
//       } else if (this.piece.getColor().equals("black")) {
//          this.circle.setFill(Color.BLACK);
//       }
//    }
   
//       public void switchSelected(Circle c) {
//          if (this.selected) {
//             if (this.piece.getColor().equals("RED")) {
//                this.selected = false;
//                c.setFill(Color.RED);
//             } else {
//                this.selected = false;
//                c.setFill(Color.BLACK);
//             }
//          } else {
//             c.setFill(Color.YELLOW);
//          }
//       }


}



