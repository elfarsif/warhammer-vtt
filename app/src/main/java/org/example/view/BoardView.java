package org.example.view;

import org.example.model.Board;

import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class BoardView {
    private final Board board;
    private final Pane pane;

    public BoardView(Board board, Pane pane) {
        this.board = board;
        this.pane = pane;
        this.pane.setBackground(Background.fill(Color.LIGHTSALMON));
        this.pane.setMaxWidth(500);
        this.pane.setMaxHeight(500);

        ModelView modelView = new ModelView(this.board.model());
        modelView.setOnDrag(pane);

        MeasuringTapeView measuringTapeView = new MeasuringTapeView(this.board.model().measuringTape());
        measuringTapeView.setOnDrag(pane, modelView.getRectangle());

        pane.getChildren().add(modelView.getRectangle());
    }

    public Pane getPane() { return this.pane; }
}


