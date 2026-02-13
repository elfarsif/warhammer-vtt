package org.example.model;

public record Board(Integer width, Integer height, Model model) {
    public Board {
        model.position().setBounds(width, height);
        model.measuringTape().segment().setBounds(width, height);
    }
}
