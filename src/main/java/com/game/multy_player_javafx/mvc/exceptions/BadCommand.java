package com.game.multy_player_javafx.mvc.exceptions;

public class BadCommand extends Exception{
    public BadCommand(int count){
        super("Bad count of command arguments length");
    }
}
