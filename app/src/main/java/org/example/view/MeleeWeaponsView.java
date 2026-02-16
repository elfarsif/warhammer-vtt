package org.example.view;

import org.example.model.datasheet.MeleeWeapon;

import java.util.List;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class MeleeWeaponsView {
    private final List<MeleeWeapon> meleeWeapons;
    private final GridPane gridPane;

    public MeleeWeaponsView(List<MeleeWeapon> meleeWeapons) {
        this.meleeWeapons = meleeWeapons;
        this.gridPane = new GridPane();
        this.gridPane.setHgap(10);
        buildGrid();
    }

    private void buildGrid() {
        String[] headers = {"Melee Weapon", "R", "A", "WS", "S", "AP", "D", "KW"};
        for (int i = 0; i < headers.length; i++) {
            gridPane.add(new Label(headers[i]), i, 0);
        }

        for (int row = 0; row < meleeWeapons.size(); row++) {
            MeleeWeapon w = meleeWeapons.get(row);
            String[] values = {
                w.name(),
                w.range().toString(),
                w.attack().toString(),
                w.weaponSkill().toString(),
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
