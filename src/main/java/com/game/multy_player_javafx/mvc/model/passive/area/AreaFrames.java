package com.game.multy_player_javafx.mvc.model.passive.area;

import com.game.multy_player_javafx.mvc.model.active.actions.human.move.Direction;

import java.util.concurrent.ThreadLocalRandom;

public class AreaFrames {
    public static boolean isInArea(Area place, Point coordinate, int step, Direction direction){
        int X = coordinate.inDirection(direction, step).X;
        int Y = coordinate.inDirection(direction, step).Y;

        switch(place) {
            case STREET -> {
                if(X < 0)
                    return false;
                else if(Y < 0)
                    return false;
                else if(X > place.width())
                    return false;
                else if(Y > place.height())
                    return false;

                return true;
            }
            default -> {return true;}
        }
    }

    public static void getRandomInArea(Area place, Point coordinate) {
        coordinate.Y = ThreadLocalRandom.current().nextInt((int)place.height());
        coordinate.X = ThreadLocalRandom.current().nextInt((int)place.width());
    }
}
