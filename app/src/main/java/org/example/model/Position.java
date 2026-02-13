package org.example.model;

import org.example.event.PositionListener;

import java.util.ArrayList;
import java.util.List;

public class Position {
    private Integer x, y;
    private int maxX = Integer.MAX_VALUE;
    private int maxY = Integer.MAX_VALUE;
    private final List<PositionListener> listeners = new ArrayList<>();

    public Position(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }

    public void setBounds(int maxX, int maxY) {
        this.maxX = maxX;
        this.maxY = maxY;
    }

    public void addListener(PositionListener listener) {
        listeners.add(listener);
    }

    public void moveTo(Integer x, Integer y) {
        this.x = Math.max(0, Math.min(x, maxX));
        this.y = Math.max(0, Math.min(y, maxY));
        for (PositionListener l : listeners) {
            l.onPositionChanged(this.x, this.y);
        }
    }

    public Integer x() { return this.x; }
    public Integer y() { return this.y; }
}
