package com.game.multy_player_javafx.mvc.model.passive.area;

import com.game.multy_player_javafx.mvc.model.active.ActiveStatus;
import com.game.multy_player_javafx.mvc.model.active.actions.human.move.Direction;
import com.game.multy_player_javafx.mvc.model.passive.area.geometry.Point;
import com.game.multy_player_javafx.mvc.model.passive.area.geometry.Polygon;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;

public class AreaFrames {
    static HashMap<Area, Polygon> areaPolygonHashMap = new HashMap<>();
    public static boolean isInArea(Area place, Point coordinate, int step, Direction direction, ActiveStatus status){
        Point toGo = coordinate.inDirection(direction, step);
        loadPolygon(place);

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
        if(areaPolygonHashMap.containsKey(Area.STREET))
            return areaPolygonHashMap.get(Area.STREET).isInPolygon(toGo);

        return false;
    }

    static void loadPolygon(Area place){
        if(!areaPolygonHashMap.containsKey(place)){
            BufferedReader reader = null;

            try{
                reader = new BufferedReader(new FileReader("src/main/resources/scene/street.txt"));
                String line;
                Polygon polygon = new Polygon();

                while ((line = reader.readLine()) != null)
                    polygon.addPoint(new Point(line));

                areaPolygonHashMap.put(place, polygon);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
