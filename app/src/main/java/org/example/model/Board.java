package org.example.model;

import java.util.List;

public record Board(Integer width, Integer height, List<Model> models, List<Obstacle> obstacles) {
    public Board {
        for (Model model : models) {
            model.position().setBounds(width, height);
            model.measuringTape().segment().setBounds(width, height);
        }
        for (Obstacle obstacle : obstacles) {
            obstacle.position().setBounds(width, height);
        }
    }

    public Model findById(String id) {
        for (Model model : models) {
            if (model.id().equals(id)) return model;
        }
        return null;
    }

    public boolean isPositionFree(String movingId, double newX, double newY) {
        Model moving = findById(movingId);
        if (moving == null) return true;
        for (Model other : models) {
            if (other.id().equals(movingId)) continue;
            double ox = other.position().x();
            double oy = other.position().y();
            if (newX < ox + other.width()  && newX + moving.width()  > ox &&
                newY < oy + other.height() && newY + moving.height() > oy) {
                return false;
            }
        }
        for (Obstacle obs : obstacles) {
            double ox = obs.position().x();
            double oy = obs.position().y();
            if (newX < ox + obs.width()  && newX + moving.width()  > ox &&
                newY < oy + obs.height() && newY + moving.height() > oy) {
                return false;
            }
        }
        return true;
    }
}
