package org.example.view;

import org.example.model.datasheet.Stats;

import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class StatsView {
    private final Stats stats;
    private final GridPane gridPane;

    public StatsView(Stats stats) {
        this.stats = stats;
        this.gridPane = new GridPane();
        this.gridPane.setHgap(10);
        buildGrid();
    }

    private void buildGrid() {
        String[] headers = {"M", "T", "SV", "W", "LD", "OC"};
        String[] values = {
            stats.movement().value().toString(),
            stats.toughness().value().toString(),
            stats.save().value().toString(),
            stats.wound().value().toString(),
            stats.leadership().value().toString(),
            stats.objectiveControl().value().toString()
        };

        for (int i = 0; i < headers.length; i++) {
            gridPane.add(new Label(headers[i]), i, 0);
            gridPane.add(new Label(values[i]), i, 1);
        }
    }

    public GridPane getGridPane() { return gridPane; }
}
