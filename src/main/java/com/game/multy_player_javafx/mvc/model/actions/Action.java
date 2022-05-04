package com.game.multy_player_javafx.mvc.model.actions;

import com.game.multy_player_javafx.mvc.model.ModelStatus;

public interface Action {
    void make(String param, Integer coordinate, ModelStatus status);
}
