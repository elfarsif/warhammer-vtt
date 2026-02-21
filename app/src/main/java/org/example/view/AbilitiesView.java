package org.example.view;

import org.example.model.datasheet.Ability;

import java.util.List;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class AbilitiesView {
    private final List<Ability> abilities;
    private final GridPane gridPane;
    private final Pane parent;

    public AbilitiesView(List<Ability> abilities, Pane parent) {
        this.abilities = abilities;
        this.parent = parent;
        this.gridPane = new GridPane();
        this.gridPane.setHgap(10);
        buildGrid();
    }

    private void buildGrid() {
        String[] headers = {"Ability", "Description"};
        for (int i = 0; i < headers.length; i++) {
            gridPane.add(new Label(headers[i]), i, 0);
        }

        for (int row = 0; row < abilities.size(); row++) {
            Ability a = abilities.get(row);
            gridPane.add(new Label(a.name()), 0, row + 1);
            Label descLabel = new Label(a.description());
            descLabel.setWrapText(true);
            descLabel.maxWidthProperty().bind(parent.widthProperty());
            gridPane.add(descLabel, 1, row + 1);
        }
    }

    public GridPane getGridPane() { return gridPane; }
}
