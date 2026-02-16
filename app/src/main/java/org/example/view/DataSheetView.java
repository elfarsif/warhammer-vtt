package org.example.view;

import org.example.model.datasheet.DataSheet;

import javafx.scene.control.Accordion;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class DataSheetView {
    private final VBox content;

    public DataSheetView(DataSheet dataSheet, Pane parent) {
        this.content = new VBox();

        PictureView pictureView = new PictureView(
            dataSheet.picture(), 
            parent,
            new ImageView(new Image(getClass().getResourceAsStream(dataSheet.picture().getImagePath())))
        );

        StatsView statsView = new StatsView(dataSheet.stats());
        statsView.getGridPane().prefWidthProperty().bind(parent.widthProperty());

        RangedWeaponsView rangedWeaponsView = new RangedWeaponsView(dataSheet.rangedWeapons());
        rangedWeaponsView.getGridPane().prefWidthProperty().bind(parent.widthProperty());

        MeleeWeaponsView meleeWeaponsView = new MeleeWeaponsView(dataSheet.meleeWeapons());
        meleeWeaponsView.getGridPane().prefWidthProperty().bind(parent.widthProperty());

        AbilitiesView abilitiesView = new AbilitiesView(dataSheet.abilities());
        abilitiesView.getGridPane().prefWidthProperty().bind(parent.widthProperty());

        content.getChildren().addAll(pictureView.getImageView(), statsView.getGridPane(), rangedWeaponsView.getGridPane(), meleeWeaponsView.getGridPane(), abilitiesView.getGridPane());
    }

    public VBox getContent() { return content; }
}
