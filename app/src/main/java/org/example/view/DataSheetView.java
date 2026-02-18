package org.example.view;

import org.example.event.ModelSelectionListener;
import org.example.model.datasheet.DataSheet;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class DataSheetView implements ModelSelectionListener {
    private final VBox content;
    private final Pane parent;

    public DataSheetView(Pane parent) {
        this.parent = parent;
        this.content = new VBox();
    }

    @Override
    public void onModelSelected(DataSheet dataSheet) {
        System.out.println("DataSheetView: displaying " + dataSheet.picture().getImagePath() + " | M:" + dataSheet.stats().movement().value() + " T:" + dataSheet.stats().toughness().value());
        content.getChildren().clear();

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
