package org.example;

public class Picture{
    private final String imagePath;

    protected Picture(String imagePath){
        this.imagePath = imagePath;
    }

    public String getImagePath(){
        return this.imagePath;
    }


}
