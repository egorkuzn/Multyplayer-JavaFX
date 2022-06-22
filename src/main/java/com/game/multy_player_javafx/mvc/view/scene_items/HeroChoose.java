package com.game.multy_player_javafx.mvc.view.scene_items;

import com.game.multy_player_javafx.mvc.view.network_controllers.ClientController;
import javafx.animation.Animation;
import javafx.animation.FillTransition;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

public class HeroChoose implements Runnable{
    ImageMap imageMap;
    //for 1960 * 1080 display
    public static final int width = 1960;
    public static final int height = 1080;
    AtomicBoolean chooseMade = new AtomicBoolean(false);

    String inputedName = "Valentine";

    HeroChoose(ImageMap imageMap){
        this.imageMap = imageMap;
    }

    @Override
    public void run() {
        MenuItem he = new MenuItem(ImageMap.chooseHeroPath.get(Environment.BOY));
        MenuItem she = new MenuItem(ImageMap.chooseHeroPath.get(Environment.GIRL));


        he.setOnMouseClicked(mouseEvent -> {
            String sex = "BOY";
            ClientController.sendCommandToServer("THREAD_" + inputedName + " " + sex);
            ClientController.sendCommandToServer("INIT");
            chooseMade.set(true);
        });

        she.setTranslateX((int)(width / 2));

        she.setOnMouseClicked(mouseEvent -> {
            String sex = "GIRL";
            ClientController.sendCommandToServer("THREAD_" + inputedName + " " + sex);
            ClientController.sendCommandToServer("INIT");
            chooseMade.set(true);
        });

        imageMap.getChildren().setAll(he, she);

        imageMap.setOnMouseClicked(mouseEvent -> {
            imageMap.getChildren().clear();
        });
    }
    private class MenuItem extends StackPane {
        public  MenuItem(String path){
            ImageView bg = new ImageView(new Image(path, (int)(width / 2), height, false, true));
            bg.setViewport(new Rectangle2D(0, 0, (int)(width / 2), height));


            Rectangle ft = new Rectangle((int)(width / 2), height, Color.BLACK);
            ft.setOpacity(0.7);
            setAlignment(Pos.CENTER_LEFT);
            getChildren().addAll(bg, ft);
            FillTransition st = new FillTransition(Duration.seconds(0.5), ft);

            setOnMouseEntered(event -> {
                ft.setOpacity(0);
                st.setFromValue(Color.DARKGRAY);
                st.setToValue(Color.DARKGOLDENROD);
                st.setCycleCount(Animation.INDEFINITE);
                st.setAutoReverse(true);
                st.play();
            });

            setOnMouseExited(event -> {
                st.stop();
                ft.setFill(Color.BLACK);
                ft.setOpacity(0.7);
            });
        }
    }

    boolean getChooseStatus(){
        return chooseMade.get();
    }
}
