package com.game.multy_player_javafx.mvc.model.passive.area;

import com.game.multy_player_javafx.mvc.model.active.ActiveStatus;
import com.game.multy_player_javafx.mvc.model.active.actions.human.move.Direction;

import java.util.concurrent.ThreadLocalRandom;

public class AreaFrames {
    public static boolean isInArea(Area place, Point coordinate, int step, Direction direction, ActiveStatus status){
        Point toGo = coordinate.inDirection(direction, step);

        switch(place) {
            case STREET -> {return street(toGo, status, direction);}
            default -> {return true;}
        }
    }

    public static void getRandomInArea(Area place, Point coordinate) {
        coordinate.Y = ThreadLocalRandom.current().nextInt((int)place.height());
        coordinate.X = ThreadLocalRandom.current().nextInt((int)place.width());
    }

    static boolean street(Point toGo, ActiveStatus status, Direction direction){

        if(toGo.X < 0)
            return false;
        else if(toGo.Y < 350)
            return false;
        else if(toGo.X > Area.STREET.width())
            return false;
        else if(toGo.Y > Area.STREET.height())
            return false;

        return true;
    }
}
