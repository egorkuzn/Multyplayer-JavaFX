package com.game.multy_player_javafx.mvc.view.scene_items.start;

import com.game.multy_player_javafx.mvc.view.network_controllers.ClientController;
import com.game.multy_player_javafx.mvc.view.scene_items.sprites_block.managing.Environment;
import com.game.multy_player_javafx.mvc.view.scene_items.sprites_block.managing.ImageMap;
import com.game.multy_player_javafx.mvc.view.system.LocalDisplay;
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

public class HeroChoose implements Runnable{
    ImageMap imageMap;
    AtomicBoolean chooseMade = new AtomicBoolean(false);

    String inputName = "Valentine";

    public HeroChoose(ImageMap imageMap){
        this.imageMap = imageMap;
    }

    @Override
    public void run() {
        MenuItem he = new MenuItem(ImageMap.chooseHeroPath.get(Environment.BOY));
        MenuItem she = new MenuItem(ImageMap.chooseHeroPath.get(Environment.GIRL));


        he.setOnMouseClicked(mouseEvent -> {
            sendReaction("BOY");
        });

        she.setTranslateX((int)(LocalDisplay.width() / 2));

        she.setOnMouseClicked(mouseEvent -> {
            sendReaction("GIRL");
        });

        imageMap.getChildren().setAll(he, she);

        imageMap.setOnMouseClicked(mouseEvent -> {
            imageMap.getChildren().clear();
        });
    }

    void sendReaction(String sex){
        ClientController.sendCommandToServer(stringFormOfReaction(inputName, sex));
        ClientController.sendCommandToServer("INIT");
        chooseMade.set(true);
    }

    String stringFormOfReaction(String inputName, String sex){
        return "THREAD_" + inputName + " " + sex + " " + "STREET";
    }

    private class MenuItem extends StackPane {
        public  MenuItem(String path){
            ImageView bg = new ImageView(new Image(path, (int)(LocalDisplay.width() / 2), LocalDisplay.height(), false, true));
            bg.setViewport(new Rectangle2D(0, 0, (int)(LocalDisplay.width() / 2), LocalDisplay.height()));

            Rectangle ft = new Rectangle((int)(LocalDisplay.width() / 2), LocalDisplay.height(), Color.BLACK);
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

    public boolean getChooseStatus(){
        return chooseMade.get();
    }

    public void setName(String inputName){
        this.inputName = inputName;
    }
}
