package org.example.model;

import org.example.event.PositionListener;

import java.util.ArrayList;
import java.util.List;

public class Position {
    private double x, y;
    private double maxX = Double.MAX_VALUE;
    private double maxY = Double.MAX_VALUE;
    private final List<PositionListener> listeners = new ArrayList<>();

    public Position(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void setBounds(double maxX, double maxY) {
        this.maxX = maxX;
        this.maxY = maxY;
    }

    public void addListener(PositionListener listener) {
        listeners.add(listener);
    }

    public void moveTo(double x, double y) {
        this.x = Math.max(0, Math.min(x, maxX));
        this.y = Math.max(0, Math.min(y, maxY));
        for (PositionListener l : listeners) {
            l.onPositionChanged(this.x, this.y);
        }
    }

    public double x() { return this.x; }
    public double y() { return this.y; }
}
