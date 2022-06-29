package com.game.multy_player_javafx.mvc.model.passive.area.geometry;

import com.game.multy_player_javafx.mvc.model.active.actions.human.move.Direction;

import java.io.Serializable;

public class Point implements Serializable{
    public int X = 0;
    public int Y = 0;

    public Point(){}
    public Point(String line){
        String[] strArr = line.split(";");

        if(strArr.length == 2) {
            X = Integer.parseInt(strArr[0]);
            Y = Integer.parseInt(strArr[1]);
        }
    }
    public Point(double X, double Y){
        this.X = (int) X;
        this.Y = (int) Y;
    }

    public Point(int X, int Y){
        this.X = X;
        this.Y = Y;
    }

    public Point inDirection(Direction direction, int step){
        return switch (direction) {
            case UP -> new Point(X, Y - step);
            case DOWN -> new Point(X, Y + step);
            case LEFT -> new Point(X - step, Y);
            case RIGHT -> new Point(X + step, Y);
        };
    }
}
