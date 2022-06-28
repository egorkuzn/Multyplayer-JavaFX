package com.game.multy_player_javafx.mvc.view.view_controllers;

import com.game.multy_player_javafx.mvc.model.passive.area.Area;

public enum Location {
    STREET,
    HOUSE;

    String path(){
        switch (this){
            case STREET -> {return "background_images/street.jpg";}
            default -> {return "background_images/house.jpg";}
        }
    }

    double dx = 0, dy = 0;

    public void setBiasX(double dx){
        this.dx = dx;
    }

    public void setBiasY(double dy){
        this.dy = dy;
    }

    public double getBiasX(){
        return dx;
    }

    public double getBiasY(){
        return dy;
    }

    public double width() {
        return Area.valueOf(this.toString()).width();
    }

    public double height() {
        return Area.valueOf(this.toString()).height();
    }
}
