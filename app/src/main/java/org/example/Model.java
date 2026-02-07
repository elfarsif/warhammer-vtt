package org.example;

public record Model(Integer width, Integer height, Position position, MeasuringTape measuringTape) {

    public void moveTo(Position position){
        this.position.moveTo(position.x(), position.y());
    }

    public void measuring(Double length) {
        this.measuringTape.length().extend(length);
    }
}
