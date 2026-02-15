package org.example.model;

public record Model(String id, Double width, Double height, Position position, MeasuringTape measuringTape) {

    public void moveTo(Position position){
        this.position.moveTo(position.x(), position.y());
    }
}
