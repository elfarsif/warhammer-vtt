package org.example;

public record Model(Integer width, Integer height, Position position) {

    public void moveTo(Position position){
        this.position.moveTo(position.x(), position.y());
    }


}
