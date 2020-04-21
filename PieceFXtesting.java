import static javafx.application.Application.launch; 
import javafx.event.EventHandler;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button; 
import javafx.scene.layout.*; 
import javafx.scene.paint.Color; 
import javafx.scene.shape.Circle; 
import javafx.scene.control.*; 
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent; 


import javafx.scene.Group; 
public class PieceFXtesting extends Application {

   public void start(Stage stage) {
      stage.setTitle("Circle Testing");
      /*
      
      Player playerRED = new Player(7, 12, 1, "RED");
      Computer playerBLK = new Computer(7, 12, 2, "BLK");

      // create new board and print
      Board board = new Board(playerRED.playerPieces, playerRED.getTeam(), playerBLK.playerPieces, playerBLK.getTeam());
      
      PieceFX p = new PieceFX(playerRED.playerPieces.get(0), board);
      
      EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() { 
         @Override 
         public void handle(MouseEvent e) { 
            //System.out.println("Hello World"); 
            p.selectPiece();
         } 
      };
      
      //Registering the event filter 
       p.getCircle().addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);
//       
//       EventHandler<MouseEvent> eventHandler2 = new EventHandler<MouseEvent>() { 
//          @Override 
//          public void handle(MouseEvent e) { 
//             //System.out.println("Hello World"); 
//             p.unselectPiece();
//          } 
//       };
//       
//       p.getCircle().addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler2);
      
      Group group = new Group(p.getCircle());
      
      Scene scene = new Scene(group, 500, 300);
      
      stage.setScene(scene);
      
      stage.show();

       */
      
   }
   
   public static void main(String args[]) {
      launch(args);
   }

}