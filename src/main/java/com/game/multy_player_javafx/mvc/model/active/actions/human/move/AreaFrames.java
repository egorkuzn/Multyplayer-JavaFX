package com.game.multy_player_javafx.mvc.model.active.actions.human.move;

import com.game.multy_player_javafx.mvc.model.passive.Point;

public class AreaFrames {
    public static boolean isInArea(String placeName, Point coordinate, int step, Direction direction){
        if(placeName.equals("street")) {
            int X = coordinate.inDirection(direction, step).X;
            int Y = coordinate.inDirection(direction,step).Y;

            if(Y < 500)
                return false;
        }

        return true;
    }

    public static void getRandomInArea(String placeName, Point coordinate) {
        coordinate.Y = 800;
        coordinate.X = 900;
    }
}
