package com.game.multy_player_javafx.mvc.view.scene_items;

import com.game.multy_player_javafx.mvc.model.passive.Point;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class ImageMap extends Pane{
    public ImageMap(){
        if(typeInfo.isEmpty())
            getTypeInfo();
    }
    ArrayList<ImageView> imageList = new ArrayList<>();
    static HashMap<String, Type> typeInfo = new HashMap<String, Type>();
    static void getTypeInfo(){
        BufferedReader reader = null;

        try{
            reader = new BufferedReader(new FileReader("/sprites/typeList.txt"));
            String line;

            while ((line = reader.readLine()) != null){
                String[] array = line.split(";");

                if(array.length != 3)
                    throw new IOException();

                Type type   = new Type();
                type.width  = Double.parseDouble(array[1]);
                type.height = Double.parseDouble(array[2]);
                type.path   = array[3];

                typeInfo.put(array[0], type);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void add(String type, Point coordinates){
        ImageView item = new ImageView(typeInfo.get(type).path);
        item.setViewport(new Rectangle2D(0, // перспектива
                                        0,     // перспектива
                                            typeInfo.get(type).width,
                                            typeInfo.get(type).height));

        item.setX(coordinates.X);
        item.setY(coordinates.Y);

        imageList.add(item);
    }

    public void setAll(){
        getChildren().setAll(imageList);
    }
}
