package org.example.model.dice;

import java.util.Random;

public class DiceRoller {
    private final Random random = new Random();
    private DicePool dicePool;
    private DiceResult diceResult;

    public DiceRoller(DicePool dicePool, DiceResult diceResult) {
        this.dicePool = dicePool;
        this.diceResult = diceResult;
    }

    public void roll() {
        int[] counts = new int[6];
        for (int i = 0; i < dicePool.numberOfDice(); i++) {
            counts[random.nextInt(6)]++;
        }
        diceResult = new DiceResult(counts[0], counts[1], counts[2], counts[3], counts[4], counts[5]);
    }

    public DicePool getDicePool() { return dicePool; }
    public void setDicePool(DicePool dicePool) { this.dicePool = dicePool; }
    public DiceResult getDiceResult() { return diceResult; }
}
