package com.game.multy_player_javafx.mvc.view.scene_items;

import com.game.multy_player_javafx.mvc.model.passive.Point;
import javafx.application.Platform;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

public class ImageMap extends Pane{
    public ImageMap(){
        if(typeInfo.isEmpty())
            getTypeInfo();
    }
    ArrayList<ImageView> imageList = new ArrayList<>();
    static HashMap<String, Type> typeInfo = new HashMap<String, Type>();
    public static HashMap<Environment, String> chooseHeroPath = new HashMap<>();
    static void getTypeInfo(){
        BufferedReader reader = null;

        try{
            reader = new BufferedReader(new FileReader("src/main/resources/sprites/typeList.txt"));
            String line;

            while ((line = reader.readLine()) != null){
                String[] array = line.split(";");

                if(array.length < 8)
                    throw new IOException();

                Type type       = new Type();
                type.gridWidth  = Double.parseDouble(array[1]);
                type.gridHeight = Double.parseDouble(array[2]);
                type.width      = Double.parseDouble(array[3]);
                type.height     = Double.parseDouble(array[4]);
                type.offsetX    = Double.parseDouble(array[5]);
                type.offsetY    = Double.parseDouble(array[6]);
                type.path       = array[7];

                if(array.length == 9) {
                    if(array[0].equals("GIRL"))
                        chooseHeroPath.put(Environment.GIRL, array[8]);
                    else if(array[0].equals("BOY"))
                        chooseHeroPath.put(Environment.BOY, array[8]);
                }

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
    public void add(String metadata, Point coordinates){
        ScriptAnalyser struct = new ScriptAnalyser(metadata);
        ImageView item = new ImageView(typeInfo.get(struct.getName()).path);
        item.setViewport(new Rectangle2D(X(struct), Y(struct), width(struct), height(struct)));
        item.setScaleX(3);
        item.setScaleY(3);
        item.setX(coordinates.X);
        item.setY(coordinates.Y);

        imageList.add(item);
    }

    double X(ScriptAnalyser struct){
        return struct.getX() * typeInfo.get(struct.getName()).gridWidth + typeInfo.get(struct.getName()).offsetX;
    }

    double Y(ScriptAnalyser struct){
        return struct.getY() * typeInfo.get(struct.getName()).gridHeight + typeInfo.get(struct.getName()).offsetY;
    }

    double width(ScriptAnalyser struct){
        return typeInfo.get(struct.getName()).width;
    }

    double height(ScriptAnalyser struct){
        return typeInfo.get(struct.getName()).height;
    }
    public void setAll(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                getChildren().clear();
                imageList.sort((o1, o2) -> (int)(o1.getY() - o2.getY()));
                getChildren().setAll(imageList);
                imageList.clear();
            }
        });
    }
}
