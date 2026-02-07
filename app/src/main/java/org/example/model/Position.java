package org.example.model;

import org.example.event.PositionListener;

import java.util.ArrayList;
import java.util.List;

public class Position {
    private Integer x, y;
    private final List<PositionListener> listeners = new ArrayList<>();

    public Position(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }

    public void addListener(PositionListener listener) {
        listeners.add(listener);
    }

    public void moveTo(Integer x, Integer y) {
        this.x = x;
        this.y = y;
        for (PositionListener l : listeners) {
            l.onPositionChanged(x, y);
        }
    }

    public Integer x() { return this.x; }
    public Integer y() { return this.y; }
}
