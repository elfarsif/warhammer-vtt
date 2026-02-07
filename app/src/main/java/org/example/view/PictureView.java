package org.example.view;

import org.example.model.datasheet.Picture;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class PictureView{
    private final Picture picture;
    private final Pane parent;
    private final ImageView imageView;

    public PictureView(Picture picture,Pane parent, ImageView imageView){
        this.picture = picture;
        this.parent = parent;
        this.imageView = imageView;

        this.imageView.fitWidthProperty().bind(parent.widthProperty());;
        this.imageView.setPreserveRatio(true);

    }

    public ImageView getImageView(){ return this.imageView; }

}
