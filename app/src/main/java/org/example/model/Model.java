package org.example.model;

public record Model(Integer width, Integer height, Position position, MeasuringTape measuringTape) {

    public void moveTo(Position position){
        this.position.moveTo(position.x(), position.y());
    }
}
