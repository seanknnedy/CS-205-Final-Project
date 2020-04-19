import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.geometry.HPos;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Button;
import javax.swing.JOptionPane;
import java.util.ArrayList;


public class BackgammonGui extends Application {
    private GridPane grid;
    private ArrayList<PieceFX> FXPieces;
    private Board board;
    private Button exit, rollDice, leftDie, rightDie;
    private int lDie, rDie;
    
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
        FXPieces = new ArrayList<PieceFX>();

        Player playerRED = new Player(7, 12, 1, "RED");
        Computer playerBLK = new Computer(7, 12, 2, "BLK");

        // create new board and print
        board = new Board(playerRED.playerPieces, playerRED.getTeam(), playerBLK.playerPieces, playerBLK.getTeam());

        // create spikes
        for (int c = 0; c < 12; c++) {
            SpikeFX spike = new SpikeFX(board.getBoard().get(c));
            //System.out.println(spike.getY());
            grid.add(spike.getTriangle(), c, 1);
        }
        for (int c = 0; c < 12; c++) {
            SpikeFX spike = new SpikeFX(board.getBoard().get(c + 12));
            //System.out.println(spike.getY());
            grid.add(spike.getTriangle(), c, 2);
        }

        //add spike padding
        grid.setVgap(100);

        // create pieces
        for (int p = 0; p < 15; p++) {
            // Red Pieces
            PieceFX pieceRED = new PieceFX(playerRED.playerPieces.get(p), board);
            pieceRED.setOnMouseClicked(this::handleClick);
            //pieceRED.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, eventHandler);
            grid.add(pieceRED.getCircle(), playerRED.playerPieces.get(p).getX()-1, playerRED.playerPieces.get(p).getY());
            grid.setHalignment(pieceRED.getCircle(), HPos.CENTER);
            FXPieces.add(pieceRED);

            // Black Pieces
            PieceFX pieceBLK = new PieceFX(playerBLK.playerPieces.get(p), board);
            pieceBLK.setOnMouseClicked(this::handleClick);
            //pieceBLK.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, eventHandler);
            grid.add(pieceBLK.getCircle(), playerBLK.playerPieces.get(p).getX()-1, playerBLK.playerPieces.get(p).getY());
            grid.setHalignment(pieceBLK.getCircle(), HPos.CENTER);
            FXPieces.add(pieceBLK);
        }

        // alignment here
        alignPieces(board, false);

        Group group = new Group();
        
        // creating & positioning rolldice button
        rollDice = new Button("Roll Dice");
        rollDice.setLayoutX(windowWidth - 100);
        rollDice.setLayoutY(300);
        
        // initialize leftDie
        leftDie = new Button("  ");
        leftDie.setLayoutX(windowWidth - 100);
        leftDie.setLayoutY(265);
        
        // initialize rightDie
        rightDie = new Button("  ");
        rightDie.setLayoutX(windowWidth - 60);
        rightDie.setLayoutY(265);
        
        // handling rollDice events
        rollDice.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
               ArrayList<Integer> roll = board.roll();
               lDie = roll.get(0);
               rDie = roll.get(1);
            
               // creating & positioning left die
               leftDie.setText(Integer.toString(lDie));
               
               // creating & positioning right die
               rightDie.setText(Integer.toString(rDie));
            }
        });
        
        // handling leftDie pressed BEFORE piece selected
        leftDie.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                Object[] option = {"OKAY"};
                if(leftDie.getText().toString().equals("  ")) {
                     JOptionPane.showOptionDialog(null, "Please roll the dice.\nClick OKAY to continue.", 
                        "Invalid Selection",JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
                        null, option, option[0]);
                } else {
                     JOptionPane.showOptionDialog(null, "You must select a piece before a die.\nClick OKAY to continue.", 
                        "Invalid Selection",JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
                        null, option, option[0]);
                }
            }
        }); 

        // handling rightDie pressed BEFORE piece selected
        rightDie.setOnAction(new EventHandler<ActionEvent>() {
             public void handle(ActionEvent e) {
                Object[] option = {"OKAY"};
                if(rightDie.getText().toString().equals("  ")) {
                     JOptionPane.showOptionDialog(null, "Please roll the dice.\nClick OKAY to continue.", 
                        "Invalid Selection",JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
                        null, option, option[0]);
                } else {
                     JOptionPane.showOptionDialog(null, "You must select a piece before a die.\nClick OKAY to continue.", 
                        "Invalid Selection",JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
                        null, option, option[0]);
                }
            }

        });   

        // creating & positioning exit button
        exit = new Button("Exit Game");
        exit.setLayoutX(windowWidth - 100);
        exit.setLayoutY(600);
        
        // handling exit event
        exit.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
               Platform.exit();
            }
        });
        
        // creating text
        Text blotsTitle = new Text(windowWidth - 120, 20, "Blots: ");
        Text p1Home = new Text(100, windowHeight - 50, "Player 1's Home:");
        Text p2Home = new Text(100, 40, "Player 2's Home:");
        Text dice = new Text(windowWidth - 120, 250, "Player Roll: ");
      
        // add line to create right column
        Line rightColumnLine = new Line(965, 0, 965, windowHeight);
        rightColumnLine.setStroke(Color.BLACK);
        rightColumnLine.setStrokeWidth(8);

        // creating shapes
        Rectangle blotContainer = new Rectangle(windowWidth - 120, 40, 110, 80);
        blotContainer.setFill(Color.LIGHTGRAY);

        group.getChildren().add(grid);
        group.getChildren().add(blotsTitle);
        group.getChildren().add(p1Home);
        group.getChildren().add(p2Home);
        group.getChildren().add(rightColumnLine);
        group.getChildren().add(blotContainer);
        group.getChildren().add(rollDice);
        group.getChildren().add(leftDie);
        group.getChildren().add(rightDie);
        group.getChildren().add(exit);
        group.getChildren().add(dice);

        Scene scene = new Scene(group, windowWidth, windowHeight);

        scene.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("mouse click detected! ");
                playerRED.makeMove(board, playerRED, playerBLK,1, 1);
                System.out.println(playerRED.playerPieces.get(0).getBoardLocation());
                alignPieces(board, true);
                //PieceFX piece = (PieceFX) event.getSource();
                //piece.selectPiece();
            }
        });



        primaryStage.setScene(scene);
        primaryStage.show();
    }


/*
    EventHandler<MouseEvent> eventHandler = new EventHandler<javafx.scene.input.MouseEvent>() {
        @Override
        public void handle(javafx.scene.input.MouseEvent e) {
            System.out.println("Hello World");
            //PieceFX piece = (PieceFX) e.getSource();
            //piece.selectPiece();
            //Circle c = (Circle) e.getSource();
            //c = pieceFX.getCircle();
        }
    };
    public void handleClick(MouseEvent mouseEvent) {
        PieceFX pieceFX = (PieceFX) mouseEvent.getSource();
        pieceFX.selectPiece();
    }
     */


    public void handleClick(MouseEvent e) {
        PieceFX piece = (PieceFX) (e.getSource());
        piece.getCircle().setFill(Color.YELLOW);
        System.out.println("mouse click detected! ");
    }


    public PieceFX getFXPieceAtID(ArrayList<PieceFX> FXPieces, String color, int pieceID) {
        for(int p = 0; p < FXPieces.size(); p++){
            if (FXPieces.get(p).getPiece().getID() == pieceID && FXPieces.get(p).getPiece().getColor().equals(color)) {
                return FXPieces.get(p);
            }
        }
        return null;
    }

    public void drawBoard() {
        this.grid.getChildren().clear();
        for (int r = 0; r < 15; r++) {
            for (int c = 0; c < 2; c++){
                //PieceFX pieceFX = new PieceFX(____.get(r), this.board);
                // TODO: finish this using Boggle outline
            }
        }
    }

    public void alignPieces(Board b, boolean move) {
        for (int s = 0; s < b.getBoard().size(); s++) {
            for (int p = 0; p < b.getBoard().get(s).getPiecesOnSpike().size(); p++) {
                Piece current = b.getBoard().get(s).getPiecesOnSpike().get(p);
                System.out.print("s=" + s + " spike=" + b.getBoard().get(s) + " spike id = " + b.getBoard().get(s).getSpikeID());
                PieceFX currentFX = getFXPieceAtID(FXPieces, current.getColor(), current.getID());
                System.out.println(" current=" + current);
                if (b.getBoard().get(s).getSpikeID() >= 13) {
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
                if (move) {
                    this.grid.getChildren().remove(currentFX);
                    // TODO: I dont know how to add the piece back to the grid
                    //this.grid.add(currentFX.getCircle(), current.getX() - 1, current.getY());
                }
            }
        }
    }
}