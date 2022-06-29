package com.game.multy_player_javafx.mvc.model.passive.area.geometry;

import java.util.ArrayList;

public class Polygon {
    ArrayList<Point> points = new ArrayList<>();
    public void addPoint(Point point) {
        points.add(point);
    }

    public boolean isInPolygon(Point point){
        boolean isIn = false;

        for(int i = 0, j = points.size() - 1; i < points.size(); j = i++)
            if ((((points.get(i).Y <= point.Y) && (point.Y < points.get(j).Y)) || ((points.get(j).Y <= point.Y) && (point.Y < points.get(i).Y))) && (((points.get(j).Y - points.get(i).Y) != 0) && (point.X > ((points.get(j).X - points.get(i).X) * (point.Y - points.get(i).Y) / (points.get(j).Y - points.get(i).Y) + points.get(i).X))))
                isIn = !isIn;

        return isIn;
    }
}
