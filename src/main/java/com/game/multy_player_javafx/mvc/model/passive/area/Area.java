package com.game.multy_player_javafx.mvc.model.passive.area;

public enum Area {
    STREET,
    HOUSE;

    public double width() {
        switch (this){
            case STREET -> {return 1200;}
            default -> {return 1200;}
        }
    }

    public double height() {
        switch (this){
            case STREET -> {return 816;}
            default -> {return 816;}
        }
    }
}
