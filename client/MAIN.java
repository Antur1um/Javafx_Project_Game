package client;

import core.field.Field;
import core.field.tiles.CapturedTile;
//import core.field.tiles.*;
import core.unitlist.units.*;
import core.unitlist.UnitList;
import client.Dispatcher;
import core.player.PlayerController;


public class MAIN {
    public static void main(String[] arg) {

        Field f = new Field(12, 30);
        f.addTile(1, 4, new CapturedTile(false));
        f.addTile(1, 3, new CapturedTile(false));
       

        Unit u0 = new Wanderer(1, 2);
        Unit u1 = new Wanderer(1, 3);
        Unit u2 = new Spearman(2, 4);     
        UnitList ul_r = new UnitList();
        ul_r.addUnit(u0);
        ul_r.addUnit(u1);
        ul_r.addUnit(u2);
        Unit u3 = new Wanderer(5, 5);
        Unit u4 = new Spearman(5, 6);     
        UnitList ul_b = new UnitList();
        ul_b.addUnit(u3);
        ul_b.addUnit(u4);

        PlayerController p1 = new PlayerController(1, f);
        PlayerController p2 = new PlayerController(2, f);


        Dispatcher dispatcher = new Dispatcher(f, p1, p2);

        dispatcher.menuHome();
       


    }
}

