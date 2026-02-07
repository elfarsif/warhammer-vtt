package org.example.view;

import org.example.model.MeasuringTape;

import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

public class MeasuringTapeView {
    private final MeasuringTape measuringTape;
    private Point2D startDrag;

    public MeasuringTapeView(MeasuringTape measuringTape) {
        this.measuringTape = measuringTape;
    }

    public void setOnDrag(Pane parent, Rectangle rectangle) {
        rectangle.addEventHandler(MouseEvent.MOUSE_PRESSED, event -> {
            Point2D local = parent.sceneToLocal(event.getSceneX(), event.getSceneY());
            this.startDrag = local;
        });

        rectangle.addEventHandler(MouseEvent.MOUSE_DRAGGED, event -> {
            Point2D local = parent.sceneToLocal(event.getSceneX(), event.getSceneY());
            double dx = local.getX() - startDrag.getX();
            double dy = local.getY() - startDrag.getY();
            double distance = Math.sqrt(dx * dx + dy * dy);
            measuringTape.length().extend(distance);
        });
    }
}
