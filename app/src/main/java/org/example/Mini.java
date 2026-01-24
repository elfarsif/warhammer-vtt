package org.example;


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
        this.model.setOnMousePressed(event -> this.handleMiniClick(event));
        this.model.setOnMouseDragged(event -> this.handleMouseDragged(event));
        this.pane = pane;
        this.text = text;
    }

    private void handleMouseDragged(MouseEvent event){
        double[] offset = (double[]) model.getUserData();
        model.setTranslateX(event.getSceneX() + offset[0]);
        model.setTranslateY(event.getSceneY() + offset[1]);
        measureTape.setEndX(model.getTranslateX()+model.getWidth()/2);
        measureTape.setEndY(model.getTranslateY() + model.getHeight() / 2);

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
                model.getTranslateY() - event.getSceneY(),
                event.getSceneX(),
                event.getSceneY()
            });
        double centerX = model.getTranslateX()+model.getWidth()/2;
        double centerY = model.getTranslateY()+model.getHeight()/2;
        measureTape.setStartX(centerX);
        measureTape.setStartY(centerY);
        measureTape.setEndX(centerX);
        measureTape.setEndY(centerY);
    }

    protected Pane getPane(){return this.pane;}

}
