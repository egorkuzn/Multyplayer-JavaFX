package com.game.multy_player_javafx.mvc.view.scene_items.sprites_block.managing;

import com.game.multy_player_javafx.mvc.view.scene_items.sprites_block.items.*;

public enum Environment {
    BOY,
    GIRL,
    DOG,
    CAR,
    TEXT;

    Item obj;

    void setObj(String info){
        switch (this.name()) {
            case "BOY" -> obj = new Boy(info);
            case "GIRL" -> obj = new Girl(info);
            case "DOG" -> obj = new Dog(info);
            case "CAR" -> obj = new Car(info);
            case "TEXT" -> obj = new Text();
        }
    }


    public double getX() {
        return obj.getX();
    }

    public double getY() {
        return obj.getY();
    }

    public double getHeight() {
        return obj.getHeight();
    }

    public double getWidth() {
        return obj.getWidth();
    }
}
