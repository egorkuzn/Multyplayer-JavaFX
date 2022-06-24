package com.game.multy_player_javafx.mvc.view.scene_items;

import impl.org.controlsfx.skin.GridCellSkin;
import javafx.animation.Animation;
import javafx.animation.FillTransition;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;
import org.controlsfx.control.GridView;

import java.util.concurrent.atomic.AtomicBoolean;

public class NameChoose implements Runnable{
    //for 1960 * 1080 display
    ImageMap imageMap;
    public static final int width = 1960;
    public static final int height = 1080;
    String inputname;
    AtomicBoolean chooseMade = new AtomicBoolean(false);

    NameChoose(ImageMap imageMap){
        this.imageMap = imageMap;
    }

    @Override
    public void run(){


        TextField comment = new TextField();
        comment.setPrefWidth(width / 2);
        comment.setTranslateX(width / 4);
        comment.setTranslateY(height / 2);
        comment.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        comment.setPromptText("Enter your comment");
        comment.setEditable(false);
        comment.setAlignment(Pos.CENTER);

        Smooth smooth = new Smooth();
        Button button = new Button();

        button.setPrefWidth(80);
        button.setFont(Font.font("Consolas", FontWeight.NORMAL, 22));
        button.setText(">");
        button.setTranslateX(width * 3 / 4 - 80);
        button.setTranslateY(height / 2 + 1);

        imageMap.getChildren().addAll(smooth, comment, button);

        comment.setOnMouseEntered(mouseEvent -> {
            comment.setEditable(true);
            smooth.changeOpacity(0.9);
        });

        button.setOnMouseEntered(mouseEvent -> {
            comment.setEditable(true);
            smooth.changeOpacity(0.9);
        });

        comment.setOnMouseExited(mouseEvent -> {
            comment.setEditable(false);
            smooth.changeOpacity(0.7);
        });

        button.setOnMouseExited(mouseEvent -> {
            comment.setEditable(false);
            smooth.changeOpacity(0.7);
        });

        button.setOnAction(keyEvent -> {
            if (!comment.getText().isEmpty()) {
                inputname = comment.getText();
                imageMap.getChildren().clear();
                chooseMade.set(true);
            } else {
                comment.setPromptText("You didn't input name (((");
            }
        });
    }

    private class Smooth extends StackPane {
        Rectangle ft = new Rectangle(width, height, Color.BLACK);
        public Smooth(){
            ft.setOpacity(0.7);
            setAlignment(Pos.CENTER_LEFT);
            getChildren().addAll(ft);
        }

        public void changeOpacity(double percent){
            ft.setOpacity(percent);
        }
    }

    public String name(){
        return inputname;
    }

    boolean getChooseStatus(){
        return chooseMade.get();
    }
}
