package org.example.view;

public class Scale{
    private final double pixelPerInch;

    public Scale(double pixelPerInch){
        this.pixelPerInch = pixelPerInch;
    }

    public double toPixels(double inches){ return inches*this.pixelPerInch; }
    public double toInches(double pixels){ return pixels / this.pixelPerInch; }

}
