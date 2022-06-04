package com.game.multy_player_javafx.mvc.view.scene_items;

import com.game.multy_player_javafx.mvc.view.scene_items.items.*;

public enum Environment {
    BOY,
    GIRL,
    DOG,
    CAR;

    Item obj;

    void setObj(String info){
        switch (this.name()) {
            case "BOY" -> obj = new Boy(info);
            case "GIRL" -> obj = new Girl(info);
            case "DOG" -> obj = new Dog(info);
            case "CAR" -> obj = new Car(info);
        }
    }


    public double getX() {
        return obj.getX();
    }

    public double getY() {
        return obj.getY();
    }
}
