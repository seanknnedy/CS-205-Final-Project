import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
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
    //private BorderPane borderPane;
    private GridPane grid;
    private ArrayList<PieceFX> FXPieces;
    private Board board;
    private Button exit, rollDice, leftDie, rightDie;
    private int lDie, rDie;
    private boolean lClicked, rClicked;
    private int playerMovesLeft;
    private int windowWidth, windowHeight;

    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Backgammon");
        grid = new GridPane();
        //borderPane = new BorderPane();
        //borderPane.setStyle("-fx-border-color: black");

        windowHeight = 660;
        windowWidth = 1100;

        // initializing clicks
        lClicked = false;
        rClicked = false;

        FXPieces = new ArrayList<PieceFX>();

        Player playerRED = new Player(7, 12, 1, "RED");
        Computer playerBLK = new Computer(7, 12, 2, "BLK");

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
        SpikeFX blotSpike = new SpikeFX(board.getSpike(27));
        grid.add(blotSpike, 13, 1);

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
                    alignPieces(board, false);

                    PieceFX c = (PieceFX) event.getSource();
                    drawBoard(board);
                    // set color of piece to yellow to look selected
                    if (c.getFill() == Color.YELLOW) {
                        if (c.getPiece().getColor().equals("RED")) {
                            c.setSelected(false);
                            c.setFill(Color.RED);
                        }
                    } else if (c.getFill() == Color.RED) {
                        c.setSelected(true);
                        c.setFill(Color.YELLOW);
                    }

                    // handle if piece is red
                    if (c.getPiece().getColor().equals("RED")) {
                        // selecting left die
                        leftDie.setOnAction(new EventHandler<ActionEvent>() {
                            public void handle(ActionEvent e) {
                                // handle if already selected
                                if (lClicked) {
                                    System.out.println("Already Used that Dice");
                                    c.setSelected(false);
                                } else if (playerMovesLeft == 0) {
                                    System.out.println("Roll Again");
                                } else if (c.getSelected()){
                                    // handle if player can make move
                                    if (playerRED.makeMove(board, playerRED, playerBLK, c.getPiece(), lDie)) {
                                        if (lDie != rDie) {
                                            lClicked = true;
                                        }
                                        alignPieces(board, true);
                                        c.setSelected(false);
                                        playerMovesLeft--;
                                        if (playerMovesLeft == 0) {
                                            playerBLK.playComp(board.roll(), board, playerBLK, playerRED);
                                            alignPieces(board, true);
                                            for(int p = 0; p < playerBLK.playerPieces.size(); p++) {
                                                int loc = playerBLK.playerPieces.get(p).getBoardLocation();
                                                int id = playerBLK.playerPieces.get(p).getID();
                                                int x = playerBLK.playerPieces.get(p).getX();
                                                int y = playerBLK.playerPieces.get(p).getY();
                                                System.out.println("Piece id: " + id + " at location: " + loc + " (x,y): " + x + ", "+ y);
                                            }
                                        }
                                    } else {
                                        System.out.println("Invalid Move.");
                                    }
                                }
                                c.setFill(Color.RED);
                            }
                        });

                        // selecting right die
                        rightDie.setOnAction(new EventHandler<ActionEvent>() {
                            // same as left die, except it handles everything for the right die
                            public void handle(ActionEvent e) {
                                if (rClicked) {
                                    System.out.println("Already Used that Dice");
                                    c.setSelected(false);
                                } else if (playerMovesLeft == 0) {
                                    System.out.println("Roll Again");
                                } else if (c.getSelected()){
                                    // handle if player can make move
                                    if (playerRED.makeMove(board, playerRED, playerBLK, c.getPiece(), rDie)) {
                                        if (lDie != rDie) {
                                            rClicked = true;
                                        }
                                        alignPieces(board, true);
                                        c.setSelected(false);
                                        playerMovesLeft--;
                                        if (playerMovesLeft == 0) {
                                            playerBLK.playComp(board.roll(), board, playerBLK, playerRED);
                                            alignPieces(board, true);
                                            System.out.println("Computer Roll: " + lDie + rDie);
                                            for(int p = 0; p < playerBLK.playerPieces.size(); p++) {
                                                int loc = playerBLK.playerPieces.get(p).getBoardLocation();
                                                int id = playerBLK.playerPieces.get(p).getID();
                                                int x = playerBLK.playerPieces.get(p).getX();
                                                int y = playerBLK.playerPieces.get(p).getY();
                                                System.out.println("Piece id: " + id + " at location: " + loc + " (x,y): " + x + ", "+ y);
                                            }
                                        }
                                    } else {
                                        System.out.println("Invalid Move.");
                                    }
                                }
                                c.setFill(Color.RED);
                            }
                        });
                    }
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
                if (playerMovesLeft == 0) {
                    ArrayList<Integer> roll = board.roll();

                    lDie = roll.get(0);
                    rDie = roll.get(1);

                    lClicked = false;
                    rClicked = false;

                    // creating & positioning left die
                    leftDie.setText(Integer.toString(lDie));

                    // creating & positioning right die
                    rightDie.setText(Integer.toString(rDie));

                    if (lDie == rDie) {
                        playerMovesLeft = 4;
                    } else {
                        playerMovesLeft = 2;
                    }
                } else {
                    System.out.println("Already Rolled");
                }
            }
        });


        // handling leftDie pressed BEFORE piece selected
        leftDie.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                System.out.println("Select Piece before die");
            }
        });

        // handling rightDie pressed BEFORE piece selected
        rightDie.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                System.out.println("Select Piece before die");
            }
        });



        // creating & positioning exit button
        exit = new Button("Exit Game");
        exit.setLayoutX(windowWidth - 100);
        exit.setLayoutY(600);

        // TODO: Skip turn button?

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

        // add line to create right column
        Line rightColumnLine = new Line(965, 0, 965, windowHeight);
        rightColumnLine.setStroke(Color.BLACK);
        rightColumnLine.setStrokeWidth(8);

        // creating shapes
        Rectangle blotContainer = new Rectangle(windowWidth - 120, 40, 110, 80);
        blotContainer.setFill(Color.LIGHTGRAY);


        //borderPane.setCenter(grid);
        group.getChildren().add(grid);

        group.getChildren().add(blotsTitle);
        group.getChildren().add(p1Home);
        //borderPane.setBottom(p2Home);
        //borderPane.setTop(p1Home);

        group.getChildren().add(p2Home);
        group.getChildren().add(rightColumnLine);
        //group.getChildren().add(blotContainer);
        group.getChildren().add(rollDice);
        group.getChildren().add(leftDie);
        group.getChildren().add(rightDie);
        group.getChildren().add(exit);

        //group.getChildren().add(borderPane);

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
        SpikeFX blotSpike = new SpikeFX(board.getSpike(27));
        grid.add(blotSpike, 13, 1);

        //maybe add vgap?
        grid.setVgap(100);


        for (int p = 0; p < FXPieces.size(); p++) {
            if (FXPieces.get(p).getPiece().getX() == 0) {
                // TODO: When piece is beared off, X = 0 and then it goes out of bounds... FIX
            } else if (FXPieces.get(p).getPiece().getBlot()){
                grid.add(FXPieces.get(p), 13, 1);
            } else {
                if (FXPieces.get(p).getPiece().getColor().equals("RED")) {
                    FXPieces.get(p).setFill(Color.RED);
                } else {
                    FXPieces.get(p).setFill(Color.BLACK);
                }
                FXPieces.get(p).setSelected(false);
                grid.add(FXPieces.get(p), FXPieces.get(p).getX() - 1, FXPieces.get(p).getY());
            }
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

    /* ---------------------------------------------------ERRORS----------------------------------------------------------
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

     */
}