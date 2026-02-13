package org.example.model;

public class Segment {
    private double startX, startY;
    private double endX, endY;
    private double maxX = Double.MAX_VALUE;
    private double maxY = Double.MAX_VALUE;

    public void setBounds(double maxX, double maxY) {
        this.maxX = maxX;
        this.maxY = maxY;
    }

    public void setStart(double x, double y) {
        this.startX = Math.max(0, Math.min(x, maxX));
        this.startY = Math.max(0, Math.min(y, maxY));
    }

    public void setEnd(double x, double y) {
        this.endX = Math.max(0, Math.min(x, maxX));
        this.endY = Math.max(0, Math.min(y, maxY));
    }

    public double startX() { return startX; }
    public double startY() { return startY; }
    public double endX() { return endX; }
    public double endY() { return endY; }

    public double length() {
        double dx = endX - startX;
        double dy = endY - startY;
        return Math.sqrt(dx * dx + dy * dy);
    }
}
