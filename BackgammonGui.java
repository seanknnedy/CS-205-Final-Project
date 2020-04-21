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
import javafx.scene.shape.Polygon;

public class BackgammonGui extends Application {
    private GridPane grid;
    private ArrayList<PieceFX> FXPieces;
    private Board board;
    private Button exit, rollDice, leftDie, rightDie;
    private int lDie, rDie;
    private boolean lClicked, rClicked;

    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Backgammon");
        grid = new GridPane();

        int windowHeight = 660;
        int windowWidth = 1100;

        // initializing clicks
        lClicked = false;
        rClicked = false;

        FXPieces = new ArrayList<PieceFX>();

        Player playerRED = new Player(7, 12, 1, "RED");
        Player playerBLK = new Player(7, 12, 2, "BLK");

        // create new board and print
        board = new Board(playerRED.playerPieces, playerRED.getTeam(), playerBLK.playerPieces, playerBLK.getTeam());

        // create spikes
        for (int c = 0; c < 12; c++) {
            SpikeFX spike = new SpikeFX(board.getBoard().get(c));
            grid.add(spike, c, 1);
        }
        for (int c = 0; c < 12; c++) {
            SpikeFX spike = new SpikeFX(board.getBoard().get(c + 12));
            grid.add(spike, c, 2);
        }

        grid.setVgap(100);

        // create pieces
        for (int p = 0; p < 15; p++) {
            // Red Pieces
            PieceFX pieceRED = new PieceFX(playerRED.playerPieces.get(p), board);
            grid.add(pieceRED, playerRED.playerPieces.get(p).getX()-1, playerRED.playerPieces.get(p).getY());
            grid.setHalignment(pieceRED, HPos.CENTER);
            FXPieces.add(pieceRED);

            // Black Pieces
            PieceFX pieceBLK = new PieceFX(playerBLK.playerPieces.get(p), board);
            grid.add(pieceBLK, playerBLK.playerPieces.get(p).getX()-1, playerBLK.playerPieces.get(p).getY());
            grid.setHalignment(pieceBLK, HPos.CENTER);
            FXPieces.add(pieceBLK);
        }

        for (int i = 0; i < grid.getChildren().size(); ++i) {
            grid.getChildren().get(i).setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {

                    PieceFX c = (PieceFX) event.getSource();

                    // set color of piece to yellow to look selected
                    if (c.getFill() == Color.YELLOW) {
                        if (c.getPiece().getColor().equals("RED")) {
                            c.setSelected(false);
                            c.setFill(Color.RED);
                        } else {
                            c.setSelected(false);
                            c.setFill(Color.BLACK);
                        }
                    } else {
                        c.setFill(Color.YELLOW);
                    }

                    // handle if piece is red
                    if (c.getPiece().getColor().equals("RED")) {

                        // selecting left die
                        leftDie.setOnAction(new EventHandler<ActionEvent>() {
                            public void handle(ActionEvent e) {
                                Object[] option = {"OKAY"};
                                // handle if already selected
                                if(lClicked) {
                                    errorDiePlayed();
                                    c.setSelected(false);
                                    c.setFill(Color.RED);
                                    // handle if the player has yet to roll
                                } else if(leftDie.getText().toString().equals("  ")) {
                                    errorRollDice();
                                    c.setSelected(false);
                                    c.setFill(Color.RED);
                                    // handle actual movement
                                } else {
                                    // handle if player can make move
                                    if(playerRED.makeMove(board, playerRED, playerBLK, c.getPiece(), lDie)) {
                                        lClicked = true;
                                        alignPieces(board, true);
                                        c.setSelected(false);
                                        c.setFill(Color.RED);
                                        // handle if they can't
                                    } else {
                                        errorNoMove();
                                    }
                                }
                            }
                        });

                        // selecting right die
                        rightDie.setOnAction(new EventHandler<ActionEvent>() {
                            // same as left die, except it handles everything for the right die
                            public void handle(ActionEvent e) {
                                Object[] option = {"OKAY"};
                                if(rClicked) {
                                    errorDiePlayed();
                                    c.setSelected(false);
                                    c.setFill(Color.RED);
                                } else if(rightDie.getText().toString().equals("  ")) {
                                    errorRollDice();
                                    c.setSelected(false);
                                    c.setFill(Color.RED);
                                } else {
                                    if(playerRED.makeMove(board, playerRED, playerBLK, c.getPiece(), rDie)){
                                        rClicked = true;
                                        alignPieces(board, true);
                                        c.setSelected(false);
                                        c.setFill(Color.RED);
                                    } else {
                                        errorNoMove();
                                    }
                                }
                            }
                        });
                        // handles if piece is black (same as red piece)
                        // TODO: I feel like this can be simplified once we properly establish turns
                    } else {
                        leftDie.setOnAction(new EventHandler<ActionEvent>() {
                            public void handle(ActionEvent e) {
                                Object[] option = {"OKAY"};
                                if(lClicked) {
                                    errorDiePlayed();
                                    c.setSelected(false);
                                    c.setFill(Color.BLACK);
                                } else if(leftDie.getText().toString().equals("  ")) {
                                    errorRollDice();
                                    c.setSelected(false);
                                    c.setFill(Color.BLACK);
                                } else {
                                    if(playerBLK.makeMove(board, playerBLK, playerRED, c.getPiece(), lDie)){
                                        lClicked = true;
                                        alignPieces(board, true);
                                        c.setSelected(false);
                                        c.setFill(Color.BLACK);
                                    } else {
                                        errorNoMove();
                                    }
                                }
                            }
                        });

                        rightDie.setOnAction(new EventHandler<ActionEvent>() {
                            public void handle(ActionEvent e) {
                                Object[] option = {"OKAY"};
                                if(rClicked) {
                                    errorDiePlayed();
                                    c.setSelected(false);
                                    c.setFill(Color.BLACK);
                                } else if(rightDie.getText().toString().equals("  ")) {
                                    errorRollDice();
                                    c.setSelected(false);
                                    c.setFill(Color.BLACK);
                                } else {
                                    if(playerBLK.makeMove(board, playerBLK, playerRED, c.getPiece(), rDie)){
                                        rClicked = true;
                                        alignPieces(board, true);
                                        c.setSelected(false);
                                        c.setFill(Color.BLACK);
                                    } else {
                                        errorNoMove();
                                    }
                                }
                            }
                        });
                    }
                    lClicked = false;
                    rClicked = false;   // this needs to be done in the turn logic
                }
            });
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
                if(leftDie.getText().toString().equals("  ")) {
                    errorRollDice();
                } else {
                    errorPickPiece();
                }
            }
        });

        // handling rightDie pressed BEFORE piece selected
        rightDie.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                if(rightDie.getText().toString().equals("  ")) {
                    errorRollDice();
                } else {
                    errorPickPiece();
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

    public void drawBoard(Board b) {
        this.grid.getChildren().clear();

        for (int r = 0; r < 12; r++) {
            for (int c = 1; c < 3; c++){
                // TODO: finish this using Boggle outline

                if (c == 2) {
                    SpikeFX spike = new SpikeFX(b.getSpike(12 - r));
                    grid.add(spike, r, c);

                }
                if (c == 1) {
                    SpikeFX spike = new SpikeFX(b.getSpike(r + 13));
                    grid.add(spike, r, c);
                }
            }
        }

        //maybe add vgap?
        grid.setVgap(100);


        for (int p = 0; p < FXPieces.size(); p++) {
            grid.add(FXPieces.get(p), FXPieces.get(p).getX()-1, FXPieces.get(p).getY());
        }
    }

    public void alignPieces(Board b, boolean move) {
        for (int s = 0; s < b.getBoard().size(); s++) {
            for (int p = 0; p < b.getBoard().get(s).getPiecesOnSpike().size(); p++) {
                Piece current = b.getBoard().get(s).getPiecesOnSpike().get(p);
                PieceFX currentFX = getFXPieceAtID(FXPieces, current.getColor(), current.getID());
                if (b.getBoard().get(s).getSpikeID() >= 13) {
                    if (p == 0) {
                        currentFX.setTranslateY(-70.0);
                    } else if (p == 1) {
                        currentFX.setTranslateY(-40.0);
                    } else if (p == 2) {
                        currentFX.setTranslateY(-10.0);
                    } else if (p == 3) {
                        currentFX.setTranslateY(20.0);
                    } else if (p == 4) {
                        currentFX.setTranslateY(50.0);
                    }
                } else {
                    if (p == 0) {
                        currentFX.setTranslateY(70.0);
                    } else if (p == 1) {
                        currentFX.setTranslateY(40.0);
                    } else if (p == 2) {
                        currentFX.setTranslateY(10.0);
                    } else if (p == 3) {
                        currentFX.setTranslateY(-20.0);
                    } else if (p == 4) {
                        currentFX.setTranslateY(-50.0);
                    }
                }
                if (move) {
                    this.grid.getChildren().remove(currentFX);
                    currentFX.setX(current.getX());
                    currentFX.setY(current.getY());
                    drawBoard(b);
                }
            }
        }
    }
    /* ---------------------------------------------------ERRORS----------------------------------------------------------*/
    public void errorRollDice() {
        Object[] option = {"OKAY"};
        JOptionPane.showOptionDialog(null, "Please roll the dice.\nClick OKAY to continue.",
                "Roll Dice",JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
                null, option, option[0]);
    }

    public void errorPickPiece() {
        Object[] option = {"OKAY"};
        JOptionPane.showOptionDialog(null, "You must select a piece before a die.\nClick OKAY to continue.",
                "Select Piece",JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
                null, option, option[0]);
    }

    public void errorDiePlayed() {
        Object[] option = {"OKAY"};
        JOptionPane.showOptionDialog(null, "This die was already played.\nPlease play other die.\nClick OKAY to continue.",
                "Die Played",JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,null, option, option[0]);
    }

    public void errorNoMove() {
        Object[] option = {"OKAY"};
        JOptionPane.showOptionDialog(null, "This piece is unable to be played with chosen roll.\nClick OKAY to continue.",
                "No Available Move",JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,null, option, option[0]);
    }
}