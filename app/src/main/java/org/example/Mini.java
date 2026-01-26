package org.example;


import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;


public class Mini{
    private final Rectangle model;
    private final Line measureTape;
    private final Pane pane;
    private final Text text;

    public Mini(Rectangle model,Line measureTape,Pane pane,Text text){
        this.model = model;
		this.measureTape = measureTape;
        this.pane = pane;
        this.text = text;

        this.model.setOnMousePressed(event -> this.handleMiniClick(event));
        this.model.setOnMouseDragged(event -> this.handleMouseDragged(event));

    }

    private void handleMouseDragged(MouseEvent event){
        double[] offset = (double[]) model.getUserData();

        model.setTranslateX(event.getSceneX() + offset[0]);
        model.setTranslateY(event.getSceneY() + offset[1]);

        Point2D drag = pane.sceneToLocal(event.getSceneX(), event.getSceneY());

        measureTape.setEndX(drag.getX());
        measureTape.setEndY(drag.getY());

        text.setX(model.getTranslateX());
        text.setY(model.getTranslateY());

        double a = measureTape.getEndX() - measureTape.getStartX();
        double b = measureTape.getEndY() - measureTape.getStartY();
        double hypotenus = Math.sqrt(a * a + b * b);
        text.setText(String.format("%.1f", hypotenus));

    }

    private void handleMiniClick(MouseEvent event){
        model.setUserData(new double[]{
                model.getTranslateX() - event.getSceneX(),
                model.getTranslateY() - event.getSceneY()
            });

        Point2D click = pane.sceneToLocal(event.getSceneX(), event.getSceneY());
        

        measureTape.setStartX(click.getX());
        measureTape.setStartY(click.getY());
        measureTape.setEndX(click.getX());
        measureTape.setEndY(click.getY());
    }

    protected Pane getPane(){return this.pane;}

}
