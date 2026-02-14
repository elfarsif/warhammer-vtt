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
    private final Scale scale;

    public MeasuringTapeView(MeasuringTape measuringTape, Pane parent, Rectangle rectangle, Scale scale) {
        this.measuringTape = measuringTape;
        this.scale = scale;
        this.line = new Line();
        this.lengthText = new Text();
        parent.getChildren().addAll(line, lengthText);

        rectangle.addEventHandler(MouseEvent.MOUSE_PRESSED, event -> {
            Point2D local = parent.sceneToLocal(event.getSceneX(), event.getSceneY());
            measuringTape.segment().setStart(scale.toInches(local.getX()), scale.toInches(local.getY()));
            line.setStartX(scale.toPixels(measuringTape.segment().startX()));
            line.setStartY(scale.toPixels(measuringTape.segment().startY()));
            line.setEndX(scale.toPixels(measuringTape.segment().endX()));
            line.setEndY(scale.toPixels(measuringTape.segment().endY()));
            lengthText.setText("");
        });

        rectangle.addEventHandler(MouseEvent.MOUSE_DRAGGED, event -> {
            Point2D local = parent.sceneToLocal(event.getSceneX(), event.getSceneY());
            measuringTape.segment().setEnd(scale.toInches(local.getX()), scale.toInches(local.getY()));
            line.setEndX(scale.toPixels(measuringTape.segment().endX()));
            line.setEndY(scale.toPixels(measuringTape.segment().endY()));
            lengthText.setText(String.format("%.1f\"", measuringTape.segment().length()));
            lengthText.setX(scale.toPixels(measuringTape.segment().startX()));
            lengthText.setY(scale.toPixels(measuringTape.segment().startY()));
        });
    }
}
