package org.example.view;

import org.example.model.Board;
import org.example.model.Model;

import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class BoardView {
    private final Board board;
    private final Pane pane;
    private final Pane modelLayer;
    private final Pane modelOverlayLayer;

    public BoardView(Board board, Scale scale) {
        this.board = board;
        this.pane = new Pane();
        this.modelLayer = new Pane();
        this.modelOverlayLayer = new Pane();
        this.modelOverlayLayer.setPickOnBounds(false);

        setupPane(scale);
        addModels(scale);
    }

    private void setupPane(Scale scale) {
        this.pane.setBackground(Background.fill(Color.LIGHTSALMON));
        this.pane.setMaxWidth(scale.toPixels(board.width()));
        this.pane.setMaxHeight(scale.toPixels(board.height()));
        this.pane.getChildren().addAll(modelLayer, modelOverlayLayer);
    }

    private void addModels(Scale scale) {
        for (Model model : this.board.models()) {
            ModelView modelView = new ModelView(model, modelLayer, modelOverlayLayer, scale);
            modelLayer.getChildren().add(modelView.getRectangle());
        }
    }

    public Pane getPane() { return this.pane; }
}


