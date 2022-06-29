package com.game.multy_player_javafx.mvc.view.scene_items.sprites_block.items;

public class Boy implements Item{
    String[] strArray;

    public Boy(String info) {
        strArray = info.split(":");
    }

    @Override
    public int getX() {
        return Integer.parseInt(strArray[1]) % 3;
    }

    @Override
    public int getY() {
        String direction = strArray[0];

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

    @Override
    public int getHeight() {
        return Integer.parseInt(strArray[2]);
    }

    @Override
    public double getWidth() {
        return Integer.parseInt(strArray[3]);
    }
}
