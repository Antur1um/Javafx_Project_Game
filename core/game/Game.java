package core.game;


import core.field.Field;
import core.field.tiles.CapturedTile;
//import core.unitlist.UnitList;
import core.player.PlayerController;
import client.Dispatcher;

public class Game {

    private boolean stop = false;
    private Field field;
    private PlayerController player_1;
    private PlayerController player_2;
    private Dispatcher dispatcher;


    public Game(){
        field = new Field(12, 30);
        field.addTile(0, 4, new CapturedTile(1));
        field.addTile(1, 4, new CapturedTile(1));
        field.addTile(2, 4, new CapturedTile(1));
        field.addTile(3, 4, new CapturedTile(1));
        field.addTile(2, 5, new CapturedTile(1));
        field.addTile(2, 3, new CapturedTile(1));
       
        field.addTile(4, 4, new CapturedTile(2));
        field.addTile(5, 4, new CapturedTile(2));
        field.addTile(6, 4, new CapturedTile(2));
        field.addTile(5, 5, new CapturedTile(2));
        field.addTile(5, 3, new CapturedTile(2));


        player_1 = new PlayerController(1, field);
        player_2 = new PlayerController(2, field);

        dispatcher = new Dispatcher(field, player_1, player_2);
    }


    public void run(){

        while(!stop){
            dispatcher.menuHome(player_1);
            dispatcher.menuHome(player_2);
        }
    }
    
}
