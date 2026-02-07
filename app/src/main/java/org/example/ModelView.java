package org.example;

import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

public class ModelView implements PositionListener {
    private final Model model;
    private final Rectangle rectangle;
    private final MeasuringTapeView measuringTapeView;

    protected ModelView(Model model) {
        this.model = model;
        this.rectangle = new Rectangle(40, 40);
        this.measuringTapeView = new MeasuringTapeView(model.measuringTape());
        model.position().addListener(this);
    }

    protected void setOnDrag(Pane parent) {
        measuringTapeView.setOnDrag(parent, rectangle);

        this.rectangle.setOnMouseDragged(event -> {
            Point2D local = parent.sceneToLocal(event.getSceneX(), event.getSceneY());
            model.moveTo(new Position((int) local.getX(), (int) local.getY()));
            measuringTapeView.onDrag(local);
        });
    }

    @Override
    public void onPositionChanged(int x, int y) {
        rectangle.setTranslateX(x);
        rectangle.setTranslateY(y);
    }

    public Rectangle getRectangle() { return this.rectangle; }
}
