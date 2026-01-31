package org.example;

public class Position{
    private Integer x,y;

    protected Position(Integer x, Integer y){
        this.x = x;
        this.y = y;
    }

    public void moveTo(Integer x, Integer y){
        this.x = x;
        this.y = y;
    }

    public Integer x(){return this.x;}
    public Integer y(){return this.y;}

}
