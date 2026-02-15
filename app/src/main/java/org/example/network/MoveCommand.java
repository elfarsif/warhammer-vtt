package org.example.network;

public class MoveCommand {
    private String modelId;
    private double x;
    private double y;

    public MoveCommand() {}

    public MoveCommand(String modelId, double x, double y) {
        this.modelId = modelId;
        this.x = x;
        this.y = y;
    }

    public String getModelId() { return modelId; }
    public double getX() { return x; }
    public double getY() { return y; }
}
