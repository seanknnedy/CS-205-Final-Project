import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.geometry.HPos;

import java.util.ArrayList;


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
        //grid.setStyle("-fx-background-color:oldlace;");
        int windowHeight = 660;
        int windowWidth = 1100;
        ArrayList<PieceFX> FXPieces = new ArrayList<PieceFX>();
        
        Player playerRED = new Player(7, 12, 1, "RED");
        Computer playerBLK = new Computer(7, 12, 2, "BLK");

        // create new board and print
        Board newBoard = new Board(playerRED.playerPieces, playerRED.getTeam(), playerBLK.playerPieces, playerBLK.getTeam());
        
//         for (int i = 0; i < newBoard.getBoard().size(); i++) {
//             SpikeFX spike = new SpikeFX(newBoard.getBoard().get(i));
//             Group g = new Group(spike.getTriangle());
//             grid.getChildren().add(g);
//         }

        // create spikes
        for (int c = 0; c < 12; c++) {
            SpikeFX spike = new SpikeFX(newBoard.getBoard().get(c));
            //System.out.println(spike.getY());
            grid.add(spike.getTriangle(), c, 1);
        }
        for (int c = 0; c < 12; c++) {
            SpikeFX spike = new SpikeFX(newBoard.getBoard().get(c + 12));
            //System.out.println(spike.getY());
            grid.add(spike.getTriangle(), c, 2);
        }

        //add spike padding
        //grid.setHgap(10);
        grid.setVgap(100);

        // create pieces
        for (int p = 0; p < playerRED.playerPieces.size(); p++) {
            PieceFX piece = new PieceFX(playerRED.playerPieces.get(p), newBoard);
            grid.add(piece.getCircle(), playerRED.playerPieces.get(p).getX()-1, playerRED.playerPieces.get(p).getY());
            grid.setHalignment(piece.getCircle(), HPos.CENTER);
            FXPieces.add(piece);
            //Spike s = newBoard.getBoard().get(playerRED.playerPieces.get(p).getBoardLocation());
            //piece.move(s.getPiecesOnSpike().size());
        }

        for (int p = 0; p < playerBLK.playerPieces.size(); p++) {
            PieceFX piece = new PieceFX(playerBLK.playerPieces.get(p), newBoard);
            grid.add(piece.getCircle(), playerBLK.playerPieces.get(p).getX()-1, playerBLK.playerPieces.get(p).getY());
            grid.setHalignment(piece.getCircle(), HPos.CENTER);
            FXPieces.add(piece);
            //Spike s = newBoard.getBoard().get(playerRED.playerPieces.get(p).getBoardLocation());
            //piece.move(s.getPiecesOnSpike().size());
        }

        // aligment here
        for (int s = 0; s < newBoard.getBoard().size(); s++) {
            for (int p = 0; p < newBoard.getBoard().get(s).getPiecesOnSpike().size(); p++) {
                Piece current = newBoard.getBoard().get(s).getPiecesOnSpike().get(p);
                System.out.println(newBoard.getBoard().get(s).getSpikeID() + " : " + newBoard.getBoard().get(s).getPiecesOnSpike());
                PieceFX currentFX = getFXPieceAtID(FXPieces, current.getColor(), current.getID());
                System.out.println(currentFX.getPiece() + "\n\n");
                if (newBoard.getBoard().get(s).getSpikeID() >= 13) {
                    if (p == 0) {
                        currentFX.getCircle().setTranslateY(-70.0);
                    } else if (p == 1) {
                        currentFX.getCircle().setTranslateY(-40.0);
                    } else if (p == 2) {
                        currentFX.getCircle().setTranslateY(-10.0);
                    } else if (p == 3) {
                        currentFX.getCircle().setTranslateY(20.0);
                    } else if (p == 4) {
                        currentFX.getCircle().setTranslateY(50.0);
                    }
                } else {
                    if (p == 0) {
                        currentFX.getCircle().setTranslateY(70.0);
                    } else if (p == 1) {
                        currentFX.getCircle().setTranslateY(40.0);
                    } else if (p == 2) {
                        currentFX.getCircle().setTranslateY(10.0);
                    } else if (p == 3) {
                        currentFX.getCircle().setTranslateY(-20.0);
                    } else if (p == 4) {
                        currentFX.getCircle().setTranslateY(-50.0);
                    }
                }
            }
        }


        // add line to create right column
        Line rightColumnLine = new Line(965, 0, 965, windowHeight);
        rightColumnLine.setStroke(Color.BLACK);
        rightColumnLine.setStrokeWidth(8);

        Group group = new Group();

        group.getChildren().add(grid);
        group.getChildren().add(rightColumnLine);

        //Testing SpikeFX
        
        //Spike testSpike = new Spike(2,2,3);
        
        
        //SpikeFX testSpikeFx = new SpikeFX(testSpike);
        //Group g = new Group(testSpikeFx.getTriangle());
        //grid.getChildren().add(g);


        Scene scene = new Scene(group,windowWidth,windowHeight);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public PieceFX getFXPieceAtID(ArrayList<PieceFX> FXPieces, String color, int pieceID) {
        for(int p = 0; p < FXPieces.size(); p++){
            if (FXPieces.get(p).getPiece().getID() == pieceID && FXPieces.get(p).getPiece().getColor().equals(color)) {
                return FXPieces.get(p);
            }
        }
        return null;
    }
}
