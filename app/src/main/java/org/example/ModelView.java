package org.example;

import javafx.scene.shape.Rectangle;

public class ModelView {
    private final Model model;
    private final Rectangle rectangle;

    protected ModelView(Model model){
        this.model = model;
        this.rectangle =  new Rectangle(40, 40);

        
    }
    
    public Rectangle getRectangle(){ return this.rectangle;}

    
}
