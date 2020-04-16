import javafx.application.Application;
import javafx.scene.Group;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.geometry.HPos;


public class BackgammonGui extends Application {
    private GridPane grid;
    public static void main(String[] args)
    {
        launch(args);

    }
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Backgammon");
        grid = new GridPane();
        grid.setStyle("-fx-background-color:oldlace;");
        
        Player playerRED = new Player(7, 12, 1, "RED");
        Computer playerBLK = new Computer(7, 12, 2, "BLK");

        // create new board and print
        Board newBoard = new Board(playerRED.playerPieces, playerRED.getTeam(), playerBLK.playerPieces, playerBLK.getTeam());
        
//         for (int i = 0; i < newBoard.getBoard().size(); i++) {
//             SpikeFX spike = new SpikeFX(newBoard.getBoard().get(i));
//             Group g = new Group(spike.getTriangle());
//             grid.getChildren().add(g);
//         }
        
        //for (int r = 0; r < 2; r++) {
        for (int c = 0; c < 12; c++) {
            SpikeFX spike = new SpikeFX(newBoard.getBoard().get(c));
            //System.out.println(spike.getY());
            grid.add(spike.getTriangle(), c, 1);
        }
        
        //add padding
        
        for (int c = 0; c < 12; c++) {
            SpikeFX spike = new SpikeFX(newBoard.getBoard().get(c + 12));
            //System.out.println(spike.getY());
            grid.add(spike.getTriangle(), c, 2);
        }
        
        //grid.setHgap(10);
        grid.setVgap(100);

        for (int p = 0; p < playerRED.playerPieces.size(); p++) {
            PieceFX piece = new PieceFX(playerRED.playerPieces.get(p), newBoard);
            grid.add(piece.getCircle(), playerRED.playerPieces.get(p).getX()-1, playerRED.playerPieces.get(p).getY());
            grid.setHalignment(piece.getCircle(), HPos.CENTER);
            piece.setYpos(50.0f);
        }


        //Testing SpikeFX
        
        //Spike testSpike = new Spike(2,2,3);
        
        
        //SpikeFX testSpikeFx = new SpikeFX(testSpike);
        //Group g = new Group(testSpikeFx.getTriangle());
        //grid.getChildren().add(g);


        Scene scene = new Scene(grid,1100,650);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
