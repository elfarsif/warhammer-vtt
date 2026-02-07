package org.example;

import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

public class MeasuringTapeView {
    private final MeasuringTape measuringTape;
    private Point2D startDrag;

    public MeasuringTapeView(MeasuringTape measuringTape) {
        this.measuringTape = measuringTape;
    }

    public void setOnDrag(Pane parent, Rectangle rectangle) {
        rectangle.setOnMousePressed(event -> {
            Point2D local = parent.sceneToLocal(event.getSceneX(), event.getSceneY());
            this.startDrag = local;
        });
    }

    public void onDrag(Point2D current) {
        double dx = current.getX() - startDrag.getX();
        double dy = current.getY() - startDrag.getY();
        double distance = Math.sqrt(dx * dx + dy * dy);
        measuringTape.length().extend(distance);
    }
}
