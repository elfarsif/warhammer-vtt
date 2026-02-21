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
import org.example.loader.DataSheetLoader;
import org.example.model.datasheet.DataSheet;
import org.example.view.BoardView;
import org.example.view.DataSheetView;
import org.example.view.DiceRollerView;
import org.example.view.Scale;
import org.example.model.dice.DicePool;
import org.example.model.dice.DiceResult;
import org.example.model.dice.DiceRoller;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
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
    private DataSheetView dataSheetView;

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
        DataSheet custodianSheet = DataSheetLoader.load("/datasheets/custodian_guard.json");
        DataSheet wardenSheet = DataSheetLoader.load("/datasheets/custodian_warden.json");
        Model model1 = new Model("model-1", 1.5, 1.5, new Position(0, 0), new MeasuringTape(new Segment()), custodianSheet);
        Model model2 = new Model("model-2", 1.5, 1.5, new Position(5, 5), new MeasuringTape(new Segment()), wardenSheet);
        board = new Board(44, 37, List.of(model1, model2));
        scale = new Scale(20);

        boardContainer = new StackPane();
        boardContainer.setBackground(Background.fill(Color.LIGHTGREEN));
        boardContainer.setMinWidth(1000);

        // left panel
        VBox leftpanel = new VBox();
        leftpanel.setMinWidth(50);
        leftpanel.setBackground(Background.fill(Color.LIGHTCYAN));

        DiceRoller diceRoller = new DiceRoller(new DicePool(1), new DiceResult(0,0,0,0,0,0));
        DiceRollerView diceRollerView = new DiceRollerView(diceRoller);
        leftpanel.getChildren().add(diceRollerView.getContent());

        // right panel
        VBox rightpanel = new VBox();
        rightpanel.setMinWidth(200);
        rightpanel.setBackground(Background.fill(Color.LIGHTCYAN));

        dataSheetView = new DataSheetView(rightpanel);
        dataSheetView.onModelSelected(board.models().get(0).dataSheet());
        rightpanel.getChildren().add(dataSheetView.getContent());

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
        BoardView boardView = new BoardView(board, scale, gameClient, dataSheetView);
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
