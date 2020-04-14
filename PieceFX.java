//Circle class

import javafx.application.Application; 
import javafx.scene.Scene; 
import javafx.scene.control.Button; 
import javafx.scene.layout.*; 
import javafx.scene.paint.Color; 
import javafx.scene.shape.Circle; 
import javafx.scene.control.*; 
import javafx.stage.Stage; 
  
import javafx.scene.Group;

public class PieceFX extends Application {
   
   private Piece piece;
   private String color;
   private int xCoord;
   private int yCoord;
   private boolean selected;
   private Circle circle;
   
   //default constructor. Not sure why it is needed, but it throws a NoSuchMethod Exception if not here
   public PieceFX() {
   }
   
   //Creates FX object representation of Piece
   //accepts piece oject, x position (in pixels) on board, and y position (in pixels) on board
   public PieceFX(Piece p, double xPos, double yPos) {
      this.selected = false;
      this.piece = p;
      //these are spike locations
      this.xCoord = p.getX();
      this.yCoord = p.getY();
      //create new circle fx object
      this.circle = new Circle(xPos, yPos, 25.0f);
      if (p.getColor() == "red") {
         this.circle.setFill(Color.RED);
      } else if (p.getColor() == "black") {
         this.circle.setFill(Color.BLACK);
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
   
   //retrieves piece object
   public Piece getPiece() {
      return piece;
   }
   
   //sets x location (in pixels) of circle
   public void setXpos(double xPos) {
      this.circle.setCenterX(xPos);
   }
   
   //sets y location (in pixels) of circle
   public void setYpos(double yPos) {
      this.circle.setCenterY(yPos);
   }
  
   //call this when a piece is clicked
   //sets color to yellow
   public void selectPiece() {
      this.selected = true;
      this.circle.setFill(Color.YELLOW);
   }
   
   //call this when a piece is unclicked
   public void unselectedPiece() {
      this.selected = false;
      if (this.piece.getColor() == "red") {
         this.circle.setFill(Color.RED);
      } else if (this.piece.getColor() == "black") {
         this.circle.setFill(Color.BLACK);
      }
   } 
  
   
   //sets starting stage
   public void start(Stage stage) throws Exception {
   
      stage.setTitle("Creating Piece in JavaFX");
      
      Piece p = new Piece("red", 1, 1, 1);
      PieceFX pFX = new PieceFX(p, 100.0f, 100.0f);
      
      Group group = new Group(pFX.circle);
      Scene scene = new Scene(group, 500, 300);
      
      stage.setScene(scene);
      
      stage.show();
      
   }
   
   //launches application
   public static void main(String args[]) {
       launch(args); 
   }

}