package com.game.multy_player_javafx.mvc.view;

import com.game.multy_player_javafx.mvc.model.passive.Point;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.*;
import java.util.Map;
import java.util.Properties;

public class GraphicalTemplate extends Pane {
    ImageView imageView;
    String instruction;
    String name;
    Point position;
    int offsetX, offsetY, width, height;
    public GraphicalTemplate(Map.Entry<String, Point> data){
        instruction = data.getKey();
        position = data.getValue();
        setImgView();
        setPosition();
    }

    void setImgView(){
        this.imageView = new ImageView(loadImgFromSrc());
        setSizes();
        this.imageView.setViewport(new Rectangle2D(offsetX, offsetY, width, height));
    }
    void setPosition(){
        this.setTranslateX(position.X);
        this.setTranslateY(position.Y);
    }

    Image loadImgFromSrc(){
        Image image = new Image(getClass().getResourceAsStream(getFileName()));
        return image;
    }

    //There enough place for modifying!!!!!!!!!
    String getFileName(){
        String[] parameters = instruction.split("_");
        name = parameters[0];
        return parameters[0].toLowerCase();
    }

    void setSizes(){
        Properties size_info =  new Properties();
        InputStream in = getClass().getResourceAsStream("/images/image_counts.properties");
        try {
            size_info.load(in);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        width = Integer.parseInt(size_info.getProperty(name + "_" + "WIDTH"));
        height = Integer.parseInt(size_info.getProperty(name + "_" + "HEIGHT"));
    }
}
