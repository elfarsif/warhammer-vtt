package org.example.view;

import org.example.model.dice.DicePool;
import org.example.model.dice.DiceRoller;
import org.example.model.dice.DiceResult;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class DiceRollerView {
    private final DiceRoller diceRoller;
    private final VBox content;
    private final Label[] countLabels = new Label[6];

    public DiceRollerView(DiceRoller diceRoller) {
        this.diceRoller = diceRoller;

        TextField numDiceField = new TextField("1");
        numDiceField.setPromptText("# of dice");

        Button rollBtn = new Button("Roll");

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(5);
        for (int i = 0; i < 6; i++) {
            grid.add(new Label(String.valueOf(i + 1)), i, 0);
            countLabels[i] = new Label("0");
            grid.add(countLabels[i], i, 1);
        }

        rollBtn.setOnAction(e -> {
            int num = Integer.parseInt(numDiceField.getText());
            diceRoller.setDicePool(new DicePool(num));
            diceRoller.roll();
            DiceResult result = diceRoller.getDiceResult();
            countLabels[0].setText(String.valueOf(result.ones()));
            countLabels[1].setText(String.valueOf(result.twos()));
            countLabels[2].setText(String.valueOf(result.threes()));
            countLabels[3].setText(String.valueOf(result.fours()));
            countLabels[4].setText(String.valueOf(result.fives()));
            countLabels[5].setText(String.valueOf(result.sixes()));
        });

        content = new VBox(5, numDiceField, rollBtn, grid);
    }

    public VBox getContent() { return content; }
}
