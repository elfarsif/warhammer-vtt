package org.example.model;

public class Length {
    private Double length;

    public Length(Double length) {
        this.length = length;
    }

    public void extend(Double length) {
        this.length = length;
        System.out.println("Length: " + length);
    }

    public Double getLength() { return this.length; }
}
