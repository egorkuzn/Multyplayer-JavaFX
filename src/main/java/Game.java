import com.game.multyplayerjavafx.mvc.controller.Command;
import com.game.multyplayerjavafx.mvc.model.City;
import com.game.multyplayerjavafx.mvc.view.Layout;

public class Game {
    Command command;
    City model;
    Layout view;

    public Game(){

    }

    public void clientRun(){
        command
    }

    public void serverRun(){
        while (RUN){
            model.getToDo(Server.getToDo());
            Server.setView(model.getCityMask());
        }
    }
}
