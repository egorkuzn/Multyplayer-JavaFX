package com.game.multy_player_javafx.mvc.view.scene_items.items;

public class Girl implements Item{
    String info;
    public Girl(String info) {
        this.info = info;
    }

    @Override
    public int getX() {
        return Integer.parseInt(info.substring(info.indexOf(':') + 1));
    }

    @Override
    public int getY() {
        String direction = info.substring(0, info.indexOf(':'));

        switch(direction){
            case "down":
                return 0;
            case "left":
                return 1;
            case "right":
                return 2;
            case "up":
                return 3;
            default:
                return 0;
        }
    }
}
