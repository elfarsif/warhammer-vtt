package org.example;

import org.example.model.Board;
import org.example.model.MeasuringTape;
import org.example.model.Segment;
import org.example.model.Model;
import org.example.model.Position;
import org.example.network.GameClient;
import org.example.network.GameServer;
import java.net.URI;
import java.util.List;
import org.example.model.datasheet.DataSheet;
import org.example.model.datasheet.Movement;
import org.example.model.datasheet.Picture;
import org.example.model.datasheet.Stats;
import org.example.model.datasheet.Toughness;
import org.example.view.BoardView;
import org.example.view.PictureView;
import org.example.view.Scale;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class App extends Application {

    private GameServer gameServer;
    private GameClient gameClient;
    private Board board;
    private StackPane boardContainer;
    private Scale scale;

    private Parent createContent(){
        BorderPane root = new BorderPane();

        // toolbar with Host/Join buttons
        VBox vbox = new VBox();
        HBox toolbar = new HBox(10);

        Button hostBtn = new Button("Host Game");
        hostBtn.setOnAction(e -> hostGame());

        Button joinBtn = new Button("Join Game");
        joinBtn.setOnAction(e -> joinGame());

        toolbar.getChildren().addAll(hostBtn, joinBtn);
        vbox.getChildren().add(toolbar);
        root.setTop(vbox);

        // board setup
        Model model1 = new Model("model-1", 1.5, 1.5, new Position(0, 0), new MeasuringTape(new Segment()));
        Model model2 = new Model("model-2", 1.5, 1.5, new Position(5, 5), new MeasuringTape(new Segment()));
        board = new Board(44, 37, List.of(model1, model2));
        scale = new Scale(20);

        boardContainer = new StackPane();
        boardContainer.setBackground(Background.fill(Color.LIGHTGREEN));
        boardContainer.setMinWidth(1000);

        // left panel
        VBox leftpanel = new VBox();
        leftpanel.setMinWidth(50);
        leftpanel.setBackground(Background.fill(Color.LIGHTCYAN));

        // right panel
        VBox rightpanel = new VBox();
        rightpanel.setMinWidth(200);
        rightpanel.setBackground(Background.fill(Color.LIGHTCYAN));

        Picture picture = new Picture("/custodian_guard.jpg");
        Stats stats = new Stats(new Movement(6), new Toughness(7));
        DataSheet dataSheet = new DataSheet(picture, stats);

        PictureView pictureView = new PictureView(
                picture, rightpanel,
                new ImageView(new Image(getClass().getResourceAsStream(picture.getImagePath())))
        );

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.add(new Label("M"), 0, 0);
        gridPane.add(new Label("T"), 1, 0);
        gridPane.add(new Label("6"), 0, 1);
        gridPane.add(new Label("7"), 1, 1);
        gridPane.prefWidthProperty().bind(rightpanel.widthProperty());

        Accordion accordion = new Accordion();
        accordion.getPanes().addAll(new TitledPane("Meele", new VBox()));
        rightpanel.getChildren().addAll(pictureView.getImageView(), gridPane, accordion);

        SplitPane splitPane = new SplitPane(leftpanel, boardContainer, rightpanel);
        root.setCenter(splitPane);

        return root;
    }

    private void hostGame() {
        gameServer = new GameServer(8887, board);
        gameServer.start();

        try {
            gameClient = new GameClient(new URI("ws://localhost:8887"), board);
            gameClient.connect();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        buildBoardView();
    }

    private void joinGame() {
        TextInputDialog dialog = new TextInputDialog("localhost:8887");
        dialog.setHeaderText("Enter host address (host:port):");
        dialog.showAndWait().ifPresent(address -> {
            try {
                gameClient = new GameClient(new URI("ws://" + address), board);
                gameClient.connect();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            buildBoardView();
        });
    }

    private void buildBoardView() {
        boardContainer.getChildren().clear();
        BoardView boardView = new BoardView(board, scale, gameClient);
        boardContainer.getChildren().add(boardView.getPane());
    }

    @Override
    public void start(Stage stage) {
        Scene scene = new Scene(this.createContent(), 640, 480);
        stage.setMaximized(true);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void stop() {
        try {
            if (gameClient != null) gameClient.close();
            if (gameServer != null) gameServer.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
