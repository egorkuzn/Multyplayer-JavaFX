package com.game.multy_player_javafx.mvc.model.passive;

import java.io.Serializable;

public class Point implements Serializable {
    public int X;
    public int Y;

    public Point(int X, int Y){
        this.X = X;
        this.Y = Y;
    }
}
