package org.example.model;

import java.util.List;

public record Board(Integer width, Integer height, List<Model> models) {
    public Board {
        for (Model model : models) {
            model.position().setBounds(width, height);
            model.measuringTape().segment().setBounds(width, height);
        }
    }

    public Model findById(String id) {
        for (Model model : models) {
            if (model.id().equals(id)) return model;
        }
        return null;
    }
}
