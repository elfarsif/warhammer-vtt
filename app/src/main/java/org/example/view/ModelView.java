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

    public ModelView(Model model, Pane parent) {
        this.model = model;
        this.rectangle = new Rectangle(40, 40);
        this.model.position().addListener(this);

        rectangle.addEventHandler(MouseEvent.MOUSE_DRAGGED, event -> {
            Point2D local = parent.sceneToLocal(event.getSceneX(), event.getSceneY());
            model.moveTo(new Position((int) local.getX(), (int) local.getY()));
        });

        new MeasuringTapeView(model.measuringTape(), parent, rectangle);
    }

    @Override
    public void onPositionChanged(int x, int y) {
        rectangle.setTranslateX(x);
        rectangle.setTranslateY(y);
    }

    public Rectangle getRectangle() { return this.rectangle; }
}
