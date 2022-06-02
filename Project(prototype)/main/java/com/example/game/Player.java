package com.example.game;

import com.example.game.Unit.Unit;

import java.util.ArrayList;

public class Player {

    private ArrayList unitlist = new ArrayList<Unit>();


    public Player(){


    }

    public void addUnit(Unit unit){
        unitlist.add(unit);
    }



}
