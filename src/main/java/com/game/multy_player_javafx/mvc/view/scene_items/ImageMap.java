package com.game.multy_player_javafx.mvc.view.scene_items;

import com.game.multy_player_javafx.mvc.model.passive.Point;
import javafx.application.Platform;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.effect.Effect;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextBoundsType;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class ImageMap extends Pane{
    public ImageMap(){
        if(typeInfo.isEmpty())
            getTypeInfo();
    }
    ArrayList<Node> imageList = new ArrayList<>();
    ArrayList<Node> textList = new ArrayList<>();
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

    int sysBiasOfX(){
        return (displayWidth() - MyImages.STREET.Width()) / 2;
    }

    int sysBiasOfY(){
        return (displayHeight() - MyImages.STREET.Height()) / 2;
    }

    public void add(String metadata, Point coordinate){
        ScriptAnalyser struct = new ScriptAnalyser(metadata);

        coordinate = Point(coordinate.X + sysBiasOfX(), coordinate.Y + sysBiasOfY());

        switch (struct.getName()) {
            case "TEXT" -> textAdder(struct, coordinate);
            default -> imageAdder(struct, coordinate);
        }
    }

    void textAdder(ScriptAnalyser struct, Point coordinate){
        int dy = 80;
        int shadowX = 2;
        int shadowY = 2;

        textList.add(getText(struct, new Point(coordinate.X - shadowX, coordinate.Y + shadowY - dy), Color.YELLOW));
        textList.add(getText(struct, new Point(coordinate.X, coordinate.Y - dy), Color.ORANGE));
    }

    Text getText(ScriptAnalyser struct, Point coordinate, Color color){
        Text text = new Text(struct.getText());
        text.setFont(Font.font("MV Boli", FontWeight.SEMI_BOLD, 20));
        text.setX(coordinate.X);
        text.setY(coordinate.Y);
        text.setFill(color);

        return text;
    }

    void imageAdder(ScriptAnalyser struct, Point coordinate){
        ImageView item = new ImageView(typeInfo.get(struct.getName()).path);
        item.setViewport(new Rectangle2D(X(struct), Y(struct), width(struct), height(struct)));
        item.setScaleX(3);
        item.setScaleY(3);
        item.setX(coordinate.X);
        item.setY(coordinate.Y);

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
                imageList.sort((o1, o2) -> (int)(((ImageView)o1).getY() - ((ImageView)o2).getY()));
                textList.sort((o1, o2) -> (int)(((Text)o1).getY() - ((Text)o2).getY()));
                imageList.addAll(textList);
                getChildren().setAll(imageList);
                imageList.clear();
                textList.clear();
            }
        });
    }
}
