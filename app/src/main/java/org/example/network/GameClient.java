package org.example.network;

import com.google.gson.Gson;
import org.example.model.Board;
import org.example.model.Model;
import org.example.model.Position;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import javafx.application.Platform;
import java.net.URI;

public class GameClient extends WebSocketClient {
    private final Board board;
    private final Gson gson = new Gson();

    public GameClient(URI serverUri, Board board) {
        super(serverUri);
        this.board = board;
    }

    public void sendMove(MoveCommand cmd) {
        System.out.println("Game Client Send move:" +gson.toJson(cmd));
        send(gson.toJson(cmd));
    }

    @Override
    public void onOpen(ServerHandshake handshake) {
        System.out.println("Connected to server");
    }

    @Override
    public void onMessage(String message) {
        MoveCommand cmd = gson.fromJson(message, MoveCommand.class);
        Platform.runLater(() -> {
            Model model = board.findById(cmd.getModelId());
            if (model != null) {
                model.moveTo(new Position(cmd.getX(), cmd.getY()));
            }
        });
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        System.out.println("Disconnected from server");
    }

    @Override
    public void onError(Exception ex) {
        ex.printStackTrace();
    }
}
