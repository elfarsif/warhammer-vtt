package org.example;

import javafx.geometry.Point2D;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class BoardView {
    private final Board board;
    private final Pane pane;

    protected BoardView(Board board, Pane pane) {
        this.board = board;
        this.pane = pane;
        pane.setBackground(Background.fill(Color.LIGHTSALMON));
        pane.setMaxWidth(500);
        pane.setMaxHeight(500);

        ModelView modelView = new ModelView(board.model());
        modelView.setOnDrag(pane);

        pane.getChildren().add(modelView.getRectangle());
    }

    public Pane getPane() { return this.pane; }
}


