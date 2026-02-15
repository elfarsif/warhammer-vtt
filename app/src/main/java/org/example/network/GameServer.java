package org.example.network;

import com.google.gson.Gson;
import org.example.model.Board;
import org.example.model.Model;
import org.example.model.Position;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import java.net.InetSocketAddress;

public class GameServer extends WebSocketServer {
    private final Board board;
    private final Gson gson = new Gson();

    public GameServer(int port, Board board) {
        super(new InetSocketAddress(port));
        this.board = board;
    }

    @Override
    public void onOpen(WebSocket conn, ClientHandshake handshake) {
        System.out.println("Server Connected Player: " + conn.getRemoteSocketAddress());
        for (Model model : board.models()) {
            MoveCommand sync = new MoveCommand(
                model.id(), model.position().x(), model.position().y()
            );
            conn.send(gson.toJson(sync));
        }
    }

    @Override
    public void onMessage(WebSocket conn, String message) {
        MoveCommand cmd = gson.fromJson(message, MoveCommand.class);
        System.out.println("Server recieved message"+cmd);
        broadcast(message);
    }

    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote) {
        System.out.println("Player disconnected: " + conn.getRemoteSocketAddress());
    }

    @Override
    public void onError(WebSocket conn, Exception ex) {
        ex.printStackTrace();
    }

    @Override
    public void onStart() {
        System.out.println("Server started on port: " + getPort());
    }
}
