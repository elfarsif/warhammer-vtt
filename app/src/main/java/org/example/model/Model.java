package org.example.model;

import org.example.model.datasheet.DataSheet;

public record Model(String id, Double width, Double height, Position position, MeasuringTape measuringTape, DataSheet dataSheet) {

    public void moveTo(Position position){
        this.position.moveTo(position.x(), position.y());
    }
}
