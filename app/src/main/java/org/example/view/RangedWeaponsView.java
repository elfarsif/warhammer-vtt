package org.example.view;

import org.example.model.datasheet.RangedWeapon;

import java.util.List;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class RangedWeaponsView {
    private final List<RangedWeapon> rangedWeapons;
    private final GridPane gridPane;

    public RangedWeaponsView(List<RangedWeapon> rangedWeapons) {
        this.rangedWeapons = rangedWeapons;
        this.gridPane = new GridPane();
        this.gridPane.setHgap(10);
        buildGrid();
    }

    private void buildGrid() {
        String[] headers = {"Ranged Weapon", "R", "A", "BS", "S", "AP", "D", "KW"};
        for (int i = 0; i < headers.length; i++) {
            gridPane.add(new Label(headers[i]), i, 0);
        }

        for (int row = 0; row < rangedWeapons.size(); row++) {
            RangedWeapon w = rangedWeapons.get(row);
            String[] values = {
                w.name(),
                w.range().toString(),
                w.attack().toString(),
                w.ballisticSkill().toString(),
                w.strength().toString(),
                w.armourPenetration().toString(),
                w.damage().toString(),
                w.keyword()
            };
            for (int col = 0; col < values.length; col++) {
                gridPane.add(new Label(values[col]), col, row + 1);
            }
        }
    }

    public GridPane getGridPane() { return gridPane; }
}
