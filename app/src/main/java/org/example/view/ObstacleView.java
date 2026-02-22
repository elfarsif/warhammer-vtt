package org.example.view;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import org.example.model.Obstacle;

public class ObstacleView {
    private final Rectangle rectangle;

    public ObstacleView(Obstacle obstacle, Scale scale) {
        rectangle = new Rectangle(
            scale.toPixels(obstacle.width()),
            scale.toPixels(obstacle.height())
        );
        rectangle.setFill(Color.SADDLEBROWN);
        rectangle.setTranslateX(scale.toPixels(obstacle.position().x()));
        rectangle.setTranslateY(scale.toPixels(obstacle.position().y()));
    }

    public Rectangle getRectangle() {
        return rectangle;
    }
}
