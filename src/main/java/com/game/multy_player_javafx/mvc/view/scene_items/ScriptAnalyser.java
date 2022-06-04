package com.game.multy_player_javafx.mvc.view.scene_items;

public class ScriptAnalyser {
    String script;
    String name;
    double x;
    double y;

    public ScriptAnalyser(String script){
        String[] array = script.split("_");
        Environment elem = Environment.valueOf(array[0]);
        elem.setObj(array[1]);

        x = elem.getX();
        y = elem.getY();
        name = elem.name();
    }


    public double getX(){
        return x;
    }

    public double getY(){
        return y;
    }

    public String getName(){
        return name;
    }
}
