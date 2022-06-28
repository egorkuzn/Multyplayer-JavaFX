package com.game.multy_player_javafx.mvc.view.system;

import java.awt.*;

public class LocalDisplay {
    static int X = 0;
    static int Y = 0;

    static void setSizes(){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        X = screenSize.height;
        Y = screenSize.width;
    }

    public static double height(){
        if(X == 0)
            setSizes();

        return X;
    }

    public static double width(){
        if(Y == 0)
            setSizes();

        return Y;
    }
}
