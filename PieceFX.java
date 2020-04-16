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

public class PieceFX extends Circle {
   
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
   public PieceFX(Piece p, Board b) { //, double xPos, double yPos) {
      this.selected = false;
      this.piece = p;
      //these are spike locations
      this.xCoord = p.getX();
      this.yCoord = p.getY();
      //create new circle fx object
      this.circle = new Circle(25.0f, 25.0f, 25.0f);
      //this.circle.setYpos(25.0, b, p);
      
      //this.circle.setStyle("-fx-padding: 0 0 0 10;");
      if (p.getColor().equals("RED")) {
         this.circle.setFill(Color.RED);
      } else if (p.getColor().equals("BLK")) {
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
   
   //retrieves circle
   public Circle getCircle() {
      return circle;
   }
   
   //sets x location (in pixels) of circle
   public void setXpos(double xPos) {
      this.circle.setCenterX(xPos);
   }
   
   //sets y location (in pixels) of circle
   public void setYpos(double yPos, Board b, Piece p) {
      //if (b.getBoard().get(p.getBoardLocation()).size() == 3)
      this.circle.setCenterY(yPos + 25.0);
         
   }
   
   //public void move()
  
   //call this when a piece is clicked
   //sets color to yellow
   public void selectPiece() {
      this.selected = true;
      this.circle.setFill(Color.YELLOW);
   }
   
   //call this when a piece is unclicked
   public void unselectedPiece() {
      this.selected = false;
      if (this.piece.getColor().equals("red")) {
         this.circle.setFill(Color.RED);
      } else if (this.piece.getColor().equals("black")) {
         this.circle.setFill(Color.BLACK);
      }
   } 
  
   
   //sets starting stage
//    public void start(Stage stage) throws Exception {
// 
//       stage.setTitle("Creating Piece in JavaFX");
//       
//       Piece p = new Piece("red", 1, 1, 1);
//       PieceFX pFX = new PieceFX(p);
//       
//       Group group = new Group(pFX.circle);
//       Scene scene = new Scene(group, 500, 300);
//       
//       stage.setScene(scene);
//       
//       stage.show();
//       
//    }
   
   //launches application
//    public static void main(String args[]) {
//        launch(args); 
//    }

}