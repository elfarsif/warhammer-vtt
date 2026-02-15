package org.example.view;

import org.example.event.PositionListener;
import org.example.model.Model;
import org.example.network.GameClient;
import org.example.network.MoveCommand;

import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class ModelView implements PositionListener {
    private final Model model;
    private final Pane parent;
    private final Rectangle rectangle;
    private final Scale scale;
    private final GameClient gameClient;
    private double offsetX, offsetY;

    public ModelView(Model model, Pane modelLayer, Pane modelOverlayLayer, Scale scale, GameClient gameClient) {
        this.model = model;
        this.parent = modelLayer;
        this.scale = scale;
        this.gameClient = gameClient;

        this.rectangle = setupRectangle(scale);
        this.model.position().addListener(this);
        this.setUpInitialPosition();
        this.setupDragEvent(scale);
        this.setupMeasuringTape(modelOverlayLayer, scale);
    }

    private void setUpInitialPosition(){
        this.onPositionChanged(this.model.position().x(), this.model.position().y());
    }

    private Rectangle setupRectangle(Scale scale) {
        Rectangle rect = new Rectangle(scale.toPixels(model.width()),
                scale.toPixels(model.height()));
        rect.setFill(Color.LIGHTBLUE);
        return rect;
    }

    private void setupDragEvent(Scale scale) {
        rectangle.addEventHandler(MouseEvent.MOUSE_PRESSED, event -> {
            Point2D local = parent.sceneToLocal(event.getSceneX(), event.getSceneY());
            offsetX = local.getX() - rectangle.getTranslateX();
            offsetY = local.getY() - rectangle.getTranslateY();
        });

        rectangle.addEventHandler(MouseEvent.MOUSE_DRAGGED, event -> {
            Point2D local = parent.sceneToLocal(event.getSceneX(), event.getSceneY());
            double inchX = scale.toInches(local.getX() - offsetX);
            double inchY = scale.toInches(local.getY() - offsetY);
            gameClient.sendMove(new MoveCommand(model.id(), inchX, inchY));
        });
    }

    private void setupMeasuringTape(Pane modelOverlayLayer, Scale scale) {
        new MeasuringTapeView(model.measuringTape(), modelOverlayLayer, rectangle, scale);
    }

    @Override
    public void onPositionChanged(double x, double y) {
        rectangle.setTranslateX(scale.toPixels(x));
        rectangle.setTranslateY(scale.toPixels(y));
    }

    public Rectangle getRectangle() { return this.rectangle; }
}
