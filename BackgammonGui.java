import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.geometry.HPos;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javax.swing.JOptionPane;
import java.awt.*;
import java.util.ArrayList;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.FontPosture;


public class BackgammonGui extends Application {
    //private BorderPane borderPane;
    private GridPane grid;
    private ArrayList<PieceFX> FXPieces;
    private Board board;
    private Button exit, rollDice, leftDie, rightDie, skipTurn;
    private int lDie, rDie;
    private boolean lClicked, rClicked;
    private int playerMovesLeft;
    private int windowWidth, windowHeight;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Backgammon");
        grid = new GridPane();
        windowHeight = 665;
        windowWidth = 1100;

        // initializing clicks
        lClicked = false;
        rClicked = false;

        FXPieces = new ArrayList<PieceFX>();

        // create players
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

        SpikeFX blotSpike1 = new SpikeFX(board.getSpike(27));
        grid.add(blotSpike1, 13, 1);
        SpikeFX blotSpike2 = new SpikeFX(board.getSpike(27));
        grid.add(blotSpike2, 13, 2);

        SpikeFX homeSpikeTop = new SpikeFX(board.getSpike(25));
        grid.add(homeSpikeTop, 10, 0);
        SpikeFX homeSpikeBottom = new SpikeFX(board.getSpike(26));
        grid.add(homeSpikeBottom, 10, 3);

        grid.setVgap(75);

        // create pieces
        for (int p = 0; p < 15; p++) {
            // Red Pieces
            PieceFX pieceRED = new PieceFX(playerRED.playerPieces.get(p), board);
            grid.add(pieceRED, playerRED.playerPieces.get(p).getX() - 1, playerRED.playerPieces.get(p).getY());
            grid.setHalignment(pieceRED, HPos.CENTER);
            FXPieces.add(pieceRED);

            // Black Pieces
            PieceFX pieceBLK = new PieceFX(playerBLK.playerPieces.get(p), board);
            grid.add(pieceBLK, playerBLK.playerPieces.get(p).getX() - 1, playerBLK.playerPieces.get(p).getY());
            grid.setHalignment(pieceBLK, HPos.CENTER);
            FXPieces.add(pieceBLK);
        }

        for (int i = 0; i < grid.getChildren().size(); ++i) {
            grid.getChildren().get(i).setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    alignPieces(board, playerRED, playerBLK, false);

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
                                if (playerMovesLeft == 0) {
                                    errorRoll();
                                } else if (lClicked) {
                                    errorDieUsed();
                                    c.setSelected(false);
                                } else if (c.getSelected()) {
                                    // handle if player can make move
                                    if (playerRED.makeMove(board, playerRED, playerBLK, c.getPiece(), lDie, true)) {
                                        if (lDie != rDie) {
                                            lClicked = true;
                                        }
                                        alignPieces(board, playerRED, playerBLK, true);
                                        c.setSelected(false);
                                        playerMovesLeft--;
                                        if (playerMovesLeft == 0) {
                                            playerBLK.playComp(board.roll(), board, playerBLK, playerRED);
                                            alignPieces(board, playerRED, playerBLK, true);
                                        }
                                    }
                                }
                                c.setFill(Color.RED);
                            }
                        });

                        // selecting right die
                        rightDie.setOnAction(new EventHandler<ActionEvent>() {
                            // same as left die, except it handles everything for the right die
                            public void handle(ActionEvent e) {
                                if (playerMovesLeft == 0) {
                                    errorRoll();
                                } else if (rClicked) {
                                    errorDieUsed();
                                    c.setSelected(false);
                                } else if (c.getSelected()) {
                                    // handle if player can make move
                                    if (playerRED.makeMove(board, playerRED, playerBLK, c.getPiece(), rDie, true)) {
                                        if (lDie != rDie) {
                                            rClicked = true;
                                        }
                                        alignPieces(board, playerRED, playerBLK, true);
                                        c.setSelected(false);
                                        playerMovesLeft--;
                                        if (playerMovesLeft == 0) {
                                            playerBLK.playComp(board.roll(), board, playerBLK, playerRED);
                                            alignPieces(board, playerRED, playerBLK, true);
                                        }
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
        alignPieces(board, playerRED, playerBLK, false);

        Group group = new Group();


        // creating & positioning rolldice button
        skipTurn = new Button("Skip Turn");
        skipTurn.setLayoutX(windowWidth - 102);
        skipTurn.setLayoutY(335);

        skipTurn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                boolean validTurnExists = false;

                for (Piece p : playerRED.playerPieces) {
                    if (!lClicked) {
                        validTurnExists = playerRED.makeMove(board, playerRED, playerBLK, p, lDie, false);
                    }
                    if (!validTurnExists && !rClicked) {
                        validTurnExists = playerRED.makeMove(board, playerRED, playerBLK, p, rDie, false);
                    }
                    if (validTurnExists) {
                        break;
                    }
                }

                if (!validTurnExists && playerMovesLeft > 0) {
                    lClicked = true;
                    rClicked = true;
                    playerMovesLeft = 0;
                    playerBLK.playComp(board.roll(), board, playerBLK, playerRED);
                    alignPieces(board, playerRED, playerBLK, true);
                } else {
                    errorValidMove();
                }


            }
        });

        // creating & positioning rolldice button
        rollDice = new Button("Roll Dice");
        rollDice.setLayoutX(windowWidth - 100);
        rollDice.setLayoutY(300);

        // initialize rightDie
        rightDie = new Button("  ");
        rightDie.setLayoutX(windowWidth - 60);
        rightDie.setLayoutY(265);

        // initialize leftDie
        leftDie = new Button("  ");
        leftDie.setLayoutX(windowWidth - 100);
        leftDie.setLayoutY(265);

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

                    // handling leftDie pressed BEFORE piece selected
                    leftDie.setOnAction(new EventHandler<ActionEvent>() {
                        public void handle(ActionEvent e) {
                            errorSelectPiece();
                        }
                    });

                    // creating & positioning right die
                    rightDie.setText(Integer.toString(rDie));

                    // handling rightDie pressed BEFORE piece selected
                    rightDie.setOnAction(new EventHandler<ActionEvent>() {
                        public void handle(ActionEvent e) {
                            errorSelectPiece();
                        }
                    });

                    if (lDie == rDie) {
                        playerMovesLeft = 4;
                    } else {
                        playerMovesLeft = 2;
                    }
                } else {
                    errorAlreadyRolled();
                }
            }
        });

        // handling leftDie pressed BEFORE piece selected
        leftDie.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                errorRoll();
            }
        });

        // handling rightDie pressed BEFORE piece selected
        rightDie.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                errorRoll();
            }
        });

        // creating & positioning exit button
        exit = new Button("Exit Game");
        exit.setLayoutX(windowWidth - 105);
        exit.setLayoutY(600);

        // handling exit event
        exit.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                Platform.exit();
            }
        });

        // creating text
        Text blotsTitleComp = new Text(windowWidth - 120, 110, "Computer Blots");
        blotsTitleComp.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 12));

        Text blotsTitlePlayer = new Text(windowWidth - 105, 565, "Player Blots");
        blotsTitlePlayer.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 12));

        Text computerHomeText = new Text(120, windowHeight - 30, "Computer Home:");
        computerHomeText.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 60));

        Text playerHomeText = new Text(237, 75, "Player Home:");
        playerHomeText.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 60));


        // creating lines
        Line rightColumnLine = new Line(965, 0, 965, windowHeight);
        rightColumnLine.setStroke(Color.BLACK);
        rightColumnLine.setStrokeWidth(8);

        Line topLine = new Line(0, 110, 965, 110);
        topLine.setStroke(Color.BLACK);
        topLine.setStrokeWidth(8);

        Line bottomLine = new Line(0, windowHeight - 110, 965, windowHeight - 110);
        bottomLine.setStroke(Color.BLACK);
        bottomLine.setStrokeWidth(8);

        // add items to group
        group.getChildren().add(grid);
        group.getChildren().add(blotsTitleComp);
        group.getChildren().add(blotsTitlePlayer);
        group.getChildren().add(playerHomeText);
        group.getChildren().add(computerHomeText);
        group.getChildren().add(rightColumnLine);
        group.getChildren().add(topLine);
        group.getChildren().add(bottomLine);
        group.getChildren().add(skipTurn);
        group.getChildren().add(rollDice);
        group.getChildren().add(leftDie);
        group.getChildren().add(rightDie);
        group.getChildren().add(exit);

        Scene scene = new Scene(group, windowWidth, windowHeight, Color.rgb(235, 217, 198));
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public PieceFX getFXPieceAtID(ArrayList<PieceFX> FXPieces, String color, int pieceID) {
        for (int p = 0; p < FXPieces.size(); p++) {
            if (FXPieces.get(p).getPiece().getID() == pieceID && FXPieces.get(p).getPiece().getColor().equals(color)) {
                return FXPieces.get(p);
            }
        }
        return null;
    }

    public void drawBoard(Board b) {
        this.grid.getChildren().clear();

        for (int r = 0; r < 12; r++) {
            for (int c = 1; c < 3; c++) {
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

        SpikeFX blotSpike1 = new SpikeFX(board.getSpike(27));
        grid.add(blotSpike1, 13, 1);

        SpikeFX blotSpike2 = new SpikeFX(board.getSpike(27));
        grid.add(blotSpike2, 13, 2);

        SpikeFX homeSpikeTop = new SpikeFX(board.getSpike(25));
        grid.add(homeSpikeTop, 10, 0);

        SpikeFX homeSpikeBottom = new SpikeFX(board.getSpike(26));
        grid.add(homeSpikeBottom, 10, 3);

        grid.setVgap(75);

        for (int p = 0; p < FXPieces.size(); p++) {

            if (FXPieces.get(p).getPiece().getBlot()) {
                FXPieces.get(p).setBlot(true);
            } else {
                FXPieces.get(p).setBlot(false);
            }

            if (FXPieces.get(p).getBlot()) {
                if (FXPieces.get(p).getPiece().getColor().equals("RED")) {
                    grid.add(FXPieces.get(p), 13, 2);
                } else {
                    grid.add(FXPieces.get(p), 13, 1);
                }
            } else if (FXPieces.get(p).getPiece().getX() == 0) {
                if (FXPieces.get(p).getPiece().getColor().equals("RED")) {
                    grid.add(FXPieces.get(p), 10, 0);
                } else {
                    grid.add(FXPieces.get(p), 10, 3);
                }
            } else {
                if (FXPieces.get(p).getPiece().getColor().equals("RED")) {
                    FXPieces.get(p).setFill(Color.RED);
                } else {
                    FXPieces.get(p).setFill(Color.BLACK);
                }
                FXPieces.get(p).setSelected(false);
                grid.add(FXPieces.get(p), FXPieces.get(p).getPiece().getX() - 1, FXPieces.get(p).getPiece().getY());
            }
        }
    }

    // aligns pieces in proper spots on spikes
    public void alignPieces(Board b, Player player, Computer computer, boolean move) {
        for (int s = 0; s < b.getBoard().size(); s++) {
            for (int p = 0; p < b.getBoard().get(s).getPiecesOnSpike().size(); p++) {
                Piece current = b.getBoard().get(s).getPiecesOnSpike().get(p);
                PieceFX currentFX = getFXPieceAtID(FXPieces, current.getColor(), current.getID());
                if (b.getBoard().get(s).getSpikeID() == 27) {
                    if (current.getColor().equals("RED")) {
                        currentFX.setTranslateY(70.0);
                    } else {
                        currentFX.setTranslateY(-70.0);
                    }
                } else if (b.getBoard().get(s).getSpikeID() == 26 || b.getBoard().get(s).getSpikeID() == 25) {
                    if (current.getColor().equals("RED")) {
                        currentFX.setTranslateY(30.0);
                    } else {
                        currentFX.setTranslateY(-30.0);
                    }
                } else if (b.getBoard().get(s).getSpikeID() >= 13) {
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

        // check for a winner
        if (computer.hasWon() || player.hasWon()) {
            String message;
            if (computer.hasWon()) {
                message = "The computer has won! Game over.";
            } else {
                message = "You have won! Game over.";
            }
            ShowMessage(message);
        }

    }

    /* ------------------------------------------------------ Pop-Up Messages/Errors ------------------------------------------------------*/

    //Displays message in jOptionPane
    private void ShowMessage(String message) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Object[] option = {"OKAY"};
                JOptionPane.showOptionDialog(null, message,
                        "Game Over", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
                        null, option, option[0]);
                System.exit(0);
            }
        });
    }

    private void errorRoll() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Object[] option = {"OKAY"};
                JOptionPane.showOptionDialog(null, "Please roll dice.",
                        "Roll Dice", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
                        null, option, option[0]);
            }
        });
    }

    private void errorAlreadyRolled() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Object[] option = {"OKAY"};
                JOptionPane.showOptionDialog(null, "You can only roll dice once a turn.\nPlease play roll.",
                        "Already Rolled", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
                        null, option, option[0]);
            }
        });
    }


    private void errorDieUsed() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Object[] option = {"OKAY"};
                JOptionPane.showOptionDialog(null, "You have already used this die.\nPlease use other roll.\nIF other die unusable, Skip Turn.",
                        "Die Used", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
                        null, option, option[0]);
            }
        });
    }

    private void errorSelectPiece() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Object[] option = {"OKAY"};
                JOptionPane.showOptionDialog(null, "Select a piece before a die.",
                        "Select Piece", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
                        null, option, option[0]);
            }
        });
    }

    private void errorValidMove() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Object[] option = {"OKAY"};
                JOptionPane.showOptionDialog(null, "You still have a valid move. Cannot Skip Turn.",
                        "Valid Move", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
                        null, option, option[0]);
            }
        });
    }

}