package com.game.multy_player_javafx.mvc.view.scene_items.sprites_block.managing;

import com.game.multy_player_javafx.mvc.view.view_controllers.Location;

public class ScriptAnalyser {
    String name;
    double x;
    double y;
    double height;
    double width;
    StringBuilder textInfo = new StringBuilder();

    public ScriptAnalyser(String script){
        String[] array = script.split("_");
        Environment elem = Environment.valueOf(array[0]);
        elem.setObj(array[1]);

        x = elem.getX();
        y = elem.getY();
        height = elem.getHeight();
        width = elem.getWidth();
        name = elem.name();

        for(int i = 1; i < array.length; i++) {
            if(i != 1)
                textInfo.append("_");

            textInfo.append(array[i]);
        }
    }


    public double getX(){
        return x;
    }

    public double getY(){
        return y;
    }

    public double getScaleX(double imgWidth){
        return width / imgWidth;
    }

    public double getScaleY(double imgHeight){
        return height / imgHeight;
    }

    public String getName(){
        return name;
    }

    public String getText(){
        return textInfo.toString();
    }

    public Location getLocation(){
        return Location.STREET;
    }
}
