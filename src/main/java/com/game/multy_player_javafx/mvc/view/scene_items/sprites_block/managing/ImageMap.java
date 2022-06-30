package com.game.multy_player_javafx.mvc.view.scene_items.sprites_block.managing;

import com.game.multy_player_javafx.mvc.model.passive.area.geometry.Point;
import javafx.application.Platform;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class ImageMap extends Pane{
    public ImageMap(){
        if(typeInfo.isEmpty())
            getTypeInfo();
    }
    CopyOnWriteArrayList<Node> imageList = new CopyOnWriteArrayList<>();
    CopyOnWriteArrayList<Node> textList = new CopyOnWriteArrayList<>();
    CopyOnWriteArrayList<Node> geometryList = new CopyOnWriteArrayList<>();
    static HashMap<String, Type> typeInfo = new HashMap<>();
    Point realCoord;
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

    public void add(String metadata, Point coordinate){
        ScriptAnalyser struct = new ScriptAnalyser(metadata);
        Point newPoint = setCoordinateForView(struct, coordinate);
        this.realCoord = coordinate;

        switch (struct.getName()) {
            case "TEXT" -> textAdder(struct, newPoint);
            default -> imageAdder(struct, newPoint);
        }
    }

    Point setCoordinateForView(ScriptAnalyser struct, Point coordinate){
        Point newPoint = new Point();
        newPoint.X = (int) (coordinate.X + struct.getLocation().getBiasX());
        newPoint.Y = (int) (coordinate.Y + struct.getLocation().getBiasY());

        if(!struct.getName().equals("TEXT")) {
            newPoint.X -= width(struct) / 2;
            newPoint.Y -= (heightScaled(struct) + height(struct)) / 2;
        } else {
            // добавить табличке с именем параметр высоты
        }

        return newPoint;
    }

    void textAdder(ScriptAnalyser struct, Point coordinate){
        int dy = (int) (150 * (coordinate.Y - 150) / struct.getLocation().height());
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
        item.setScaleX(struct.getScaleX(width(struct)));
        item.setScaleY(struct.getScaleY(height(struct)));
        item.setX(coordinate.X);
        item.setY(coordinate.Y);

        geometryAdder(struct, coordinate);

        imageList.add(item);
    }

    void geometryAdder(ScriptAnalyser struct, Point coordinate){
        Rectangle rectangle = new Rectangle(width(struct), height(struct));
        rectangle.setX(coordinate.X);
        rectangle.setY(coordinate.Y);
        rectangle.setScaleX(struct.getScaleX(width(struct)));
        rectangle.setScaleY(struct.getScaleY(height(struct)));

        rectangle.setFill(Color.GREEN);
        rectangle.setOpacity(0.8);
        rectangle.setStroke(Color.MAROON);
        geometryList.add(rectangle);

        rectangle = new Rectangle(2, 2);
        rectangle.setX(realCoord.X);
        rectangle.setY(realCoord.Y);
        rectangle.setStroke(Color.MAROON);

        geometryList.add(rectangle);
    }

    int widthScaled(ScriptAnalyser struct){
        return (int) (width(struct) * struct.getScaleX(width(struct)));
    }

    int heightScaled(ScriptAnalyser struct){
        return (int) (height(struct) * struct.getScaleY(height(struct)));
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
                imageList.sort((o1, o2) -> (int) (((ImageView) o1).getY() - ((ImageView) o2).getY()));
                textList.sort((o1, o2) -> (int) (((Text) o1).getY() - ((Text) o2).getY()));
                textList.addAll(geometryList);
                imageList.addAll(textList);
                getChildren().setAll(imageList);
                imageList.clear();
                textList.clear();
                geometryList.clear();
            }
        });
    }
}
