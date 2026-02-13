package org.example.view;

import org.example.model.MeasuringTape;

import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class MeasuringTapeView {
    private final MeasuringTape measuringTape;
    private final Line line;
    private final Text lengthText;

    public MeasuringTapeView(MeasuringTape measuringTape, Pane parent, Rectangle rectangle) {
        this.measuringTape = measuringTape;
        this.line = new Line();
        this.lengthText = new Text();
        parent.getChildren().addAll(line, lengthText);

        rectangle.addEventHandler(MouseEvent.MOUSE_PRESSED, event -> {
            Point2D local = parent.sceneToLocal(event.getSceneX(), event.getSceneY());
            measuringTape.segment().setStart(local.getX(), local.getY());
            line.setStartX(local.getX());
            line.setStartY(local.getY());
            line.setEndX(measuringTape.segment().endX());
            line.setEndY(measuringTape.segment().endY());
            lengthText.setText("");
        });

        rectangle.addEventHandler(MouseEvent.MOUSE_DRAGGED, event -> {
            Point2D local = parent.sceneToLocal(event.getSceneX(), event.getSceneY());
            measuringTape.segment().setEnd(local.getX(), local.getY());
            line.setEndX(measuringTape.segment().endX());
            line.setEndY(measuringTape.segment().endY());
            lengthText.setText(String.format("%.1f", measuringTape.segment().length()));
            lengthText.setX(measuringTape.segment().endX());
            lengthText.setY(measuringTape.segment().endY());
        });
    }
}
