import javafx.application.Application;
import javafx.scene.Group;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;


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

        //Testing SpikeFX
        Spike testSpike = new Spike(2,2,3);
        SpikeFX testSpikeFx = new SpikeFX(testSpike);
        Group g = new Group(testSpikeFx.getTriangle());
        //grid.add(g,1,2);
        grid.getChildren().add(g);

        Scene scene = new Scene(grid,550,400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
