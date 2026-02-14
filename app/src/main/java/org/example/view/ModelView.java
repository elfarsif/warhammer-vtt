package org.example.view;

import org.example.event.PositionListener;
import org.example.model.Model;
import org.example.model.Position;

import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

public class ModelView implements PositionListener {
    private final Model model;
    private final Rectangle rectangle;
    private final Scale scale;
    private double offsetX, offsetY;

    public ModelView(Model model, Pane parent, Scale scale) {
        this.model = model;
        this.scale = scale;
        this.rectangle = new Rectangle(scale.toPixels(model.width()),
                scale.toPixels(model.height()));
        this.model.position().addListener(this);

        rectangle.addEventHandler(MouseEvent.MOUSE_PRESSED, event -> {
            Point2D local = parent.sceneToLocal(event.getSceneX(), event.getSceneY());
            offsetX = local.getX() - rectangle.getTranslateX();
            offsetY = local.getY() - rectangle.getTranslateY();
        });

        rectangle.addEventHandler(MouseEvent.MOUSE_DRAGGED, event -> {
            Point2D local = parent.sceneToLocal(event.getSceneX(), event.getSceneY());
            double inchX = scale.toInches(local.getX() - offsetX);
            double inchY = scale.toInches(local.getY() - offsetY);
            model.moveTo(new Position(inchX, inchY));
        });

        new MeasuringTapeView(model.measuringTape(), parent, rectangle, scale);
    }

    @Override
    public void onPositionChanged(double x, double y) {
        rectangle.setTranslateX(scale.toPixels(x));
        rectangle.setTranslateY(scale.toPixels(y));
    }

    public Rectangle getRectangle() { return this.rectangle; }
}
